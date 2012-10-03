//$Id: ClassMapping.java,v 1.18.2.6 2004/07/22 21:26:28 maxcsaucdk Exp $
package net.sf.hibernate.tool.hbm2java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import net.sf.hibernate.CompositeUserType;
import net.sf.hibernate.UserType;
import net.sf.hibernate.type.PrimitiveType;
import net.sf.hibernate.type.Type;
import net.sf.hibernate.type.TypeFactory;
import net.sf.hibernate.util.ReflectHelper;
import net.sf.hibernate.util.StringHelper;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Attribute;
import org.jdom.Element;


public class ClassMapping extends MappingElement {
    
   static private Log log = LogFactory.getLog(ClassMapping.class);
    
   
   private ClassName name = null;
   private ClassName generatedName = null;
   private String superClass = null;
   private ClassMapping superClassMapping = null;
   private String proxyClass = null;
   private List fields = new ArrayList();
   private TreeSet imports = new TreeSet();
   private List subclasses = new ArrayList();
   private final Map components = new HashMap();
   private boolean mustImplementEquals = false;
   
   private boolean shouldBeAbstract = false;
         
   public ClassMapping(String classPackage, MappingElement parentElement, ClassName superClass, ClassMapping superClassMapping, Element classElement, MultiMap inheritedMeta) {
         this(classPackage, parentElement, superClass, classElement, inheritedMeta);
      
         this.superClassMapping = superClassMapping;
         
         if(this.superClassMapping!=null) {
             List l = this.superClassMapping.getAllFieldsForFullConstructor();
             for (Iterator iter = l.iterator(); iter.hasNext();) {
                FieldProperty element = (FieldProperty) iter.next();
                ClassName ct = element.getClassType();
                if(ct!=null) { // add imports for superclasses possible fields.
                 addImport(ct); 
                } else {
                 addImport(element.getFullyQualifiedTypeName());
                }
            }
         }
    }
   
   public ClassMapping(String classPackage, MappingElement parentElement, ClassName superClass, Element classElement, MultiMap inheritedMeta)  {
      super(classElement, parentElement);
      initWith(classPackage, superClass, classElement, false, inheritedMeta);
   }
   
   public ClassMapping(String classPackage, Element classElement, MappingElement parentElement, MultiMap inheritedMeta) {
       super(classElement, parentElement);
      initWith(classPackage, null, classElement, false, inheritedMeta);
   }
   
   public ClassMapping(String classPackage, Element classElement, MappingElement parentElement, boolean component, MultiMap inheritedMeta)  {
       super(classElement, parentElement);
      initWith(classPackage, null, classElement, component, inheritedMeta);
   }

