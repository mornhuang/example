using System;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Runtime.Remoting.Channels.Http;
using System.Runtime.Remoting.Services;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class HelloClient
	{
		static void Main(string[] args)
		{
         TrackingServices.RegisterTrackingHandler(new TrackingHandler());

			ChannelServices.RegisterChannel(new TcpClientChannel());
			ChannelServices.RegisterChannel(new HttpClientChannel());

			RemotingConfiguration.RegisterWellKnownClientType(
				typeof(Wrox.ProfessionalCSharp.Hello), 
				"tcp://localhost:8086/Hi");

/*			Hello obj = (Hello)Activator.GetObject(
				typeof(Wrox.ProfessionalCSharp.Hello),
				"tcp://localhost:8086/Hi");
*/

			Hello obj = new Hello();
		//	Hello obj = (Hello)RemotingServices.Connect(typeof(Wrox.ProfessionalCSharp.Hello), 
		//		"http://localhost:8085/Hi");

			if (obj == null)
			{
				Console.WriteLine("could not locate server");
				return;
			}

			for (int i=0; i< 5; i++)
			{
				Console.WriteLine(obj.Greeting("Christian"));
			}
		}
	}
}
