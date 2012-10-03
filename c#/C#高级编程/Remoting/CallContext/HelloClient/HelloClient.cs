using System;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Messaging;

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

         CallContextData cookie = new CallContextData();
         cookie.Data = "information for the server";
         CallContext.SetData("mycookie", cookie); 
         for (int i=0; i< 5; i++)
         {
            Console.WriteLine(obj.Greeting("Christian"));
         }


			for (int i=0; i< 5; i++)
			{
				Console.WriteLine(obj.Greeting("Christian"));
			}
		}
	}
}
