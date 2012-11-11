package pl;

import java.util.ArrayList;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import pl.criteria.OrderEntry;
import pl.map.*;
import pl.converter.*;

/**
 * This class implements ConfigLoader interface and is responsible
 * for loading Persistence Layer configs from XML files.
 * <p>
 * The main element of the XML file must have 'map' name.
 * <p>
 * The config file is started with a database description. Here is an example of
 * a database description for Oracle database.<br>
 * <code><pre>
 * &lt;database>
 *     &lt;database-name>Oracle&lt;/database-name>
 *     &lt;database-class>pl.test.OracleDatabase&lt;/database-class>
 *
 *     &lt;parameter>
 *         &lt;parameter-name>user&lt;/parameter-name>
 *         &lt;parameter-value>system&lt;/parameter-value>
 *     &lt;/parameter>
 *     &lt;parameter>
 *         &lt;parameter-name>password&lt;/parameter-name>
 *         &lt;parameter-value>manager&lt;/parameter-value>
 *     &lt;/parameter>
 *     &lt;parameter>
 *         &lt;parameter-name>driver&lt;/parameter-name>
 *         &lt;parameter-value>oracle.jdbc.driver.OracleDriver&lt;/parameter-value>
 *     &lt;/parameter>
 *     &lt;parameter>
 *         &lt;parameter-name>url&lt;/parameter-name>
 *         &lt;parameter-value>jdbc:oracle:oci8:@art&lt;/parameter-value>
 *     &lt;/parameter>
 *
 *     &lt;id-generator>
 *         &lt;id-generator-name>idGenerator&lt;/id-generator-name>
 *         &lt;id-generator-class>pl.test.OracleIdGenerator&lt;/id-generator-class>
 *     &lt;/id-generator>
 * &lt;/database>
 * </pre></code>
 * The 'database' element has two mandatory child elements. The value of the 'database-name' element
 * is the name of the database. This name will be used later in a class map description. The
 * value of the 'database-class' element the fully qualified name of this database driver class.<br>
 * Database element can have several 'parameter' child elemnts. Each 'parameter'
 * element has two mandatory childs. The value of the 'name' child element is the name of the parameter and
 * the value of the 'value' child element is the value of this parameter. These parameters will be passed as
 * java.util.Properties to the <code>init</code> method of the RelationalDatabase class.<br>
 * Inside the 'database' element there can be declared one or more ID generators for
 * the database. Each ID generator is declared by the 'id-generator' element.
 * This element has two mandatory child elements. The value of the 'id-generator-name'
 * element id a name of the ID generator. This name will be used later in attribute's
 * ID generator declarations to reference ID generators. The value of the
 * 'id-generator-class' is a fully qualified name of the class for this ID generator.
 * 'id-generator' element has one optional element 'single-instance' with 'true'
 * as a default value. The value of this element shows whether the Persistence Layer
 * will create new instance of the 'id-generator-class' when new ID generator is
 * required or will return the same instance. 'id-generator' element can has zero
 * or more 'parameter' child elements like  a 'database' element. This parameters
 * will be passed to the <code>init</code> method of the ID generator class.
 * <p>
 * Then you can specify zero or more global converters to use them later at attribute map definitions.
 * Converter is specified by the 'converter' tag. This tag has the following chid elements:
 * 'converter-name' specifies the name of the converter. You can use this name to refer to the converter
 * from attribute map definitions. 'class-name' specifies the class name of the converter.
 * Converter class must implement <code>pl.converter.Converter</code> interface. You can
 * also specify parameters for the converter just like parameters for databases. This
 * parameters will path to the <code>init</code> method of the <code>pl.converter.Converter</code>
 * interface.
 * <p>
 * Converter definitions are followed by class map definitions. Each class map definition
 * is described by the 'class' element. This tag has three mandatory child elements. The value 'class-name'
 * element is the fully qualified name of the class. The value of the 'table-name' element is the name of a table
 * in a database this class will be mapped to. The value of the 'database-name' element is the name of the
 * database. The value of the 'xml-name' element is the name of xml tag for this class.
 * If 'xml-name' is not specified it will be set to 'class-name' by default.<br>
 * 'class' element can have one or more 'attribute' elements. Each 'attribute' element
 * has following child elements. The value of the 'attribute-name' element is a mandatory name of
 * the class attribute. Each class attribute with the name <code>abcde</code> must have a
 * <code>setAbcde(AbcdeClass abcde)</code> method as setter and a <code>getAbcde()</code> or
 * <code>isAbcde()</code> method as getter. The value of the 'column-name' element is
 * optional and is the name of the column this class attribute will be mapped to. You can
 * also specify a converter for an attribute with the 'converter' element. You can refer to
 * previously difined global or local converter by its name or create new local converter.
 * The value of the 'xml-name' element is the name of xml tag or xml attribute for this attribute.
 * If 'xml-name' is not specified it will be set to 'attribute-name' by default.
 * The value of the 'xml-type' element is one of 'attribute' or 'node' shows where
 * class attribute will be marshalled to. Default value of the 'xml-type' is 'node'.
 * You can also specify xml converter by 'xml-converter' element.
 * The value of the 'key' element is optional and can has two values, 'primary' if this class attribute is
 * a primary key or 'foreign' if this class attribute is a foreign key. The value of the
 * 'reference' element is optional name of the superclass attribute this class attribute references to. This is
 * required for the inheritance support.<br>
 * For primary key attributes ID generator can be specified by the 'attribute-id-generator'
 * element. This element has mandatory 'id-generator-name' element. The value of
 * this element is the name of an ID generator from the attribute's class databse.
 * The 'attribute-id-generator' element can has zero or more 'parameter' elements.
 * This parameters will be appended to the parameters defined for the ID generator
 * in a 'database' section and passed to the <code>init</code> method of the ID
 * generator class for not single instance ID generators. If ID generator is single
 * instance this parameters will be ignored.<br>
 * The value of the 'timestamp' element is the optional value indicatites that this class attribute
 * will be used for optimistic lock support. If 'timestamp' value is 'true' the column of
 * this class attribute must be mapped to the 'timestamp' attribute of the PersistentObject.
 * The value of the 'proxy' element is the optional value indicatites that this class attribute
 * will be retrieved with proxy objects. Default value is <code>true</code>. Key attributes
 * are always retrieved with proxy objects.
 * Here is an example of the class map definition<br>
 * <code><pre>
 * &lt;class>
 *     &lt;class-name>pl.test.Person&lt;/class-name>
 *     &lt;table-name>person&lt;/table-name>
 *     &lt;database-name>Database&lt;/database-name>
 *
 *     &lt;attribute>
 *         &lt;attribute-name>id&lt;/attribute-name>
 *         &lt;column-name>id&lt;/column-name>
 *         &lt;key>primary&lt;/key>
 *         &lt;attribute-id-generator>
 *             &lt;id-generator-name>idGenerator&lt;/id-generator-name>
 *         &lt;/attribute-id-generator>
 *     &lt;/attribute>
 *     &lt;attribute>
 *         &lt;name>name&lt;/name>
 *         &lt;column>name&lt;/column>
 *         &lt;proxy>false&lt;/proxy>
 *     &lt;/attribute>
 *     &lt;attribute>
 *         &lt;name>timestamp&lt;/name>
 *         &lt;column>last_update_time&lt;/column>
 *         &lt;timestamp>true&lt;/timestamp>
 *     &lt;/attribute>
 * &lt;/class>
 * </pre></code><br>
 * Note if you use support for inheritance class maps for superclasses must be defined before
 * class maps for their subclasses.
 * <p>
 * The last element used by Persistence Layer is the 'association' element. This element describes associations
 * between classes. Here is an example of the association definition<br>
 * <code><pre>
 * &lt;association>
 *     &lt;from-class>pl.test.Employee&lt;/from-class>
 *     &lt;to-class>pl.test.Position&lt;/to-class>
 *     &lt;cardinality>oneToOne&lt;/cardinality>
 *     &lt;target>position&lt;/target>
 *     &lt;retrieve-automatic>true&lt;/retrieve-automatic>
 *     &lt;save-automatic>true&lt;/save-automatic>
 *
 *     &lt;order-attribute>
 *         &lt;name>name&lt;/name>
 *         &lt;direction>ascend&lt;/direction>
 *     &lt;/order-attribute>
 *
 *     &lt;entry>
 *         &lt;to-attribute>id&lt;/to-attribute>
 *         &lt;from-attribute>positionId&lt;/from-attribute>
 *     &lt;/entry>
 * &lt;/association>
 * </pre></code>
 * In this example an association from the <code>Employee</code> class (the 'from-class' class) to
 * the <code>Position</code> class (the 'to-class' class) is defined. An implementation of the
 * association is the reference from the <code>positionId</code> attribute ('from-attribute') of the
 * 'from-class' to the <code>id</code> attribute ('to-attribute') of the 'to-class'.
 * The <code>Position</code> will be automatically retrieved with the <code>Employee</code> and
 * will be stored in the <code>position</code> attribute (the 'target' attribute) of the <code>Employee</code> class
 * This 'target' attribute must be described in the class map definition of <code>Employee</code> class.
 * Cardinality of an association can be 'oneToOne' or 'oneToMany'. If cardinality of the association is
 * 'oneToOne' type of the 'target' attribute must be compatible with the type of the object that will
 * be stored in this attribute. If cardinality is 'oneToMany' type of the 'target' attribute must be
 * implementation of the <code>java.util.Collection</code> interface. See the <code>java.util</code> package
 * documentation for details. Attributes 'retrieve-automatic', 'delete-automatic', 'save-automatic' and 'inverse' of
 * the 'association' tag are optional and have <code>false</code> as a default value.<br>
 * Inside the 'association' tag there can be one or more 'entry' tags. Each 'entry' tag describes one
 * attribute to attribute association as described in the example. If the 'inverse' property of the association is
 * <code>false</code> the 'from-attribute' in the <code>entry</code> will be taken from the 'from-class' and the
 * 'toAttribute' will be taken from the 'toClass'. If the 'inverse' property is <code>true</code> the
 * 'from-attribute' in the <code>entry</code> will be taken from the 'to-class' and the 'to-attribute'
 * will be taken from the 'from-class', that is the direction of the association is not the same as the direction
 * of the references between attributes that implements this association.<br>
 * You can also specify ordering of retreived association data by 'order-attribute' tag.
 * This tag has two subelements: 'name' specifies the name of the order attribute and
 * 'direction' specifies order direction. Direction can be 'ascend' or 'descend'.
 * <br>
 * Here are two example of associations. The first arrow is the direction of the association and the second
 * arrow is the direction of the reference in a database that represents the association<br>
 * Ordinary association<br>
 * <code>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1<br>
 * Employee&nbsp;&nbsp; --------&gt; Position<br>
 * positionId --------&gt; id<br>
 * </code>
 * from-class = Employee<br>
 * to-class = Position<br>
 * from-attribute = positionId<br>
 * to-attribute = id<br>
 * <br>
 * Here is an example of the <code>inverse</code> association<br>
 * <code>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;n<br>
 * Employee --------&gt; Task<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; id &lt;-------- employeeId<br>
 * </code>
 * from-class = Employee<br>
 * to-class = Task<br>
 * from-attribute = employeeId<br>
 * to-attribute = id<br>
 *
 * @author: Artem Rudoy
 */
