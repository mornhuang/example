using System;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Runtime.Remoting.Channels.Http;

namespace Wrox.ProCSharp.Remoting.PassingObjects
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class HelloClient
	{
		[STAThread]
		static void Main(string[] args)
		{
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

			MySerialized ser = obj.GetMySerialized();
			if (!RemotingServices.IsTransparentProxy(ser))
			{
				Console.WriteLine("ser is not a transparent proxy");
			}
			ser.Foo();

			MyRemote rem = obj.GetMyRemote();
			if (RemotingServices.IsTransparentProxy(rem))
			{
				Console.WriteLine("rem is a transparent proxy");
			}
			rem.Foo();
/*
			MySerialized ser1 = new MySerialized(1);
			obj.InSerialized(ser1);
			Console.WriteLine(ser1.A);

			MySerialized ser2 = new MySerialized(2);
			obj.RefSerialized(ref ser2);
			Console.WriteLine(ser2.A);

			MySerialized ser3;
			obj.OutSerialized(out ser3);
			Console.WriteLine(ser3.A);
*/
		}
	}
}
