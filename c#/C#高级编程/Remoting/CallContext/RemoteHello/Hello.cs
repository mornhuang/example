using System;
using System.Runtime.Remoting.Messaging;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	public class Hello : System.MarshalByRefObject
	{
		public Hello()
		{
			Console.WriteLine("Constructor called");
		}

		~Hello()
		{
			Console.WriteLine("Destructor called");
		}

		public string Greeting(string name)
		{
			Console.WriteLine("Greeting called");

         CallContextData cookie =
            (CallContextData)CallContext.GetData("mycookie");
         if (cookie != null)
         {
            Console.WriteLine("Cookie: " + cookie.Data);
         }

			return "Hello, " + name;
		}
	}
}
