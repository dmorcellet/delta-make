package delta.make.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author DAM
 */
public class SoftwareComponent
{
  private static final Logger LOGGER=Logger.getLogger(SoftwareComponent.class);

  private CppProject _project;
  private String _name;
  // Ordered map for schemas
  private LinkedHashMap<String,Schema> _schemas;

  SoftwareComponent(CppProject project, String name)
  {
    _project=project;
    _name=name;
    _schemas=new LinkedHashMap<String,Schema>();
  }

  public CppProject getProject()
  {
    return _project;
  }

  public String getName()
  {
    return _name;
  }

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

  public Schema getSchema(String schemaName)
  {
    Schema ret=_schemas.get(schemaName);
    return ret;
  }

  public List<String> getSchemaNames()
  {
    List<String> ret=new ArrayList<String>(_schemas.size());
    ret.addAll(_schemas.keySet());
    return ret;
  }
}
