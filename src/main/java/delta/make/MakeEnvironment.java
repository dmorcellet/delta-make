package delta.make;

import delta.make.project.CppProject;

/**
 * @author DAM
 */
public class MakeEnvironment
{
  private CppProject _project;
  private ProjectFileSystem _fileSystem;
  private ExternalSoftwareManager _softManager;

  public MakeEnvironment(CppProject project, ProjectFileSystem fileSystem, ExternalSoftwareManager softManager)
  {
    _project=project;
    _fileSystem=fileSystem;
    _softManager=softManager;
  }

  public CppProject getProject()
  {
    return _project;
  }

  public ProjectFileSystem getFileSystem()
  {
    return _fileSystem;
  }

  public ExternalSoftwareManager getSoftManager()
  {
    return _softManager;
  }
}
