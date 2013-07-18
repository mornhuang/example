package mobile.android.ch13.scan.server.ports;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity
{
	private TextView tvPorts;
	private Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			tvPorts.append(String.valueOf(msg.what) + ":ok\n");
			super.handleMessage(msg);
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tvPorts = (TextView) findViewById(R.id.tvPorts);
	}

	class ScanPorts extends Thread
	{
		private int minPort, maxPort;

		public ScanPorts(int minPort, int maxPort)
		{
			this.minPort = minPort;
			this.maxPort = maxPort;
		}

		public void run()
		{
			for (int i = minPort; i <= maxPort; i++)
			{
				try
				{

					Socket socket = new Socket();
					SocketAddress socketAddress = new InetSocketAddress(
							"192.168.17.81", i);
					socket.connect(socketAddress, 50);
					handler.sendEmptyMessage(i);
					socket.close();
				}
				catch (Exception e)
				{
					
				}
			}
			handler.post(new Runnable()
			{
				
				@Override
				public void run()
				{
					Toast.makeText(Main.this, "扫描完成.", Toast.LENGTH_LONG).show();
					
				}
			});
		}
	}

	public void onClick_Scan_Server_Ports(View view)
	{
		Thread thread = new Thread(new ScanPorts(1, 1000));
		thread.start();
		Toast.makeText(this, "开始扫描", Toast.LENGTH_LONG).show();

	} 
}