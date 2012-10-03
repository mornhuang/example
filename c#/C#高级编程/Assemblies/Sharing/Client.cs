using System;

namespace Wrox.ProCSharp.Assemblies.Sharing
{
	class Client
	{
		[STAThread]
		static void Main(string[] args)
		{
			SimpleShared quotes = new SimpleShared(@"C:\ProCSharp\Assemblies\Quotes.txt");
			for (int i=0; i < 3; i++)
			{
				Console.WriteLine(quotes.GetQuoteOfTheDay());
				Console.WriteLine();
			}
		}
	}
}
