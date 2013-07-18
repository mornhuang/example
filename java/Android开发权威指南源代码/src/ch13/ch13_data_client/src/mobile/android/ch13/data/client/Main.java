package mobile.android.ch13.data.client;

import java.net.Socket;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_Send_Data(View view)
	{
		try
		{
			Socket socket = new Socket("127.0.0.1", 1234);
			
			Random random = new Random();
			String data = String.valueOf(random.nextInt());
			socket.getOutputStream().write(data.getBytes());
			socket.getOutputStream().flush();			
			socket.close();
			Toast.makeText(this, "数据已发送", Toast.LENGTH_LONG).show();
		}
		catch (Exception e)
		{			
		}
	}
}