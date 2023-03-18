package delta.make;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an external software component.
 * @author DAM
 */
public class ExternalSoftware
{
  private String _name;
  private List<File> _includePaths;
  private List<File> _libPaths;
  private List<String> _libNames;

  /**
   * Constructor.
   * @param name Name of this external software.
   */
  ExternalSoftware(String name)
  {
    _name=name;
    _includePaths=new ArrayList<File>();
    _libPaths=new ArrayList<File>();
    _libNames=new ArrayList<String>();
  }

  /**
   * Get the name of this external software.
   * @return the name of this external software.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Add a new path to the list of include paths.
   * @param path Path to add.
   */
  public void addIncludePath(File path)
  {
    _includePaths.add(path);
  }

  /**
   * Get the include paths.
   * @return A list of include paths.
   */
  public List<File> getIncludePaths()
  {
    List<File> ret=new ArrayList<File>(_includePaths.size());
    ret.addAll(_includePaths);
    return ret;
  }

  /**
   * Add a new path to the list of library search paths.
   * @param path Path to add.
   */
  public void addLibPath(File path)
  {
    _libPaths.add(path);
  }

  /**
   * Add a new library name to the list of library names.
   * @param name Name of library to add.
   */
  public void addLib(String name)
  {
    _libNames.add(name);
  }
}