    protected void initWith(String classPackage, ClassName mySuperClass, Element classElement, boolean component, MultiMap inheritedMeta) {
    
    	String fullyQualifiedName = classElement.getAttributeValue(component?"class":"name");
    
    	if(fullyQualifiedName.indexOf('.')<0 && classPackage!=null && classPackage.trim().length()>0) {
    		fullyQualifiedName = classPackage + "." + fullyQualifiedName;
    	}
    	log.debug("Processing mapping for class: " + fullyQualifiedName);
    
    	setMetaAttribs(MetaAttributeHelper.loadAndMergeMetaMap(classElement, inheritedMeta));
    
    	//    class & package names
    	name = new ClassName(fullyQualifiedName);
    	
    	if(getMeta("generated-class")!=null) {
    		generatedName = new ClassName(getMetaAsString("generated-class").trim());
    		shouldBeAbstract = true;
    		log.warn("Generating " + generatedName + " instead of " + name);   
    	} else {
    		generatedName = name;
    	}
    	
    	if(mySuperClass!=null) {
    		this.superClass = mySuperClass.getName();
    		addImport(mySuperClass); // can only be done AFTER this class gets its own name.
    	}
    	
      
      // get the properties defined for this class
      List propertyList = new ArrayList();
      propertyList.addAll( classElement.getChildren("property") );
      propertyList.addAll( classElement.getChildren("version") );
      propertyList.addAll( classElement.getChildren("timestamp") );
      propertyList.addAll( classElement.getChildren("key-property") );
      propertyList.addAll( classElement.getChildren("any"));
      propertyList.addAll( classElement.getChildren("parent"));
    
      // get all many-to-one associations defined for the class
      List manyToOneList = new ArrayList();
      manyToOneList.addAll( classElement.getChildren("many-to-one") );
      manyToOneList.addAll( classElement.getChildren("key-many-to-one") );
    
      Attribute att = classElement.getAttribute("proxy");
      if (att!=null) proxyClass = att.getValue();
    
      Element id = classElement.getChild("id");
    
      if (id != null) {
      	 propertyList.add(0, id);
      	// implementEquals();
      }
    
      // composite id
      Element cmpid = classElement.getChild("composite-id");
      if (cmpid != null) {
      	 
         String cmpname = cmpid.getAttributeValue("name");
         String cmpclass = cmpid.getAttributeValue("class");
         if ( cmpname==null || cmpname.equals(StringHelper.EMPTY_STRING) ) {
            //Embedded composite id
            //implementEquals();
            propertyList.addAll(0, cmpid.getChildren("key-property") );
            manyToOneList.addAll(0, cmpid.getChildren("key-many-to-one") );
         } 
         else {
         	implementEquals();
         	//Composite id class
            ClassMapping mapping = new ClassMapping(classPackage, cmpid, this, true, getMetaAttribs());
            MultiMap metaForCompositeid = MetaAttributeHelper.loadAndMergeMetaMap(cmpid, getMetaAttribs());
            mapping.implementEquals();
            ClassName classType = new ClassName(cmpclass);
            // add an import and field for this property
            addImport(classType);
            FieldProperty cmpidfield =  new FieldProperty(cmpid, this, cmpname, classType, false, true, false, metaForCompositeid);
            addFieldProperty(cmpidfield);
            components.put( mapping.getFullyQualifiedName(), mapping);
         }
      }
    
      // checked after the default sets of implement equals.
      if(getMetaAsBool("implement-equals")) {
      	implementEquals();
      } else if(getMeta("implement-equals")!=null && !getMetaAsBool("implement-equals")){
      	implementEquals(false);
      }
      
      // derive the class imports and fields from the properties
      for (Iterator properties = propertyList.iterator(); properties.hasNext();) {
         Element propertyElement = (Element) properties.next();
         
         MultiMap metaForProperty = MetaAttributeHelper.loadAndMergeMetaMap(propertyElement, getMetaAttribs());
         String propertyName = propertyElement.getAttributeValue("name");
         if ( propertyName == null || propertyName.trim().equals(StringHelper.EMPTY_STRING) ) {
            continue; //since an id doesn't necessarily need a name
         }
         
         // ensure that the type is specified
         String type = propertyElement.getAttributeValue("type");
         if (type == null && cmpid != null)  { // for composite-keys
           type = propertyElement.getAttributeValue("class");
         }
         if("timestamp".equals(propertyElement.getName())){
             type = "java.util.Date";
         }
         
         if("any".equals(propertyElement.getName())) {
             type = "java.lang.Object";
         }
         
         if("parent".equals(propertyElement.getName())) {
         	type = ((ClassMapping)this.getParentElement()).getFullyQualifiedName();
         }
         
         if ( type == null || type.trim().equals(StringHelper.EMPTY_STRING) ) {
            log.warn("property \"" + propertyName + "\" in class " + getName() + " is missing a type attribute");
            continue;
         }
         
         
         // handle in a different way id and properties...
         // ids may be generated and may need to be of object type in order to support
         // the unsaved-value "null" value.
         // Properties may be nullable (ids may not)
         if (propertyElement == id)  {
            Element generator = propertyElement.getChild("generator");
            String unsavedValue = propertyElement.getAttributeValue("unsaved-value");
            boolean mustBeNullable = ( unsavedValue != null && unsavedValue.equals("null") );
            boolean generated = !generator.getAttributeValue("class").equals("assigned");
            ClassName rtype = getFieldType(type, mustBeNullable, false);
            addImport(rtype);
            FieldProperty idField = new FieldProperty(propertyElement, this, propertyName, rtype, false, true, generated, metaForProperty);
            addFieldProperty(idField);
         } 
         else {
            String notnull = propertyElement.getAttributeValue("not-null");
            // if not-null property is missing lets see if it has been
            // defined at column level
            if(notnull == null) {
               Element column = propertyElement.getChild("column");
               if(column != null)
                  notnull = column.getAttributeValue("not-null");
            }
            boolean nullable = ( notnull == null || notnull.equals("false") );
         	boolean key = propertyElement.getName().startsWith("key-"); //a composite id property
            ClassName t = getFieldType(type,nullable,false);
            addImport(t);
            FieldProperty stdField =new FieldProperty(propertyElement, this, propertyName, t, nullable && !key, key, false, metaForProperty);
            addFieldProperty(stdField);
            
         }
      }
    
      // one to ones
      List onetooneList = classElement.getChildren("one-to-one");
      for ( Iterator onetoones = onetooneList.iterator(); onetoones.hasNext(); ) {
            Element onetoone = (Element) onetoones.next();
    		
            MultiMap metaForOneToOne = MetaAttributeHelper.loadAndMergeMetaMap(onetoone,getMetaAttribs());
            String propertyName = onetoone.getAttributeValue("name");
            
            // ensure that the class is specified
            String clazz = onetoone.getAttributeValue("class");
            if( StringUtils.isEmpty(clazz) ) {
                log.warn("one-to-one \"" + name + "\" in class " + getName() + " is missing a class attribute");
                continue;
            }
            ClassName cn = getFieldType(clazz);
            addImport(cn);
            FieldProperty fm =  new FieldProperty(onetoone, this, propertyName, cn, true, metaForOneToOne);
            addFieldProperty(fm);
            
      }
    
      // many to ones - TODO: consolidate with code above
      for ( Iterator manytoOnes = manyToOneList.iterator(); manytoOnes.hasNext(); ) {
         Element manyToOne = (Element) manytoOnes.next();
         
         MultiMap metaForManyToOne = MetaAttributeHelper.loadAndMergeMetaMap(manyToOne,getMetaAttribs());
         String propertyName = manyToOne.getAttributeValue("name");
         
         // ensure that the type is specified
         String type = manyToOne.getAttributeValue("class");
         if ( StringUtils.isEmpty(type) ) {
            log.warn("many-to-one \"" + propertyName + "\" in class " + getName() + " is missing a class attribute");
            continue;
         }
         ClassName classType = new ClassName(type);
         
         // is it nullable?
         String notnull = manyToOne.getAttributeValue("not-null");
         boolean nullable = ( notnull == null || notnull.equals("false") );
         boolean key = manyToOne.getName().startsWith("key-"); //a composite id property
          
         // add an import and field for this property
         addImport(classType); 
         FieldProperty f = new FieldProperty(manyToOne, this, propertyName, classType, nullable && !key, key, false, metaForManyToOne);
         addFieldProperty(f);
      }
    
      // collections
      doCollections(classPackage, classElement, "list", "java.util.List", "java.util.ArrayList", getMetaAttribs());
      doCollections(classPackage, classElement, "map", "java.util.Map", "java.util.HashMap", getMetaAttribs());
      doCollections(classPackage, classElement, "set", "java.util.Set", "java.util.HashSet", getMetaAttribs());
      doCollections(classPackage, classElement, "bag", System.getProperty("hbm2java.bag.interface","java.util.List"), "java.util.ArrayList", getMetaAttribs());
      doCollections(classPackage, classElement, "idbag", System.getProperty("hbm2java.idbag.interface","java.util.List"), "java.util.ArrayList", getMetaAttribs());
      doArrays(classElement, "array", getMetaAttribs());
      doArrays(classElement, "primitive-array", getMetaAttribs());
    
    
    
    
      //components
    
      for ( Iterator iter = classElement.getChildren("component").iterator(); iter.hasNext(); ) {
         Element cmpe = (Element) iter.next();
         MultiMap metaForComponent = MetaAttributeHelper.loadAndMergeMetaMap(cmpe, getMetaAttribs());
         String cmpname = cmpe.getAttributeValue("name");
         String cmpclass = cmpe.getAttributeValue("class");
         if ( cmpclass==null || cmpclass.equals(StringHelper.EMPTY_STRING) ) {
            log.warn("component \"" + cmpname + "\" in class " + getName() + " does not specify a class");
            continue;
         }
         ClassMapping mapping = new ClassMapping(classPackage, cmpe, this, true, getMetaAttribs());
         
         ClassName classType = new ClassName(cmpclass);
         // add an import and field for this property
         addImport(classType);
         FieldProperty ff =  new FieldProperty(cmpe, this, cmpname, classType, false, metaForComponent);
         addFieldProperty(ff);
         components.put( mapping.getFullyQualifiedName(), mapping );
      }
    
        
        //    subclasses (done last so they can access this superclass for info)
    
         for ( Iterator iter = classElement.getChildren("subclass").iterator(); iter.hasNext(); ) {
            Element subclass = (Element) iter.next();
            ClassMapping subclassMapping = new ClassMapping(classPackage, this, name, this,subclass, getMetaAttribs());
            addSubClass(subclassMapping);
         }
    
         for ( Iterator iter = classElement.getChildren("joined-subclass").iterator(); iter.hasNext(); ) {
            Element subclass = (Element) iter.next();
            ClassMapping subclassMapping = new ClassMapping(classPackage, this, name, this, subclass, getMetaAttribs());
			addSubClass(subclassMapping);
         }
         
         validateMetaAttribs();
}


