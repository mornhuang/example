package mobile.android.ch13.socket.data.transmit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_Data_Transmit(View view)
	{
		try
		{
			Socket socket = new Socket("51happyblog.com", 80);
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("GET / HTTP/1.1\r\nHost:51happyblog.com\r\n\r\n");
			bw.flush();
	
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s = "";
			TextView textView = (TextView) findViewById(R.id.textview);
			while ((s = br.readLine()) != null)
				textView.append(s + "\n");
			socket.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
}