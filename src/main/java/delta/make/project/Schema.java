package delta.make.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Schema description.
 * @author DAM
 */
public class Schema
{
  private SoftwareComponent _parent;
  private String _name;

  // Classes
  private List<String> _managedClassFiles;
  // Exports
  private List<String> _exportedHeaderFiles;
  // Dependencies
  private List<String> _neededParentSchemas;
  private List<String> _neededLibraries;
  private List<String> _neededExternalSoftwares;

  /**
   * Constructor.
   * @param parent Parent software component.
   * @param name Name of this schema.
   */
  public Schema(SoftwareComponent parent, String name)
  {
    _parent=parent;
    _name=name;
    _managedClassFiles=new ArrayList<String>();
    _exportedHeaderFiles=new ArrayList<String>();
    _neededParentSchemas=new ArrayList<String>();
    _neededLibraries=new ArrayList<String>();
    _neededExternalSoftwares=new ArrayList<String>();
  }

  /**
   * Get the name of this schema.
   * @return the name of this schema.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Get the parent software component.
   * @return the parent software component.
   */
  public SoftwareComponent getParent()
  {
    return _parent;
  }

  /**
   * Add a new class file into this schema.
   * @param classFile Name of class file.
   */
  public void addClassFile(String classFile)
  {
    _managedClassFiles.add(classFile);
  }

  /**
   * Get the list of classes in this schema.
   * @return A list of class names.
   */
  public List<String> getClasses()
  {
    return _managedClassFiles;
  }

  /**
   * Add a exported header file into this schema.
   * @param headerFile Name of exported header file.
   */
  public void addExportedHeader(String headerFile)
  {
    _exportedHeaderFiles.add(headerFile);
  }

  /**
   * Add a brother schema dependency.
   * @param schemaName Name of brother schema.
   */
  public void addNeededSchema(String schemaName)
  {
    _neededParentSchemas.add(schemaName);
  }

  /**
   * Get the list of needed brother schemas.
   * @return the list of needed brother schemas.
   */
  public List<String> getNeededSchemas()
  {
    return _neededParentSchemas;
  }

  /**
   * Add a library dependency.
   * @param libraryName Name of needed library.
   */
  public void addNeededLibrary(String libraryName)
  {
    _neededLibraries.add(libraryName);
  }

  /**
   * Get the list of needed libraries.
   * @return the list of needed libraries.
   */
  public List<String> getNeededLibraries()
  {
    return _neededLibraries;
  }

  /**
   * Add an external software dependency.
   * @param externalSoftwareName Name of needed software.
   */
  public void addNeededSoftware(String externalSoftwareName)
  {
    _neededExternalSoftwares.add(externalSoftwareName);
  }

  /**
   * Get the list of needed external software.
   * @return the list of needed external software.
   */
  public List<String> getNeededExternalSoftware()
  {
    return _neededExternalSoftwares;
  }
}
