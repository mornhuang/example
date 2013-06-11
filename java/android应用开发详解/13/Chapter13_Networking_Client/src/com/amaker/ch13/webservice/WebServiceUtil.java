package com.amaker.ch13.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParserException;

/**
 * 
 * @author 郭宏志
 * 天气预报工具类
 */
public class WebServiceUtil {
	
	/*
	 * 通过传递城市名称获得天气信息
	 */
	public static String getWeatherMsgByCity(String cityName) {
		String url = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather";
		HttpPost request = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("theCityCode", cityName));
		params.add(new BasicNameValuePair("theUserID", ""));
		String result = null;
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,
					HTTP.UTF_8);
			request.setEntity(entity);
			HttpResponse response = new DefaultHttpClient().execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
				return parse2(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 使用ksoap，获得城市列表
	 */
	public static List<String> getCityList() {
		// 命名空间
		String serviceNamespace = "http://WebXml.com.cn/";
		// 请求URL
		String serviceURL = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx";
		// 调用的方法
		String methodName = "getRegionProvince";
		// 实例化SoapObject对象
		SoapObject request = new SoapObject(serviceNamespace, methodName);
		// 获得序列化的Envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		(new MarshalBase64()).register(envelope);
		
		// Android传输对象
		AndroidHttpTransport ht = new AndroidHttpTransport(serviceURL);
		ht.debug = true;

		try {
			// 调用
			ht.call("http://WebXml.com.cn/getRegionProvince", envelope);
			if (envelope.getResponse() != null) {
				return parse(envelope.bodyIn.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/*
	 * 对天气信息XML文件进行解析
	 */
	private static String parse2(String str){
		String temp;
		String[] temps;
		List list = new ArrayList();
		StringBuilder sb = new StringBuilder("");
		if(str!=null&&str.length()>0){
			temp = str.substring(str.indexOf("<string>"));
			temps = temp.split("</string>");
			for (int i = 0; i < temps.length; i++) {
				sb.append(temps[i].substring(12));
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	/*
	 * 对得到的城市XML信息进行解析
	 */
	private static List<String> parse(String str) {
		String temp;
		List<String> list = new ArrayList<String>();
		if (str != null && str.length() > 0) {
			int start = str.indexOf("string");
			int end = str.lastIndexOf(";");
			temp = str.substring(start, end - 3);
			String[] test = temp.split(";");
			for (int i = 0; i < test.length; i++) {
				if (i == 0) {
					temp = test[i].substring(7);
				} else {
					temp = test[i].substring(8);
				}
				int index = temp.indexOf(",");
				list.add(temp.substring(0, index));
			}
		}
		return list;
	}

}
