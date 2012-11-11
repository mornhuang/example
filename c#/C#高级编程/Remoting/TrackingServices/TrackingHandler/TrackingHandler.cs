using System;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Services;
using System.Runtime.Remoting.Channels;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	public class TrackingHandler : ITrackingHandler
	{
		public TrackingHandler()
		{
		}

      public void MarshaledObject(object obj, ObjRef or)
      {
         Console.WriteLine("--- Marshaled Object " +      
            obj.GetType() + " ---");      
         Console.WriteLine("Object URI: " + or.URI);
         object[] channelData = or.ChannelInfo.ChannelData;
         foreach (object data in channelData)
         {
            ChannelDataStore dataStore = data as ChannelDataStore;
            if (dataStore != null)
            {
               foreach (string uri in dataStore.ChannelUris)
               {
                  Console.WriteLine("Channel URI: " + uri);
               }
            }
         }
         Console.WriteLine("---------");
         Console.WriteLine();
      }

      public void UnmarshaledObject(object obj, ObjRef or)
      {
         Console.WriteLine("Unmarshal");
      }

      public void DisconnectedObject(object obj)
      {
         Console.WriteLine("Disconnect");            
      } 
	}
}
