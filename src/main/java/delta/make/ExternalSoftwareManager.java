package delta.make;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

/**
 * @author DAM
 */
public class ExternalSoftwareManager
{
  private static final Logger _logger=MakeLoggers.getRootLogger();

  // Ordered map for external softwares
  private LinkedHashMap<String,ExternalSoftware> externalSoftwares;

  public ExternalSoftwareManager()
  {
    externalSoftwares=new LinkedHashMap<String,ExternalSoftware>();
  }

  public ExternalSoftware addExternalSoftware(String softName)
  {
    ExternalSoftware ret=externalSoftwares.get(softName);
    if (ret!=null)
    {
      _logger.warn("Cannot create child external software ["+softName+"]. Reusing old one.");
    }
    else
    {
      ret=new ExternalSoftware(softName);
    }
    return ret;
  }

  public ExternalSoftware getSoftware(String softName)
  {
    ExternalSoftware ret=externalSoftwares.get(softName);
    return ret;
  }
}
