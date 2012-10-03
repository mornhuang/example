using System;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Runtime.Remoting.Channels.Http;
using Wrox.ProCSharp.Remoting;

namespace Wrox.ProCSharp.Remoting
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class Class1
	{
		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main(string[] args)
		{
			ChannelServices.RegisterChannel(new TcpClientChannel());
			ChannelServices.RegisterChannel(new HttpClientChannel());

			#region Using RemotingConfiguration

			// a) Using RemotingConfiguration
			// RemotingConfiguration.RegisterWellKnownClientType(
			// 	typeof(Wrox.ProfessionalCSharp.Hello), 
			//	"tcp://localhost:8086/Hi");

			// Hello obj = new Hello();

			#endregion

			#region Using Activator.GetObject

			// b) using Activator.GetObject
			// Hello obj = (Hello)Activator.GetObject(
			//				typeof(Hello),
			//				"tcp://localhost:8086/Hi");
			
			#endregion

			#region UsingRemotingServices
			// c) Using RemotingServices
				Hello obj = (Hello)RemotingServices.Connect(typeof(Hello), 
					"http://localhost:8085/Hi");

			#endregion


			if (obj == null)
			{
				Console.WriteLine("could not locate server");
				return;
			}
			for (int i=0; i< 5; i++)
			{
				Console.WriteLine(obj.Greeting("Christian"));
			}
			Console.ReadLine();
		}
	}
}
