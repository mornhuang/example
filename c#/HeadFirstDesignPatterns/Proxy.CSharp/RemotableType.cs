using System;
using System.Text;

namespace HeadFirstDesignPatterns.Proxy.CSharp
{
	/// <summary>
	/// Summary description for RemotableType.
	/// </summary>
	public class RemotableType: MarshalByRefObject
	{
		public string StringMethod()
		{
			return GetRemoteMessage();
		}

		private string GetRemoteMessage()
		{
			StringBuilder internalString = new StringBuilder();
			internalString.Append("\n");
			internalString.Append("**********************************************\n");
			internalString.Append("***********Head First Design Patterns*********\n");
			internalString.Append("**********************************************\n");
			internalString.Append("This is a remote message from\n");
			internalString.Append("the RemotableType as an example\n");
			internalString.Append(".Net remoting which utilizes the\n");
			internalString.Append("proxy pattern like the gumball example. Go to\n");
			internalString.Append("http://msdn.microsoft.com/library/default.asp?url=/library/en-us/cpguide/html/cpconnetremotingarchitecture.asp\n");
			internalString.Append("For more information.\n");
			internalString.Append("**********************************************\n");

			return internalString.ToString();
		}
	}
}
