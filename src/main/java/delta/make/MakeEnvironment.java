package delta.make;

import delta.make.project.CppProject;

/**
 * Make environment.
 * @author DAM
 */
public class MakeEnvironment
{
  private CppProject _project;
  private ProjectFileSystem _fileSystem;
  private ExternalSoftwareManager _softManager;

  /**
   * Constructor.
   * @param project Project.
   * @param fileSystem File system rules.
   * @param softManager External software manager.
   */
  public MakeEnvironment(CppProject project, ProjectFileSystem fileSystem, ExternalSoftwareManager softManager)
  {
    _project=project;
    _fileSystem=fileSystem;
    _softManager=softManager;
  }

  /**
   * Get the managed project.
   * @return the managed project.
   */
  public CppProject getProject()
  {
    return _project;
  }

  /**
   * Get the file system rules.
   * @return the file system rules.
   */
  public ProjectFileSystem getFileSystem()
  {
    return _fileSystem;
  }

  /**
   * Get the external software manager.
   * @return the external software manager.
   */
  public ExternalSoftwareManager getSoftManager()
  {
    return _softManager;
  }
}
