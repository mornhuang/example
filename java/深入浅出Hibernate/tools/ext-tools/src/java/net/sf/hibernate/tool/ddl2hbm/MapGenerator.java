/*
 * SchemaExport.java
 *
 * Created on November 1, 2002, 1:50 PM
 */
package net.sf.hibernate.tool.ddl2hbm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.hibernate.tool.hbm2java.ClassMapping;
import net.sf.hibernate.tool.hbm2java.ClassName;
import net.sf.hibernate.tool.hbm2java.Generator;
import net.sf.hibernate.util.StringHelper;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author  Administrator
 */
public class MapGenerator {
	private static Log logger = LogFactory.getLog(MapGenerator.class);
	private String generator;
	private String[] generatorParameters;
	private String packageName;
	private String catalog;
	private String schemaPattern;
	private String idType;
	private String[] tableTypes=new String[] { "TABLE", "VIEW", "SYNONYM", "ALIAS"};
	private File outputDirectory;
	private String idName;
	private DocumentBuilder docBuilder;
	private TransformerFactory tFactory;
	private boolean generateSource=true;
	private String baseClass;
	private org.jdom.input.DOMBuilder jdomBuilder;
	private boolean singleMapFile;
	private String[] tableNames;
	private String mappingFile;
	private boolean hibernateTypes;
	
	/** Creates a new instance of SchemaExport */
	public MapGenerator() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			docBuilder = dbf.newDocumentBuilder();
			tFactory = TransformerFactory.newInstance();
			jdomBuilder = new org.jdom.input.DOMBuilder(false);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	public void generate(Connection c) throws IOException, SQLException, Exception   {
		Document d = getMap(c);
		writeMapping(d);
		generateCode(d);
	}
	
	public Document getMap(Connection c) throws IOException, SQLException {
		Document hbm = createMappingDoc();
		logger.debug("reading tables from database");
		String[] tableNames = getTableNames();
		for (int i=0; tableNames != null && i < tableNames.length; i++) {
			addHibernateClass (
				c,
				tableNames[i],
				hbm.getDocumentElement()
			);
		}
		logger.debug("done reading tables from database");
		return hbm;
	}
	
	public void generateCode(Document doc) throws Exception {
		throw new UnsupportedOperationException("Use hbm2java directly instead");
//		Map map = new HashMap();
//		NodeList classes = doc.getElementsByTagName("class");
//		if (classes == null || classes.getLength() == 0) return;
//		for (int i=0; i < classes.getLength(); i++) {
//			Element classElement = (Element)classes.item(i);
//			ClassMapping cmap=null;
//			if (getBaseClass() != null) {
//				ClassName cName = new ClassName();
//				cName.setFullyQualifiedName(getBaseClass());
//				cmap = new ClassMapping(cName, jdomBuilder.build(classElement), new MultiHashMap());
//			}
//			else cmap = new ClassMapping(jdomBuilder.build(classElement), new MultiHashMap());
//			map.put(cmap.getCanonicalName(), cmap);
//		}
//		// generate source files
//		Generator g = new Generator();
//		g.setBaseDirName(getOutputDirectory().getAbsolutePath());
//		g.generate(map);
	}
	
	protected void writeMapping(Document mapping) throws IOException {
		java.lang.String pkgName =  packageName.replace(StringHelper.DOT, java.io.File.separatorChar) + File.separatorChar;
		File packageDir = new File(outputDirectory, pkgName);
		if (!packageDir.exists()) packageDir.mkdirs();
		if (isSingleMapFile() ) {
			File mappingFile = new File(packageDir, getMappingFile());
			FileOutputStream fos = new FileOutputStream(mappingFile);
			try {
				logger.debug("writing to file " + mappingFile);
				writeHbm(mapping, fos);
			}
			finally {
				fos.close();
			}
		}
		else {
			NodeList classes = mapping.getElementsByTagName("class");
			for (int i=0; i < classes.getLength();i++) {
				Element classElement = (Element)classes.item(i);
				Document hibDoc = createMappingDoc();
				classElement = (Element)hibDoc.importNode(classElement, true);
				hibDoc.getDocumentElement().appendChild(classElement);
				String fname = makeEntityName(classElement.getAttribute("table")) + ".hbm.xml";
				File mappingFile = new File(packageDir, fname);
				logger.debug("writing to file " + mappingFile);
				FileOutputStream fos = new FileOutputStream(mappingFile);
				try {
					writeHbm(hibDoc, fos);
				}
				finally {
					fos.close();
				}
			}
		}
		
		
	}
	
