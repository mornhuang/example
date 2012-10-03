using System;

namespace Wrox.ProCSharp.WinServices
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
			QuoteServer qs = new QuoteServer(@"c:\ProCSharp\WinServices\quotes.txt", 4567);
			qs.Start();
			Console.WriteLine("Hit return to exit");
			Console.ReadLine();
			qs.Stop();
		}
	}
}
