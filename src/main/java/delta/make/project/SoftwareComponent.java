package delta.make.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for software components (libraries and executables).
 * @author DAM
 */
public class SoftwareComponent
{
  private static final Logger LOGGER=LoggerFactory.getLogger(SoftwareComponent.class);

  private CppProject _project;
  private String _name;
  // Ordered map for schemas
  private LinkedHashMap<String,Schema> _schemas;

  /**
   * Constructor.
   * @param project Parent project.
   * @param name Component name.
   */
  SoftwareComponent(CppProject project, String name)
  {
    _project=project;
    _name=name;
    _schemas=new LinkedHashMap<String,Schema>();
  }

  /**
   * Get the parent project.
   * @return A project.
   */
  public CppProject getProject()
  {
    return _project;
  }

  /**
   * Get the name of this component..
   * @return A component name.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Add a schema.
   * @param schemaName Schema name.
   * @return the newly built schema.
   */
  public Schema addSchema(String schemaName)
  {
    Schema ret=_schemas.get(schemaName);
    if (ret!=null)
    {
      LOGGER.warn("Cannot create child schema ["+schemaName+"]. Reusing old one.");
    }
    else
    {
      ret=new Schema(this,schemaName);
      _schemas.put(schemaName,ret);
    }
    return ret;
  }

  /**
   * Get a schema using its name.
   * @param schemaName Schema name.
   * @return A schema or <code>null</code> if not found.
   */
  public Schema getSchema(String schemaName)
  {
    Schema ret=_schemas.get(schemaName);
    return ret;
  }

  /**
   * Get the list of managed schemas.
   * @return A list of schema names.
   */
  public List<String> getSchemaNames()
  {
    List<String> ret=new ArrayList<String>(_schemas.size());
    ret.addAll(_schemas.keySet());
    return ret;
  }
}
