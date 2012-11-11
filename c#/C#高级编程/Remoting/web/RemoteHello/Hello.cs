using System;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	public class Hello : System.MarshalByRefObject
	{
		public Hello()
		{
		}

		~Hello()
		{
		}

		public string Greeting(string name)
		{
			return "Hello, " + name;
		}
	}
}
