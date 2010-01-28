package delta.make;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import delta.common.utils.system.externalCommands.ExternalCommand;

/**
 * @author DAM
 */
public class Linker
{
  private static final String GNU_LINKER="ld";
  private static final String RELOCATABLE_OBJECTS_OPTION="-r";
  private static final String SHARED_LIB_OPTION="-shared";

  public void linkSharedLibrary(LinkTaskInfo info)
  {
    List<String> cmdLine=buildLinkSharedLibraryCommandLine(info);
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

  private List<String> buildLinkSharedLibraryCommandLine(LinkTaskInfo info)
  {
    List<String> ret=new ArrayList<String>();
    // Compiler command
    ret.add(GNU_LINKER);
    // Linker options
    ret.add(SHARED_LIB_OPTION);
    // Target file
    ret.add("-o");
    File targetFile=info.getTargetFile();
    ret.add(targetFile.getPath());
    // Add object files
    List<File> objectFiles=info.getObjectFiles();
    if ((objectFiles!=null) && (objectFiles.size()>0))
    {
      File objectFile;
      for(Iterator<File> it=objectFiles.iterator();it.hasNext();)
      {
        objectFile=it.next();
        ret.add(objectFile.getPath());
      }
    }
    return ret;
  }

  public void linkObjects(LinkTaskInfo info)
  {
    List<String> cmdLine=buildLinkObjectsCommandLine(info);
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

  private List<String> buildLinkObjectsCommandLine(LinkTaskInfo info)
  {
    List<String> ret=new ArrayList<String>();
    // Compiler command
    ret.add(GNU_LINKER);
    // Linker options
    ret.add(RELOCATABLE_OBJECTS_OPTION);
    // Target file
    ret.add("-o");
    File targetFile=info.getTargetFile();
    ret.add(targetFile.getPath());
    // Add object files
    List<File> objectFiles=info.getObjectFiles();
    if ((objectFiles!=null) && (objectFiles.size()>0))
    {
      File objectFile;
      for(Iterator<File> it=objectFiles.iterator();it.hasNext();)
      {
        objectFile=it.next();
        ret.add(objectFile.getPath());
      }
    }
    return ret;
  }
}