	protected void writeHbm(Node node, OutputStream os) {
		try {
			// Use a Transformer for output
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://hibernate.sourceforge.net/hibernate-mapping-2.0.dtd");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//Hibernate/Hibernate Mapping DTD 2.0//EN");
			DOMSource source = new DOMSource(node);
			StreamResult result = new StreamResult(os);
			transformer.transform(source, result);
		}
		catch (TransformerConfigurationException tce) {
			logger.error("Transformation configuration error", tce);
			throw new RuntimeException(tce.getMessage());
		}
		catch (TransformerException te) {
			// Error generated by the parser
			logger.error("Transformation error", te);
			throw new RuntimeException(te.getMessage());
		}
	}
	
	protected String getXml(Node node) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writeNode(node, baos);
			baos.close();
			return baos.toString();
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe.getMessage());
		}
	}
	
	protected void writeNode(Node node, OutputStream os) {
		try {
			// Use a Transformer for output
			Transformer transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(node);
			StreamResult result = new StreamResult(os);
			transformer.transform(source, result);
		}
		catch (TransformerConfigurationException tce) {
			logger.error("Transformation configuration error", tce);
			throw new RuntimeException(tce.getMessage());
		}
		catch (TransformerException te) {
			// Error generated by the parser
			logger.error("Transformation error", te);
			throw new RuntimeException(te.getMessage());
		}
	}
	
	
	protected Document createMappingDoc() {
		Document hbm = docBuilder.newDocument();
		Element hibernate = hbm.createElement("hibernate-mapping");
		hbm.appendChild(hibernate);
		//logger.debug(getXml(hbm));
		return hbm;
	}
	
	protected void addHibernateClass(Connection c, String tableName, Element mappingElement) throws SQLException  {
		logger.debug("adding hibernate class for table " + tableName);
		
		Document hbm = mappingElement.getOwnerDocument();
		Element classElement = hbm.createElement("class");
		String className = getPackageName() + "." + makeEntityName(tableName);
		classElement.setAttribute("name", className);
		classElement.setAttribute("table", tableName);
		if (getSchemaPattern() != null) {
			classElement.setAttribute("schema", getSchemaPattern());
		}
		
		List pkColumns = JDBCUtil.getPrimaryKeyColumns(c, getCatalog(), getSchemaPattern(), tableName);
		Set fkColumns = JDBCUtil.getForeignKeyColumns(c, getCatalog(), getSchemaPattern(), tableName);
		List columns = JDBCUtil.getTableColumns(c, getCatalog(), getSchemaPattern(), tableName);
		if (pkColumns.size() == 1) {
			addId(classElement, (JDBCUtil.Column)pkColumns.iterator().next());
		}
		else if (pkColumns.size() > 1) {
			addCompositeId(classElement, pkColumns, fkColumns);
		}
		addProperties(classElement, pkColumns, fkColumns, columns);
		
		logger.debug("class element: " + getXml(classElement));
		mappingElement.appendChild(classElement);
	}
	
	protected void addId(Element classElement, JDBCUtil.Column pkColumn) {
		Document hbm = classElement.getOwnerDocument();
		
		Element identifierElement = hbm.createElement("id");
		String idName = getIdName();
		if (idName == null) {
			idName = makeMemberName(pkColumn.name);
		}
		identifierElement.setAttribute("name", idName);
		identifierElement.setAttribute("column", pkColumn.name);
		if (getIdType() != null) {
			identifierElement.setAttribute("type", getIdType());
		}
		else {
			String type = (isHibernateTypes()) ? pkColumn.hibernateType.getName() : pkColumn.javaType.getName();
			identifierElement.setAttribute("type", type);
			if (pkColumn.sqlColumnLength > 0) {
				identifierElement.setAttribute("length", StringHelper.EMPTY_STRING + pkColumn.sqlColumnLength);
			}
		}
		addGenerator(identifierElement);
		classElement.appendChild(identifierElement);
		
	}
	
	protected void addCompositeId(Element classElement, Collection primaryKeys, Collection foreignKeys)  {
		Document hbm = classElement.getOwnerDocument();
		
		Element identifierElement=hbm.createElement("composite-id");
		//         ciElement.setAttribute("class", getPackageName() + "." + makeEntityName(tableName) + "Key");
		//String compositeId = this.getIdName();
		//if (compositeId == null) compositeId = makeMemberName(tableName) + "Id";
		//         ciElement.setAttribute("name", compositeId);
		for (Iterator it = primaryKeys.iterator(); it.hasNext();) {
			JDBCUtil.Column pkColumn = (JDBCUtil.Column)it.next();
			Element property = hbm.createElement("key-property");
			property.setAttribute("name", makeMemberName(pkColumn.name));
			
			if (getIdType() != null && foreignKeys.contains(pkColumn)) {
				// if the primary key column is also a foreign key, use the
				// specified keyType.
				property.setAttribute("type", getIdType());
			}
			else {
				property.setAttribute("column", pkColumn.name);
				String type = (isHibernateTypes()) ? pkColumn.hibernateType.getName() : pkColumn.javaType.getName();
				property.setAttribute("type", type);
				if (pkColumn.sqlColumnLength > 0) {
					property.setAttribute("length", StringHelper.EMPTY_STRING + pkColumn.sqlColumnLength);
				}
			}
			identifierElement.appendChild(property);
		}
		classElement.appendChild(identifierElement);
	}
	
	
	protected void addProperties(Element classElement, Collection pkColumns, Collection fkColumns, Collection columns) {
		Document hbm = classElement.getOwnerDocument();
		
		for (Iterator it = columns.iterator(); it.hasNext();) {
			JDBCUtil.Column column = (JDBCUtil.Column)it.next();
			if (!pkColumns.contains(column)) {
				Element propertyElement = hbm.createElement("property");
				propertyElement.setAttribute("name", makeMemberName(column.name));
				propertyElement.setAttribute("column", column.name);
				if (getIdType() != null && fkColumns.contains(column)) {
					propertyElement.setAttribute("type", getIdType());
				}
				else {
					String type = (isHibernateTypes()) ? column.hibernateType.getName() : column.javaType.getName();
					propertyElement.setAttribute("type", type);
					if (column.sqlColumnLength > 0) {
						propertyElement.setAttribute("length", StringHelper.EMPTY_STRING + column.sqlColumnLength);
					}
				}
				if (column.sqlNotNull) propertyElement.setAttribute("not-null", StringHelper.EMPTY_STRING + column.sqlNotNull);
				logger.debug("column info: " + getXml(propertyElement));
				classElement.appendChild(propertyElement);
			}
		}
	}
	
	
	protected void addGenerator(Element idElement) {
		Document hbm = idElement.getOwnerDocument();
		Element generator = hbm.createElement("generator");
		generator.setAttribute("class", getGenerator());
		if (generatorParameters != null) {
			for (int i=0; i < generatorParameters.length;i++) {
				Element param = hbm.createElement("param");
				param.appendChild(hbm.createTextNode(generatorParameters[i]));
				generator.appendChild(param);
			}
		}
		idElement.appendChild(generator);
	}
	
	protected String makeEntityName(String name) {
		String tmp = makeMemberName(name);
		tmp = tmp.substring(0,1).toUpperCase() + tmp.substring(1);
		return tmp;
	}
	
	protected String makeMemberName(String name) {
		String memberName = name.toLowerCase();
		int i;
		while ( (i = memberName.indexOf(StringHelper.UNDERSCORE) ) != -1 ) {
			java.lang.String tmp1 = memberName.substring(0, i);
			if (i+1 < memberName.length()) {
				tmp1 += memberName.substring(i+1, i+2).toUpperCase();
			}
			if (i+2 < memberName.length()) {
				tmp1 += memberName.substring(i+2);
			}
			memberName = tmp1;
		}
		return memberName;
	}
	
	
	
	
	
	/** Getter for property schemaPattern.
	 * @return Value of property schemaPattern.
	 */
	public java.lang.String getSchemaPattern() {
		return schemaPattern;
	}
	
	/** Setter for property schemaPattern.
	 * @param schemaPattern New value of property schemaPattern.
	 */
	public void setSchemaPattern(java.lang.String schemaPattern) {
		this.schemaPattern = schemaPattern;
	}
	
	/** Getter for property tablePattern.
	 * @return Value of property tablePattern.
	 */
	public java.lang.String[] getTableNames() {
		return tableNames;
	}
	
	/** Setter for property tablePattern.
	 * @param tablePattern New value of property tablePattern.
	 */
	public void setTableNames(java.lang.String[] tableNames) {
		this.tableNames = tableNames;
	}
	
	/** Getter for property catalog.
	 * @return Value of property catalog.
	 */
	public java.lang.String getCatalog() {
		return catalog;
	}
	
	/** Setter for property catalog.
	 * @param catalog New value of property catalog.
	 */
	public void setCatalog(java.lang.String catalog) {
		this.catalog = catalog;
	}
	
	/** Getter for property tableTypes.
	 * @return Value of property tableTypes.
	 */
	public java.lang.String[] getTableTypes() {
		return this.tableTypes;
	}
	
	/** Setter for property tableTypes.
	 * @param tableTypes New value of property tableTypes.
	 */
	public void setTableTypes(java.lang.String[] tableTypes) {
		this.tableTypes = tableTypes;
	}
	
	/** Getter for property packageName.
	 * @return Value of property packageName.
	 */
	public java.lang.String getPackageName() {
		return packageName;
	}
	
	/** Setter for property packageName.
	 * @param packageName New value of property packageName.
	 */
	public void setPackageName(java.lang.String packageName) {
		this.packageName = packageName;
	}
	
	/** Getter for property outputDirectory.
	 * @return Value of property outputDirectory.
	 */
	public java.io.File getOutputDirectory() {
		return outputDirectory;
	}
	
	/** Setter for property outputDirectory.
	 * @param outputDirectory New value of property outputDirectory.
	 */
	public void setOutputDirectory(java.io.File outputDirectory) {
		this.outputDirectory = outputDirectory;
		if (!outputDirectory.exists() || !outputDirectory.isDirectory()) {
			throw new RuntimeException("Invalid directory " + outputDirectory);
		}
	}
	
	/** Getter for property idName.
	 * @return Value of property idName.
	 */
	public java.lang.String getIdName() {
		return idName;
	}
	
	/** Setter for property idName.
	 * @param idName New value of property idName.
	 */
	public void setIdName(java.lang.String idName) {
		this.idName = idName;
	}
	
	/** Getter for property generator.
	 * @return Value of property generator.
	 */
	public java.lang.String getGenerator() {
		return generator;
	}
	
	/** Setter for property generator.
	 * @param generator New value of property generator.
	 */
	public void setGenerator(java.lang.String generator) {
		this.generator = generator;
	}
	
	/** Getter for property generatorParameters.
	 * @return Value of property generatorParameters.
	 */
	public java.lang.String[] getGeneratorParameters() {
		return this.generatorParameters;
	}
	
	/** Setter for property generatorParameters.
	 * @param generatorParameters New value of property generatorParameters.
	 */
	public void setGeneratorParameters(java.lang.String[] generatorParameters) {
		this.generatorParameters = generatorParameters;
	}
	
	/** Getter for property mappingFile.
	 * @return Value of property mappingFile.
	 */
	public java.lang.String getMappingFile() {
		return mappingFile;
	}
	
	/** Setter for property mappingFile.
	 * @param mappingFile New value of property mappingFile.
	 */
	public void setMappingFile(java.lang.String mappingFile) {
		this.mappingFile = mappingFile;
	}
	
	/** Getter for property idType.
	 * @return Value of property idType.
	 */
	public java.lang.String getIdType() {
		return idType;
	}
	
	/** Setter for property idType.
	 * @param idType New value of property idType.
	 */
	public void setIdType(java.lang.String idType) {
		this.idType = idType;
	}
	
	/** Getter for property baseClass.
	 * @return Value of property baseClass.
	 */
	public java.lang.String getBaseClass() {
		return baseClass;
	}
	
	/** Setter for property baseClass.
	 * @param baseClass New value of property baseClass.
	 */
	public void setBaseClass(java.lang.String baseClass) {
		this.baseClass = baseClass;
	}
	
	/** Getter for property generateSource.
	 * @return Value of property generateSource.
	 */
	public boolean isGenerateSource() {
		return generateSource;
	}
	
	/** Setter for property generateSource.
	 * @param generateSource New value of property generateSource.
	 */
	public void setGenerateSource(boolean generateSource) {
		this.generateSource = generateSource;
	}
	
	
	
	
	/** Getter for property singleMapFile.
	 * @return Value of property singleMapFile.
	 */
	public boolean isSingleMapFile() {
		return singleMapFile;
	}
	
	/** Setter for property singleMapFile.
	 * @param singleMapFile New value of property singleMapFile.
	 */
	public void setSingleMapFile(boolean singleMapFile) {
		this.singleMapFile = singleMapFile;
	}
	
	/** Getter for property hibernateTypes.
	 * @return Value of property hibernateTypes.
	 */
	public boolean isHibernateTypes() {
		return hibernateTypes;
	}
	
	/** Setter for property hibernateTypes.
	 * @param hibernateTypes New value of property hibernateTypes.
	 */
	public void setHibernateTypes(boolean hibernateTypes) {
		this.hibernateTypes = hibernateTypes;
	}
	
	
}






