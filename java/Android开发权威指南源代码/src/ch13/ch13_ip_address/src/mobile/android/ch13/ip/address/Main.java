package mobile.android.ch13.ip.address;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketOptions;
import java.util.Enumeration;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_Client_IP(View view)
	{

		try
		{
			TextView tvIPs = (TextView)findViewById(R.id.tvIPs);
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements())
			{
				NetworkInterface ni = netInterfaces.nextElement();

				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements())
				{
					tvIPs.append(ips.nextElement().getHostAddress() + "\n\n");
				}
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
}