    private void addFieldProperty(FieldProperty fieldProperty) {
        if(fieldProperty.getParentClass()==null) {
            fields.add(fieldProperty);
        } else {
            throw new IllegalStateException("Field " + fieldProperty + " is already associated with a class: " + fieldProperty.getParentClass());
        }
        
    }

   
   public void implementEquals() {
      implementEquals(true);
   }
   
   public void implementEquals(boolean f) {
   		mustImplementEquals = f;
   }
   
   public boolean mustImplementEquals() {
      return (!isInterface()) && mustImplementEquals;
   }
   
   public List getFields() {
      return fields;
   }
   
   public Set getImports() {
      return imports;
   }
   
   /** shorthand method for getClassName().getFullyQualifiedName() */
   public String getFullyQualifiedName() {
      return getClassName().getFullyQualifiedName();
   }
   
   /** shorthand method for getClassName().getName() */
   public String getName() {
      return getClassName().getName();
   }
  
   /** shorthand method for getClassName().getPackageName() */   
   public String getPackageName() {
        return getClassName().getPackageName();
   }
      
   public ClassName getClassName() {
   	return name;	
   }
   
   public String getGeneratedName() {
       return generatedName.getName();
   }
   
      
   public String getGeneratedPackageName() {
     return generatedName.getPackageName();   
   }

  
   
