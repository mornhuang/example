//$Id: Renderer.java,v 1.3 2004/03/29 18:07:16 maxcsaucdk Exp $
package net.sf.hibernate.tool.hbm2java;


import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;


public interface Renderer {
	
	/** Called with the optional list of properties from config.xml */ 
	public void configure(Properties properties);
	
    /** 
     * 
     * @param savedToPackage what package is this class placed in
     * @param savedToClass what classname does it really get
     * @param classMapping what classmapping is this for
     * @param class2classmap A complete map from classname to the classmapping
     * @param writer where we want the output
     * @throws Exception
     */    
	public void render(String savedToPackage, String savedToClass, ClassMapping classMapping, Map class2classmap, PrintWriter writer) throws Exception;
    
    /**
     * Called by the generator to determine the package name of the rendered class.
     * 
     * @param classMapping The class mapping of the generated class
     * @return the package name the class should be saved to
     */
    public String getSaveToPackage(ClassMapping classMapping);
    
    /**
     * Called by the generator to determine the class name of the rendered class.
     * 
     * @param classMapping The class mapping of the generated class
     * @return the class name the class should be saved to
     */
    public String getSaveToClassName(ClassMapping classMapping);
}






