using System;
using System.Text;
using System.Net;
using System.Net.Sockets;

namespace Wrox.ProCSharp.InternetAccess.UdpExample
{
    
	class Class1
	{
		[STAThread]
		static void Main(string[] args)
		{
			UdpClient udpClient = new UdpClient();

			string sendMsg = "Hello Echo Server";
			byte [] sendBytes = Encoding.ASCII.GetBytes(sendMsg);

			udpClient.Send(sendBytes, sendBytes.Length, "localhost", 7);

			IPEndPoint endPoint = new IPEndPoint(0,0);
			byte [] rcvBytes = udpClient.Receive(ref endPoint);
			string rcvMessage = Encoding.ASCII.GetString(rcvBytes, 0, rcvBytes.Length);

			// should print out "Hello Echo Server"
			Console.WriteLine(rcvMessage);
			Console.ReadLine();
		}
	}
}