   public String getProxy() {
      return proxyClass;
   }
   
   public List getSubclasses() {
      return subclasses;
   }
   
   public String getSuperClass() {
      return superClass;
   }
   
   
   // We need a minimal constructor only if it's different from
   // the full constructor or the no-arg constructor.
   // A minimal construtor is one that lets
   // you specify only the required fields.
   public boolean needsMinimalConstructor() {   	
   	return (getAllFieldsForFullConstructor().size()!=getAllFieldsForMinimalConstructor().size()) &&
			getAllFieldsForMinimalConstructor().size()>0;            
   }
   
   public List getLocalFieldsForFullConstructor() {
          List result = new ArrayList();
            for(Iterator myFields = getFields().iterator(); myFields.hasNext();) {
                 FieldProperty field = (FieldProperty) myFields.next();
                 if(!field.isIdentifier() || (field.isIdentifier() && !field.isGenerated())) {
                     result.add(field);
                 }
             }       
       
          return result;
      }
   
   
    public List getFieldsForSupersFullConstructor() {
          List result = new ArrayList();
          if(getSuperClassMapping()!=null) {
              // The correct sequence is vital here, as the subclass should be
              // able to invoke the fullconstructor based on the sequence returned
              // by this method!
              result.addAll(getSuperClassMapping().getFieldsForSupersFullConstructor());
              result.addAll(getSuperClassMapping().getLocalFieldsForFullConstructor());
          }
       
          return result;
      }
         
