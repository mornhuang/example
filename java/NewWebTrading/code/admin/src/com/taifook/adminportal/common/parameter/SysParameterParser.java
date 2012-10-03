package com.taifook.adminportal.common.parameter;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import com.taifook.adminportal.common.util.ReaderPropertiesUtil;

public class SysParameterParser {

	public void parser(){
		ReaderPropertiesUtil reader = new ReaderPropertiesUtil("sysparamconfig.properties");
		Properties pros = reader.reader();
		if (pros != null) {
			Set keys = pros.keySet();
			for (Iterator it = keys.iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = pros.getProperty(key);
				ParameterManager.putParameter(key, value);				
			}
		}
	}

}
