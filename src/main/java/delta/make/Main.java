package delta.make;

import delta.make.project.CppProject;
import delta.make.project.SharedLibrary;

/**
 * @author dm
 */
public class Main
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    DeltaCppProject delta=new DeltaCppProject();
    CppProject project=delta.getProject();
    ProjectFileSystem fs=new ProjectFileSystem();
    ExternalSoftwareManager softManager=delta.getSoftManager();
    MakeEnvironment env=new MakeEnvironment(project,fs,softManager);
    SharedLibrary base=project.getSharedLibrary("Base");
    CompileSharedLibraryTask task=new CompileSharedLibraryTask(env,base);
    task.doIt();
  }
}
