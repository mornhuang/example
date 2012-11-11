using System;
using System.Drawing;


namespace ConsoleApplication1
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
			   
			
			   Point topLeft = new Point(10,10);
			   Size rectangleSize = new Size(50,50);
			   Point bottomRight = topLeft + rectangleSize;
			   Console.WriteLine("topLeft = " + topLeft);
			   Console.WriteLine("bottomRight = " + bottomRight);
			   Console.WriteLine("Size = " + rectangleSize);
			


			Console.ReadLine();

				//
			// TODO: Add code to start application here
			//
		}
	}
}
