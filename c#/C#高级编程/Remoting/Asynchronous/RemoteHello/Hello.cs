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
         System.Threading.Thread.Sleep(10000);
			return "Hello, " + name;
		}

      [OneWay]
      public void TakeAWhile(int ms)
      {
         Console.WriteLine("TakeAWhile started");
         System.Threading.Thread.Sleep(ms);
         Console.WriteLine("TakeAWhile finished");
      }
	}
}
