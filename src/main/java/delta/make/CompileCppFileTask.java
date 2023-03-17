package delta.make;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import delta.make.project.Schema;

/**
 * @author DAM
 */
public class CompileCppFileTask
{
  private static final Logger LOGGER=Logger.getLogger(CompileCppFileTask.class);

  private MakeEnvironment _env;
  private Schema _schema;
  private String _classFile;

  private File _targetFile;

  public CompileCppFileTask(MakeEnvironment env, Schema schema, String classFile)
  {
    _env=env;
    _schema=schema;
    _classFile=classFile;
    ProjectFileSystem fs=_env.getFileSystem();
    String libName=_schema.getParent().getName();
    String schemaName=_schema.getName();
    _targetFile=fs.getObjectFile(libName,schemaName,_classFile);
  }

  public void doIt()
  {
    buildCompilerCommandLine();
  }

  public File getTargetFile()
  {
    return _targetFile;
  }

  private void buildCompilerCommandLine()
  {
    ProjectFileSystem fs=_env.getFileSystem();
    CompilerConfiguration compilerCfg=new CompilerConfiguration();
    compilerCfg.setOptimizationsEnabled(true);
    compilerCfg.setDebug(true);
    compilerCfg.setWarningsEnabled(true);
    compilerCfg.setExceptionsEnabled(true);

    String libName=_schema.getParent().getName();
    String schemaName=_schema.getName();

    Compiler compiler=new Compiler(compilerCfg);

    CompilationTaskInfo taskInfo=new CompilationTaskInfo();
    // 0. Defines
    taskInfo.addDefine("__UNIX__");
    taskInfo.addDefine("__LINUX__");
    taskInfo.addDefine("__ALL_OS_VERSIONS__");
    taskInfo.addDefine("__LSB__");
    taskInfo.addDefine("TRACES");
    taskInfo.addDefine("DEBUG");

    // 1. Compute paths to include
    // 1.a0. This schema
    {
      File includeDir=fs.getSchemaHeadersDir(libName,schemaName);
      taskInfo.addIncludeDir(includeDir);
    }
    // 1.a. Brother schemas
    {
      List<String> brotherSchemas=_schema.getNeededSchemas();
      if ((brotherSchemas!=null) && (brotherSchemas.size()>0))
      {
        String brotherSchema;
        File includeDir;
        for(Iterator<String> it=brotherSchemas.iterator();it.hasNext();)
        {
          brotherSchema=it.next();
          includeDir=fs.getSchemaHeadersDir(libName,brotherSchema);
          taskInfo.addIncludeDir(includeDir);
        }
      }
    }
    // 1.b Needed libraries includes
    {
      List<String> neededLibraries=_schema.getNeededLibraries();
      if ((neededLibraries!=null) && (neededLibraries.size()>0))
      {
        String neededLibrary;
        File includeDir;
        for(Iterator<String> it=neededLibraries.iterator();it.hasNext();)
        {
          neededLibrary=it.next();
          includeDir=fs.getIncludeDir(neededLibrary);
          taskInfo.addIncludeDir(includeDir);
        }
      }
    }
    // 1.c External softwares includes
    {
      List<String> neededExternalSoftware=_schema.getNeededExternalSoftware();
      if ((neededExternalSoftware!=null) && (neededExternalSoftware.size()>0))
      {
        ExternalSoftwareManager softManager=_env.getSoftManager();
        ExternalSoftware soft;
        String softName;
        File includeDir;
        for(Iterator<String> it=neededExternalSoftware.iterator();it.hasNext();)
        {
          softName=it.next();
          soft=softManager.getSoftware(softName);
          if (soft!=null)
          {
            List<File> includePaths=soft.getIncludePaths();
            if ((includePaths!=null) && (includePaths.size()>0))
            {
              for(Iterator<File> itIncludes=includePaths.iterator();itIncludes.hasNext();)
              {
                includeDir=itIncludes.next();
                taskInfo.addIncludeDir(includeDir);
              }
            }
          }
          else
          {
            LOGGER.error("Cannot find definition for software ["+softName+"]");
          }
        }
      }
    }

    // Source file
    File sourceFile=fs.getSchemaSourceFile(libName,schemaName,_classFile);
    taskInfo.setSourceFile(sourceFile);
    // Target file
    taskInfo.setTargetFile(_targetFile);
    compiler.buildSharedObject(taskInfo);
  }
}
