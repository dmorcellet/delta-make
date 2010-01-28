package delta.make;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DAM
 */
public class CompilationTaskInfo
{
  private List<String> _defines;
  private List<File> _includeDirs;
  private File _sourceFile;
  private File _targetFile;

  public CompilationTaskInfo()
  {
    _defines=new ArrayList<String>();
    _includeDirs=new ArrayList<File>();
    _sourceFile=null;
    _targetFile=null;
  }

  public void addDefine(String define)
  {
    _defines.add(define);
  }

  public List<String> getDefines()
  {
    return _defines;
  }

  public void addIncludeDir(File dir)
  {
    _includeDirs.add(dir);
  }

  public List<File> getIncludeDirs()
  {
    return _includeDirs;
  }

  public void setSourceFile(File sourceFile)
  {
    _sourceFile=sourceFile;
  }

  public File getSourceFile()
  {
    return _sourceFile;
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
