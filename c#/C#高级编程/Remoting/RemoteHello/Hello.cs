using System;

namespace Wrox.ProCSharp.Remoting
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
			return "Hello, " + name;
		}
	}
}
