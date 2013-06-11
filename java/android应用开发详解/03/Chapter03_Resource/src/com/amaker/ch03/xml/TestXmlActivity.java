package com.amaker.ch03.xml;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.amaker.test.R;
/**
 * 
 * @author 郭宏志
 * 测试xml资源
 */
public class TestXmlActivity extends Activity {
	private TextView myTextView;
	private Button myButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.test_xml);
       
       myTextView = (TextView)findViewById(R.id.xmlContentTextView01);
       myButton = (Button)findViewById(R.id.xmltTestButton01);
       
       myButton.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			   int counter = 0;
		       StringBuilder sb = new StringBuilder("");
		       Resources r = getResources();
	    	   XmlResourceParser xrp = r.getXml(R.xml.test);
		       try {
				while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) { 
					     if (xrp.getEventType() == XmlResourceParser.START_TAG) { 
					          String name = xrp.getName();
					          if(name.equals("customer")){
					        	  counter++;
					        	  sb.append("第"+counter+"条客户信息："+"\n");
					        	  sb.append(xrp.getAttributeValue(0)+"\n");
					        	  sb.append(xrp.getAttributeValue(1)+"\n");
					        	  sb.append(xrp.getAttributeValue(2)+"\n");
					        	  sb.append(xrp.getAttributeValue(3)+"\n\n");
					          }
					     } else if (xrp.getEventType() == XmlPullParser.END_TAG) { 
					     } else if (xrp.getEventType() == XmlPullParser.TEXT) { 
					     } 
					     xrp.next(); 
					}
				myTextView.setText(sb.toString());
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	});
    }
}