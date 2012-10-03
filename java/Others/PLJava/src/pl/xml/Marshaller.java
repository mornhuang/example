package pl.xml;

import java.util.*;

import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import pl.*;
import pl.map.*;

/**
 * This class is responsible for marshalling persistence objects to xml.
 *
 * @author  Artem Rudoy
 */
public class Marshaller
{
    private static final String CDATA_ATTRIBUTE_TYPE = "CDATA";
    private static final String NAMESPACE_ATTRIBUTE_PREFIX = "xmlns";
    private static final String NAMESPACE_ATTRIBUTE_URI = "http://www.w3.org/2000/xmlns/";

    String namespaceURI = null;
    String namespacePrefix = null;
    PersistenceManagerFactory factory = null;

    /**
     * Creates new Marshaller
     */
    public Marshaller(String namespaceURI, String namespacePrefix, PersistenceManagerFactory factory) throws PlException
    {
        this(factory);

        if(namespaceURI == null)
            throw new PlException("Namespace URI can not be null");
        if(namespacePrefix == null)
            throw new PlException("Namespace prefix can not be null");

        this.namespaceURI = namespaceURI;
        this.namespacePrefix = namespacePrefix;
    }

    /**
     * Creates new Marshaller
     */
    public Marshaller(PersistenceManagerFactory factory)
    {
        this.factory = factory;
    }

    /**
     * Marshall the specified object and append result DOM tree to the rootNode
     * node. Document is used as a factory for the DOM nodes.
     */
    public void marshall(PersistentObject object, Node rootNode, Document document) throws PlException
    {
        DOMBuilder db = new DOMBuilder(rootNode, document);
        marshall(object, db);
    }

    /**
     * Marshall the specified object by generating SAX events for the specified
     * content handler.
     */
    public void marshall(PersistentObject object, ContentHandler handler) throws PlException
    {
        try
        {
            ClassMap cm = factory.getClassMap(object);
            if(cm == null)
            {
                throw new PlException("Class map not found for " + object.getClass().getName());
            }

            AttributesImpl classAtts = new AttributesImpl();
            String classQName = getQName(cm.getXmlName());
            if(namespacePrefix != null)
            {
                if(namespacePrefix.length() > 0)
                {
                    classAtts.addAttribute(NAMESPACE_ATTRIBUTE_URI,
                        namespacePrefix,
                        NAMESPACE_ATTRIBUTE_PREFIX + ":" + namespacePrefix,
                        CDATA_ATTRIBUTE_TYPE,
                        namespaceURI);
                }
                else
                {
                    classAtts.addAttribute(NAMESPACE_ATTRIBUTE_URI,
                        namespacePrefix,
                        NAMESPACE_ATTRIBUTE_PREFIX,
                        CDATA_ATTRIBUTE_TYPE,
                        namespaceURI);
                }
            }
            fillAttributes(object, cm, classAtts);
            handler.startElement(namespaceURI, cm.getXmlName(), classQName, classAtts);

            // Marshall object
            marshall(object, handler, cm);

            handler.endElement(namespaceURI, cm.getXmlName(), classQName);
        }
        catch(SAXException e)
        {
            throw new PlException(e);
        }
    }

    private void marshall(PersistentObject object, ContentHandler handler, ClassMap cm) throws PlException
    {
        try
        {
            // Marshall superclass first
            if(cm.getSuperClass() != null)
                marshall(object, handler, cm.getSuperClass());

            // Marshall attributes
            for(int i = 0; i < cm.getXmlSize(); i++)
            {
                AttributeMap aMap = cm.getXmlAttributeMap(i);

                if(aMap.getXmlMap().getType() == XmlMap.TYPE_NODE)
                {
                    Object value = aMap.getXmlMap().getConverter().convertFrom(aMap.getValue(object));
                    // null value - no value
                    if(value != null)
                    {
                        // Create QName for the attribute node
                        String qName = getQName(aMap.getXmlMap().getName());
                        AttributesImpl atts = new AttributesImpl();
                        handler.startElement(namespaceURI, aMap.getXmlMap().getName(), qName, atts);
                        char[] charValue = value.toString().toCharArray();
                        handler.characters(charValue, 0, charValue.length);
                        handler.endElement(namespaceURI, aMap.getXmlMap().getName(), qName);
                    }
                }
            }

            // Marshall associations
            Iterator associations = cm.getAssociationMaps().values().iterator();
            while(associations.hasNext())
            {
                UniDirectionalAssociationMap aMap = (UniDirectionalAssociationMap)associations.next();

                if(!aMap.isRetrieveAutomatic())
                    continue;

                // Create QName for the association node
                String qName = getQName(aMap.getTarget().getXmlMap().getName());
                AttributesImpl atts = new AttributesImpl();

                if(aMap.getCardinality() == aMap.ONE_TO_ONE)
                {
                    PersistentObject value = (PersistentObject)aMap.getTarget().getValue(object);
                    if(value != null)
                    {
                        handler.startElement(namespaceURI, aMap.getTarget().getXmlMap().getName(), qName, atts);
                        marshall(value, handler);
                        handler.endElement(namespaceURI, aMap.getTarget().getXmlMap().getName(), qName);
                    }
               }
                else if(aMap.getCardinality() == aMap.ONE_TO_MANY)
                {
                    Collection collection = (Collection)aMap.getTarget().getValue(object);
                    if(collection != null)
                    {
                        handler.startElement(namespaceURI, aMap.getTarget().getXmlMap().getName(), qName, atts);
                        Iterator values = collection.iterator();
                        while(values.hasNext())
                        {
                            PersistentObject value = (PersistentObject)values.next();
                            marshall(value, handler);
                        }
                        handler.endElement(namespaceURI, aMap.getTarget().getXmlMap().getName(), qName);
                    }
                }
            }
        }
        catch(SAXException e)
        {
            throw new PlException(e);
        }
    }

    private void fillAttributes(PersistentObject object, ClassMap cm, AttributesImpl atts) throws PlException
    {
        // Fill attribute from superclass first
        if(cm.getSuperClass() != null)
            fillAttributes(object, cm.getSuperClass(), atts);

        for(int i = 0; i < cm.getXmlSize(); i++)
        {
            AttributeMap aMap = cm.getXmlAttributeMap(i);

            if(aMap.getXmlMap().getType() == XmlMap.TYPE_ATTRIBUTE)
            {
                Object value = aMap.getXmlMap().getConverter().convertFrom(aMap.getValue(object));
                // null value - no value
                if(value != null)
                {
                    // Create QName for the attribute node
                    String qName = getQName(aMap.getName());
                    atts.addAttribute(namespaceURI,
                        aMap.getXmlMap().getName(),
                        qName,
                        CDATA_ATTRIBUTE_TYPE,
                        value.toString());
                }
            }
        }
    }

    private String getQName(String localName)
    {
        if(namespacePrefix == null)
            return null;

        if(namespacePrefix.length() == 0)
            return localName;
        else
            return namespacePrefix + ":" + localName;
    }
}
