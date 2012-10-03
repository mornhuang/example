using System;
using System.Runtime.Remoting.Lifetime;

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
			return "Hello, " + name;
		}

	/*	public override Object InitializeLifetimeService()
		{
			ILease lease = (ILease)base.InitializeLifetimeService();
			lease.InitialLeaseTime = TimeSpan.FromMinutes(10);
			lease.RenewOnCallTime = TimeSpan.FromSeconds(40);

			return lease;
		} */
	}
}