   public List getLocalFieldsForMinimalConstructor() {
    List result = new ArrayList();
    for (Iterator myFields = getFields().iterator(); myFields.hasNext();) {
        FieldProperty field = (FieldProperty) myFields.next();
        if ((!field.isIdentifier() && !field.isNullable()) || 
             (field.isIdentifier() && !field.isGenerated())) {
            result.add(field);
        }
    }
    return result;
}

	public List getAllFields() {
		List result = new ArrayList();
		
		if(getSuperClassMapping()!=null) {
		 result.addAll(getSuperClassMapping().getAllFields());	
		} 
		result.addAll(getFields());
		
		return result;
	}
	
	
	public List getAllFieldsForFullConstructor() {
       
       List result = getFieldsForSupersFullConstructor();
       result.addAll(getLocalFieldsForFullConstructor());
       return result;
   }
   
     public List getFieldsForSupersMinimalConstructor() {
          List result = new ArrayList();
          if(getSuperClassMapping()!=null) {
              // The correct sequence is vital here, as the subclass should be
              // able to invoke the fullconstructor based on the sequence returned
              // by this method!
              result.addAll(getSuperClassMapping().getFieldsForSupersMinimalConstructor());
              result.addAll(getSuperClassMapping().getLocalFieldsForMinimalConstructor());
          }
       
          return result;
      }
      
    public List getAllFieldsForMinimalConstructor() {
       
         List result = getFieldsForSupersMinimalConstructor();
         result.addAll(getLocalFieldsForMinimalConstructor());
         return result;
       }
       
   public void addImport(ClassName className) {
      // if the package is java.lang or our own package don't add
      if ( !className.inJavaLang() && !className.inSamePackage(generatedName) && !className.isPrimitive()) {
          if(className.isArray()) {
            imports.add( className.getFullyQualifiedName().substring(0,className.getFullyQualifiedName().length()-2) ); // remove []
          } else {
              imports.add( className.getFullyQualifiedName() );
          }
      }
   }
   
   public void addImport(String className) {
       ClassName cn = new ClassName(className);
       addImport(cn);
       
   }
  
   
   public Map getComponents() {
      return components;
   }
   
   private void doCollections(String classPackage, Element classElement, String xmlName, String interfaceClass, String implementingClass, MultiMap inheritedMeta) {
      
   	String originalInterface = interfaceClass;
   	String originalImplementation = implementingClass;
      
      for (Iterator collections = classElement.getChildren(xmlName).iterator(); collections.hasNext();) {
      
         Element collection = (Element) collections.next();
         MultiMap metaForCollection = MetaAttributeHelper.loadAndMergeMetaMap(collection, inheritedMeta);
         String propertyName = collection.getAttributeValue("name");
         
			//		Small hack to switch over to sortedSet/sortedMap if sort is specified. (that is sort != unsorted)
			String sortValue = collection.getAttributeValue("sort");
			if (sortValue!=null && !"unsorted".equals(sortValue) && !"".equals(sortValue.trim())) {
				  if("map".equals(xmlName)) {
					  interfaceClass = SortedMap.class.getName();
					  implementingClass = TreeMap.class.getName();
				  } else if("set".equals(xmlName)) {
					  interfaceClass = SortedSet.class.getName();
					  implementingClass = TreeSet.class.getName();
				  }
			   } else {
			   		interfaceClass = originalInterface;
			   		implementingClass = originalImplementation;
			   }
        
		ClassName interfaceClassName = new ClassName(interfaceClass);
		ClassName implementationClassName = new ClassName(implementingClass);      	
         
         // add an import and field for this collection
         addImport(interfaceClassName);
          // import implementingClassName should only be 
          // added if the initialisaiton code of the field 
          // is actually used - and currently it isn't!
         //addImport(implementingClassName);
         
          
         ClassName foreignClass = null;
         Set foreignKeys = null;
         // Collect bidirectional data
         if(collection.getChildren("one-to-many").size()!=0) {
            foreignClass = new ClassName(collection.getChild("one-to-many").getAttributeValue("class"));
            
         } else if (collection.getChildren("many-to-many").size()!=0) {
            foreignClass = new ClassName(collection.getChild("many-to-many").getAttributeValue("class"));
         }

         // Do the foreign keys and import
         if (foreignClass != null) {
            // Collect the keys
            foreignKeys = new HashSet();
            foreignKeys.add(collection.getChild("key").getAttributeValue("column"));
            
            for(Iterator iter = collection.getChild("key").getChildren("column").iterator(); iter.hasNext();) {
                foreignKeys.add(((Element) iter.next()).getAttributeValue("name"));
            }
            
            //addImport(foreignClass);
         }
         FieldProperty cf = new FieldProperty(collection, this, propertyName, interfaceClassName, implementationClassName, false, foreignClass, foreignKeys, metaForCollection);
         addFieldProperty(cf);
         if (collection.getChildren("composite-element") != null) {
           for (Iterator compositeElements = collection.getChildren("composite-element").iterator(); compositeElements.hasNext(); ) {
             Element compositeElement = (Element) compositeElements.next();
             String compClass = compositeElement.getAttributeValue("class");
             
             try {
               ClassMapping mapping = new ClassMapping(classPackage, compositeElement, this, true, getMetaAttribs());
               ClassName classType = new ClassName(compClass);
               // add an import and field for this property
               addImport(classType);
               components.put( mapping.getFullyQualifiedName(), mapping );
             } catch (Exception e) {
               log.error("Error building composite-element " + compClass,e);
             }
           }
         }           
      }
   }
   
