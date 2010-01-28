package delta.make;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DAM
 */
public class LinkTaskInfo
{
  private List<File> _objectFiles;
  private File _targetFile;

  public LinkTaskInfo()
  {
    _objectFiles=new ArrayList<File>();
    _targetFile=null;
  }

  public void addObjectFile(File objectFile)
  {
    _objectFiles.add(objectFile);
  }

  public List<File> getObjectFiles()
  {
    return _objectFiles;
  }

  public void setTargetFile(File targetFile)
  {
    _targetFile=targetFile;
  }

  public File getTargetFile()
  {
    return _targetFile;
  }
}
