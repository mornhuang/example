using System;
using System.Runtime.Remoting.Messaging;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	///
   [Serializable]
	public class CallContextData : ILogicalThreadAffinative
	{
		public CallContextData()
		{
		}

      public string Data
      {
         get
         {
            return data;
         }
         set
         {
            data = value;
         }
      }

      protected string data;
	}
}
