package delta.make.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;

import delta.make.MakeLoggers;

/**
 * @author DAM
 */
public class CppProject
{
  private static final Logger _logger=MakeLoggers.getRootLogger();

  private String _name;
  // Ordered map for shared libs
  private LinkedHashMap<String,SharedLibrary> _sharedLibraries;

  public CppProject(String name)
  {
    _name=name;
    _sharedLibraries=new LinkedHashMap<String,SharedLibrary>();
  }

  public String getName()
  {
    return _name;
  }

  public SharedLibrary addSharedLibrary(String libraryName)
  {
    SharedLibrary ret=_sharedLibraries.get(libraryName);
    if (ret!=null)
    {
      _logger.warn("Cannot create child shared library ["+libraryName+"]. Reusing old one.");
    }
    else
    {
      ret=new SharedLibrary(this,libraryName);
      _sharedLibraries.put(libraryName,ret);
    }
    return ret;
  }

  public SharedLibrary getSharedLibrary(String libraryName)
  {
    SharedLibrary ret=_sharedLibraries.get(libraryName);
    return ret;
  }

  public List<String> getSharedLibrariesNames()
  {
    List<String> ret=new ArrayList<String>(_sharedLibraries.size());
    ret.addAll(_sharedLibraries.keySet());
    return ret;
  }
}
