package delta.make.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Description of a C++ project.
 * @author DAM
 */
public class CppProject
{
  private static final Logger LOGGER=Logger.getLogger(CppProject.class);

  private String _name;
  // Ordered map for shared libs
  private LinkedHashMap<String,SharedLibrary> _sharedLibraries;

  /**
   * Constructor.
   * @param name Project name.
   */
  public CppProject(String name)
  {
    _name=name;
    _sharedLibraries=new LinkedHashMap<String,SharedLibrary>();
  }

  /**
   * Get the project name.
   * @return the project name.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Add a shared library.
   * @param libraryName Library name.
   * @return the newly added library (or the previously existing one).
   */
  public SharedLibrary addSharedLibrary(String libraryName)
  {
    SharedLibrary ret=_sharedLibraries.get(libraryName);
    if (ret!=null)
    {
      LOGGER.warn("Cannot create child shared library ["+libraryName+"]. Reusing old one.");
    }
    else
    {
      ret=new SharedLibrary(this,libraryName);
      _sharedLibraries.put(libraryName,ret);
    }
    return ret;
  }

  /**
   * Get a shared library using its name.
   * @param libraryName Name of the library to get.
   * @return A shared library or <code>null</code> if not found.
   */
  public SharedLibrary getSharedLibrary(String libraryName)
  {
    SharedLibrary ret=_sharedLibraries.get(libraryName);
    return ret;
  }

  /**
   * Get the names of the managed libraries.
   * @return A list of library names (unsorted).
   */
  public List<String> getSharedLibrariesNames()
  {
    List<String> ret=new ArrayList<String>(_sharedLibraries.size());
    ret.addAll(_sharedLibraries.keySet());
    return ret;
  }
}
