/**
 * 
 */
package com.itsz.web.rtq.util;

/**
 * @author swliu
 *
 */
public class LangConvert {

	public static String etnetConvert(String sourceLang) {
		String etnetLang = "chi";  //default
		if (sourceLang.equals("EN")) {
			etnetLang = "eng";
		} else if (sourceLang.equals("GB")) {
			etnetLang = "chn";
		}
		return etnetLang;
	}
	
	public static String aastockConvert(String sourceLang) {
//   	 lang = "chi" - Traditional Chinese / "eng" - English / "chn" - Simplified Chinese
    	String aastockLang = "chi";  
    	if ("EN".equals(sourceLang)) {
    		aastockLang = "eng";
    	} else if ("GB".equals(sourceLang)) {
    		aastockLang= "chn";
    	}
    	return aastockLang;
	}
}
