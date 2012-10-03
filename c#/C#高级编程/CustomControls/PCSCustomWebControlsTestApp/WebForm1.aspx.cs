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
using PCSCustomWebControls;

namespace PCSCustomWebControlsTestApp
{
	/// <summary>
	/// Summary description for WebForm1.
	/// </summary>
	public class WebForm1 : System.Web.UI.Page
	{
		protected System.Web.UI.WebControls.Label resultLabel;
		protected PCSCustomWebControls.StrawPoll strawPoll1;
	
		private void Page_Load(object sender, System.EventArgs e)
		{
			// Put user code to initialize the page here
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
			this.strawPoll1.Voted += new PCSCustomWebControls.CandidateEventHandler(this.strawPoll1_Voted);
			this.Load += new System.EventHandler(this.Page_Load);

		}
		#endregion

		protected void strawPoll1_Voted(object sender, CandidateEventArgs e)
		{
			resultLabel.Text = "You voted for " + e.OriginatingCandidate.Name + ".";
		}

//		protected void cycleButton_Click(object sender, System.EventArgs e)
//		{
//			this.rainbowLabel1.Cycle();
//		}
	}
}
