/*
 * Created on 12-10-2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sf.hibernate.tool.hbm2java;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * @author MAX
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class VelocityRenderer extends AbstractRenderer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.hibernate.tool.hbm2java.Renderer#render(java.lang.String,
	 *      java.lang.String, net.sf.hibernate.tool.hbm2java.ClassMapping,
	 *      java.util.Map, java.io.PrintWriter)
	 */
	public void render(String savedToPackage, String savedToClass,
			ClassMapping classMapping, Map class2classmap, PrintWriter writer) throws Exception {

		Properties p = new Properties();

		p.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
				"org.apache.velocity.runtime.log.SimpleLog4JLogSystem");

		p.setProperty("runtime.log.logsystem.log4j.category", "hbm2java");
		
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
				
		VelocityEngine ve = new VelocityEngine();				
		ve.init(p);
		
		VelocityContext context = new VelocityContext();

		context.put("savedToPackage", savedToPackage);
		context.put("savedToClass", savedToClass);
		context.put("clazz", classMapping);

		context.put("class2classmap", class2classmap);

		context.put("javaTool", new JavaTool());

		StringWriter sw = new StringWriter();
		
		// First run - writes to in-memory string
		ve.mergeTemplate(properties.getProperty("template", "pojo.vm"), "ISO-8859-1", context, sw);

		context.put("classimports", new JavaTool().genImports(classMapping));

		// Second run - writes to file (allows for placing imports correctly and optimized ;)
		ve.evaluate(context, writer, "hbm2java", sw.toString()); 
		
	}

}