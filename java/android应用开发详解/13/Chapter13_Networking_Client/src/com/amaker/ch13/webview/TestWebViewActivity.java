package com.amaker.ch13.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.amaker.ch13.R;
/**
 * @author ¹ùºêÖ¾
 * Í¨¹ýWebViewä¯ÀÀÍøÂç
 */
public class TestWebViewActivity extends Activity {
	private WebView webView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_webview);
        webView = (WebView)findViewById(R.id.mywebview);
       /* String url = "http://www.google.com";
        webView.loadUrl(url);*/
        
        String html = "";
        html+="<html>";
	        html+="<body>";
	        	html+="<a href=http://www.google.com>Google Home</a>";
	        html+="</body>";
        html+="</html>";
        
        webView.loadData(html, "text/html", "utf-8");
        
        
    }
}