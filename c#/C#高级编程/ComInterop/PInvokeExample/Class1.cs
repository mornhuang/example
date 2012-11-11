using System;
using System.Runtime.InteropServices;

namespace Wrox.ProCSharp.ComInterop.UnmanagedExample
{
	class PInvokeExample
	{
		//Declare the external, unmanaged function.
		//[DllImport ("user32.dll")]
		// Uncomment the next line and comment the above line to create an alias
		[DllImport("user32.dll",EntryPoint="MessageBoxA")]
		public static extern int ErrorMessage (int Modal,
			string Message,
			string Caption,
			int Options);
   
		//Program starts here.
		public static void Main (string[] args)
		{
			//Invoke the unmanaged function using PInvoke.
			//MessageBoxA(0,"PInvoke worked!","PInvoke Example",0);
			// Uncomment the next line, and comment the above line to use the alias
		   ErrorMessage(0, "PInvoke worked with alias name!", "PInvoke Example",0);
		}
	}
}
