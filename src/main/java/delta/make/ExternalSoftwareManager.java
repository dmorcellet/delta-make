package delta.make;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * External software manager.
 * @author DAM
 */
public class ExternalSoftwareManager
{
  private static final Logger LOGGER=LoggerFactory.getLogger(ExternalSoftwareManager.class);

  // Ordered map for external softwares
  private LinkedHashMap<String,ExternalSoftware> externalSoftwares;

  /**
   * Constructor.
   */
  public ExternalSoftwareManager()
  {
    externalSoftwares=new LinkedHashMap<String,ExternalSoftware>();
  }

  /**
   * Add an external software.
   * @param softName Software name.
   * @return the newly built external software.
   */
  public ExternalSoftware addExternalSoftware(String softName)
  {
    ExternalSoftware ret=externalSoftwares.get(softName);
    if (ret!=null)
    {
      LOGGER.warn("Cannot create child external software ["+softName+"]. Reusing old one.");
    }
    else
    {
      ret=new ExternalSoftware(softName);
    }
    return ret;
  }

  /**
   * Get an external software using its name.
   * @param softName Software name.
   * @return An external software or <code>null</code> if not found.
   */
  public ExternalSoftware getSoftware(String softName)
  {
    ExternalSoftware ret=externalSoftwares.get(softName);
    return ret;
  }
}
