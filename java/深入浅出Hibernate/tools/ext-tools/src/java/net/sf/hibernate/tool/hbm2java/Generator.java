//$Id: Generator.java,v 1.3.2.3 2004/07/22 21:26:28 maxcsaucdk Exp $
package net.sf.hibernate.tool.hbm2java;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import net.sf.hibernate.util.StringHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;


/**
 *
 */
public class Generator {

     private Log log = LogFactory.getLog(Generator.class);

     private String rendererClass = "net.sf.hibernate.tool.hbm2java.BasicRenderer";
     private String baseDirName = "generated";
     private String packageName = null;
     private String suffix = StringHelper.EMPTY_STRING;
     private String prefix = StringHelper.EMPTY_STRING;
     private String extension = "java";
     private boolean lowerFirstLetter = false;

	private Properties params = new Properties();

     /**
      * Constructs a new Generator using the defaults.
      */
     public Generator () {}

     /**
      * Constructs a new Generator, configured from XML.
      */
     public Generator(Element generateElement) throws Exception {
         String value = null;

         // set rendererClass field
         if ((this.rendererClass = generateElement.getAttributeValue("renderer")) == null) {
             throw new Exception("attribute renderer is required.");
         }

         // set dirName field
         if ((value = generateElement.getAttributeValue("dir")) != null) {
             this.baseDirName = value;
         }

         // set packageName field
         this.packageName = generateElement.getAttributeValue("package");
         
         // set prefix
         if ((value = generateElement.getAttributeValue("prefix")) != null) {
             this.prefix = value;
         }

         // set suffix
         if ((value = generateElement.getAttributeValue("suffix")) != null) {
             this.suffix = value;
         }

         // set extension
         if ((value = generateElement.getAttributeValue("extension")) != null) {
             this.extension = value;
         }

         // set lowerFirstLetter
         value = generateElement.getAttributeValue("lowerFirstLetter");
         this.lowerFirstLetter = Boolean.valueOf(value).booleanValue();

         params = new Properties();
         Iterator iter = generateElement.getChildren("param").iterator();
         while( iter.hasNext() ) {
         	Element childNode = (Element) iter.next();
         	params.setProperty(
         			childNode.getAttributeValue("name"),
         			childNode.getText()
         	);
         }
         
     }

     /**
      *
      */
     public void generate(Map classMappingsCol) throws Exception {
     	log.info("Generating " + classMappingsCol.size() + " in " + getBaseDirName());
         Renderer renderer = (Renderer) Class.forName(this.rendererClass).newInstance();
         
         /** Configure renderer */
         renderer.configure(params);
         
         Map components = new HashMap();
		 /** Running through actual classes */
         for (Iterator classMappings = classMappingsCol.values().iterator(); classMappings.hasNext();) {
             ClassMapping classMapping = (ClassMapping) classMappings.next();
             write(classMapping, classMappingsCol, renderer);
             components.putAll(classMapping.getComponents());
         }
         /** Running through components */
         for (Iterator cmpMappings = components.values().iterator(); cmpMappings.hasNext();) {
             ClassMapping mapping = (ClassMapping) cmpMappings.next();
  			write(mapping, classMappingsCol, renderer);
         }
     }	

    /**
     *
     */
    private void write(ClassMapping classMapping, Map class2classmap, Renderer renderer) throws Exception {
        String saveToPackage = renderer.getSaveToPackage(classMapping);
        String saveToClassName = renderer.getSaveToClassName(classMapping);
        File dir = this.getDir(saveToPackage);
        File file = new File(dir, this.getFileName(saveToClassName));
        log.debug("Writing " + file);

        PrintWriter writer = new PrintWriter(new FileOutputStream(file));

        renderer.render(getPackageName(saveToPackage), getName(saveToClassName), classMapping, class2classmap, writer);
        writer.close();
    }

    /**
     *
     */
    private String getFileName(String className) {
        return this.getName(className) + "." + this.extension;
    }

    /**
     *
     */
    private String getName(String className) {
        String name = null;

        if (this.lowerFirstLetter) {
            name = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
        } else {
            name = className;
        }

        return this.prefix+ name + this.suffix;
    }

    private String getPackageName(String packageName) {
        if (this.packageName == null) {
            return packageName==null ? StringHelper.EMPTY_STRING : packageName;
        } else {
            return this.packageName;
        }
    }
    /**
     *
     */
    private File getDir(String packageName) throws Exception {
        File baseDir = new File(this.baseDirName);
        File dir = null;

        String p = getPackageName(packageName);

        dir = new File(baseDir, p.replace(StringHelper.DOT, File.separatorChar));

        // if the directory exists, make sure it is a directory
        if (dir.exists()) {
            if (!dir.isDirectory()) {
                throw new Exception("The path: " + dir.getCanonicalPath() + " exists, but is not a directory");
            }
        } // else make the directory and any non-existent parent directories
        else {
            if (!dir.mkdirs()) {
                throw new Exception("unable to create directory: " + dir.getCanonicalPath());
            }
        }

        return dir;
    }

    public String getBaseDirName() {
        return baseDirName;
    }

    public void setBaseDirName(String baseDirName) {
        if (baseDirName != null) {
            this.baseDirName = baseDirName;
        }
    }

}
