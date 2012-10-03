using System;
using System.Runtime.Remoting;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class HelloClient
	{
		static void Main(string[] args)
		{
			RemotingConfiguration.Configure("HelloClient.exe.config");

			Hello obj = new Hello();

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