public class XMLConfigLoader implements ConfigLoader
{
    private java.io.InputStream in = null;
    private java.lang.ClassLoader classLoader = null;
    private PersistenceManagerFactory broker = null;

    /**
     * XMLConfigLoader constructor.
     *
     * @param in an input stream with the configuration to be loaded
     */
    public XMLConfigLoader(java.io.InputStream in)
    {
        this(in, XMLConfigLoader.class.getClassLoader());
    }

    /**
     * XMLConfigLoader constructor.
     *
     * @param in an input stream with the configuration to be loaded
     */
    public XMLConfigLoader(java.io.InputStream in, ClassLoader classLoader)
    {
        super();

        this.in = in;
        this.classLoader = classLoader;
    }

    /**
     * XMLConfigLoader constructor.
     *
     * @param fileName a name of file with the configuration to be loaded
     */
    public XMLConfigLoader(String fileName) throws java.io.FileNotFoundException
    {
        this(new java.io.FileInputStream(fileName));
    }

    /**
     * XMLConfigLoader constructor.
     *
     * @param fileName a name of file with the configuration to be loaded
     */
    public XMLConfigLoader(String fileName, ClassLoader classLoader) throws java.io.FileNotFoundException
    {
        this(new java.io.FileInputStream(fileName), classLoader);
    }

