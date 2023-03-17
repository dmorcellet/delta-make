package delta.make;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import delta.common.utils.system.externalCommands.ExternalCommand;

/**
 * Compiler interface.
 * @author DAM
 */
public class Compiler
{
  private static final String GPP_COMPILER="g++";
  private static final String OPTIM_OPTION="-O2";
  private static final String DEBUG_OPTION="-g";
  private static final String WARNING_OPTION="-Wall";
  private static final String EXCEPTIONS_OPTION="-fexceptions";
  private static final String SHARED_OBJECTS_OPTIONS="-fPIC";

  private CompilerConfiguration _cfg;

  /**
   * Constructor.
   * @param cfg Configurtion.
   */
  public Compiler(CompilerConfiguration cfg)
  {
    _cfg=cfg;
  }

  /**
   * Build a shared object.
   * @param info Description of the compilation to do.
   */
  public void buildSharedObject(CompilationTaskInfo info)
  {
    List<String> cmdLine=buildCommandLine(info);
    File parentDir=info.getTargetFile().getParentFile();
    parentDir.mkdirs();
    System.out.println(cmdLine);
    ExternalCommand cmd=new ExternalCommand(cmdLine);
    Integer ret=cmd.executeSynchronously();
    if ((ret==null || (ret.intValue()!=0)))
    {
      String out=cmd.getStdout();
      System.out.println(out);
      String err=cmd.getStderr();
      System.out.println(err);
      System.out.println(ret);
    }
  }

  private List<String> buildCommandLine(CompilationTaskInfo info)
  {
    List<String> ret=new ArrayList<String>();
    // Compiler command
    ret.add(GPP_COMPILER);
    // Compiler options
    ret.add(SHARED_OBJECTS_OPTIONS);
    if (_cfg.isDebugEnabled())
    {
      ret.add(DEBUG_OPTION);
    }
    if (_cfg.areOptimizationsEnabled())
    {
      ret.add(OPTIM_OPTION);
    }
    if (_cfg.areWarningsEnabled())
    {
      ret.add(WARNING_OPTION);
    }
    if (_cfg.areExceptionsEnabled())
    {
      ret.add(EXCEPTIONS_OPTION);
    }
    // Add defines
    List<String> defines=info.getDefines();
    if ((defines!=null) && (defines.size()>0))
    {
      String define;
      for(Iterator<String> it=defines.iterator();it.hasNext();)
      {
        define=it.next();
        ret.add("-D"+define);
      }
    }
    // Add include dirs
    List<File> includeDirs=info.getIncludeDirs();
    if ((includeDirs!=null) && (includeDirs.size()>0))
    {
      File includeDir;
      for(Iterator<File> it=includeDirs.iterator();it.hasNext();)
      {
        includeDir=it.next();
        ret.add("-I"+includeDir);
      }
    }
    // Add compile option
    ret.add("-c");
    // Target file
    ret.add("-o");
    File targetFile=info.getTargetFile();
    ret.add(targetFile.getPath());
    // Source file
    File sourceFile=info.getSourceFile();
    ret.add(sourceFile.getPath());

    return ret;
  }
}
