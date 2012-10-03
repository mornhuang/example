using System;
using System.Runtime.Remoting;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class Server
	{
		static void Main(string[] args)
		{
         RemotingConfiguration.Configure("Server.exe.config");


			ActivatedServiceTypeEntry[] entries = RemotingConfiguration.GetRegisteredActivatedServiceTypes();

			foreach (ActivatedServiceTypeEntry entry in entries)
			{
				Console.WriteLine(entry.TypeName.ToString());
				Console.WriteLine(entry.AssemblyName.ToString());
				Console.WriteLine(entry.ObjectType.ToString());
				Console.WriteLine("--");
			}

         Console.WriteLine("Hit to exit");
         Console.ReadLine();
		}
	}
}
