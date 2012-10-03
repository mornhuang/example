using System;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Runtime.Remoting.Channels.Http;
using System.Collections;


namespace Wrox.ProCSharp.Remoting
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class HelloServer
	{
		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main(string[] args)
		{

			/* Uncomment out the Setting Channel Properties region for page 15 of the chapter.
			 * Then comment out the TcpChannels region
			 * */

			 
			
			 
			#region Setting Channel Properties
			IDictionary properties = new Hashtable();
			properties["name"] = "TCP Channel with a SOAP Formatter";
			properties["priority"] = "20";
			properties["port"] = "8086";
			SoapServerFormatterSinkProvider sinkProvider = 
				new SoapServerFormatterSinkProvider();
			TcpServerChannel tcpChannel = 
				new TcpServerChannel(properties, sinkProvider);
			ShowChannelProperties(tcpChannel);
			#endregion

			#region TcpChannels

		//	TcpServerChannel tcpChannel = new TcpServerChannel(8086);
		//	ShowChannelProperties(tcpChannel);
			#endregion

			#region HttpChannel
			HttpServerChannel httpChannel = new HttpServerChannel(8085);
			ShowChannelProperties(httpChannel);
			#endregion

			#region RegisterChannels

			ChannelServices.RegisterChannel(tcpChannel);
			ChannelServices.RegisterChannel(httpChannel);

			#endregion


			/*
			HttpServerChannel channel = 
				(HttpServerChannel)ChannelServices.GetChannel("http");
			channel.StopListening(null);
*/
			RemotingConfiguration.RegisterWellKnownServiceType(
				typeof(Hello),
				"Hi",
				WellKnownObjectMode.SingleCall);

			System.Console.WriteLine("hit to exit");
			System.Console.ReadLine();
		}

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

	}
}
