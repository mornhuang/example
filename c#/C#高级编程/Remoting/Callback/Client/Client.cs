using System;
using System.Runtime.Remoting;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class Client
	{
		[STAThread]
		static void Main(string[] args)
		{
         RemotingConfiguration.Configure("Client.exe.config");

         EventSink sink = new EventSink();

         RemoteObject obj = new RemoteObject();

         // register client sink in server - subscribe to event
         obj.Status += new StatusEvent(sink.StatusHandler);

         obj.LongWorking(5000);

         // unsubscribe to event
         obj.Status -= new StatusEvent(sink.StatusHandler);

         obj.LongWorking(5000);

         Console.WriteLine("Hit to exit");
         Console.ReadLine();
		}
	}
}
