package mobile.android.ch08.file.outputinput;

import java.io.InputStream;
import java.io.OutputStream;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try
		{
			OutputStream os = openFileOutput("file.txt", Activity.MODE_PRIVATE);
			String str1 = "《Android/OPhone开发完全讲义》";
			os.write(str1.getBytes("utf-8"));
			os.close();

			InputStream is = openFileInput("file.txt");
			byte[] buffer = new byte[100];
			int byteCount = is.read(buffer);
			String str2 = new String(buffer, 0, byteCount, "utf-8");
			TextView textView = (TextView)findViewById(R.id.textview);
			textView.setText(str2);			
			is.close();
		}
		catch (Exception e) {
			
		}
	}
}