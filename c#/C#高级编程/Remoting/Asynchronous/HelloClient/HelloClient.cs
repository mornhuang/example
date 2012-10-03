using System;
// using System.Threading;
using System.Runtime.Remoting;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class HelloClient
	{
      private delegate String GreetingDelegate(String name);
  //    private GreetingDelegate delegate;
      // private static ManualResetEvent e = new ManualResetEvent(false);
      private static string greeting;


		static void Main(string[] args)
		{
			RemotingConfiguration.Configure("HelloClient.exe.config");

			Hello obj = new Hello();

			if (obj == null)
			{
				Console.WriteLine("could not locate server");
				return;
         }

         // synchronous:
         // greeting = obj.Greeting("Christian");

         // asynchronous:
         GreetingDelegate d = new GreetingDelegate(obj.Greeting);
         Console.WriteLine("Starting remote method");
         IAsyncResult ar = d.BeginInvoke("Christian", null, null);

         // do some work
         Console.WriteLine("Do some other work");

         ar.AsyncWaitHandle.WaitOne();

         if (ar.IsCompleted)
         {
            greeting = d.EndInvoke(ar);
         }

         Console.WriteLine(greeting);

/*
         Console.WriteLine("starting long call");
         obj.TakeAWhile(10000);
         Console.WriteLine("finished long call");
*/
		}
/*
      public static void Callback(IAsyncResult ar)
      {
         if (ar.IsCompleted)
         {
//            GreetingDelegate d = (GreetingDelegate)ar.AsyncObject;
            greeting = d.EndInvoke(ar);
         }

         e.Set();            
      }
*/
	}
}
