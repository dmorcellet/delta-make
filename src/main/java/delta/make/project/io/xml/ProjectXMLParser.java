package delta.make.project.io.xml;

import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import delta.common.utils.xml.DOMParsingTools;
import delta.make.project.CppProject;
import delta.make.project.Schema;
import delta.make.project.SharedLibrary;
import delta.make.project.SoftwareComponent;

/**
 * Parser for a project.
 * @author DAM
 */
public class ProjectXMLParser
{
  // Tag names
  private static final String CPP_PROJECT_TAG="cppProject";
  private static final String SHARED_LIBRARY_TAG="sharedLibrary";
  private static final String SCHEMA_TAG="schema";
  private static final String HEADER_TAG="header";
  private static final String CLASS_TAG="class";
  private static final String SCHEMA_DEPENDANCY_TAG="dependOnSchema";
  private static final String NAME_ATTRIBUTE="name";

  /**
   * Simple constructor.
   */
  public ProjectXMLParser()
  {
    // Nothing to do !!
  }

  /**
   * Parse a C++ project from the given XML tag.
   * @param root Input tag.
   * @return the parsed project.
   * @throws Exception If an error occcurs.
   */
  public CppProject parseProject(Element root) throws Exception
  {
    String tagName=root.getTagName();
    if (!CPP_PROJECT_TAG.equals(tagName))
    {
      return null;
    }
    NamedNodeMap attributes_l=root.getAttributes();
    // Find project name
    String nameAttr=DOMParsingTools.getStringAttribute(attributes_l,NAME_ATTRIBUTE,"");
    CppProject project=new CppProject(nameAttr);
    // Handle shared libraries
    {
      NodeList sharedLibraryNodes=root.getElementsByTagName(SHARED_LIBRARY_TAG);
      int nbSharedLibraryNodes=sharedLibraryNodes.getLength();
      for(int i=0;i<nbSharedLibraryNodes;i++)
      {
        Node node=sharedLibraryNodes.item(i);
        if (node.getNodeType()==Node.ELEMENT_NODE)
        {
          Element element=(Element)node;
          parseSharedLibrary(element,project);
        }
      }
    }
    return project;
  }

  private SharedLibrary parseSharedLibrary(Element root, CppProject project)
  {
    NamedNodeMap attributes_l=root.getAttributes();
    // Find library name
    String nameAttr=DOMParsingTools.getStringAttribute(attributes_l,NAME_ATTRIBUTE,"");
    SharedLibrary library=project.addSharedLibrary(nameAttr);
    // Handle schemas
    {
      NodeList schemaNodes=root.getElementsByTagName(SCHEMA_TAG);
      int nbSchemaNodes=schemaNodes.getLength();
      for(int i=0;i<nbSchemaNodes;i++)
      {
        Node node=schemaNodes.item(i);
        if (node.getNodeType()==Node.ELEMENT_NODE)
        {
          Element element=(Element)node;
          parseSchema(element,library);
        }
      }
    }
    return library;
  }

  private Schema parseSchema(Element root, SoftwareComponent parent)
  {
    NamedNodeMap attributes_l=root.getAttributes();
    // Find schema name
    String nameAttr=DOMParsingTools.getStringAttribute(attributes_l,NAME_ATTRIBUTE,"");
    Schema schema=parent.addSchema(nameAttr);
    // Handle headers
    {
      List<Element> headerNodes=DOMParsingTools.getChildTagsByName(root,HEADER_TAG);
      if ((headerNodes!=null) && (headerNodes.size()>0))
      {
        Element headerNode;
        String headerName;
        for(Iterator<Element> it=headerNodes.iterator();it.hasNext();)
        {
          headerNode=it.next();
          headerName=DOMParsingTools.getStringAttribute(headerNode.getAttributes(),NAME_ATTRIBUTE,null);
          if (headerName!=null)
          {
            schema.addExportedHeader(headerName);
          }
        }
      }
    }
    // Handle classes
    {
      List<Element> classNodes=DOMParsingTools.getChildTagsByName(root,CLASS_TAG);
      if ((classNodes!=null) && (classNodes.size()>0))
      {
        Element classNode;
        String className;
        for(Iterator<Element> it=classNodes.iterator();it.hasNext();)
        {
          classNode=it.next();
          className=DOMParsingTools.getStringAttribute(classNode.getAttributes(),NAME_ATTRIBUTE,null);
          if (className!=null)
          {
            schema.addClassFile(className);
          }
        }
      }
    }
    // Handle schema dependencies
    {
      List<Element> schemaDepsNodes=DOMParsingTools.getChildTagsByName(root,SCHEMA_DEPENDANCY_TAG);
      if ((schemaDepsNodes!=null) && (schemaDepsNodes.size()>0))
      {
        Element schemaDepNode;
        String schemaName;
        for(Iterator<Element> it=schemaDepsNodes.iterator();it.hasNext();)
        {
          schemaDepNode=it.next();
          schemaName=DOMParsingTools.getStringAttribute(schemaDepNode.getAttributes(),NAME_ATTRIBUTE,null);
          if (schemaName!=null)
          {
            schema.addNeededSchema(schemaName);
          }
        }
      }
    }
    return schema;
  }
}
