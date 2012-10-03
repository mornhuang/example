package com.itsz.sht.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;

public class HttpClientUtil {

	private HttpClient httpclient;
	private static HttpClientUtil clientUtil;
	
	private HttpClientUtil() {
		httpclient = new HttpClient();
		//connection timeout
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(1000 * 5);
		//set Timeout
		httpclient.getHttpConnectionManager().getParams().setSoTimeout(1000 * 30);
	}	
	
	public static HttpClientUtil getInstance() {
		if (clientUtil == null) {
			synchronized (HttpClientUtil.class) {
				clientUtil = new HttpClientUtil();
			}
		}
		return clientUtil;
	}
	
	private void isHttps(String httpUrl) {
		if (httpUrl.startsWith("https://")) {
			Protocol myhttps = new Protocol("https", new MySSLSocketFactory(),443);
			Protocol.registerProtocol("https", myhttps);
		}
	}
	
	public String getEpaymentToken(String url, String bankCode, String accounts,
			String custCode, String lang, String loginId, String channelsCode,
			String amount,String defaultAccount) throws Exception {
		isHttps(url);
		PostMethod  httpPost = new PostMethod(url);
		NameValuePair parNankCode = new NameValuePair("bankCode",bankCode);
		NameValuePair parAccounts = new NameValuePair("accounts",accounts);
		NameValuePair parCustCode = new NameValuePair("custCode",custCode);
		NameValuePair parLang = new NameValuePair("lang",lang);
		NameValuePair parLoginId = new NameValuePair("loginId",loginId);
		NameValuePair parChannelsCode = new NameValuePair("channelsCode",channelsCode);
		NameValuePair parAmount = new NameValuePair("amount",amount);
		NameValuePair parDefaultAccount = new NameValuePair("defaultAccount",defaultAccount);
		httpPost.setRequestBody(new NameValuePair[] { parNankCode, parAccounts,
				parCustCode, parLang, parLoginId, parChannelsCode, parAmount,parDefaultAccount });

		try {
			//set Timeout
			int statusCode = httpclient.executeMethod(httpPost);
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("request ePayment Gateway server failed: " + httpPost.getStatusLine());
			} else {
				// read body
				return httpPost.getResponseBodyAsString();
			}
		}catch(Exception ex) {
			throw ex;
		} finally {
			httpPost.releaseConnection();
		}
	}
	
	public String executeMethod(String httpUrl) throws Exception {
		isHttps(httpUrl);
		GetMethod httpget = new GetMethod(httpUrl);
		try {
			int statusCode = httpclient.executeMethod(httpget);
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("request server Method failed: " + httpget.getStatusLine());
			} else {
				// read body
				return httpget.getResponseBodyAsString();
			}
		}catch(Exception ex) {
			throw ex;
		} finally {
			httpget.releaseConnection();
		}
	}
	
	public static void main(String[] args) {
		try {
			HttpClientUtil.getInstance().executeMethod("http://10.100.1.201:8081/webbshare/logout.do?params=2b7uGEdBfpmg0_ygUIK2wQmXmxzT1f2I2hps-pA93SmY1o_c6QYAu6cY6525Iie1");
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
