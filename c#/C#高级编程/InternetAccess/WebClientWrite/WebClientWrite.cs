using System;
using System.Net;
using System.IO;

namespace Wrox.ProCSharp.InternetAccess.WebWrite
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
			//
			// TODO: Add code to start application here
			//
			WebClient webClient = new WebClient();
			try
			{

				Stream stream = webClient.OpenWrite("http://localhost/accept/newfile.txt","PUT");
				StreamWriter streamWriter = new StreamWriter(stream);
			
				
   				streamWriter.WriteLine("Hello there, World....");
				streamWriter.Close();
			}
			catch (Exception e) {Console.WriteLine( e.ToString() ); }
			Console.ReadLine();

		}
	}
}
