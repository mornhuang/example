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

namespace PCSWebApp3
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
		protected System.Data.DataSet ds;
		protected System.Data.DataTable eventTable;
		protected System.Data.OleDb.OleDbDataAdapter daAttendees;
		protected System.Data.OleDb.OleDbDataAdapter daRooms;
		protected System.Web.UI.WebControls.DataGrid eventDetails1;
		protected System.Web.UI.WebControls.DataList eventDetails2;
		protected System.Web.UI.WebControls.Label edName;
		protected System.Web.UI.WebControls.Label edDate;
		protected System.Web.UI.WebControls.Label edRoom;
		protected System.Web.UI.WebControls.Label edAttendees;
		protected System.Data.OleDb.OleDbDataAdapter daEvents;
		protected System.Data.OleDb.OleDbConnection oleDbConnection1;
	
		private void Page_Load(object sender, System.EventArgs e)
		{
			// Put user code to initialize the page here
			oleDbConnection1.Open();
			ds = new DataSet();
			daAttendees = new System.Data.OleDb.OleDbDataAdapter("SELECT * FROM Attendees", oleDbConnection1);
			daRooms = new System.Data.OleDb.OleDbDataAdapter("SELECT * FROM Rooms", oleDbConnection1);
			daEvents = new System.Data.OleDb.OleDbDataAdapter("SELECT * FROM Events", oleDbConnection1);

			daAttendees.Fill(ds, "Attendees");
			daRooms.Fill(ds, "Rooms");
			daEvents.Fill(ds, "Events");
			attendeeList.DataSource = ds.Tables["Attendees"];
			roomList.DataSource = ds.Tables["Rooms"];
			eventTable = ds.Tables["Events"];
			eventDetails1.DataSource = eventTable;
			eventDetails2.DataSource = eventTable;

			if (!this.IsPostBack)
			{
				System.DateTime trialDate = System.DateTime.Now;
				calendar.SelectedDate = getFreeDate(trialDate);
				this.DataBind();
			}
			else
			{
				eventDetails1.DataBind();
				eventDetails2.DataBind();
			}
			oleDbConnection1.Close();
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
			this.oleDbConnection1 = new System.Data.OleDb.OleDbConnection();
			this.calendar.SelectionChanged += new System.EventHandler(this.calendar_SelectionChanged);
			this.submitButton.Click += new System.EventHandler(this.submitButton_Click);
			this.eventDetails2.SelectedIndexChanged += new System.EventHandler(this.eventDetails2_SelectedIndexChanged);
			this.Load += new System.EventHandler(this.Page_Load);
			this.oleDbConnection1.ConnectionString = @"Provider=Microsoft.Jet.OLEDB.4.0;Password="""";User ID=Admin;Data " +
										             "Source=C:\\Inetpub\\wwwroot\\PCSWebApp3\\PCSWebApp3.mdb";

		}
		#endregion

		private void submitButton_Click(object sender, System.EventArgs e)
		{
			if (this.IsValid)
			{
				String attendees = "";
				foreach (ListItem attendee in attendeeList.Items)
				{
					if (attendee.Selected)
					{
						attendees += attendee.Text + " (" + attendee.Value + "), ";
					}
				}
				attendees += " and " + nameBox.Text;
				String dateString = calendar.SelectedDate.Date.Date.ToShortDateString();
				String oleDbCommand = "INSERT INTO Events (Name, Room, " +
										"AttendeeList, EventDate) VALUES ('" +
										eventBox.Text + "', '" +
										roomList.SelectedItem.Value + "', '" +
										attendees + "', '" + dateString + "')";
				System.Data.OleDb.OleDbCommand insertCommand = new System.Data.OleDb.OleDbCommand(oleDbCommand, oleDbConnection1);
				oleDbConnection1.Open();
				int queryResult = insertCommand.ExecuteNonQuery();
				if (queryResult == 1)
				{
					resultLabel.Text = "Event Added.";
					daEvents = new System.Data.OleDb.OleDbDataAdapter("SELECT * FROM Events", oleDbConnection1);
					ds.Clear();
					daEvents.Fill(ds, "Events");
					eventTable = ds.Tables["Events"];
					calendar.SelectedDate = getFreeDate(calendar.SelectedDate.AddDays(1));
					eventDetails1.DataBind();
					eventDetails2.DataBind();
				}
				else
				{
					resultLabel.Text = "Event not added due to DB access problem.";
				}
				oleDbConnection1.Close();
			}
		}

		private System.DateTime getFreeDate(System.DateTime trialDate)
		{
			if (eventTable.Rows.Count > 0)
			{
				System.DateTime testDate;
				bool trialDateOK = false;
				while (!trialDateOK)
				{
					trialDateOK = true;
					foreach (System.Data.DataRow testRow in eventTable.Rows)
					{
						testDate = (System.DateTime)testRow["EventDate"];
						if (testDate.Date == trialDate.Date)
						{
							trialDateOK = false;
							trialDate = trialDate.AddDays(1);
						}
					}
				}
			}
			return trialDate;
		}

		private void calendar_SelectionChanged(object sender, System.EventArgs e)
		{
			System.DateTime trialDate = calendar.SelectedDate;
			calendar.SelectedDate = getFreeDate(trialDate);
		}

		protected void calendar_DayRender(object sender, System.Web.UI.WebControls.DayRenderEventArgs e)
		{
			if (eventTable.Rows.Count > 0)
			{
				System.DateTime testDate;
				foreach (System.Data.DataRow testRow in eventTable.Rows)
				{
					testDate = (System.DateTime)testRow["EventDate"];
					if (testDate.Date == e.Day.Date)
					{
						e.Cell.BackColor = Color.Red;
					}
				}
			}
		}

		protected void eventDetails2_SelectedIndexChanged(object sender, System.EventArgs e)
		{
			eventDetails2.DataBind();
			DataRow selectedEventRow = eventTable.Rows[eventDetails2.SelectedIndex];
			edName.Text = (string)selectedEventRow["Name"];
			edDate.Text = "<b>Date:</b> " + ((DateTime)selectedEventRow["EventDate"]).ToLongDateString();
			edAttendees.Text = "<b>Attendees:</b> " + (string)selectedEventRow["AttendeeList"];
			DataRow selectedEventRoomRow = ds.Tables["Rooms"].Rows[(int)selectedEventRow["Room"] - 1];
			edRoom.Text = "<b>Room:</b> " + selectedEventRoomRow["Room"];
		
		}
	}
}
