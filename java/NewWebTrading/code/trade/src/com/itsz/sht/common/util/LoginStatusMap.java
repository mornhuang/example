package com.itsz.sht.common.util;

import com.taifook.mcs.core.beans.msg.LoginECertResponse;

public class LoginStatusMap {
	//new status
	private final  static String SUCCESS = "SUCCESS";
	private final  static String WARNING = "WARNING";
	private final  static String FAILED = "FAILED";
	
	//new subcode
	private final static String  NORMAL = "NORMAL";
	private final static String  INVECERT = "INVECERT";
	private final static String  SUSPEND = "SUSPEND";
	private final static String  SUSPENDED = "SUSPENDED";
	private final static String  LOCKED = "LOCKED";
	private final static String  INVDOCID = "INVDOCID";
	private final static String  CHGPASS = "CHGPASS";
	private final static String  CHASUSP = "CHASUSP";
	private LoginStatusMap(){
		
	}
	public static LoginECertResponse  mapToNewStatus(LoginECertResponse response){//to do 
		//check parameters 
		if (response==null){
			return response;
		}					
		if ("Y".equalsIgnoreCase(response.getIsValid())){
			response.setNewStatus(SUCCESS);
			response.setNewSubCode(NORMAL);
			//first login 
			if ("Y".equalsIgnoreCase(response.getFirstLoginFlag())){
				response.setNewStatus(WARNING);
				response.setNewSubCode(CHGPASS);
			}
			return response;
		}else{
			 response.setNewStatus(FAILED);
			 if(!NORMAL.equalsIgnoreCase(response.getChannelStatus())){
					response.setNewSubCode(CHASUSP);
					return response;
			 }
			 if ("N".equalsIgnoreCase(response.getIsValidECert())){
				 response.setNewSubCode(INVECERT);
				 return response;
			 }
			 if ("Y".equalsIgnoreCase(response.getIsValidECert())&& SUSPEND.equalsIgnoreCase(response.getLoginStatus())){
				 response.setNewSubCode(SUSPENDED);
				 return response;
			 }
			 if ("Y".equalsIgnoreCase(response.getIsValidECert())&& LOCKED.equalsIgnoreCase(response.getLoginStatus())){
				 response.setNewSubCode(LOCKED);
				 return response;
			 }
			 if("N".equalsIgnoreCase(response.getIsValidDocId())){
				 response.setNewSubCode(INVDOCID);
				 return  response;
			 }
		}
		return response;
	}
	
	
	public static void main(String args[]){
		//(MSID='RELI', LOST='SUSPEND', LOID='haha520', CUCD='0041038', NAME='Client 02-0041038-30 ', SMSP=%NULL%, STAT=%NULL%, FLDT='2005-02-18 00:00:00.0', LADT='2006-06-23 16:27:40.0', IVCT='0', REDT=%NULL%, GLCT='0', GLCF='N', TFLG='N', DISC='N', TEAC=%NULL%, TRPO='N', ISVD='N', IVEC='Y', MIPC='10', FCPD='90', GLID='Y', MGLC='3', FLGF='N', CHST='NORMAL', NSTA='FAILED', NSSC='INVECERT', ALTF='Y', TRAC='02-0041038-30', SHKS='N', TACC=%NULL%, RQAF='Y', RINF-L=())
		LoginECertResponse re = new LoginECertResponse();
		re.setIsValidDocId("N");
		re.setIsValid("N");
		re.setChannelStatus("NORMAL");
		re.setIsValidECert("Y");
		re.setFirstLoginFlag("N");
		re.setLoginStatus("SUSPENDED");
		//re.setChannelStatus(LOCKED);
	
		re = mapToNewStatus(re);
	    System.out.println("new Status:" + re.getNewStatus() + "\r\nnesSub:" + re.getNewSubCode());
	}

}
