using System;

namespace Wrox.ProCSharp.Assemblies.AppDomains
{
	class Test
	{
		[STAThread]
		static void Main(string[] args)
		{
			AppDomain currentDomain = AppDomain.CurrentDomain;
			Console.WriteLine(currentDomain.FriendlyName);
			AppDomain secondDomain = AppDomain.CreateDomain("New AppDomain");

			// Comment out this next line for the 2nd Example
			secondDomain.ExecuteAssembly("AssemblyA.exe");

			// Uncomment the next block, and comment out the above line to run the 2nd Example
			/*
			secondDomain.CreateInstance("AssemblyA",
				"Wrox.ProCSharp.Assemblies.AppDomains.Class1", true,
				System.Reflection.BindingFlags.CreateInstance,
				null, new object[] {7, 3}, null, null, null);

			*/

		}
	}
}
