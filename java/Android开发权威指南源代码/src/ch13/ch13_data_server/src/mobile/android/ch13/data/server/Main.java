package mobile.android.ch13.data.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class Main extends Activity
{
	private TextView textView;

	private Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			textView.append(String.valueOf(msg.obj) + "\n\n");
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textView = (TextView) findViewById(R.id.textview);
		new ServerSocketThread().start();
	}

	class ServerSocketThread extends Thread
	{

		@Override
		public void run()
		{
			try
			{
				ServerSocket serverSocket = new ServerSocket();
				serverSocket.bind(new InetSocketAddress("127.0.0.1", 1234));
				while (true)
				{
					Socket socket = serverSocket.accept();
					InputStream is = socket.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					Message message = new Message();
					message.obj = br.readLine();
					handler.sendMessage(message);
					socket.close();
				}
			}
			catch (Exception e)
			{

			}
		}

	}

}