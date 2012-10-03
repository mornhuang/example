package com.itsz.sht.common.beanutil;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * $Id: BeanCopyUtils.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:BeanCopyUtils.java
 * @Description:
 * @Author:edxu
 * @Date:2007-6-29
 */
public class BeanCopyUtils {
	private static final String STRING2BIG_DECIMAL = "java.lang.String2java.math.BigDecimal";
	private static final String STRING2LONG = "java.lang.String2java.lang.Long";
	private static final String STRING2TIMESTAMP = "java.lang.String2java.sql.Timestamp";
	private final static Map map = new HashMap();
	
	static{
		map.put(STRING2BIG_DECIMAL, new StringDecimalConverter());
		map.put(STRING2LONG, new StringLongConverter());
		map.put(STRING2TIMESTAMP, new StringTimestampConverter());
	}
	
	public static void copyProperties(Object dest, Object orig) throws IllegalArgumentException, IllegalAccessException {
		if (orig == null || dest == null)
			return;
		copy(dest, orig);
	}

	private static void copy(Object dest, Object orig) throws IllegalArgumentException, IllegalAccessException {

		Field[] destFields = dest.getClass().getDeclaredFields();
		Field[] origFields = orig.getClass().getDeclaredFields();
		AccessibleObject.setAccessible(destFields, true);
		AccessibleObject.setAccessible(origFields, true);
		String name = null;
		String origType = null;
		String destType = null;

		Map destMap = fieldArray2Map(destFields);
		Field destField = null;
		for (int i = 0; i < origFields.length; i++) {
			name = origFields[i].getName();
			origType = origFields[i].getType().getName();
			destField = (Field)destMap.get(name);
			if(destField == null) continue;
			destType = destField.getType().getName();
			if (origType.equals(destType)){ 
				destField.set(dest, origFields[i].get(orig));
				continue;
			}
			Converter converter = (Converter)map.get(origType+"2"+destType);
			if(converter != null)
				destField.set(dest, converter.convert(origFields[i].get(orig)));
		}
	}

	private static Map fieldArray2Map(Field[] destFields) {
		Map map = new HashMap();
		for (int i = 0; i < destFields.length; i++)
			map.put(destFields[i].getName(), destFields[i]);
		return map;
	}

}
