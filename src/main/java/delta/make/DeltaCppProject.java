package delta.make;

import java.io.File;
import java.net.URL;

import org.w3c.dom.Element;

import delta.common.utils.url.URLTools;
import delta.common.utils.xml.DOMParsingTools;
import delta.make.project.CppProject;
import delta.make.project.io.xml.ProjectXMLParser;

/**
 * @author DAM
 */
public class DeltaCppProject
{
  private CppProject _project;
  private ExternalSoftwareManager _soft;

  public DeltaCppProject()
  {
    URL url=URLTools.getFromClassPath("deltaProject.xml",this);
    ProjectXMLParser parser=new ProjectXMLParser();
    Element root=DOMParsingTools.parse(url);
    try
    {
      _project=parser.parseProject(root);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    buildExternalSoftware();
  }

  public CppProject getProject()
  {
    return _project;
  }

  public ExternalSoftwareManager getSoftManager()
  {
    return _soft;
  }

  private void buildExternalSoftware()
  {
    _soft=new ExternalSoftwareManager();
    // X11
    ExternalSoftware x11=_soft.addExternalSoftware("X11");
    File x11Includes=new File("/usr/include/X11");
    x11.addIncludePath(x11Includes);
    File x11Libs=new File("/usr/lib");
    x11.addLibPath(x11Libs);
    x11.addLib("X11");

    // ILOG Views
    ExternalSoftware ilogViews=_soft.addExternalSoftware("ILOG Views");
    File ilogHome=new File("/ilog");
    File ilogIncludes=new File(ilogHome,"include");
    ilogViews.addIncludePath(ilogIncludes);
    File ilogLibs=new File(ilogHome,"lib");
    ilogViews.addLibPath(ilogLibs);
    ilogViews.addLib("ilvgadgt");
    ilogViews.addLib("xviews");
    ilogViews.addLib("views");

    // Threads
    ExternalSoftware threads=_soft.addExternalSoftware("threads");
    threads.addLib("pthread");
  }

/*
  # Configuration PostgreSQL 7.0
  CHEMINS_INCLUDES_PGSQL = ${DELTA_POSTGRES_HOME}/include
  CHEMINS_LIBRAIRIES_PGSQL = ${DELTA_POSTGRES_HOME}/lib
  LIBRAIRIES_PGSQL = pq

  # Configuration MySQL 3.23.36
  CHEMINS_INCLUDES_MYSQL = /usr/include/mysql
  CHEMINS_LIBRAIRIES_MYSQL = /usr/lib
  LIBRAIRIES_MYSQL = mysqlclient

  # Configuration libjpeg
  CHEMINS_INCLUDES_LIBJPEG = ${DELTA_HOME}/external/software/libjpeg/include
  CHEMINS_LIBRAIRIES_LIBJPEG = ${DELTA_HOME}/external/software/libjpeg/lib
  LIBRAIRIES_LIBJPEG = jpeg

  # Librairies IP
  CHEMINS_INCLUDES_TCPIP =
  CHEMINS_LIBRAIRIES_TCPIP =
  LIBRAIRIES_IP =

  # Librairies Dynamic Loader
  CHEMINS_INCLUDES_DL =
  CHEMINS_LIBRAIRIES_DL =
  LIBRAIRIES_DL = dl
*/
}
