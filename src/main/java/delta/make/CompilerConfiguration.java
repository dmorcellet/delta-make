package delta.make;

/**
 * @author dm
 */
public class CompilerConfiguration
{
  private boolean _warnings;
  private boolean _debug;
  private boolean _exceptions;
  private boolean _optimization;

  public CompilerConfiguration()
  {
    _warnings=false;
    _debug=false;
    _exceptions=false;
    _optimization=false;
  }

  public void setWarningsEnabled(boolean warnings)
  {
    _warnings=warnings;
  }

  public void setDebug(boolean debug)
  {
    _debug=debug;
  }

  public void setExceptionsEnabled(boolean exceptions)
  {
    _exceptions=exceptions;
  }

  public void setOptimizationsEnabled(boolean optimization)
  {
    _optimization=optimization;
  }

  /**
   * @return the warnings
   */
  public boolean areWarningsEnabled()
  {
    return _warnings;
  }

  /**
   * @return the debug
   */
  public boolean isDebugEnabled()
  {
    return _debug;
  }

  /**
   * @return the exceptions
   */
  public boolean areExceptionsEnabled()
  {
    return _exceptions;
  }

  /**
   * @return the optimizations
   */
  public boolean areOptimizationsEnabled()
  {
    return _optimization;
  }
}
