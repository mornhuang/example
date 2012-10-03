//$Id: FieldProperty.java,v 1.1 2003/12/02 21:44:35 maxcsaucdk Exp $
package net.sf.hibernate.tool.hbm2java;

import java.util.Set;

import org.apache.commons.collections.MultiMap;
import org.jdom.Element;


public class FieldProperty extends MappingElement {
    
    /** the field name */
	private String fieldName = null;
	
    /** the property name */
	private String accessorName = null;
    
    /** true if this is part of an id */
	private boolean id = false;
    
    
	private boolean generated = false;
	private boolean nullable = true;
	private ClassName classType;
    private ClassName foreignClass;
    private Set foreignKeys;

    private ClassName implementationClassName;

    private ClassMapping parentClass;
        
    
    public FieldProperty(Element element, MappingElement parent, String name, ClassName type, boolean nullable, MultiMap metaattribs) {
        super(element,parent);
        initWith(name, type, type, nullable, id, false, null, null, metaattribs);
    }
    
    public FieldProperty(Element element, MappingElement parent, String name, ClassName type, boolean nullable, boolean id, boolean generated, MultiMap metaattribs) {
        super(element,parent);
        initWith(name, type, type, nullable, id, generated, null, null, metaattribs);
    }
    
    public FieldProperty(Element element, MappingElement parent, String name, ClassName type, ClassName implementationClassName, boolean nullable, ClassName foreignClass, Set foreignKeys, MultiMap metaattribs) {
        super(element,parent);
        initWith(name, type, implementationClassName, nullable, id, false, foreignClass, foreignKeys, metaattribs);
    }
    
    protected void initWith(String name, ClassName type, ClassName implementationClassName, boolean nullable, boolean id, boolean generated, ClassName foreignClass, Set foreignKeys, MultiMap metaattribs) {
        this.fieldName = name;
        setType(type);        
        this.nullable = nullable;
        this.id = id;
        this.generated = generated;
        this.implementationClassName = implementationClassName;
        this.accessorName = beancapitalize(name);
        this.foreignClass = foreignClass;
        this.foreignKeys = foreignKeys;
        setMetaAttribs(metaattribs);
        
    }
    
    /**
     * foo -> Foo
     * FOo -> FOo
     * 
     * @param name2
     * @return
     */
    private String beancapitalize(String fieldname) {
            if (fieldname == null || fieldname.length() == 0) {
                return fieldname;
            }
            
            if (fieldname.length() > 1 && Character.isUpperCase(fieldname.charAt(1)) &&
                    Character.isUpperCase(fieldname.charAt(0))){
                return fieldname;
            }
            char chars[] = fieldname.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return new String(chars);      
    }    
    
    public String getFieldName() {
        return this.fieldName;
    }
    
    public String getAccessorName() {
        return this.accessorName;
    }
    
    private String getGetterType() {
		return (getFullyQualifiedTypeName().equals("boolean") ) ? "is" : "get";
    }
    
    public String getFullyQualifiedTypeName() {
        return classType.getFullyQualifiedName();        
    }
    
    public boolean isIdentifier() {
        return id;
    }
    
    public boolean isNullable() {
        return nullable;
    }
    
    public boolean isGenerated() {
        return generated;
    }

    public String toString() {
        return getFullyQualifiedTypeName() + ":" + getFieldName();    
    }    
	
	/**
	 * Returns the classType. 
	 * @return ClassName
	 */
	public ClassName getClassType() {
		return classType;
	}
	
    private void setType(ClassName type) {
        this.classType = type;
    }
    

    /**
     * Returns the foreignClass.
     * @return ClassName
     */
    public ClassName getForeignClass() {
        return foreignClass;
    }

    /**
     * Sets the foreignClass.
     * @param foreignClass The foreignClass to set
     */
    public void setForeignClass(ClassName foreignClass) {
        this.foreignClass = foreignClass;
    }

    /**
     * Returns the foreignKeys.
     * @return Set
     */
    public Set getForeignKeys() {
        return foreignKeys;
    }

	/**
	 * Method getGetterSignature.
	 * @return String
	 */
	public String getGetterSignature() {
		return getGetterType() + getAccessorName() + "()";
	}

	public boolean isGeneratedAsProperty() {
		return getMetaAsBool("gen-property", true);
	}
    
    /**
     * 
     * @return  Return the implementation specific type for this property. e.g. java.util.ArrayList when the type is java.util.List;
     */
    public ClassName getImplementationClassName() {
        return implementationClassName;
    }

    /**
     * @return
     */
    public ClassMapping getParentClass() {
        return parentClass;
    }

	public String getScope(String localScopeName, String defaultScope) {
	    if (defaultScope==null) defaultScope = "private";
	    return ( getMeta(localScopeName)==null )? defaultScope : getMetaAsString(localScopeName);
	}
	
	public String getFieldScope() {
		return getScope("scope-field", "private");
	}
	
	public String getPropertyGetScope() {
			return getScope("scope-get", "public");
		}
	
	public String getPropertySetScope() {
				return getScope("scope-set", "public");
			}
}
