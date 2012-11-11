using System;

namespace Wrox.ProfessionalCSharp
{
   [Serializable]
   public class StatusEventArgs
   {
      public StatusEventArgs(string m)
      {
         message = m;
      }

      public string Message
      {
         get
         {
            return message;
         }
         set
         {
            message = value;
         }
      }

      private string message;
   }


   public delegate void StatusEvent(object sender, StatusEventArgs e);

	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	public class RemoteObject : MarshalByRefObject
	{
		public RemoteObject()
		{
         Console.WriteLine("RemoteObject constructor called");
		}

      public event StatusEvent Status;

      public void LongWorking(int ms)
      {
         Console.WriteLine("RemoteObject: LongWorking() Started");

         StatusEventArgs e = new StatusEventArgs(
            "Message for Client: LongWorking() Started");
         // fire event
         if (Status != null)
         {
            Console.WriteLine("RemoteObject: Firing Starting Event");
            Status(this, e);
         }

         System.Threading.Thread.Sleep(ms);

         e.Message = "Message for Client: LongWorking() Ending";
         // fire ending event
         if (Status != null)
         {
            Console.WriteLine("RemoteObject: Firing Ending Event");
            Status(this, e);
         }

         Console.WriteLine("RemoteObject: LongWorking() Ending");
      }
	}
}
