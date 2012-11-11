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

namespace PCSUserCWebApp1
{
	/// <summary>
	/// Summary description for WebForm1.
	/// </summary>
	 
	

	public class WebForm1 : System.Web.UI.Page
	{
		protected System.Web.UI.WebControls.RadioButtonList suitList;
		protected System.Web.UI.WebControls.ImageButton clubButton;
		protected System.Web.UI.WebControls.ImageButton diamondButton;
		protected System.Web.UI.WebControls.ImageButton heartButton;
		protected System.Web.UI.WebControls.ImageButton spadeButton;
		protected PCSUserC1 myUserControl;

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
			this.suitList.SelectedIndexChanged += new System.EventHandler(this.suitList_SelectedIndexChanged);
			this.clubButton.Click += new System.Web.UI.ImageClickEventHandler(this.clubButton_Click);
			this.diamondButton.Click += new System.Web.UI.ImageClickEventHandler(this.diamondButton_Click);
			this.heartButton.Click += new System.Web.UI.ImageClickEventHandler(this.heartButton_Click);
			this.spadeButton.Click += new System.Web.UI.ImageClickEventHandler(this.spadeButton_Click);
			this.Load += new System.EventHandler(this.Page_Load);

		}
		#endregion

		private void suitList_SelectedIndexChanged(object sender, System.EventArgs e)
		{
			myUserControl.Suit = (suit)Enum.Parse(typeof(suit), suitList.SelectedItem.Value);
		}

		protected void clubButton_Click(object sender, System.Web.UI.ImageClickEventArgs e)
		{
			myUserControl.Club();
			suitList.SelectedIndex = 0;
		}

		protected void diamondButton_Click(object sender, System.Web.UI.ImageClickEventArgs e)
		{
			myUserControl.Diamond();
			suitList.SelectedIndex = 1;
		}

		protected void heartButton_Click(object sender, System.Web.UI.ImageClickEventArgs e)
		{
			myUserControl.Heart();
			suitList.SelectedIndex = 2;
		}

		protected void spadeButton_Click(object sender, System.Web.UI.ImageClickEventArgs e)
		{
			myUserControl.Spade();
			suitList.SelectedIndex = 3;
		}
	}
}
