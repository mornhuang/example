package com.itsz.sht.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;

import com.itsz.emc2.webservice.axis.EMSWsException;
import com.itsz.emc2.webservice.axis.EmcClientUtil;
import com.itsz.sht.common.PropertyConfig;


/**
 * $Id: EmcUtil.java,v 1.11 2011/03/10 09:45:50 xlliu Exp $
 * @Project:NewWebTrading
 * @File:EmcUtil.java
 * @Description:
 * @Author:kyzou
 * @Date:2011-1-26
 */
public class EmcUtil {
	private String emcHost;
	private String emcLoginUser;
	private String emcLoginPass;
	private String emcUrl;
	private String emcIndexUrl;

	public String getEmcUrl() {
		return emcUrl;
	}

	public void setEmcUrl(String emcUrl) {
		this.emcUrl = emcUrl;
	}

	public String getEmcIndexUrl() {
		return emcIndexUrl;
	}

	public void setEmcIndexUrl(String emcIndexUrl) {
		this.emcIndexUrl = emcIndexUrl;
	}

	public String getEmcLoginUser() {
		return emcLoginUser;
	}

	public void setEmcLoginUser(String emcLoginUser) {
		this.emcLoginUser = emcLoginUser;
	}

	public String getEmcLoginPass() {
		return emcLoginPass;
	}

	public void setEmcLoginPass(String emcLoginPass) {
		this.emcLoginPass = emcLoginPass;
	}

	public String getEmcHost() {
		return emcHost;
	}

	public void setEmcHost(String emcHost) {
		this.emcHost = emcHost;
	}
	
	public int getMsgCountByReceive(String token,String clientId,String custCode){		
		int count = 0;
		try {
			EmcClientUtil util = new EmcClientUtil(emcHost, emcLoginUser, emcLoginPass);
			try {
				count = util.getUnreadMsgCountByReceive(null, custCode);
			} catch (EMSWsException e) {
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public String showInfoUrl(String custCode, String custName, Locale locale)
	{
		 String key = "";
		 BufferedReader in = null;
	     InputStream is = null;
	     //String keyUrl = PropertyConfig.getCommonProperty("emc_url");
	     String keyUrl = emcUrl;
	     String getNewMsgCntPage = "";
		 //String strIndexUrl = PropertyConfig.getCommonProperty("emc_index_url");
	     String strIndexUrl = emcIndexUrl;
	     URL url = null;
	     
	     keyUrl = keyUrl + "?custCode="+custCode;
	     
	     try{
	    	 //receive key
	    	 url = new URL(keyUrl);
	    	 	URLConnection urlConn = null;
	    	 	urlConn = url.openConnection();
	    	 	urlConn.setDoInput(true);
	    	 	urlConn.setDoOutput(false);
	    	    urlConn.setAllowUserInteraction(false);
	    	    is = urlConn.getInputStream();
	    	 	if (is!=null){
	    	 		in = new BufferedReader(new InputStreamReader(is));
	    	 	}
	    		int i =0;
	    		while(key.equals("") || key == null)
	    		{
	    			key = in.readLine();
	    			i++;
	    			if(i >10)
	    				break;
	    		}
	            if("-1".equals(key) || null== key || "".equals(key))
	            {
	       //     	System.out.println("key is invalid!");
	            	return null;
	            }
	    //	 	  System.out.println("key="+key);
	    	 	  
	    	 //connect to the emessageinfo page
	  	    custName = URLEncoder.encode(custName,"UTF-8");
	  	    getNewMsgCntPage=strIndexUrl+"?key="+key+"&custCode="+custCode+"&channel=eService&lang="+locale+"&custName="+custName;
	  	//    System.out.println("custCode="+custCode);  
		//  getNewMsgCntPage=strIndexUrl+"?key="+key+"&custCode="+custCode+"&channel=eService&lang="+locale;
	     }catch(Exception ex)
	     {
	    	 ex.printStackTrace();
	     }
	     finally {
				try {
					if(in != null){
						in.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		  return getNewMsgCntPage;
	}
}