    /**
     * @return pl.map.AttributeMap
     * @param node org.w3c.dom.Element
     */
    private AttributeMap getAttributeMap(Element node, ClassMap clm, TableMap tableMap) throws PlException
    {
        String attributeName = getNamedChildTextNodeValue(node, "attribute-name");
        String column = getNamedChildTextNodeValue(node, "column-name");
        String key = getNamedChildTextNodeValue(node, "key");
        String reference = getNamedChildTextNodeValue(node, "reference");
        String proxy = getNamedChildTextNodeValue(node, "proxy");
        String xmlName = getNamedChildTextNodeValue(node, "xml-name");
        String xmlType = getNamedChildTextNodeValue(node, "xml-type");

        if(attributeName != null)
        {
            AttributeMap am = new AttributeMap(attributeName);

            // Set column map for this attribute map
            if(column != null)
            {
                // Create converter for this column map
                Converter converter = getConverter(getFirstNamedChildElement(node, "converter"));
                // Create column map for this attribute map
                ColumnMap cm = new ColumnMap(column, tableMap, converter);
                if(key != null)
                {
                    if("primary".equalsIgnoreCase(key))
                    {
                        cm.setKeyType(ColumnMap.PRIMARY_KEY);
                        // Get ID generator
                        Element idGeneratorElement = getFirstNamedChildElement(node, "attribute-id-generator");
                        if(idGeneratorElement != null)
                        {
                            String idGeneratorName = getNamedChildTextNodeValue(idGeneratorElement, "id-generator-name");
                            if(idGeneratorName == null)
                                throw new PlException("ID generator name for the " + attributeName +
                                " attribute of the class " + clm.getName() + " is not specified");
                            java.util.Properties parameters = getParameters(idGeneratorElement);
                            pl.sql.IdGenerator idGenerator =
                            clm.getRelationalDatabase().getIdGenerator(idGeneratorName, parameters);
                            if(idGenerator == null)
                            {
                                throw new PlException("ID generator with name " + idGeneratorName + " does not exist");
                            }
                            cm.setIdGenerator(idGenerator);
                        }
                    }
                    else if("foreign".equalsIgnoreCase(key))
                    {
                        cm.setKeyType(ColumnMap.FOREIGN_KEY);
                    }
                }
                if("false".equals(proxy))
                    am.setProxy(false);
                am.setColumnMap(cm);
            }

            // Set xml map for this attribute map
            // Create converter element for this xml map
            Converter xmlConverter = getConverter(getFirstNamedChildElement(node, "xml-converter"));
            if(xmlName == null)
                xmlName = attributeName;
            XmlMap xm = new XmlMap(attributeName, xmlConverter);
            if("attribute".equalsIgnoreCase(xmlType))
                xm.setType(XmlMap.TYPE_ATTRIBUTE);
            else
                xm.setType(XmlMap.TYPE_NODE);
            am.setXmlMap(xm);

            // Set reference for this attribute map
            if(reference != null && clm.getSuperClass() != null)
            {
                AttributeMap referenceAttribute = clm.getSuperClass().getAttributeMap(reference);
                if(referenceAttribute != null)
                    am.setReference(referenceAttribute);
            }

            return am;
        }
        else
        {
            throw new PlException("Error in attribute map definition. Attribute name is not specified.");
        }
    }

