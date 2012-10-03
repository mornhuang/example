/**
 * 
 */
package com.itsz.util;

/**
 * @author swliu
 *
 */
public class CommonUtil {
	
	public static String getCompanyName(String language){
		String companyName="";
		if ("C".equals(language)) {
			companyName="大福證券有限公司";
			
		}
		else if ("GB".equals(language)){
			companyName="大福证券有限公司";
		}
		else {
			companyName="Taifook Securities Company Limited";
		}
		
		return companyName;
	}

}
