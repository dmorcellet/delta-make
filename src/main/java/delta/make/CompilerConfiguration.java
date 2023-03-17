package delta.make;

/**
 * Configuration of the compiler.
 * @author DAM
 */
public class CompilerConfiguration
{
  private boolean _warnings;
  private boolean _debug;
  private boolean _exceptions;
  private boolean _optimization;

  /**
   * Constructor.
   */
  public CompilerConfiguration()
  {
    _warnings=false;
    _debug=false;
    _exceptions=false;
    _optimization=false;
  }

  /**
   * Enable/disable warnings.
   * @param warnings <code>true</code> to enable warnings, <code>false</code> to disable them.
   */
  public void setWarningsEnabled(boolean warnings)
  {
    _warnings=warnings;
  }

  /**
   * Enable/disable debug.
   * @param debug <code>true</code> to enable debug, <code>false</code> to disable it.
   */
  public void setDebug(boolean debug)
  {
    _debug=debug;
  }

  /**
   * Enable/disable exceptions.
   * @param exceptions <code>true</code> to enable exceptions, <code>false</code> to disable them.
   */
  public void setExceptionsEnabled(boolean exceptions)
  {
    _exceptions=exceptions;
  }

  /**
   * Enable/disable optimization.
   * @param optimization <code>true</code> to enable optimization, <code>false</code> to disable it.
   */
  public void setOptimizationsEnabled(boolean optimization)
  {
    _optimization=optimization;
  }

  /**
   * Indicates if warnings are enabled.
   * @return <code>true</code> if they are, <code>false</code> otherwise.
   */
  public boolean areWarningsEnabled()
  {
    return _warnings;
  }

  /**
   * Indicates if debug is enabled.
   * @return <code>true</code> if it is, <code>false</code> otherwise.
   */
  public boolean isDebugEnabled()
  {
    return _debug;
  }

  /**
   * Indicates if exceptions are enabled.
   * @return <code>true</code> if they are, <code>false</code> otherwise.
   */
  public boolean areExceptionsEnabled()
  {
    return _exceptions;
  }

  /**
   * Indicates if optimizations are enabled.
   * @return <code>true</code> if they are, <code>false</code> otherwise.
   */
  public boolean areOptimizationsEnabled()
  {
    return _optimization;
  }
}
