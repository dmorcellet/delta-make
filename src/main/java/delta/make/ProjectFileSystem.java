package delta.make;

import java.io.File;

/**
 * Project file system rules.
 * @author DAM
 */
public class ProjectFileSystem
{
  private File _rootDir;
  private File _tmpDir;
  private File _srcDir;
  private File _cppSrcDir;

  /**
   * Constructor.
   */
  public ProjectFileSystem()
  {
    String home=System.getenv("DELTA_HOME");

    _rootDir=new File(home);
    _tmpDir=new File(_rootDir,"tmp");
    _srcDir=new File(_rootDir,"src");
    _cppSrcDir=new File(_srcDir,"cpp/standard");
  }

  /**
   * Get the root directory of the project.
   * @return A directory.
   */
  public File getRootDir()
  {
    return _rootDir;
  }

  /**
   * Get the source directory of the project.
   * @return A directory.
   */
  public File getSourceDir()
  {
    return _srcDir;
  }

  /**
   * Get the source directory for a C++ library.
   * @param libName Library name.
   * @return A directory.
   */
  public File getCppLibrarySrcDir(String libName)
  {
    String libDirName="lib"+libName;
    File ret=new File(_cppSrcDir,libDirName);
    return ret;
  }

  /**
   * Get the directory for a C++ schema.
   * @param libName Library name.
   * @param schema Schema name.
   * @return A directory.
   */
  public File getCppSchemaSrcDir(String libName, String schema)
  {
    String libDirName="lib"+libName;
    File libDir=new File(_cppSrcDir,libDirName);
    File ret=new File(libDir,schema);
    return ret;
  }

  /**
   * Get the directory for the includes of a library.
   * @param libName Library name.
   * @return A directory.
   */
  public File getIncludeDir(String libName)
  {
    File includeDir=new File(_tmpDir,"include");
    String libDirName="lib"+libName;
    File ret=new File(includeDir,libDirName);
    return ret;
  }

  /**
   * Get an object file.
   * @param softComponentName Name of the software component (library or executable).
   * @param schemaName Schema name.
   * @param className Class name.
   * @return A file.
   */
  public File getObjectFile(String softComponentName, String schemaName, String className)
  {
    File objDir=new File(_tmpDir,"obj");
    String objName=softComponentName+"_"+schemaName+"_"+className+".o";
    File ret=new File(objDir,objName);
    return ret;
  }

  /**
   * Get the object file for a schema.
   * @param softComponentName Name of the software component (library or executable).
   * @param schemaName Schema name.
   * @return A file.
   */
  public File getSchemaObjectFile(String softComponentName, String schemaName)
  {
    File objDir=new File(_tmpDir,"obj");
    String objName=softComponentName+"_"+schemaName+".o";
    File ret=new File(objDir,objName);
    return ret;
  }

  /**
   * Get the file for a shared library.
   * @param libraryName Library name.
   * @return A file.
   */
  public File getSharedLibFile(String libraryName)
  {
    File libDir=new File(_tmpDir,"lib");
    String libFileName="lib"+libraryName+".so";
    File ret=new File(libDir,libFileName);
    return ret;
  }

  /**
   * Get a source file.
   * @param softComponentName Name of the software component (library or executable).
   * @param schemaName Schema name.
   * @param className Class name.
   * @return A file.
   */
  public File getSchemaSourceFile(String softComponentName, String schemaName, String className)
  {
    File dir=getCppSchemaSrcDir(softComponentName,schemaName);
    File body=new File(dir,"body");
    File ret=new File(body,className+".cxx");
    return ret;
  }

  /**
   * Get the directory for the header files of a schema.
   * @param softComponentName Name of the software component (library or executable).
   * @param schemaName Schema name.
   * @return A directory.
   */
  public File getSchemaHeadersDir(String softComponentName, String schemaName)
  {
    File dir=getCppSchemaSrcDir(softComponentName,schemaName);
    File ret=new File(dir,"use");
    return ret;
  }
}
