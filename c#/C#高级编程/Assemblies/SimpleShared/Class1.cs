using System;
using System.Collections.Specialized;
using System.IO;
namespace Wrox.ProCSharp.Assemblies.Sharing
{
	public class SimpleShared
	{
		private StringCollection quotes;
		private Random random;
		public SimpleShared(string filename)
		{
			quotes = new StringCollection();
			Stream stream = File.OpenRead(filename);
			StreamReader streamReader = new StreamReader(stream);
			string quote;
			while ((quote = streamReader.ReadLine()) != null)
			{
				quotes.Add(quote);
			}
			streamReader.Close();
			stream.Close();
			random = new Random();
		}
		public string GetQuoteOfTheDay()
		{
			int index = random.Next(1, quotes.Count);
			return quotes[index];
		}
	}
}