   private void doArrays(Element classElement, String type, MultiMap inheritedMeta) {
      for ( Iterator arrays = classElement.getChildren(type).iterator(); arrays.hasNext(); ) {
         Element array = (Element) arrays.next();
         MultiMap metaForArray = MetaAttributeHelper.loadAndMergeMetaMap(array,inheritedMeta);
         String role = array.getAttributeValue("name");
         String elementClass = array.getAttributeValue("element-class");
         if (elementClass==null) {
            Element elt = array.getChild("element");
            if (elt==null) elt = array.getChild("one-to-many");
            if (elt==null) elt = array.getChild("many-to-many");
            if (elt==null) elt = array.getChild("composite-element");
            if (elt==null) {
               log.warn("skipping collection with subcollections");
               continue;
            }
            elementClass = elt.getAttributeValue("type");
            if (elementClass==null) elementClass=elt.getAttributeValue("class");
         }
         ClassName cn = getFieldType(elementClass, false, true);
         
         addImport(cn);
         FieldProperty af = new FieldProperty( array, this, role, cn, false, metaForArray);
         addFieldProperty(af);
      }
   }
   
   private ClassName getFieldType(String hibernateType) {
      return getFieldType(hibernateType, false, false);
   }
   
   /**
    * Return a ClassName for a hibernatetype.
    * 
    * @param hibernateType Name of the hibernatetype (e.g. "binary")
    * @param needObject 
    * @param isArray if the type should be postfixed with array brackes ("[]")
    * @return
    */
   private ClassName getFieldType(String hibernateType, boolean mustBeNullable, boolean isArray) {
       String postfix = isArray ? "[]" : "";
      // deal with hibernate binary type
      ClassName cn = null;
      if ( hibernateType.equals("binary") ) {
          cn = new ClassName("byte[]" + postfix);          
          return cn;
      }
      else {
         Type basicType = TypeFactory.basic(hibernateType);
         if ( basicType!=null ) {
            
            if (
            (basicType instanceof PrimitiveType) &&
            !hibernateType.trim().equals( basicType.getReturnedClass().getName() ) &&
            !mustBeNullable
            ) {
               cn = new ClassName(( (PrimitiveType) basicType ).getPrimitiveClass().getName() + postfix);
               return cn;
            }
            else {
                cn = new ClassName(basicType.getReturnedClass().getName() + postfix);
                return cn;
            }
            
         }
         else {
         	// check and resolve correct type if it is an usertype
         	hibernateType = getTypeForUserType(hibernateType);
            cn = new ClassName(hibernateType + postfix);
            // add an import and field for this property
            addImport(cn);
            return cn;
         }
      }
   }
   