    /**
     * Return element's attribute value or <bode>null</code>
     * if attribute does not exist.
     *
     * @return element's attribute value
     * @param element
     * @param attrubuteName
     */
    /*private String getAttributeValue(Element element, String attributeName)
    {
        Attr attribute = element.getAttributeNode(attributeName);
        if(attribute == null)
            return null;
        else
            return attribute.getValue();
    }*/

    /**
     * @return pl.map.ClassMap
     * @param node org.w3c.dom.Node
     */
    private ClassMap getClassMap(Element node) throws PlException
    {
        String className = getNamedChildTextNodeValue(node, "class-name");
        String table = getNamedChildTextNodeValue(node, "table-name");
        String databaseName = getNamedChildTextNodeValue(node, "database-name");
        String xmlName = getNamedChildTextNodeValue(node, "xml-name");

        if(className != null && table != null && databaseName != null)
        {
            pl.sql.RelationalDatabase database = broker.getRelationalDatabase(databaseName);
            if(database == null)
                throw new PlException("Database with name " + databaseName + " for class " + className + " not found");

            // Create current class map
            ClassMap cm = new ClassMap(className, database, classLoader, broker);

            // Create database map for the current table
            DatabaseMap dm = new DatabaseMap(databaseName);

            // Create current table map
            TableMap tableMap = new TableMap();
            tableMap.setName(table);
            tableMap.setDatabaseMap(dm);

            if(xmlName != null)
                cm.setXmlName(xmlName);

            Element child = getFirstNamedChildElement(node, "attribute");
            while(child != null)
            {
                AttributeMap am = getAttributeMap(child, cm, tableMap);
                if("true".equals(getNamedChildTextNodeValue(child, "timestamp")))
                {
                    if(!am.getName().equals(PersistentObject.TIMESTAMP_FIELD_NAME))
                        throw new PlException("Timestamp attribute for optimistic locking support must be mapped to the timestamp attribute of the PersistentObject");
                    if(cm.getTimestampAttributeMap() != null)
                        throw new PlException("Timestamp attribute for optimistic already exists");
                    cm.setTimestampAttributeMap(am);
                }
                else
                    cm.addAttributeMap(am);

                child = getNextSiblingElement(child);
            }
            return cm;
        }
        else
        {
            if(className == null)
                throw new PlException("Error in class map definition. Class name is not specified.");
            else if(table == null)
                throw new PlException("Error in class map definition for class" + className +
                ". Table name is not specified.");
            else
                throw new PlException("Error in class map definition for class" + className +
                ". Database name is not specified.");
        }
    }

