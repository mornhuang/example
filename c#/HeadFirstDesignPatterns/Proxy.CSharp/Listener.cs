using System;
using System.Runtime.Remoting;

namespace HeadFirstDesignPatterns.Proxy.CSharp
{
	/// <summary>
	/// Summary description for Listener.
	/// </summary>
	public class Listener
	{
			public static void Main()
			{
				RemotingConfiguration.Configure("Listener.exe.config");
				Console.WriteLine("Listening for requests. Press Enter to exit...");
				Console.ReadLine();
			}
	}
}
