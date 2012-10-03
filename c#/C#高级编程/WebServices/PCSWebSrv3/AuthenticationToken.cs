using System;
using System.Web.Services.Protocols;

namespace PCSWebSrv3
{
	/// <summary>
	/// Summary description for AuthenticationToken.
	/// </summary>
	public class AuthenticationToken : SoapHeader
	{
		public Guid InnerToken;
	}
}
