//$Id: ClassName.java,v 1.3 2003/12/04 21:42:17 maxcsaucdk Exp $
package net.sf.hibernate.tool.hbm2java;

import java.util.HashSet;
import java.util.Set;

import net.sf.hibernate.util.StringHelper;

/*
 * Represents a type/classname - both primitive and Class types.
 *  
 * @author MAX
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ClassName {
	
    static final Set PRIMITIVES = new HashSet();
    static {
		PRIMITIVES.add("byte");
		PRIMITIVES.add("short");
		PRIMITIVES.add("int");
		PRIMITIVES.add("long");		
		PRIMITIVES.add("float");
		PRIMITIVES.add("double");
		PRIMITIVES.add("char");
		PRIMITIVES.add("boolean");
	}
	
    public ClassName(String fqn) {
        initFullyQualifiedName(fqn);
    }
	    
	private String fullyQualifiedName = null;
	private String name = null;
	private String packageName = null;
    
    /** Two ClassName are equals if their fullyQualifiedName are the same/equals! */
	public boolean equals(Object other) {
		ClassName otherClassName = (ClassName) other;
		return otherClassName.fullyQualifiedName.equals(fullyQualifiedName);
	}

	public String getFullyQualifiedName() {
		return this.fullyQualifiedName;
	}

    /** returns the atomar name for a class. 
     * 
     *  java.util.Set -> "Set" */
	public String getName() {
		return this.name;
	}

    /** returns the package name for a class/type. 
     * 
     *  java.util.Set -> "java.util" and Foo -> ""
     * @return
     */
	public String getPackageName() {
		return this.packageName;
	}

	public boolean inJavaLang() {
		return "java.lang".equals(packageName);
	}

	public boolean inSamePackage(ClassName other) {
		return other.packageName == this.packageName
			|| (other.packageName != null && other.packageName.equals(this.packageName));
	}
	
    /** return true if this type is an array. Check is done by checking if the type ends with []. */
	public boolean isArray() {
		return fullyQualifiedName.endsWith("[]");
	}
		
	/**
	 * Type is primitive if the basename (fqn without []) is in the PRIMITIVE set.
	 * @return boolean
	 */
	public boolean isPrimitive() {
		String baseTypeName = StringHelper.replace(fullyQualifiedName, "[]", "");
		return PRIMITIVES.contains(baseTypeName);
	}

	
    /*
     * Initialize the class fields with info from a fully qualified name.
     */
	private void initFullyQualifiedName(String fqn) {
		this.fullyQualifiedName = fqn;
		if (!isPrimitive()) {
			if (fqn != null) {

				int lastDot = fqn.lastIndexOf(".");
				if (lastDot < 0) {
					name = fqn;
					packageName = null;
				} else {
					name = fqn.substring(lastDot + 1);
					packageName = fqn.substring(0, lastDot);
				}
			} else {
				name = fqn;
				packageName = null;
			}
		} else {
			name = fqn;
			packageName = null;
		}

	}

	public String toString() {
		return getFullyQualifiedName();
	}

}
