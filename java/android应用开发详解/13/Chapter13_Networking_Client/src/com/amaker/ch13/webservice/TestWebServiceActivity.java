package com.amaker.ch13.webservice;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;

public class TestWebServiceActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String serviceNamespace = "http://tempuri.org/";
		String serviceURL = "http://www.ayandy.com/Service.asmx";
		String methodName = "getWeatherbyCityName";  
		  
		SoapObject request = new SoapObject(serviceNamespace, methodName);
		
		PropertyInfo info = new PropertyInfo();
		info.setName("theCityName");
		info.setValue("±±¾©");
		
		PropertyInfo info2 = new PropertyInfo();
		info2.setName("theDayFlag");
		info2.setValue("1");
		
		request.addProperty(info);
		request.addProperty(info2);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = request;
		(new MarshalBase64()).register(envelope);
		
		AndroidHttpTransport ht = new AndroidHttpTransport(serviceURL);
		
		ht.debug = true;
		
		try {
			ht.call("http://tempuri.org/getWeatherbyCityName", envelope);
			if(envelope.getResponse()!=null){
				System.out.println(envelope.getResult());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }
}