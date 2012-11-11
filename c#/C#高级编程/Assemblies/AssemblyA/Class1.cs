using System;

namespace Wrox.ProCSharp.Assemblies.AppDomains
{
	class Class1
	{
		public Class1(int val1, int val2)
		{
			Console.WriteLine("Constructor with the values {0}, {1}" +
				" in domain {2} called", val1, val2,
				AppDomain.CurrentDomain.FriendlyName);
		}
		[STAThread]
		static void Main(string[] args)
		{
			Console.WriteLine("Main in domain {0} called",
				AppDomain.CurrentDomain.FriendlyName);
		}
	}
}
