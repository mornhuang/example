using System;
using Wrox.ProCSharp.ComPlus.TransactionTest;

namespace Wrox.ProCSharp.ComPlus.TransactionTestClient
{
	class Class1
	{
      
		[STAThread]
		static void Main(string[] args)
		{
			TxTest txObj = new TxTest();
			bool result = txObj.OrderProduct(1,20);
			Console.WriteLine("OrderProduct returned {0} for 20 orders.", result);
			result = txObj.OrderProduct(1,20);
			Console.WriteLine("OrderProduct returned {0} for a further 20 "+
				"orders.", result);
			Console.ReadLine();
		}
	}
}