    /**
     * Returns the first child element of the specified node
     * of null if no child element exists
     *
     * @return the first child element of the specified node
     * @param node
     */
/*    private Element getFirstChildElement(Node node)
    {
        Node child = node.getFirstChild();

        while(child != null && child.getNodeType() != Node.ELEMENT_NODE)
        {
            child = child.getNextSibling();
        }

        return (Element)child;
    }*/

    /**
     * Returns the first child element of the specified node
     * with the specified name of null if no child element exists
     *
     * @return the first child element of the specified node
     * @param node
     */
    private Element getFirstNamedChildElement(Node node, String elementName)
    {
        Node child = node.getFirstChild();

        while(child != null)
        {
            if(child.getNodeType() == Node.ELEMENT_NODE &&
            ((Element)child).getTagName().equals(elementName))
                break;
            child = child.getNextSibling();
        }

        return (Element)child;
    }

    /**
     * Returns value of the first child text node
     *
     * @return value of the first child text node
     * @param node
     */
    private String getNamedChildTextNodeValue(Node node, String childElementName)
    {
        Node child = getFirstNamedChildElement(node, childElementName);
        if(child == null)
            return null;

        Node sibling = child.getFirstChild();

        String value = null;
        while(sibling != null)
        {
            if(sibling.getNodeType() == Node.TEXT_NODE)
            {
                value = sibling.getNodeValue().trim();
                break;
            }
            sibling = sibling.getNextSibling();
        }

        if(value == null)
            value = "";

        return value;
    }

