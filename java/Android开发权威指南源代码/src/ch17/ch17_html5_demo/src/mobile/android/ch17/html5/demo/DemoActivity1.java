package mobile.android.ch17.html5.demo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class DemoActivity1 extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo);
		WebView webView = (WebView) findViewById(R.id.webview);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient());
	
		//webView.loadUrl("http://HTML5demos.com/canvas");
		

	}
}
