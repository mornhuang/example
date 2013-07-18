package mobile.android.ch17.path;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		WebView webView = (WebView) findViewById(R.id.webview);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient());
		StringBuilder html = new StringBuilder();
		try
		{
			InputStream is = getResources().getAssets().open("test.html");
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String s = "";  
			int count = 0;
			while ((s = br.readLine()) != null)
			{
				html.append(s + "\r\n");
			}
			br.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

		webView.loadDataWithBaseURL(null, html.toString(), "text/html",
				"utf-8", null);
    }
}