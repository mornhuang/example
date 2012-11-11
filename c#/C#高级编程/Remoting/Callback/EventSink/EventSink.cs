using System;
using System.Runtime.Remoting.Messaging;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	public class EventSink : MarshalByRefObject
	{
		public EventSink()
		{
		}

      [OneWay]
      public void StatusHandler(object sender, StatusEventArgs e)
      {
         Console.WriteLine("EventSink: Event occurred: " + e.Message);
      }
	}
}
