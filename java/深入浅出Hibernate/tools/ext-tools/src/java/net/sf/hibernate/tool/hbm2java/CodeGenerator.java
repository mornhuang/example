/*
 * $Id: CodeGenerator.java,v 1.7.2.1 2004/07/22 21:26:28 maxcsaucdk Exp $
 */
package net.sf.hibernate.tool.hbm2java;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.MappingException;
import net.sf.hibernate.util.DTDEntityResolver;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;


/**
 *
 */
public class CodeGenerator {

    private static final Log log = LogFactory.getLog(CodeGenerator.class);
    
      public static void main(String[] args) {
          
          if(args.length==0) {
              System.err.println("No arguments provided. Nothing to do. Exit.");
              System.exit(-1);              
          }
        try {
            ArrayList mappingFiles = new ArrayList();
    
            SAXBuilder builder = new SAXBuilder(true);
            builder.setEntityResolver( new DTDEntityResolver() );
            
            builder.setErrorHandler( new ErrorHandler() {
    			public void error(SAXParseException error) {
    				log.error("Error parsing XML: " + error.getSystemId() + '(' + error.getLineNumber() + ')',error);
    			}
    			public void fatalError(SAXParseException error) { 
    				error(error); 
    			}
    			public void warning(SAXParseException error) {
    				log.warn("Warning parsing XML: " + error.getSystemId() + '(' + error.getLineNumber() + ')' );
    			}
            } );
            
            String outputDir = null;
    
            List generators = new ArrayList();
    
            MultiMap globalMetas = new MultiHashMap();
            // parse command line parameters
            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith("--")) {
    
                    if ( args[i].startsWith("--config=") ) {
                        // parse config xml file
                    	builder.setValidation(false);
                        Document document = builder.build( new File( args[i].substring(9) ) );
                        globalMetas = MetaAttributeHelper.loadAndMergeMetaMap(document.getRootElement(), null);
                        Iterator generateElements = document.getRootElement().getChildren("generate").iterator();
                        
                        while (generateElements.hasNext()) {
                            generators.add( new Generator( (Element) generateElements.next() ) );
                        }
                        builder.setValidation(true);
                    }
                    else if ( args[i].startsWith("--output=") ) {
                    	outputDir = args[i].substring(9);
                    }
                    	
                } 
                else {
                    mappingFiles.add( args[i] );
                }
            }
    
            // if no config xml file, add a default generator
            if (generators.size() == 0) {
                generators.add( new Generator() );
            }
    		
            HashMap classMappings = new HashMap();
            builder.setValidation(true);
    		for ( Iterator iter = mappingFiles.iterator(); iter.hasNext(); ) {
            	// parse the mapping file
				File file = new File( (String) iter.next() );
			
				Document document = builder.build( file);
            	
            	Element rootElement = document.getRootElement();
            	
            	org.jdom.Attribute a = rootElement.getAttribute("package");
            	String pkg = null;
            	if(a!=null) {
            		pkg = a.getValue();
            	}
                MappingElement me = new MappingElement(rootElement, null/**TODO-hbm2java: - should be config.xml**/);
            	Iterator classElements = rootElement.getChildren("class").iterator();
                MultiMap mm = MetaAttributeHelper.loadAndMergeMetaMap(rootElement, globalMetas);
                handleClass(pkg,me, classMappings, classElements, mm, false);
        	    
				classElements = rootElement.getChildren("subclass").iterator();
				handleClass(pkg,me,classMappings, classElements, mm, true);
				
				classElements = rootElement.getChildren("joined-subclass").iterator();
				handleClass(pkg,me,classMappings, classElements, mm, true);
    		}
    
            // generate source files
            for ( Iterator iterator = generators.iterator(); iterator.hasNext(); ) {
            	Generator g = (Generator) iterator.next();
            	g.setBaseDirName(outputDir);
                g.generate(classMappings);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

	private static void handleClass(String classPackage, MappingElement me, HashMap classMappings, Iterator classElements, MultiMap mm, boolean extendz) throws MappingException {
		while ( classElements.hasNext() ) {
			Element clazz = (Element) classElements.next();
			
			if(!extendz) {
		    	ClassMapping cmap = new ClassMapping(classPackage, clazz, me, mm);
		    	cmap.storeIn(classMappings);		    	
			} else {				
				String ex = clazz.getAttributeValue("extends");
				if(ex==null) {
					throw new MappingException("Missing extends attribute on <" + clazz.getName() + " name=" + clazz.getAttributeValue("name") + ">" );
				}
				ClassMapping superclass = (ClassMapping) classMappings.get(ex);
				if(superclass == null) {
					throw new MappingException("Cannot extend unmapped class " + ex);					
				}
				ClassMapping subclassMapping = new ClassMapping(classPackage, me, superclass.getClassName(), superclass, clazz, mm);
				subclassMapping.storeIn(classMappings);
			}
		}
	}
}






