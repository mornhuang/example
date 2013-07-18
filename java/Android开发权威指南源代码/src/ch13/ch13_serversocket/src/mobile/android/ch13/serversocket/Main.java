package mobile.android.ch13.serversocket;

import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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

	class ServerSocketThread extends Thread
	{

		@Override
		public void run()
		{

			try
			{

				ServerSocket serverSocket = new ServerSocket();
				serverSocket.bind(new InetSocketAddress("192.168.1.101", 1234));
				while (true)
				{
					Socket socket = serverSocket.accept();
					final TelephonyManager telephonyManager = (TelephonyManager) getBaseContext()
							.getSystemService(Context.TELEPHONY_SERVICE);
					socket.getOutputStream().write(("DeviceId Hashcode：" + 
							String.valueOf(
									telephonyManager
											.getDeviceId()).hashCode())
									.getBytes());
					socket.close();
				} 
			}
			catch (Exception e)
			{

			}
		}

	}

	public void onClick_Start_Server(View view)
	{
		new ServerSocketThread().start();
		Toast.makeText(this, "服务已开启", Toast.LENGTH_LONG).show();

	}
}