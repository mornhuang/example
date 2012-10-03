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

namespace PCSWebApp2
{
	/// <summary>
	/// Summary description for WebForm1.
	/// </summary>
	public class WebForm1 : System.Web.UI.Page
	{
		protected System.Web.UI.WebControls.TextBox nameBox;
		protected System.Web.UI.WebControls.RequiredFieldValidator validateName;
		protected System.Web.UI.WebControls.Calendar calendar;
		protected System.Web.UI.WebControls.TextBox eventBox;
		protected System.Web.UI.WebControls.RequiredFieldValidator validateEvent;
		protected System.Web.UI.WebControls.DropDownList roomList;
		protected System.Web.UI.WebControls.RequiredFieldValidator validateRoom;
		protected System.Web.UI.WebControls.ListBox attendeeList;
		protected System.Web.UI.WebControls.RequiredFieldValidator validateAttendees;
		protected System.Web.UI.WebControls.Button submitButton;
		protected System.Web.UI.WebControls.ValidationSummary validationSummary;
		protected System.Web.UI.WebControls.Label resultLabel;
	
		private void Page_Load(object sender, System.EventArgs e)
		{
			// Put user code to initialize the page here
			if (!this.IsPostBack)
			{
				calendar.SelectedDate = System.DateTime.Now;
			}
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
			this.submitButton.Click += new System.EventHandler(this.submitButton_Click);
			this.Load += new System.EventHandler(this.Page_Load);

		}
		#endregion

		private void submitButton_Click(object sender, System.EventArgs e)
		{
			if (this.IsValid)
			{
				resultLabel.Text = roomList.SelectedItem.Text +
					" has been booked on " +
					calendar.SelectedDate.ToLongDateString() +
					" by " + nameBox.Text + " for " +
					eventBox.Text + " event. ";
				foreach (ListItem attendee in attendeeList.Items)
				{
					if (attendee.Selected)
					{
						resultLabel.Text += attendee.Text + ", ";
					}
				}
				resultLabel.Text += " and " + nameBox.Text +
					" will be attending.";
			}
		}
	}
}
