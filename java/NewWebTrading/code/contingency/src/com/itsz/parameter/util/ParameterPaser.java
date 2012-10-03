/**
 * 
 */
package com.itsz.parameter.util;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.util.ReaderPropertiesUtil;


/**
 * @author swliu
 *
 */
public class ParameterPaser {
	
	Log log=LogFactory.getLog(ParameterPaser.class);
	
	public void paser() {
		try {
			ReaderPropertiesUtil reader = new ReaderPropertiesUtil(
					"parameters.properties");
			Properties rightPros = reader.reader();
			
			Set keySet = rightPros.keySet();
			Iterator it = keySet.iterator();
			int index = 0;
			ParameterManager.clear();
			log.info("++++++++++++++++++++++read parameters++++++++++++++++++++++");
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = rightPros.getProperty(key);
				ParameterManager.add(key,value);
				System.out.println("++++++++key="+key+"			value="+value);
			}
			log.info("total parameters:"+ParameterManager.size());
		} catch (Exception e) {
			ParameterManager.clear();
			log.error(e.getMessage());
			log.debug(e);
		}
	}
}
