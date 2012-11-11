using System;
using Wrox.ProCSharp.ComPlus.JitTestExample;

namespace Wrox.ProCSharp.ComPlus.JitTestClient
{
	class Class1
	{
      
		[STAThread]
		static void Main(string[] args)
		{
			Console.WriteLine("There is no JIT object yet....");
			JitTest jitObj = new JitTest();
			Console.WriteLine("Now we call a method on our JIT object.");
			jitObj.DoSomething();
	
			Console.WriteLine("We have returned from the method....");
			jitObj.DoSomething();

			Console.ReadLine();
		}
	}
}
