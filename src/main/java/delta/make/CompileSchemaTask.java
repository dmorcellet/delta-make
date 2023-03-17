package delta.make;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import delta.make.project.Schema;

/**
 * Task to compile a schema.
 * @author DAM
 */
public class CompileSchemaTask
{
  private static final Logger LOGGER=Logger.getLogger(CompileSchemaTask.class);

  private MakeEnvironment _env;
  private Schema _schema;
  private File _targetFile;

  /**
   * Constructor.
   * @param env Environment.
   * @param schema Schema to build.
   */
  public CompileSchemaTask(MakeEnvironment env, Schema schema)
  {
    _env=env;
    _schema=schema;
    ProjectFileSystem fs=_env.getFileSystem();
    String libName=_schema.getParent().getName();
    String schemaName=_schema.getName();
    _targetFile=fs.getSchemaObjectFile(libName,schemaName);
    if (LOGGER.isDebugEnabled())
    {
      LOGGER.debug("Schema object file is ["+_targetFile+"]");
    }
  }

  /**
   * Get the target file.
   * @return the target file.
   */
  public File getTargetFile()
  {
    return _targetFile;
  }

  /**
   * Do compilation. 
   */
  public void doIt()
  {
    List<String> classes=_schema.getClasses();
    if ((classes!=null) && (classes.size()>0))
    {
      LinkTaskInfo linkInfo=new LinkTaskInfo();
      // Compile each source file in this schema
      String className;
      CompileCppFileTask subTask;
      File targetObjFile;
      for(Iterator<String> it=classes.iterator();it.hasNext();)
      {
        className=it.next();
        subTask=new CompileCppFileTask(_env,_schema,className);
        subTask.doIt();
        targetObjFile=subTask.getTargetFile();
        if (LOGGER.isDebugEnabled())
        {
          LOGGER.debug("Added object file ["+targetObjFile+"]");
        }
        linkInfo.addObjectFile(targetObjFile);
      }
      // Link generated objects
      linkInfo.setTargetFile(_targetFile);
      Linker linker=new Linker();
      linker.linkObjects(linkInfo);
    }
  }
}
