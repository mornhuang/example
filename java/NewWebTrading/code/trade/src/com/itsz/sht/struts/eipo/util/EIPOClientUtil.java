package com.itsz.sht.struts.eipo.util;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.builder.ToStringBuilder;


public class EIPOClientUtil {

	public static ApplyClient eipoClient;
	
	static {
		eipoClient = ApplyClient.getInstance(EIPOConstants.TIMEOUT);
	}
	
	public static Date con2Date(XMLGregorianCalendar cal) {
		if (cal != null) {
			GregorianCalendar ca = cal.toGregorianCalendar();
	        return ca.getTime();
		}
		return null;
	}
	
	public static String getToString(Object obj) {
		return ToStringBuilder.reflectionToString(obj);
	}
	
	public static boolean isIPOServiceDate(Date startDateTime,Date endDateTime) {
		if (new Date(System.currentTimeMillis()).after(startDateTime)
			 && new Date(System.currentTimeMillis()).before(endDateTime)) {
			return true;
		}
		return false;
	}
	
	public static boolean isCurrentTimeAfterEndDateTime(Date endDateTime) {
		if (new Date(System.currentTimeMillis()).after(endDateTime)) {
			return true;
		}
		return false;
	}
}
