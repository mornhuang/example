using System;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Web;
using System.Web.Services;
using System.Web.Services.Protocols;

namespace PCSWebSrv3
{
	/// <summary>
	/// Summary description for Service1.
	/// </summary>
	public class Service1 : System.Web.Services.WebService
	{
		public AuthenticationToken AuthenticationTokenHeader;

		public Service1()
		{
			//CODEGEN: This call is required by the ASP.NET Web Services Designer
			InitializeComponent();
		}

		#region Component Designer generated code
		
		//Required by the Web Services Designer 
		private IContainer components = null;
				
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if(disposing && components != null)
			{
				components.Dispose();
			}
			base.Dispose(disposing);		
		}
		
		#endregion

		// WEB SERVICE EXAMPLE
		// The HelloWorld() example service returns the string Hello World
		// To build, uncomment the following lines then save and build the project
		// To test this web service, press F5

		[WebMethod]
		public Guid Login(string userName, string password)
		{
			if ((userName == "Karli") && (password == "Cheese"))
			{
				Guid currentUser = Guid.NewGuid();
				Application["currentUser"] = currentUser;
				return currentUser;
			}
			else
			{
				Application["currentUser"] = Guid.Empty;
				return Guid.Empty;
			}
		}

		[WebMethod]
		[SoapHeaderAttribute("AuthenticationTokenHeader",
			 Direction = SoapHeaderDirection.In,
			 Required = true)]
		public string DoSomething()
		{
			if ((AuthenticationTokenHeader.InnerToken == (Guid)Application["currentUser"]) && (AuthenticationTokenHeader.InnerToken != Guid.Empty))
			{
				return "Authentication OK.";
			}
			else
			{
				return "Authentication failed.";
			}
		}
	}
}
