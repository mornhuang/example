/*
 * Created on 25-03-2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package net.sf.hibernate.tool.hbm2java;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

/**
 * @author max
 */
public abstract class AbstractRenderer implements Renderer {

	Properties properties;
	
	public void configure(Properties props) {
		this.properties = props;
	}
	
	public String getFieldScope(FieldProperty field, String localScopeName, String defaultScope) {
		return field.getScope(localScopeName, defaultScope);
	}

    public String getPackageDeclaration(String savedToPackage, ClassMapping classMapping) {
        if (savedToPackage!=null && !savedToPackage.trim().equals("")) {
            return "package " + savedToPackage + ";";
        } else if ( classMapping.getGeneratedPackageName()!=null ) {
        	return "package " + classMapping.getGeneratedPackageName() + ";";
        }        
        return "";
    }

    protected void genPackageDelaration(String savedToPackage, ClassMapping classMapping, PrintWriter w) {
        String string = getPackageDeclaration(savedToPackage, classMapping);
        if(string.length()>0) {
            w.println(string);
        } else {
            w.println("// default package");
        }
    }

    public String getSaveToClassName(ClassMapping classMapping) {        
        return classMapping.getGeneratedName();
    }

    public String getSaveToPackage(ClassMapping classMapping) {
        return classMapping.getGeneratedPackageName();
    }

}
