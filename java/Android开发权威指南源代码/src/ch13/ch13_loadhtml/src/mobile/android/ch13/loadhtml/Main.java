package mobile.android.ch13.loadhtml;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		WebView webView = (WebView) findViewById(R.id.webview);
		String html = "<html>"
				+ "<body>"
				+ "图书封面<br>"
				+ "<table width='200' border='1' >"
				+ "<tr>"
				+ "<td><a onclick='alert(\"《Android/OPhone开发完全讲义》\")' ><img style='margin:10px' src='file:///android_asset/android_ophone_shupi.jpg' width='100'/></a></td>"				
				+ "<td><a onclick='alert(\"《人人都玩开心网》\")' ><img style='margin:10px' src='http://www.blogjava.net/images/blogjava_net/nokiaguy/10113411c.jpg' width='100'/></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><img style='margin:10px' src='http://images.china-pub.com/ebook25001-30000/27518/zcover.jpg' width='100'/></td>"
				+ "<td><img  style='margin:10px' src='http://images.china-pub.com/ebook30001-35000/34838/zcover.jpg' width='100'/></td>"
				+ "</tr>" + "</table>" + "</body>" + "</html>";

		 webView.loadDataWithBaseURL("图书名", html, "text/html", "utf-8", null);

		 webView.getSettings().setJavaScriptEnabled(true);
		 webView.setWebChromeClient(new WebChromeClient());
	
		 
	}
}
 