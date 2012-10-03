package pl.xml;

import java.util.*;
import org.xml.sax.*;
import org.w3c.dom.*;

/**
 * This class builds DOM tree from SAX events.
 *
 * @author  Artem Rudoy
 */
public class DOMBuilder implements ContentHandler
{
    private Stack nodeStack = new Stack();
    private Node currentNode = null;
    private Document document = null;

    /**
     * Creates new DOMBuilder.
     */
    public DOMBuilder(Node rootNode, Document document)
    {
        currentNode = rootNode;
        this.document = document;
    }
    
    public void endDocument() throws org.xml.sax.SAXException
    {
    }
    
    public void ignorableWhitespace(char[] values, int param, int param2) throws org.xml.sax.SAXException
    {
    }
    
    public void endElement(java.lang.String str, java.lang.String str1, java.lang.String str2) throws org.xml.sax.SAXException
    {
        currentNode = (Node)nodeStack.pop();
    }
    
    public void skippedEntity(java.lang.String str) throws org.xml.sax.SAXException
    {
    }
    
    public void processingInstruction(java.lang.String str, java.lang.String str1) throws org.xml.sax.SAXException
    {
    }
    
    public void startElement(String namespaceURI, String localName, String qName, org.xml.sax.Attributes attributes) throws org.xml.sax.SAXException
    {
        Element element = null;
        if(namespaceURI == null)
        {
            element = document.createElement(localName);
        }
        else
        {
            element = document.createElementNS(namespaceURI, qName);
        }
        currentNode.appendChild(element);
        nodeStack.push(currentNode);
        currentNode = element;

        // Add attributes
        for(int i = 0; i < attributes.getLength(); i++)
        {
            if(attributes.getURI(i) != null)
                element.setAttributeNS(attributes.getURI(i), attributes.getQName(i), attributes.getValue(i));
            else
                element.setAttribute(attributes.getLocalName(i), attributes.getValue(i));
        }
    }
    
    public void endPrefixMapping(java.lang.String str) throws org.xml.sax.SAXException
    {
    }
    
    public void startPrefixMapping(java.lang.String str, java.lang.String str1) throws org.xml.sax.SAXException
    {
    }
    
    public void characters(char[] ch, int start, int length) throws org.xml.sax.SAXException
    {
        currentNode.appendChild(document.createTextNode(new String(ch, start, length)));
    }
    
    public void setDocumentLocator(org.xml.sax.Locator locator)
    {
    }
    
    public void startDocument() throws org.xml.sax.SAXException
    {
    }
}
