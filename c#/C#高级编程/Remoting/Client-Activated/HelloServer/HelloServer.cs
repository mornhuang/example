using System;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Runtime.Remoting.Channels.Http;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class HelloServer
	{
		protected static void ShowChannelProperties(IChannelReceiver channel)
		{
			Console.WriteLine("Name: " + channel.ChannelName);
			Console.WriteLine("Priority: " + channel.ChannelPriority);

			if (channel is HttpChannel)
			{
				HttpChannel httpChannel = channel as HttpChannel;
				Console.WriteLine("Scheme: " + httpChannel.ChannelScheme);
			}

			ChannelDataStore data = (ChannelDataStore)channel.ChannelData;
			foreach (string uri in data.ChannelUris)
			{
				Console.WriteLine("URI: " + uri);
			}
			Console.WriteLine();
		}

		public static void Main(string[] args)
		{
			TcpServerChannel tcpChannel = new TcpServerChannel(8086);

			ShowChannelProperties(tcpChannel);

			HttpServerChannel httpChannel = new HttpServerChannel(8085);

			ShowChannelProperties(httpChannel);

			ChannelServices.RegisterChannel(tcpChannel);
			ChannelServices.RegisterChannel(httpChannel);

			Console.WriteLine(RemotingConfiguration.ApplicationName);
			RemotingConfiguration.ApplicationName = "HelloServer";

			RemotingConfiguration.RegisterActivatedServiceType(
				typeof(Hello));

			System.Console.WriteLine("hit to exit");
			System.Console.ReadLine();
		}
	}
}