    /**
     * Returns next sibling element of the specified node
     * with the specified name of null if no sibling
     * element exists
     *
     * @return next sibling element of the specified node
     * @param node
     * @param elementName
     */
    private Element getNextNamedSiblingElement(Node node, String elementName)
    {
        Node sibling = node.getNextSibling();

        while(sibling != null)
        {
            if(sibling.getNodeType() == Node.ELEMENT_NODE &&
            ((Element)sibling).getTagName().equals(elementName))
                break;
            sibling = sibling.getNextSibling();
        }

        return (Element)sibling;
    }

    /**
     * Return next sibling element of the specified node
     * of null if no sibling element exists
     *
     * @return next sibling element of the specified node
     * @param node
     */
    private Element getNextSiblingElement(Node node)
    {
        Node sibling = node.getNextSibling();

        while(sibling != null && sibling.getNodeType() != Node.ELEMENT_NODE)
        {
            sibling = sibling.getNextSibling();
        }

        return (Element)sibling;
    }

    /**
     * Return parameters of the specified node.
     *
     * @return parameters of the specified node
     * @param node
     * @exception pl.PlException
     */
    private java.util.Properties getParameters(Node node) throws PlException
    {
        java.util.Properties parameters = new java.util.Properties();
        Element child = getFirstNamedChildElement(node, "parameter");

        while(child != null)
        {
            String parameterName = getNamedChildTextNodeValue(child, "parameter-name");
            if(parameterName == null)
                throw new PlException("Parameter name is not specified");
            String parameterValue = getNamedChildTextNodeValue(child, "parameter-value");
            if(parameterValue == null)
                throw new PlException("Parameter value is not specified");
            parameters.put(parameterName, parameterValue);

            child = getNextNamedSiblingElement(child, "parameter");
        }

        return parameters;
    }

    /**
     * @return pl.pm.PersistenceMechanism
     * @param node org.w3c.dom.Element
     */
    private pl.sql.RelationalDatabase getRelationalDatabase(Element node) throws PlException
    {
        String persistenceMechanismName = getNamedChildTextNodeValue(node, "database-name");
        String className = getNamedChildTextNodeValue(node, "database-class");

        if(persistenceMechanismName != null && className != null)
        {
            pl.sql.RelationalDatabase pm = null;
            try
            {
                java.util.Properties parameters = getParameters(node);
                pm = pl.sql.RelationalDatabaseFactory.getRelationalDatabase(className, classLoader, parameters);
                pm.setName(persistenceMechanismName);
            }
            catch(Exception e)
            {
                throw new PlException("Error in " + persistenceMechanismName +
                " persisntece mechanism definition. Class " + className +
                " not found for persistence mechanism");
            }

            // Get ID generators for this database
            Element child = getFirstNamedChildElement(node, "id-generator");
            while(child != null)
            {
                processIdGenerator(child, pm);
                child = getNextNamedSiblingElement(child, "id-generator");
            }

            return pm;
        }
        else
        {
            if(persistenceMechanismName == null)
                throw new PlException("Error in persisntece mechanism definition. Persistence mechanism name is not specified.");
            else
                throw new PlException("Error in " + persistenceMechanismName +
                " persisntece mechanism definition. Persistence mechanism class name not specified.");
        }
    }

