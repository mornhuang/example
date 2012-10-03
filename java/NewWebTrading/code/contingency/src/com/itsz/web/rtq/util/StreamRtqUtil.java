/**
 * <p>Title:Channels Server</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.web.rtq.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.common.Constants;
import com.itsz.parameter.util.ParameterManager;
import com.itsz.web.rtq.obj.IQLoginProfile;
import com.qpigroup.crypto.MD5;

public class StreamRtqUtil {
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
	private static int maxConnectionsPerHost = 100;
	private static int maxTotalConnections = 1000;
	private static int httpClientTimeout = 30000;
	private static int cnnTimeout = 30000;
	private static int httpCnnTimeout = 30000;
	private static HttpClient httpclient = null;
	private static MultiThreadedHttpConnectionManager mtm ;

	private static void loadConf()
	{

		maxConnectionsPerHost = Integer.parseInt(ParameterManager.getValue(Constants.HTTP_MaxConn_PerHost));

		maxTotalConnections = Integer.parseInt(ParameterManager.getValue(Constants.HTTP_MaxConn_Total));

		httpClientTimeout = Integer.parseInt(ParameterManager.getValue(Constants.HTTP_Client_Timeout));

		cnnTimeout = Integer.parseInt(ParameterManager.getValue(Constants.CNN_Timeout));

		httpCnnTimeout = Integer.parseInt(ParameterManager.getValue(Constants.HTTP_CNN_Timeout));
	}

	
	public static String GenerateMD5Key(String uid){
		MD5 md5 = new MD5();
		return md5.genDigest(uid + "taifook");
	}
	

	public static String GenerateMD5Key(String uid,String pwd,String domain,String token){

		MD5 md5 = new MD5();
		return md5.genDigest("domain=" + domain + "&uid=" + uid + "&pwd=" + pwd + "&token=" + token);

	}

	
	public static HttpClient gethttpclient(){
		
		int httpClientTimeout_send;
		int cnnTimeout_send;
		int httpCnnTimeout_send;

		httpClientTimeout_send = httpClientTimeout;
		cnnTimeout_send = cnnTimeout;
		httpCnnTimeout_send = httpCnnTimeout;

		mtm.setMaxConnectionsPerHost(maxConnectionsPerHost);
		mtm.setMaxTotalConnections(maxTotalConnections);
	
		httpclient.setHttpConnectionManager(mtm);
		httpclient.setTimeout(httpClientTimeout_send);
		httpclient.setConnectionTimeout(cnnTimeout_send);
		httpclient.setHttpConnectionFactoryTimeout(httpCnnTimeout_send);
		return httpclient;
	}


	static{

		loadConf();
		httpclient=new HttpClient();
		mtm=new MultiThreadedHttpConnectionManager();

	}
	
	
	public static IQLoginProfile IQObtainKey(String url){
		
		HttpClient httpclient = new HttpClient();
		//httpclient.setTimeout(30000);
		GetMethod httpget = new GetMethod(url);
		String body = null;
		try {
			httpclient.executeMethod(httpget);
			body = httpget.getResponseBodyAsString();
		} catch (HttpException e1) {
			IQLoginProfile profile = new IQLoginProfile();
			profile.setResult(Constants.IQ_SERVER_ERROR);
			return profile;
		} catch (IOException e2) {
			IQLoginProfile profile = new IQLoginProfile();
			profile.setResult(Constants.IQ_SERVER_ERROR);
			e2.printStackTrace();
			return profile;
		} catch (Exception e){
			IQLoginProfile profile = new IQLoginProfile();
			profile.setResult(Constants.IQ_SERVER_ERROR);
			e.printStackTrace();
			return profile;
		}finally {
			httpget.releaseConnection();
		}

		IQLoginProfile bean = new IQLoginProfile();
		bean.setResult(body);
		return bean;
		
	}
	
	public static IQLoginProfile OldIQObtainKey(String url, String uid,
			String pwd) {

		// url="http://iq.etnet.com.hk/iqservice/jsp/CorpAccount/Login.jsp";
		PostMethod httpget = new PostMethod(url);

		NameValuePair[] data = { new NameValuePair("request", "login"),
				new NameValuePair("uid", uid), new NameValuePair("pwd", pwd) };
		httpget.setRequestBody(data);

		Properties p = null;

		try {
			// gethttpclient().
			gethttpclient().executeMethod(httpget);
			InputStream in = httpget.getResponseBodyAsStream();
			p = new Properties();
			p.load(in);
		} catch (HttpException e1) {
			IQLoginProfile profile = new IQLoginProfile();
			profile.setResult(Constants.IQ_SERVER_ERROR);
			return profile;
		} catch (IOException e2) {
			IQLoginProfile profile = new IQLoginProfile();
			profile.setResult(Constants.IQ_SERVER_ERROR);
			log_debug.info(e2.getMessage(), e2);
			return profile;
		} catch (Exception e) {
			IQLoginProfile profile = new IQLoginProfile();
			profile.setResult(Constants.IQ_SERVER_ERROR);
			log_debug.info(e.getMessage(), e);
			return profile;
		} finally {
			httpget.releaseConnection();
		}

		IQLoginProfile bean = new IQLoginProfile();
		bean.setResult(p.getProperty(Constants.IQ_RESULT));
		bean.setPassport(p.getProperty(Constants.IQ_PASSPORT));
		return bean;

	}
	

	
	public static String QSObtainKey(String url){

		// url="http://www.quotepower.com/web/login/gettoken.asp";
	       PostMethod httpget = new PostMethod(url);

	        String str = null;

		try {
			gethttpclient().executeMethod(httpget);
			str = httpget.getResponseBodyAsString();
		} catch (HttpException e1) {
			log_debug.info(e1.getMessage(),e1);
		} catch (IOException e2) {
			log_debug.info(e2.getMessage(),e2);
		} catch (Exception e){
			log_debug.info(e.getMessage(),e);
		}finally {
			httpget.releaseConnection();
		}	
		return str;
	}
	
    
    
    
    
    
    //*********************************TESTING***********************************************************
    
    
	public static IQLoginProfile IQObtainKey1(String url){
		HttpMethod httpget = new PostMethod(url);
	   	Properties p = null;

		try {
			gethttpclient().executeMethod(httpget);
			InputStream in = httpget.getResponseBodyAsStream();
			p = new Properties();
			p.load(in);
		} catch (HttpException e1) {
			IQLoginProfile profile = new IQLoginProfile();
			profile.setResult(Constants.IQ_SERVER_ERROR);
			return profile;
		} catch (IOException e2) {
			IQLoginProfile profile = new IQLoginProfile();
			profile.setResult(Constants.IQ_SERVER_ERROR);
			log_debug.info(e2.getMessage(), e2);
			return profile;
		} catch (Exception e){
			IQLoginProfile profile = new IQLoginProfile();
			profile.setResult(Constants.IQ_SERVER_ERROR);
			log_debug.info(e.getMessage(), e);
			return profile;
		}finally {
			httpget.releaseConnection();
		}

		IQLoginProfile bean = new IQLoginProfile();
		bean.setResult(p.getProperty(Constants.IQ_RESULT));
		bean.setPassport(p.getProperty(Constants.IQ_PASSPORT));
		return bean;

	}

}


