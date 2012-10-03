using System;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Web;
using System.Web.SessionState;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.HtmlControls;
using PCSWebClient3.authenticateService;

namespace PCSWebClient3
{
	/// <summary>
	/// Summary description for WebForm1.
	/// </summary>
	public class WebForm1 : System.Web.UI.Page
	{
		protected System.Web.UI.WebControls.TextBox userNameBox;
		protected System.Web.UI.WebControls.TextBox passwordBox;
		protected System.Web.UI.WebControls.Button loginButton;
		protected System.Web.UI.WebControls.Label tokenLabel;
		protected System.Web.UI.WebControls.Button invokeButton;
		protected System.Web.UI.WebControls.Label resultLabel;
		protected Service1 myService;
		protected bool authenticated;
	
		private void Page_Load(object sender, System.EventArgs e)
		{
			myService = new Service1();
			AuthenticationToken header = new AuthenticationToken();
			if (ViewState["AuthenticationTokenHeader"] != null)
			{
				header.InnerToken = (Guid)ViewState["AuthenticationTokenHeader"];
			}
			else
			{
				header.InnerToken = Guid.Empty;
			}
			myService.AuthenticationTokenValue = header;
		}

		#region Web Form Designer generated code
		override protected void OnInit(EventArgs e)
		{
			//
			// CODEGEN: This call is required by the ASP.NET Web Form Designer.
			//
			InitializeComponent();
			base.OnInit(e);
		}
		
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{    
			this.loginButton.Click += new System.EventHandler(this.loginButton_Click);
			this.invokeButton.Click += new System.EventHandler(this.invokeButton_Click);
			this.Load += new System.EventHandler(this.Page_Load);

		}
		#endregion

		private void loginButton_Click(object sender, System.EventArgs e)
		{
			Guid authenticationTokenHeader = myService.Login(userNameBox.Text, passwordBox.Text);
			tokenLabel.Text = authenticationTokenHeader.ToString();
			ViewState.Add("AuthenticationTokenHeader", authenticationTokenHeader);
		}

		private void invokeButton_Click(object sender, System.EventArgs e)
		{
			resultLabel.Text = myService.DoSomething();
		}

	}
}
