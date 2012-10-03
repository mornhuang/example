//$Id: BasicRenderer.java,v 1.16.2.4 2004/05/24 19:04:10 maxcsaucdk Exp $
package net.sf.hibernate.tool.hbm2java;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.hibernate.util.StringHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BasicRenderer extends AbstractRenderer {
	
	static final protected int ORDINARY = 0;
	static final protected int BOUND = 1;
	static final protected int CONSTRAINT = 3;//any constraint properties are bound as well

	private static Log log = LogFactory.getLog(BasicRenderer.class);
	JavaTool javaTool = new JavaTool();
	
 	public void render(String savedToPackage, String savedToClass, ClassMapping classMapping, Map class2classmap, PrintWriter mainwriter) throws Exception {
 		
 		
        genPackageDelaration(savedToPackage, classMapping, mainwriter);
        mainwriter.println();
    
        // switch to another writer to be able to insert the actually
        // used imports when whole class has been rendered. 
        StringWriter strWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(strWriter);
        
    
        // class declaration
        if(classMapping.getMeta("class-description")==null) {
            writer.println("/** @author Hibernate CodeGenerator */");
        } else {
            writer.println("/** \n" + javaTool.toJavaDoc(classMapping.getMetaAsString("class-description"),0)  + "*/");   
        }
        
        String classScope = classMapping.getScope();
        String declarationType = classMapping.getDeclarationType();
        

        classMapping.addImport(Serializable.class);
        //String modifiers = classMapping.getModifiers();
        if(classMapping.shouldBeAbstract() && (classScope.indexOf("abstract")==-1)) {
            writer.print( "abstract " + classScope + " " + declarationType + " " + savedToClass );
        } else {
            writer.print( classScope +  " " + declarationType + " " + savedToClass );    
        }
    
        if(generateConcreteEmptyClasses()) {
        	writer.print(" extends " + getProperty("baseclass-prefix","Base") + classMapping.getName());
        } else {
        	if(javaTool.hasExtends(classMapping)) {
        		writer.print(" extends " + javaTool.getExtends(classMapping));
        	}
        }
        
		if(javaTool.hasImplements(classMapping)) {
			writer.print(" implements " + javaTool.getImplements(classMapping));
		}
        
        writer.println(" {");
        writer.println();
        
        // switch to another writer to be able to insert the 
        // veto- and changeSupport fields
        StringWriter strPropWriter = new StringWriter();
        PrintWriter propWriter = new PrintWriter(strPropWriter);

        String vetoSupport = null, changeSupport = null;
        int fieldTypes = 0;
        
        if(!generateConcreteEmptyClasses()) {
        	if(!classMapping.isInterface()) {
        		/*doFieldNameConstants(classMapping, propWriter);*/
        		doFields(classMapping, class2classmap, propWriter);
        		doConstructors(savedToClass, classMapping, class2classmap, propWriter);
        	}
        	
        	vetoSupport = makeSupportField("vetos", classMapping.getAllFields());
        	changeSupport = makeSupportField("changes", classMapping.getAllFields());    
        	fieldTypes = doFieldAccessors(classMapping, class2classmap, propWriter, vetoSupport, changeSupport);
        	
        	if(!classMapping.isInterface()) {
        		doSupportMethods(fieldTypes, vetoSupport, changeSupport, propWriter);
        		
        		doToString(classMapping, propWriter);
        		
        		doEqualsAndHashCode(savedToClass,classMapping, propWriter);
        		
        	}
        	if(classMapping.getMeta("class-code")!=null) {
        		propWriter.println("// The following is extra code specified in the hbm.xml files");
        		List extras = classMapping.getMeta("class-code");
        		Iterator iter = extras.iterator();
        		while (iter.hasNext()) {
        			String code = iter.next().toString();
        			propWriter.println(code);	
        		}
        		
        		propWriter.println("// end of extra code specified in the hbm.xml files");
        	}
        } else {
        	// only put in constructors when base-generation is active
        	doConstructors(savedToClass, classMapping, class2classmap, propWriter);
        }
        
     	propWriter.println("}");
	      
        //insert change and VetoSupport
        if(!classMapping.isInterface() && generateConcreteEmptyClasses()) {
        	doSupports(fieldTypes, classMapping, vetoSupport, changeSupport, writer);
        }
        
        writer.print(strPropWriter.toString());
        
        
        // finally write the imports
        doImports(classMapping, mainwriter);
        mainwriter.print(strWriter.toString());
        
        
    }

	private String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	/**
	 * Method doSupportMethods.
	 * @param fieldTypes
	 * @param vetoSupport
	 * @param changeSupport
	 * @param propWriter
	 */
	private void doSupportMethods(
		int fieldTypes,
		String vetoSupport,
		String changeSupport,
		PrintWriter writer) {
			if((fieldTypes&CONSTRAINT)==CONSTRAINT) {
				writer.println("    public void addVetoableChangeListener( VetoableChangeListener l ) {");
				writer.println("        "+vetoSupport+".addVetoableChangeListener(l);");
				writer.println("    }");
				writer.println("    public void removeVetoableChangeListener( VetoableChangeListener l ) {");
				writer.println("        "+vetoSupport+".removeVetoableChangeListener(l);");
				writer.println("    }");
				writer.println();
			}
			if((fieldTypes&BOUND)==BOUND) {
				writer.println("    public void addPropertyChangeListener( PropertyChangeListener l ) {");
				writer.println("        "+changeSupport+".addPropertyChangeListener(l);");
				writer.println("    }");
				writer.println("    public void removePropertyChangeListener( PropertyChangeListener l ) {");
				writer.println("        "+changeSupport+".removePropertyChangeListener(l);");
				writer.println("    }");
				writer.println();
			}
	}


	/**
	 * Method doSupports.
	 * @param vetoSupport
	 * @param changeSupport
	 * @param writer
	 */
	private void doSupports( int fieldTypes, 
											ClassMapping classMapping,
											String vetoSupport,
											String changeSupport,
											PrintWriter writer) {
			if((fieldTypes&CONSTRAINT)==CONSTRAINT) {
				writer.println( "    private VetoableChangeSupport "+vetoSupport+
					" = new VetoableChangeSupport(this);" );
				classMapping.getImports().add("java.beans.VetoableChangeSupport");
				classMapping.getImports().add("java.beans.PropertyVetoException");
				classMapping.getImports().add("java.beans.VetoableChangeListener");
			}
			if((fieldTypes&BOUND)==BOUND) {
				writer.println( "    private PropertyChangeSupport "+changeSupport+
					" = new PropertyChangeSupport(this);" );
				writer.println();
				classMapping.getImports().add("java.beans.PropertyChangeSupport");
				classMapping.getImports().add("java.beans.PropertyChangeListener");
			}
	}


	public void doConstructors(String savedToClass, ClassMapping classMapping, Map class2classmap, PrintWriter writer) {
		// full constructor
		List allFieldsForFullConstructor = classMapping.getAllFieldsForFullConstructor();
		
		writer.println("    /** full constructor */");
		String fullCons = "    public " + savedToClass + StringHelper.OPEN_PAREN;
		
		fullCons += javaTool.fieldsAsParameters(allFieldsForFullConstructor, classMapping, class2classmap);
		
		writer.println(fullCons + ") {");
		
		if(!generateConcreteEmptyClasses()) {
			//invoke super to initialize superclass...
			List supersConstructorFields = classMapping.getFieldsForSupersFullConstructor();
			if (!supersConstructorFields.isEmpty()) {
				writer.print("        super(");
				for (Iterator fields = supersConstructorFields.iterator(); fields.hasNext();) {
					FieldProperty field = (FieldProperty) fields.next();
					writer.print(field.getFieldName());
					if(fields.hasNext()) {
						writer.print(", ");
					}
				}
				writer.println(");");
			}
			
			// initialisation of localfields
			for(Iterator fields = classMapping.getLocalFieldsForFullConstructor().iterator(); fields.hasNext();) {
				FieldProperty field = (FieldProperty) fields.next();
				if(field.isGeneratedAsProperty()) {
					writer.println("        this." + field.getFieldName() + " = " + field.getFieldName() + ";");
				}
			}
		} else {
			// invoke base class constructor 
			List supersConstructorFields = classMapping.getAllFieldsForFullConstructor();
			if (!supersConstructorFields.isEmpty()) {
				writer.print("        super(");
				for (Iterator fields = supersConstructorFields.iterator(); fields.hasNext();) {
					FieldProperty field = (FieldProperty) fields.next();
					writer.print(field.getFieldName());
					if(fields.hasNext()) {
						writer.print(", ");
					}
				}
				writer.println(");");
			}
		}
		
		writer.println("    }");
		writer.println();
		
		// no args constructor (if fullconstructor had any arguments!)
		if (allFieldsForFullConstructor.size() > 0) {
		    writer.println("    /** default constructor */");
			writer.println("    public " + savedToClass + "() {");
			writer.println("    }");
			writer.println();
		}
		
		// minimal constructor (only if the fullconstructor had any arguments)
		if ((allFieldsForFullConstructor.size() > 0) && classMapping.needsMinimalConstructor()) {
		
		    List allFieldsForMinimalConstructor = classMapping.getAllFieldsForMinimalConstructor();
		    writer.println("    /** minimal constructor */"); 
		
		    String minCons = "    public " + savedToClass + "(";
		    for (Iterator fields = allFieldsForMinimalConstructor.iterator(); fields.hasNext();) {
		        FieldProperty field = (FieldProperty) fields.next();
		        minCons = minCons + JavaTool.shortenType(JavaTool.getTrueTypeName(field, class2classmap), classMapping.getImports()) + " " + field.getFieldName();
		        if (fields.hasNext()) {
		            minCons = minCons + ", ";
		        }
		    }
		
		    writer.println(minCons + ") {");
		    if(!generateConcreteEmptyClasses()) {
		    	// invoke super to initialize superclass...
		    	List supersMinConstructorFields = classMapping.getFieldsForSupersMinimalConstructor();
		    	if (!supersMinConstructorFields.isEmpty()) {
		    		writer.print("      super(");
		    		for (Iterator fields = supersMinConstructorFields.iterator(); fields.hasNext();) {
		    			FieldProperty field = (FieldProperty) fields.next();
		    			writer.print(field.getFieldName());
		    			if(fields.hasNext()) {
		    				writer.print(StringHelper.COMMA_SPACE);
		    			}
		    		}
		    		writer.println(");");
		    	}
		    	
		    	// initialisation of localfields
		    	for (Iterator fields = classMapping.getLocalFieldsForMinimalConstructor().iterator(); fields.hasNext();) {
		    		FieldProperty field = (FieldProperty) fields.next();
		    		if(field.isGeneratedAsProperty()) {
		    			writer.println("        this." + field.getFieldName() + " = " + field.getFieldName() + ";");
		    		}
		    	}
		    } else {
		    	List supersMinConstructorFields = classMapping.getAllFieldsForMinimalConstructor();
			    if (!supersMinConstructorFields.isEmpty()) {
			    	writer.print("      super(");
			    	for (Iterator fields = supersMinConstructorFields.iterator(); fields.hasNext();) {
			    		FieldProperty field = (FieldProperty) fields.next();
			    		writer.print(field.getFieldName());
			    		if(fields.hasNext()) {
			    			writer.print(StringHelper.COMMA_SPACE);
			    		}
			    	}
			    	writer.println(");");
			    }			    	
		    }
		    writer.println("    }");
		    writer.println();
		}
	}

   /* public void doFieldNameConstants(ClassMapping classMapping, PrintWriter writer) {
        // fields
        for ( Iterator fields = classMapping.getFields().iterator(); fields.hasNext(); ) {
            FieldProperty field = (FieldProperty) fields.next();

            if(field.isGeneratedAsProperty()) {
                String fieldName = "    "+"public static String FIELD_"+field.getFieldName().toUpperCase()
                    +" = \""+classMapping.getFullyQualifiedName()+"."+field.getFieldName()+"\";";
                writer.println(fieldName);
            }
        }
        writer.println();
    }*/


	private boolean generateConcreteEmptyClasses() {
		return isPropertySet("generate-concrete-empty-classes");
	}

	public void doFields(ClassMapping classMapping, Map class2classmap, PrintWriter writer) {
		// fields
		if(!classMapping.isInterface()) {
			if(classMapping.isSuperInterface()) {
				doFields(classMapping.getAllFields(), classMapping.getImports(), class2classmap, writer); 
			}
		}
		List fieldList = classMapping.getFields();
		Set imports = classMapping.getImports();
		doFields(fieldList, imports, class2classmap, writer);
	}

	private void doFields(List fieldList, Set imports, Map class2classmap, PrintWriter writer) {
		for ( Iterator fields = fieldList.iterator(); fields.hasNext(); ) {
		    FieldProperty field = (FieldProperty) fields.next();
		    
		    if(field.isGeneratedAsProperty()) {
		    String fieldScope = getFieldScope(field, "scope-field", "private");
		    writer.println( 
		    	"    /** " + 
		    	( field.isNullable() && !field.isIdentifier() ? "nullable " : StringHelper.EMPTY_STRING ) +
		    	( field.isIdentifier() ? "identifier" : "persistent" )
		    	+ " field */");
		    
			writer.print(
		    	"    " + fieldScope + " " + 
		    	JavaTool.shortenType( JavaTool.getTrueTypeName(field, class2classmap), imports ) + 
		    	' ' + 
		    	field.getFieldName());
                
            if(field.getMeta("default-value")!=null) {
                writer.print(" = " + field.getMetaAsString("default-value"));
            }
            writer.println(';');
		    }
			writer.println();
		}
	}

	public void doEqualsAndHashCode(String savedToClass, ClassMapping classMapping, PrintWriter writer) {
		if(classMapping.getSuperClassMapping()!=null) {
			for (Iterator fields = classMapping.getFields().iterator(); fields.hasNext();) {
				FieldProperty field = (FieldProperty) fields.next();
				if(field.getMetaAsBool("use-in-equals")) {
					log.warn("use-in-equals for property " + field.getFieldName() + " in " + classMapping.getName() + " will be ignored since it is in a subclass.  \"There is simply no way to extend an instantiable class and add an aspect while preserving the equals() contract\" -- Bloch.");
				}
			}			
		} else {
			if ( classMapping.mustImplementEquals() ) {
				classMapping.getImports().add("org.apache.commons.lang.builder.EqualsBuilder");
				classMapping.getImports().add("org.apache.commons.lang.builder.HashCodeBuilder");
				writer.println("    public boolean equals(Object other) {");
				writer.println("        if ( (this == other ) ) return true;"); // == identity
				writer.println("        if ( !(other instanceof " + savedToClass + ") ) return false;"); // same class ?
				writer.println("        " + savedToClass + " castOther = (" + savedToClass + ") other;"); 
				writer.println("        return new EqualsBuilder()");
				int usedFields = 0;
				List idFields = new ArrayList();
				for (Iterator fields = classMapping.getAllFields().iterator(); fields.hasNext();) {
					FieldProperty field = (FieldProperty) fields.next();
					if(field.getMetaAsBool("use-in-equals")) {
						writer.println("            .append(this." + field.getGetterSignature() + ", castOther." + field.getGetterSignature() + StringHelper.CLOSE_PAREN);		    									
						usedFields++;
					}
					if(field.isIdentifier()) {
						idFields.add(field);
					}		    		
				}
				if(usedFields==0) {
					log.warn("No properties has been marked as being used in equals/hashcode for " + classMapping.getName() + ". Using object identifier which is RARELY safe to use! See http://hibernate.org/109.html");
					for (Iterator fields = idFields.iterator(); fields.hasNext();) {
						FieldProperty field = (FieldProperty) fields.next();
						writer.println("            .append(this." + field.getGetterSignature() + ", castOther." + field.getGetterSignature() + StringHelper.CLOSE_PAREN);
					}		    		
				}
				writer.println("            .isEquals();");
				
				writer.println("    }");
				writer.println();
				
				writer.println("    public int hashCode() {");
				writer.println("        return new HashCodeBuilder()");
				
				for (Iterator fields = classMapping.getAllFields().iterator(); fields.hasNext();) {
					FieldProperty field = (FieldProperty) fields.next();
					if(field.getMetaAsBool("use-in-equals")) {
						writer.println("            .append(" + field.getGetterSignature() + StringHelper.CLOSE_PAREN);
					}
				}
				if(usedFields==0) {
					for (Iterator fields = idFields.iterator(); fields.hasNext();) {
						FieldProperty field = (FieldProperty) fields.next();
						writer.println("            .append(" + field.getGetterSignature() + StringHelper.CLOSE_PAREN);
					}		    		
				}
				
				writer.println("            .toHashCode();");
				writer.println("    }");
				writer.println();
			}
		}
	}

	public void doToString(ClassMapping classMapping, PrintWriter writer) {
		if(classMapping.getMetaAsBool("implement-tostring",true)) {
		classMapping.addImport("org.apache.commons.lang.builder.ToStringBuilder");	
		writer.println("    public String toString() {");
		writer.println("        return new ToStringBuilder(this)");
		for (Iterator fields = classMapping.getAllFields().iterator(); fields.hasNext();) {
			FieldProperty field = (FieldProperty) fields.next();
			// If nothing is stated about id then include it in toString()
			if(field.isIdentifier() && field.getMeta("use-in-tostring")==null) {
				writer.println("            .append(\"" + field.getFieldName() + "\", " + field.getGetterSignature() + ")");
			} else if(field.getMetaAsBool("use-in-tostring")) {
				writer.println("            .append(\"" + field.getFieldName() + "\", " + field.getGetterSignature() + ")");
			}
		}
		writer.println("            .toString();");
		writer.println("    }");
		writer.println();
		}
	}
	
	static Map primitiveToObject = new HashMap();
	{
	  primitiveToObject.put("char", "Character");
	  
	  primitiveToObject.put("byte", "Byte");
	  primitiveToObject.put("short", "Short");
	  primitiveToObject.put("int",  "Integer");
	  primitiveToObject.put("long", "Long");
	  
	  primitiveToObject.put("boolean", "Boolean");
	  
	  primitiveToObject.put("float", "Float");
	  primitiveToObject.put("double", "Double");
	    	
	}
	
    public int doFieldAccessors(ClassMapping classMapping, 
    											Map class2classmap, 
    											PrintWriter writer,
    											String vetoSupport,
    											String changeSupport) {
    	int fieldTypes=ORDINARY;
    	
    	if(classMapping.isSuperInterface()) {
    		fieldTypes = doFields(classMapping, class2classmap, writer, vetoSupport, changeSupport, fieldTypes, classMapping.getAllFields());
    	}
    	List fieldz = classMapping.getFields();
        fieldTypes = doFields(classMapping, class2classmap, writer, vetoSupport, changeSupport, fieldTypes, fieldz);
        return fieldTypes;
        
    }

    private int doFields(ClassMapping classMapping, Map class2classmap, PrintWriter writer, String vetoSupport, String changeSupport, int fieldTypes, List fieldz) {
		// field accessors
        for (Iterator fields = fieldz.iterator(); fields.hasNext();) {
            FieldProperty field = (FieldProperty) fields.next();
            if(field.isGeneratedAsProperty()) {
        
            // getter
            String getAccessScope = getFieldScope(field, "scope-get", "public");
            
        
            if(field.getMeta("field-description")!=null) {
            writer.println("    /** \n" + javaTool.toJavaDoc(field.getMetaAsString("field-description"), 4) + "     */"); 
            }
            writer.print("    " + getAccessScope + " " + JavaTool.shortenType(JavaTool.getTrueTypeName(field, class2classmap),classMapping.getImports()) + " " + field.getGetterSignature());
 			if(classMapping.isInterface()) {
				writer.println(";");
 			} else {
				writer.println(" {");
			    writer.println("        return this." + field.getFieldName() + ";");
            	writer.println("    }");
			}
            writer.println();
        
            // setter
            int fieldType=0;
            if(field.getMeta("beans-property-type")!=null) {
            	String beansPropertyType = field.getMetaAsString("beans-property-type").trim().toLowerCase();
            	if(beansPropertyType.equals("constraint") ) {
            		fieldTypes = (fieldTypes | CONSTRAINT);
            		fieldType = CONSTRAINT;
            	}
            	else if(beansPropertyType.equals("bound") ) {
            		fieldTypes = (fieldTypes | BOUND);
            		fieldType = BOUND;
            	}
            }
            String setAccessScope = getFieldScope(field, "scope-set", "public");
            writer.print("    " + setAccessScope + " void set" + field.getAccessorName() + StringHelper.OPEN_PAREN + JavaTool.shortenType(JavaTool.getTrueTypeName(field, class2classmap),classMapping.getImports()) + " " + field.getFieldName() + ")");
            writer.print((fieldType&CONSTRAINT)==CONSTRAINT ? " throws PropertyVetoException ":"");
            if(classMapping.isInterface()) {
            	writer.println(";");
            } else {
				writer.println(" {");
				if((fieldType&CONSTRAINT)==CONSTRAINT || (fieldType&BOUND)==BOUND) {
					writer.println("        Object oldValue = "+getFieldAsObject(true, field)+";");
				}
				if((fieldType&CONSTRAINT)==CONSTRAINT) {
						
            			writer.println("        "+vetoSupport+".fireVetoableChange(\""+field.getFieldName()+"\",");
					    writer.println("                oldValue,");
            			writer.println("                "+getFieldAsObject(false, field)+");");
				}
				
            	writer.println("        this." + field.getFieldName() + " = " + field.getFieldName() + ";");
            	if((fieldType&BOUND)==BOUND) {
            			writer.println("        "+changeSupport+".firePropertyChange(\""+field.getFieldName()+"\",");
            			writer.println("                oldValue,");
            			writer.println("                "+getFieldAsObject(false, field)+");");
            	}
            	writer.println("    }");
            }
            writer.println();
            
            // add/remove'rs (commented out for now)
            /* 
            if(field.getForeignClass()!=null) { 
                ClassName foreignClass = field.getForeignClass();
                
                String trueforeign = getTrueTypeName(foreignClass, class2classmap);
                classMapping.addImport(trueforeign);
                
                // Try to identify the matching set method on the child.
                ClassMapping forignMap = (ClassMapping) class2classmap.get(foreignClass.getFullyQualifiedName());
                
                if(forignMap!=null) {
                  Iterator foreignFields = forignMap.getFields().iterator();
                  while (foreignFields.hasNext()) {
                    Field ffield = (Field) foreignFields.next();
                    if(ffield.isIdentifier()) {
                       log.debug("Trying to match " + ffield.getName() + " with " + field.getForeignKeys());   
                    }
                }
                  
                } else {
                  log.error("Could not find foreign class's mapping - cannot provide bidirectional setters!");   
                }
                
                String addAccessScope = getFieldScope(field, "scope", "scope-add");
                writer.println("    " + setAccessScope + " void add" + field.getAsSuffix() + StringHelper.OPEN + shortenType(trueforeign, classMapping.getImports()) + " a" + field.getName() + ") {");
                writer.println("        this." + getterType + field.getAsSuffix() + "().add(a" + field.getName() + ");");
                writer.println("        a" + field.getName() + ".setXXX(this);");
                writer.println("    }");
                writer.println();
            
            
            }
            */
        }
        }
		return fieldTypes;
	}

	public void doImports(ClassMapping classMapping, PrintWriter writer) {
            writer.println(javaTool.genImports(classMapping));
            writer.println();
    }
    
    protected String makeSupportField(String fieldName, List fieldList) {
		String suffix = "";
		boolean needSuffix = false;
		for (Iterator fields = fieldList.iterator(); fields.hasNext();) {
			String name = ((FieldProperty) fields.next()).getFieldName();
			if (name.equals(fieldName))
				needSuffix = true;
			suffix += name;
		}
		return needSuffix ? fieldName + "_" + suffix : fieldName;
	}
	
	private String getFieldAsObject(boolean prependThis, FieldProperty field) {
				ClassName type = field.getClassType();			
				if(type != null && type.isPrimitive() && !type.isArray()) {
						String typeName = (String) primitiveToObject.get(type.getName());
						typeName = "new "+typeName+"( ";
						typeName += prependThis ? "this." : "";
						return typeName+field.getFieldName()+" )";        			
				}
				return (prependThis?"this.":"")+field.getFieldName(); 
		}

	boolean isPropertySet(String key) {
		String val = properties.getProperty(key);
		return Boolean.valueOf(val).booleanValue();
	}	
}
