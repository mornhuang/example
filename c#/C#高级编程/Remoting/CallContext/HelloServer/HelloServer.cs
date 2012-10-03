using System;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Http;
using System.Runtime.Remoting.Contexts;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class HelloServer
	{
		public static void Main(string[] args)
		{
			RemotingConfiguration.Configure("HelloServer.exe.config");	

			Console.WriteLine("Application: " + RemotingConfiguration.ApplicationName);
			ShowActivatedServiceTypes();
			ShowWellKnownServiceTypes();

			System.Console.WriteLine("hit to exit");
			System.Console.ReadLine();
		}

		public static void ShowWellKnownServiceTypes()
		{
			WellKnownServiceTypeEntry[] entries = RemotingConfiguration.GetRegisteredWellKnownServiceTypes();

			foreach (WellKnownServiceTypeEntry entry in entries)
			{
				Console.WriteLine("Assembly: " + entry.AssemblyName);
				Console.WriteLine("Mode: " + entry.Mode);
				Console.WriteLine("URI: " + entry.ObjectUri);
				Console.WriteLine("Type: " + entry.TypeName);

				IContextAttribute[] attributes = entry.ContextAttributes;
				if (attributes != null)
				{
					foreach (ContextAttribute attribute in attributes)
					{
						Console.WriteLine("Context attribute: " + attribute.Name);
					}
				}
			}
		}

		public static void ShowActivatedServiceTypes()
		{
			ActivatedServiceTypeEntry[] entries = RemotingConfiguration.GetRegisteredActivatedServiceTypes();

         IChannel[] channels = ChannelServices.RegisteredChannels;

         foreach (IChannel ch in channels)
         {
            HttpChannel httpChannel = ch as HttpChannel;
            if (httpChannel != null)
            {
               ChannelDataStore store = httpChannel.ChannelData as ChannelDataStore;
               if (store != null)
               {
                  foreach (string uri in store.ChannelUris)
                     Console.WriteLine(uri);
               }
            }
         }

			foreach (ActivatedServiceTypeEntry entry in entries)
			{
				Console.WriteLine("Assembly: " + entry.AssemblyName);
				Console.WriteLine("Type: " + entry.TypeName);
				IContextAttribute[] attributes = entry.ContextAttributes;
            if (attributes != null)
            {
               foreach (ContextAttribute attribute in attributes)
               {
                  Console.WriteLine("context attribute: " + attribute.Name);
               }
            }
			}
		}
	}
}
