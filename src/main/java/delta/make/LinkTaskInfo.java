package delta.make;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description of a linker task.
 * @author DAM
 */
public class LinkTaskInfo
{
  private List<File> _objectFiles;
  private File _targetFile;

  /**
   * Constructor.
   */
  public LinkTaskInfo()
  {
    _objectFiles=new ArrayList<File>();
    _targetFile=null;
  }

  /**
   * Add an object file.
   * @param objectFile Object file to add.
   */
  public void addObjectFile(File objectFile)
  {
    _objectFiles.add(objectFile);
  }

  /**
   * Get the list of object files.
   * @return A list of object files.
   */
  public List<File> getObjectFiles()
  {
    return _objectFiles;
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
