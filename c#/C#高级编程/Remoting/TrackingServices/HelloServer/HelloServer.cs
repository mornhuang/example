using System;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Runtime.Remoting.Channels.Http;
using System.Runtime.Remoting.Services;

using System.Collections.Specialized;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class HelloServer
	{
      public static void ShowChannelProperties(IChannelReceiver channel)
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
         TrackingServices.RegisterTrackingHandler(new TrackingHandler());

			TcpServerChannel tcpChannel = new TcpServerChannel(8086);

//			HttpServerChannel httpChannel = new HttpServerChannel(8085);


         Console.WriteLine();
/*
			foreach (object o in httpChannel)
			{
				Console.WriteLine(o);
			}
			foreach (object key in httpChannel.Keys)
			{
				Console.WriteLine(key + ": " + httpChannel[key]);
			}
*/

			ChannelServices.RegisterChannel(tcpChannel);
		//	ChannelServices.RegisterChannel(httpChannel);

			RemotingConfiguration.RegisterWellKnownServiceType(
				typeof (Wrox.ProfessionalCSharp.Hello), // Type
				"Hi",									// URI
				WellKnownObjectMode.SingleCall);		// Mode

			System.Console.WriteLine("hit to exit");
			System.Console.ReadLine();
		}

	}
}