    /**
     * This method is called from the {@link PersistenceBroker}
     * {@link PersistenceBroker#loadConfig loadConfig(ConfigLoader)} method.
     *
     * @param broker
     */
    public void loadConfig(PersistenceManagerFactory broker) throws PlException
    {
        this.broker = broker;
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(in);

            Element root = getFirstNamedChildElement(document, "map");
            if(root == null)
                throw new PlException("Invalid XML document. No map definition found");

            Element child = null;

            // Get databases
            child = getFirstNamedChildElement(root, "database");
            while(child != null)
            {
                pl.sql.RelationalDatabase pm = getRelationalDatabase(child);
                broker.addRelationalDatabase(pm);
                child = getNextNamedSiblingElement(child, "database");
            }

            // Get converters
            child = getFirstNamedChildElement(root, "converter");
            while(child != null)
            {
                // getConverter will put loaded converter to the PersistenceManagerFactory
                getConverter(child);
            }

            // Get classes
            child = getFirstNamedChildElement(root, "class");
            while(child != null)
            {
                ClassMap cm = getClassMap(child);
                broker.addClassMap(cm);
                child = getNextNamedSiblingElement(child, "class");
            }

            // Get associations
            child = getFirstNamedChildElement(root, "association");
            while(child != null)
            {
                processAssociation(child);
                child = getNextNamedSiblingElement(child, "association");
            }
        }
        catch(PlException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PlException(e);
        }
        finally
        {
            try
            {
                in.close();
            }
            catch(Exception e) {}
        }
    }

    /**
     * @return pl.map.UniDirectionalAssociationMap
     * @param node org.w3c.dom.Element
     */
    private void processAssociation(Element node) throws PlException
    {
        String fromClass = getNamedChildTextNodeValue(node, "from-class");
        String toClass = getNamedChildTextNodeValue(node, "to-class");
        String target = getNamedChildTextNodeValue(node, "target");
        String cardinality = getNamedChildTextNodeValue(node, "cardinality");

        if(fromClass != null && toClass != null && target != null)
        {
            ClassMap fromClassMap = broker.getClassMap(fromClass);
            if(fromClassMap == null)
                throw new PlException("Error in association definition. Class map was not found for '" +
                fromClass + "' from-class.");

            ClassMap toClassMap = broker.getClassMap(toClass);
            if(toClassMap == null)
                throw new PlException("Error in association definition. Class map was not found for '" +
                toClass + "' to-class.");

            UniDirectionalAssociationMap am = new UniDirectionalAssociationMap();

            am.setForClass(toClassMap);
            am.setTargetName(target);
            am.setTarget(fromClassMap.getAttributeMap(target));

            am.setDeleteAutomatic("true".equalsIgnoreCase(getNamedChildTextNodeValue(node, "delete-automatic")));
            am.setSaveAutomatic("true".equalsIgnoreCase(getNamedChildTextNodeValue(node, "save-automatic")));
            am.setRetrieveAutomatic("true".equalsIgnoreCase(getNamedChildTextNodeValue(node, "retrieve-automatic")));
            am.setInverse("true".equalsIgnoreCase(getNamedChildTextNodeValue(node, "inverse")));

            if(cardinality != null)
            {
                if(cardinality.equalsIgnoreCase("onetoone"))
                {
                    am.setCardinality(am.ONE_TO_ONE);
                }
                else if(cardinality.equalsIgnoreCase("onetomany"))
                {
                    am.setCardinality(am.ONE_TO_MANY);
                }
            }

            Element child = getFirstNamedChildElement(node, "entry");
            while(child != null)
            {
                String fromAttribute = getNamedChildTextNodeValue(child, "from-attribute");
                String toAttribute = getNamedChildTextNodeValue(child, "to-attribute");
                if(fromAttribute != null && toAttribute != null)
                {
                    if(am.isInverse())
                    {
                        if(toClassMap.getAttributeMap(fromAttribute) == null)
                            throw new PlException("Error in association map entry definition. " +
                            "Attribute map was not found for '" + fromAttribute +
                            "' from-attribute in " + toClass + " class");
                        if(fromClassMap.getAttributeMap(toAttribute) == null)
                            throw new PlException("Error in association map entry definition. " +
                            "Attribute map was not found for '" + toAttribute +
                            "' to-attribute in " + fromClass + " class");
                        UDAMapEntry entry = new UDAMapEntry(toClassMap.getAttributeMap(fromAttribute),
                        fromClassMap.getAttributeMap(toAttribute));
                        am.addEntry(entry);
                    }
                    else
                    {
                        if(fromClassMap.getAttributeMap(fromAttribute) == null)
                            throw new PlException("Error in association map entry definition. " +
                            "Attribute map was not found for '" + fromAttribute +
                            "' from-attribute in " + fromClass + " class");
                        if(toClassMap.getAttributeMap(toAttribute) == null)
                            throw new PlException("Error in association map entry definition. " +
                            "Attribute map was not found for '" + toAttribute +
                            "' to-attribute in " + toClass + " class");
                        UDAMapEntry entry = new UDAMapEntry(fromClassMap.getAttributeMap(fromAttribute),
                        toClassMap.getAttributeMap(toAttribute));
                        am.addEntry(entry);
                    }
                }
                else
                {
                    if(fromAttribute == null)
                        throw new PlException("Error in association map entry definition. " +
                        "from-attribute in not specified.");
                    else
                        throw new PlException("Error in association map entry definition. " +
                        "to-attribute in not specified.");
                }
                child = getNextNamedSiblingElement(child, "entry");
            }

            // Get order attributes
            child = getFirstNamedChildElement(node, "order-attribute");
            ArrayList orderAttributes = new ArrayList();
            while(child != null)
            {
                String attributeName = getNamedChildTextNodeValue(child, "name");
                if(attributeName == null)
                    throw new PlException("Error in order attribute specification. " +
                        "attribute name is not specified.");
                AttributeMap orderAttribute = am.getForClass().getAttributeMap(attributeName);
                if(orderAttribute == null)
                    throw new PlException("Order attribute with name " + attributeName + " not found.");
                boolean ascend = !"descend".equals(getNamedChildTextNodeValue(child, "direction"));
                orderAttributes.add(new OrderEntry(orderAttribute, ascend));

                child = getNextNamedSiblingElement(child, "order-attribute");
            }

            if(orderAttributes.size() > 0)
            {
                am.setOrderAttributes((OrderEntry[])orderAttributes.toArray(new OrderEntry[0]));
            }

            fromClassMap.putAssociationMap(am);
        }
        else
        {
            if(fromClass == null)
                throw new PlException("Error in association definition. from-class is not specified.");
            else if(toClass == null)
                throw new PlException("Error in association definition. to-class is not specified.");
            else
                throw new PlException("Error in association definition. Target is not specified.");
        }
    }

    /**
     * Process idGenerator tag.
     */
    private void processIdGenerator(Element node, pl.sql.RelationalDatabase database) throws PlException
    {
        String name = getNamedChildTextNodeValue(node, "id-generator-name");
        String className = getNamedChildTextNodeValue(node, "id-generator-class");

        if(name == null || name.length() == 0)
            throw new PlException("ID generator name is not specified");
        if(className == null || className.length() == 0)
            throw new PlException("ID generator class name is not specified");
        boolean isSingleInstance = "true".equalsIgnoreCase(getNamedChildTextNodeValue(node, "single-instance"));

        Class idGeneratorClass = null;
        // Try to load ID generator class
        try
        {
            idGeneratorClass = Class.forName(className, true, classLoader);
        }
        catch(ClassNotFoundException e)
        {
            throw new PlException("ID generator class with name " + className + " not found");
        }

        // Get ID generator parameters
        java.util.Properties parameters = getParameters(node);

        // Put ID generator instance to the relational database
        database.putIdGenerator(name, idGeneratorClass, isSingleInstance, parameters);
    }

    /**
     * Create converter.
     */
    private Converter getConverter(Element node) throws PlException
    {
        if(node == null)
            return ConverterFactory.getTrivialConverter();
        String name = getNamedChildTextNodeValue(node, "converter-name");
        if(name != null)
        {
            // Try to get converter from the PersistenceManagerFactory
            Converter converter = broker.getConverter(name);
            if(converter != null)
                return converter;
        }

        // Create new converter
        String className = getNamedChildTextNodeValue(node, "class-name");
        if(className == null)
            throw new PlException("Converter class name is not specified");
        Converter converter = ConverterFactory.getConverter(className, classLoader, getParameters(node));
        if(name != null)
        {
            // Put converter to the PersistenceManagerFactory
            broker.putConverter(name, converter);
        }

        return converter;
    }
}
