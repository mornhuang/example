/*
 * Created on 12-10-2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.sf.hibernate.tool.hbm2java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.hibernate.util.StringHelper;

import org.apache.commons.lang.StringUtils;

/**
 * @author MAX
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class JavaTool {

	/** 
	 * Returns "package packagename;" where packagename is either the declared packagename,
	 * or the one provide via meta attribute "generated-class".
	 * 
	 * Returns "// default package" if no package declarition available.
	 *  
	 * @param cm
	 * @return
	 */
	public String getPackageDeclaration(ClassMapping cm) {
	   if (cm.getGeneratedPackageName()!=null ) {
			 return "package " + cm.getGeneratedPackageName() + ";";
		   } else {        
			  return "// default package";
		  }
	  }

	/**
	 * Returns the true name for the given fields class name. By true name is
	 * that it will return the Proxy for the class name if the class was
	 * defined with a proxy attribute.
	 * 
	 * If the Field has an <meta attribute="property-type"></meta> then that
	 * will overrule any other information.
	 * 	  
	 * @param field class name that we use to serach in class2classmap
	 * @param class2classmap a map from classname to classmappings
	 * @return String return either name or the proxy name of the classmap
	 */
	static public String getTrueTypeName(FieldProperty field, Map class2classmap) {
		String name =
			(field.getClassType() != null)
				? field.getClassType().getFullyQualifiedName()
				: field.getFullyQualifiedTypeName();
	
		if(field.getMeta("property-type")!=null) {
			name = field.getMetaAsString("property-type");
		}				
		ClassMapping cmap = (ClassMapping) class2classmap.get(name);
	
		if (cmap != null) {
			if (cmap.getProxy() != null) {
				return cmap.getProxy();
			}
		}
		return name;
	}

	public String getTrueTypeName(ClassName cn, Map class2classmap) {
		String name = cn.getFullyQualifiedName();
		ClassMapping cmap = (ClassMapping) class2classmap.get(name);
	
		if (cmap != null) {
			if (cmap.getProxy() != null) {
				return cmap.getProxy();
			}
		}
		return name;
	}

	/**
	 * Returns the last part of type if it is in the set of imports.
	 * e.g. java.util.Date would become Date, if imports contains 
	 * java.util.Date.
	 * 
	 * @param type
	 * @param imports
	 * @return String
	 */
	static public String shortenType(String type, Set imports) {
	    String result = type;
	    if( imports.contains(type) ) {
	      result = type.substring( type.lastIndexOf(StringHelper.DOT)+1 );
	    } 
	    else if( type.endsWith("[]") ) {
	        result = shortenType( type.substring(0, type.length()-2), imports ) + "[]";    
	    } else if (type.startsWith("java.lang.")) {
	        result = type.substring("java.lang.".length());   
	    }	  
	    
	    return result;
	}

	/**
	 * Convert string into something that can be rendered nicely into a javadoc
	 * comment.
	 * Prefix each line with a star ('*').
	 * @param string
	 */
	public String toJavaDoc(String string, int indent) {
	    StringBuffer result = new StringBuffer();
	    
	    if(string!=null) {
	        String[] lines = StringUtils.split(string, "\n\r\f");
	        for (int i = 0; i < lines.length; i++) {
	            String docline = " * " + lines[i] + "\n";
	            result.append(StringUtils.leftPad(docline, docline.length() + indent));
	        }
	    }
	    
	    return result.toString();
	}
	
	public boolean hasExtends(ClassMapping cmap) { return getExtends(cmap)!=null; } 
	
		public String getExtends(ClassMapping cmap) {
			String extendz = null;
			
			if(cmap.isInterface()) {
				if(cmap.getSuperClassMapping()!= null && cmap.getSuperClassMapping().isInterface()) {
					extendz += cmap.getSuperClassMapping().getName();					
				}
				if(cmap.getMeta(extendz)!=null) {
					if(extendz!=null) {
						extendz += ",";
					}
					extendz = cmap.getMetaAsString("extends");
				}
				
			} else if(cmap.getSuperClass()!=null) {
				if(cmap.getSuperClassMapping()!=null && cmap.getSuperClassMapping().isInterface()) {
					// class cannot extend it's superclass because the superclass is marked as an interface
				} else {
					extendz = cmap.getSuperClass();
				}
			} else if (cmap.getMeta("extends")!=null) {
				extendz = cmap.getMetaAsString("extends");
			}
		
			return extendz;
		
		}
	
		public boolean hasImplements(ClassMapping cmap) {
			return getImplements(cmap)!=null;
		}
	
		public String getImplements(ClassMapping cmap) {
			List interfaces = new ArrayList();
		
			//			implement proxy, but NOT if the proxy is the class it self!
			if (cmap.getProxy()!=null && 
					( !cmap.getProxy().equals( cmap.getFullyQualifiedName() ) )) {
				interfaces.add(cmap.getProxy());
			}
			
			if(!cmap.isInterface()) {
				if(cmap.getSuperClassMapping()!=null && cmap.getSuperClassMapping().isInterface()) {
					interfaces.add(cmap.getSuperClassMapping().getClassName().getFullyQualifiedName());
				}
				if (cmap.getMeta("implements")!=null) {
					interfaces.addAll(cmap.getMeta("implements"));
				}
				interfaces.add(Serializable.class.getName());
			} else { 
			   // interfaces can't implement suff
			} 
		
			
			if(interfaces.size()>0) {
				StringBuffer sbuf = new StringBuffer();
				for (Iterator iter = interfaces.iterator(); iter.hasNext();) {				
					sbuf.append(JavaTool.shortenType(iter.next().toString(), cmap.getImports()));
					if(iter.hasNext()) sbuf.append(",");
				} 
				return sbuf.toString();
			} else {
				return null;
			}
		}

	public String fieldsAsParameters(List fieldslist, ClassMapping classMapping, Map class2classmap) {
		StringBuffer buf = new StringBuffer();
		for(Iterator fields = fieldslist.iterator(); fields.hasNext();) {
					FieldProperty field = (FieldProperty) fields.next();
						buf.append(JavaTool.shortenType(JavaTool.getTrueTypeName(field, class2classmap), classMapping.getImports()) + " " + field.getFieldName());
						if(fields.hasNext()) {
						  buf.append(", ");
						}
				}
		return buf.toString();
	}
	
	public String fieldsAsArguments(List fieldslist, ClassMapping classMapping, Map class2classmap) {
			StringBuffer buf = new StringBuffer();
			for(Iterator fields = fieldslist.iterator(); fields.hasNext();) {
						FieldProperty field = (FieldProperty) fields.next();
							buf.append(field.getFieldName());
							if(fields.hasNext()) {
							  buf.append(", ");
							}
					}
			return buf.toString();
		}
	
	public String genImports(ClassMapping classMapping) {
		StringBuffer buf = new StringBuffer();
		
		for ( Iterator imports = classMapping.getImports().iterator(); imports.hasNext(); ) {
				buf.append("import " + imports.next() + ";\n");
		}
		

	    List imports = classMapping.getMeta("extra-import");
	    if (imports != null) {
		    for (Iterator it = imports.iterator(); it.hasNext(); ) {
			    String cname = it.next().toString();
			    buf.append("import " + cname + ";\n");
		    }		    
	    }
		return buf.toString();            
	}
}
