/**
 * 
 */
package com.itsz.parameter.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swliu
 *
 */
public class ParameterManager {
	
	public final static Map paraMap = new HashMap();

	public static void add(String key, String value) {
		paraMap.put(key, value);
	}

	public static void remove(String key) {
		paraMap.remove(key);
	}

	public static void replace(String key, String value) {
		paraMap.put(key, value);
	}
	
	public static String getValue(String key){
		return (String)paraMap.get(key);
	}
	
	public static void clear(){
		paraMap.clear();
	}
	
	public static int size(){
		return paraMap.size();
	}
}
