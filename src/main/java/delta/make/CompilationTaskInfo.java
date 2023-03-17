package delta.make;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description of a compilation task.
 * @author DAM
 */
public class CompilationTaskInfo
{
  private List<String> _defines;
  private List<File> _includeDirs;
  private File _sourceFile;
  private File _targetFile;

  /**
   * Constructor.
   */
  public CompilationTaskInfo()
  {
    _defines=new ArrayList<String>();
    _includeDirs=new ArrayList<File>();
    _sourceFile=null;
    _targetFile=null;
  }

  /**
   * Add a define.
   * @param define Define to add.
   */
  public void addDefine(String define)
  {
    _defines.add(define);
  }

  /**
   * Get defines.
   * @return a list of defines.
   */
  public List<String> getDefines()
  {
    return _defines;
  }

  /**
   * Add an include directory.
   * @param dir Directory to add.
   */
  public void addIncludeDir(File dir)
  {
    _includeDirs.add(dir);
  }

  /**
   * Get the include directories.
   * @return A list of directories.
   */
  public List<File> getIncludeDirs()
  {
    return _includeDirs;
  }

  /**
   * Set the source file.
   * @param sourceFile Source file.
   */
  public void setSourceFile(File sourceFile)
  {
    _sourceFile=sourceFile;
  }

  /**
   * Get the source file.
   * @return the source file.
   */
  public File getSourceFile()
  {
    return _sourceFile;
  }

  /**
   * Set the target file.
   * @param targetFile Target file.
   */
  public void setTargetFile(File targetFile)
  {
    _targetFile=targetFile;
  }

  /**
   * Get the target file.
   * @return the target file.
   */
  public File getTargetFile()
  {
    return _targetFile;
  }
}
