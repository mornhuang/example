using System;
using System.Net;



namespace Wrox.ProCSharp.AccessInternet.IPAddressExample
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
			//
			// TODO: Add code to start application here
			//

			IPAddress ipAddress = IPAddress.Parse("234.56.78.9");
			long  address = ipAddress.Address;          // address will be assigned 37105130
			string ipString = ipAddress.ToString(); 
			Console.WriteLine(ipString);
			Console.WriteLine(address.ToString());
			//IPHostEntry HostEntry = new IPHostEntry();

			IPHostEntry wroxHost = Dns.Resolve("www.wrox.com");
			IPHostEntry wroxHostCopy = Dns.GetHostByAddress("204.148.170.161");
			
			Console.WriteLine(wroxHostCopy.HostName.ToString());

			Console.ReadLine();

			// ipString will be assigned
			// the text "234.45.54.2" 

		}
	}
}
