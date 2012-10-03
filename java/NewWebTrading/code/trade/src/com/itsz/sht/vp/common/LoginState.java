package com.itsz.sht.vp.common;

import com.itsz.sht.common.Constants;


public class LoginState {

	public static boolean checkAllowLoginStatus(String newSubCode){
		if(newSubCode==null) return false;
		boolean isNORMAL=newSubCode.equals(Constants.LONGIN_STATUS_NORMAL);
		if(isNORMAL) return true;
		boolean isCHGPASS=newSubCode.equals(Constants.LONGIN_STATUS_CHGPASS);	
		if(isCHGPASS) return true;
		boolean isGRACELOGIN=newSubCode.equals(Constants.LONGIN_STATUS_GRACELOGIN);	
		if(isGRACELOGIN) return true;
		boolean isGRACECNT=newSubCode.equals(Constants.LONGIN_STATUS_GRACECNT);
		if(isGRACECNT) return true;
		boolean isISSUED=newSubCode.equals(Constants.LONGIN_STATUS_ISSUED);	
		if(isISSUED) return true;
		return false;
		
	}

}
