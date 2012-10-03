using System;
using System.Runtime.Remoting;

namespace HeadFirstDesignPatterns.Proxy.CSharp
{
	/// <summary>
	/// Summary description for Client.
	/// </summary>
	public class Client
	{
		public static void Main()
		{
			RemotingConfiguration.Configure("Client.exe.config");
			RemotableType remoteObject = new RemotableType();
			Console.WriteLine(remoteObject.StringMethod());
		}
	}
}
