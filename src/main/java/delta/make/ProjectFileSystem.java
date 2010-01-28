package delta.make;

import java.io.File;

/**
 * @author DAM
 */
public class ProjectFileSystem
{
  private File _rootDir;
  private File _tmpDir;
  private File _srcDir;
  private File _cppSrcDir;

  public ProjectFileSystem()
  {
    String home=System.getenv("DELTA_HOME");

    _rootDir=new File(home);
    _tmpDir=new File(_rootDir,"tmp");
    _srcDir=new File(_rootDir,"src");
    _cppSrcDir=new File(_srcDir,"cpp/standard");
  }

  public File getRootDir()
  {
    return _rootDir;
  }

  public File getSourceDir()
  {
    return _srcDir;
  }

  public File getCppLibrarySrcDir(String libName)
  {
    String libDirName="lib"+libName;
    File ret=new File(_cppSrcDir,libDirName);
    return ret;
  }

  public File getCppSchemaSrcDir(String libName, String schema)
  {
    String libDirName="lib"+libName;
    File libDir=new File(_cppSrcDir,libDirName);
    File ret=new File(libDir,schema);
    return ret;
  }

  public File getIncludeDir(String libName)
  {
    File includeDir=new File(_tmpDir,"include");
    String libDirName="lib"+libName;
    File ret=new File(includeDir,libDirName);
    return ret;
  }

  public File getObjectFile(String softComponentName, String schemaName, String className)
  {
    File objDir=new File(_tmpDir,"obj");
    String objName=softComponentName+"_"+schemaName+"_"+className+".o";
    File ret=new File(objDir,objName);
    return ret;
  }

  public File getSchemaObjectFile(String softComponentName, String schemaName)
  {
    File objDir=new File(_tmpDir,"obj");
    String objName=softComponentName+"_"+schemaName+".o";
    File ret=new File(objDir,objName);
    return ret;
  }

  public File getSharedLibFile(String softComponentName)
  {
    File libDir=new File(_tmpDir,"lib");
    String libFileName="lib"+softComponentName+".so";
    File ret=new File(libDir,libFileName);
    return ret;
  }

  public File getSchemaSourceFile(String softComponentName, String schemaName, String className)
  {
    File dir=getCppSchemaSrcDir(softComponentName,schemaName);
    File body=new File(dir,"body");
    File ret=new File(body,className+".cxx");
    return ret;
  }

  public File getSchemaHeadersDir(String softComponentName, String schemaName)
  {
    File dir=getCppSchemaSrcDir(softComponentName,schemaName);
    File ret=new File(dir,"use");
    return ret;
  }
}
