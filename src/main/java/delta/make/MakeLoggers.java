package delta.make;

import org.apache.log4j.Logger;

import delta.common.utils.traces.LoggersRegistry;

/**
 * Management class for all make loggers.
 * @author DAM
 */
public class MakeLoggers
{
  /**
   * Name of the "MAKE" root logger.
   */
  public static final String MAKE="MAKE";

  private static final Logger _makeLogger=LoggersRegistry.getLogger(MAKE);

  /**
   * Get the root logger used for make.
   * @return the root logger used for make.
   */
  public static Logger getRootLogger()
  {
    return _makeLogger;
  }
}
