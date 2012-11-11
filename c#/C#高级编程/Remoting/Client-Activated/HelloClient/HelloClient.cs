using System;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Runtime.Remoting.Channels.Http;
using System.Runtime.Remoting.Activation;
using System.Runtime.Remoting.Proxies;
using System.Runtime.Remoting.Lifetime;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class HelloClient
	{
		static void Main(string[] args)
		{
			ChannelServices.RegisterChannel(new TcpClientChannel());
		//	ChannelServices.RegisterChannel(new HttpClientChannel());

			// using the Activator class to create remote objects
			
			object[] attrs = {new UrlAttribute("tcp://localhost:8086/HelloServer") };
			ObjectHandle handle = Activator.CreateInstance(
				"RemoteHello", "Wrox.ProfessionalCSharp.Hello", attrs);

	//		Hello obj = (Hello)Activator.CreateInstance(typeof(Wrox.ProfessionalCSharp.Hello),
	//			null, attrs);
			if (handle == null)
			{
				Console.WriteLine("could not locate server");
				return;
			}
			Hello obj = (Hello)handle.Unwrap();


			// using the new operator to create remote objects
	/*		RemotingConfiguration.RegisterActivatedClientType(
				typeof(Wrox.ProfessionalCSharp.Hello),
				"tcp://localhost:8086/HelloServer");

			Hello obj = new Hello();
*/
			if (RemotingServices.IsTransparentProxy(obj))
			{
				//Console.WriteLine("Using a transparent proxy");
	
				RealProxy proxy = RemotingServices.GetRealProxy(obj);

				// proxy.Invoke(message);
			}

			ILease lease = (ILease)obj.GetLifetimeService();
			if (lease != null)
			{
				Console.WriteLine("Lease Configuration:");
				Console.WriteLine("InitialLeaseTime: " +
					lease.InitialLeaseTime);
				Console.WriteLine("RenewOnCallTime: " +
					lease.RenewOnCallTime);
				Console.WriteLine("SponsorshipTimeout: " +
					lease.SponsorshipTimeout);
				Console.WriteLine(lease.CurrentLeaseTime);
			}


		   Console.WriteLine(obj.Greeting("Christian"));
/*
			for (int i=0; i< 5; i++)
			{
				Console.WriteLine(obj.Greeting("Christian"));
			}*/
		}
	}
}