   /** Returns name of returnedclass if type is an UserType **/
	   private String getTypeForUserType(String type) {
		   Class clazz = null;
		   try {
			   clazz = ReflectHelper.classForName(type);
			
			   if (UserType.class.isAssignableFrom(clazz)) {
				   UserType ut = (UserType) clazz.newInstance();
				   log.debug("Resolved usertype: " + type + " to " + ut.returnedClass().getName());
				   String t= clazzToName(ut.returnedClass());
				   return t;
			   } 

			if (CompositeUserType.class.isAssignableFrom(clazz)) {
				CompositeUserType ut = (CompositeUserType) clazz.newInstance();
				log.debug("Resolved composite usertype: " + type + " to " + ut.returnedClass().getName());
				String t= clazzToName(ut.returnedClass());
				return t;
			} 

		   } catch (ClassNotFoundException e) {
			   log.warn("Could not find UserType: " + type + ". Using the type '" + type + "' directly instead. (" + e.toString() +")");
		   } catch (IllegalAccessException iae) {
			   log.warn("Error while trying to resolve UserType. Using the type '" + type + "' directly instead. (" + iae.toString() + ")");
		   } catch (InstantiationException e) {
			   log.warn("Error while trying to resolve UserType. Using the type '" + type + "' directly instead. (" + e.toString() + ")");
		   }

		   return type;

	   }

	   private String clazzToName(Class cl) {
		   String s = null;
		
		   if(cl.isArray()) {
			   s = clazzToName(cl.getComponentType()) + "[]";
		   } else {
			   s = cl.getName();
		   }
		
		   return s;
		
		
	   }

   
    /**
     * Returns the superClassMapping.
     * @return ClassMapping
     */
    public ClassMapping getSuperClassMapping() {
        return superClassMapping;
    }
    
    
    /**
     * Method shouldBeAbstract.
     * @return boolean
     */
    public boolean shouldBeAbstract() {
        return shouldBeAbstract;
    }
    
    // Based on some raw heuristics the following method validates the provided metaattribs.
    void validateMetaAttribs() {
        // Inform that "extends" is not used if this one is a genuine subclass
        if(getSuperClass()!=null && getMeta("extends")!=null) {
            log.warn("Warning: meta attribute extends='" + getMetaAsString("extends") + "' will be ignored for subclass " + name);
        }
            
    }  
    
	/**
 	* @see java.lang.Object#toString()
	 */
	public String toString() {
    	return "ClassMapping: " + name.getFullyQualifiedName();
	}


    public boolean isInterface() {
        return getMetaAsBool("interface");
    }

    /**
     * @param subclassMapping
     */
    public void addSubClass(ClassMapping subclassMapping) {
        subclasses.add(subclassMapping);
    }

	/**
	 * @return
	 */
	public String getScope() {
		String classScope = "public";
		if(getMeta("scope-class")!=null) {
			classScope = getMetaAsString("scope-class").trim();
		}
		return classScope;
	}

	/**
	 * @return
	 */
	public String getDeclarationType() {
		if(isInterface()) {
			return "interface";
		} else {
			return "class";
		}		
	}

	/**
	 * Return the modifers for this class.
	 * Adds "abstract" if class should be abstract (but not if scope contains abstract)
	 * TODO: deprecate/remove scope-class and introduce class-modifier instead
	 * @return
	 */
	public String getModifiers() {
		if(shouldBeAbstract() && (getScope().indexOf("abstract")==-1)) {
          return "abstract";
		} else {
		  return "";        	
		}		
	}

	public void addImport(Class clazz) {
		addImport(clazz.getName());		
	}

	/**
	 * @return
	 */
	public boolean isSuperInterface() {
		return getSuperClassMapping()==null?false:getSuperClassMapping().isInterface();
	}

	public void storeIn(Map cmap) {
		cmap.put(getFullyQualifiedName(), this);
		
		Iterator it = getSubclasses().iterator();
		while (it.hasNext()) {
			ClassMapping element = (ClassMapping) it.next();
			element.storeIn(cmap);
		}
		
	}
	
	
}







