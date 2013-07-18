package mobile.android.ch17.html5.demo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class DemoActivity6 extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo);
		WebView webView = (WebView) findViewById(R.id.webview);

		int width = getWindowManager().getDefaultDisplay().getWidth();
		int height = getWindowManager().getDefaultDisplay().getHeight();

		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient());
		if (width == 320 && height == 480)
			webView.loadUrl("http://HTML5demos.com/canvas");
		else if (width == 480 && height == 800)
			webView.loadUrl("http://www.benjoffe.com/code/demos/canvascape/textures");
	}
}
