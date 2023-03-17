package delta.make;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import delta.make.project.Schema;
import delta.make.project.SharedLibrary;

/**
 * Task to compile a shared library.
 * @author DAM
 */
public class CompileSharedLibraryTask
{
  private MakeEnvironment _env;
  private SharedLibrary _sharedLibrary;

  /**
   * Constructor.
   * @param env Environment.
   * @param sharedLibrary Shared library to build.
   */
  public CompileSharedLibraryTask(MakeEnvironment env, SharedLibrary sharedLibrary)
  {
    _env=env;
    _sharedLibrary=sharedLibrary;
  }

  /**
   * Do compilation.
   */
  public void doIt()
  {
    List<String> schemaNames=_sharedLibrary.getSchemaNames();
    if ((schemaNames!=null) && (schemaNames.size()>0))
    {
      LinkTaskInfo linkInfo=new LinkTaskInfo();
      // Compile each schema
      String schemaName;
      CompileSchemaTask subTask;
      Schema schema;
      File targetObjFile;
      for(Iterator<String> it=schemaNames.iterator();it.hasNext();)
      {
        schemaName=it.next();
        schema=_sharedLibrary.getSchema(schemaName);
        subTask=new CompileSchemaTask(_env,schema);
        subTask.doIt();
        targetObjFile=subTask.getTargetFile();
        linkInfo.addObjectFile(targetObjFile);
      }
      // Link generated objects
      ProjectFileSystem fs=_env.getFileSystem();
      File targetFile=fs.getSharedLibFile(_sharedLibrary.getName());
      linkInfo.setTargetFile(targetFile);
      Linker linker=new Linker();
      linker.linkSharedLibrary(linkInfo);
    }
  }
}
