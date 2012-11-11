using System;
using System.Data;
using System.Data.SqlClient;
using System.EnterpriseServices;

namespace Wrox.ProCSharp.ComPlus.JitTestExample
{
//	[JustInTimeActivation(true)]
	
	public class JitTest : ServicedComponent
	{
		public int number = 0 ;
		public JitTest()
		{
			
		}



		
		protected override void Activate()
		{
			Console.WriteLine("Activated!!!");
		}
        
		
		protected override void Deactivate()
		{
			Console.WriteLine("De-Activated!!!");
		}
   
		
		//THIS METHOD WOULD BE INVOKED BY THE CLIENT. 
		public void DoSomething()
		{
			
				Console.WriteLine("This is our JIT object in action - Attempt {0}.",number);
				number++;
		
				ContextUtil.DeactivateOnReturn = true;

		}
	}
}


	
