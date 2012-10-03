using System;
using System.Globalization;
using System.Threading;

namespace Wrox.ProCSharp.Assemblies.LocalNumbersExample
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
			int val = 1234567890;

			// culture of the current thread

			Console.WriteLine(val.ToString("N"));

			// use IFormatProvider

			Console.WriteLine(val.ToString("N", 
				new CultureInfo("fr-fr")));

			// change the culture of the thread

			Thread.CurrentThread.CurrentCulture = 
				new CultureInfo("de-de");
			Console.WriteLine(val.ToString("N"));
		}
	}
}
