/**
 * <p>Title:Channels Server</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.model.common.IQLoginProfile;
import com.qpigroup.crypto.MD5;

public class StreamRtqUtil {
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
	
	public static IQLoginProfile IQObtainKey(String url){		
		HttpClient httpclient = new HttpClient();
		httpclient.setTimeout(30000);
		GetMethod httpget = new GetMethod(url);
		Properties p = null;
		
		try {
			httpclient.executeMethod(httpget);
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
	
	public static String getEtnetAcessResult(String url){		
		HttpClient httpclient = new HttpClient();
		httpclient.setTimeout(30000);
		GetMethod httpget = new GetMethod(url);
		String result = null;		
		try {
			httpclient.executeMethod(httpget);
			result = httpget.getResponseBodyAsString();
		} catch (HttpException e1) {
			log_debug.info(e1);
			return null;
		} catch (IOException e2) {
			log_debug.info(e2);
			return null;
		} catch (Exception e){
			log_debug.info(e);
			return null;
		}finally {
			httpget.releaseConnection();
		}
		return result;
		
	}
	
	public static String QSObtainKey(String url){
		HttpClient httpclient = new HttpClient();
		httpclient.setTimeout(30000);
		GetMethod httpget = new GetMethod(url);
		String str = null;
		try {
			httpclient.executeMethod(httpget);
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
	
	public static String GenerateMD5Key(String uid,String pwd,String domain,String token){		
		MD5 md5 = new MD5();
		return md5.genDigest("domain=" + domain + "&uid=" + uid + "&pwd=" + pwd + "&token=" + token);

	}
	
	public static String GenerateMD5Key(String baseKey){		
		MD5 md5 = new MD5();
		return md5.genDigest(baseKey);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IQLoginProfile iq = IQObtainKey("https://iqdemo.etnet.com.hk/iqservice/jsp/IFC/IFCLogin.jsp?request=login&uid=iq3013&pwd=rrq6767m");
		System.out.println(iq.getResult());
		System.out.println(iq.getPassport());
	}

}
