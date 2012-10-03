/**
 * <p>Title:Channels Server</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common.util;

import com.itsz.sht.common.Constants;

public class AccountType {
	public static boolean isSecAccount(String accountType){
		String first = null;
		boolean isSecAccount = false;
		if(accountType!=null){
			first = accountType.substring(0,1);
		}
		for(int i=0;i<Constants.SecAccuontType.length;i++){
			if(Constants.SecAccuontType[i].equals(first)){
				isSecAccount = true;
			}
		}
		return isSecAccount;
	}
}
