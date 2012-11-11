using System;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Web;
using System.Web.Services;

namespace PCSWebSrv2
{
	/// <summary>
	/// Summary description for Service1.
	/// </summary>
	public class Service1 : System.Web.Services.WebService
	{
		public Service1()
		{
			//CODEGEN: This call is required by the ASP.NET Web Services Designer
			InitializeComponent();
		}

		[WebMethod]
		public DataSet GetData()
		{
			return (DataSet)Application["ds"];
		}

		[WebMethod]
		public int AddEvent(String eventName, String eventRoom,
			String eventAttendees, String eventDate)
		{
			System.Data.OleDb.OleDbConnection oleDbConnection1;
			System.Data.OleDb.OleDbDataAdapter daEvents;
			DataSet ds;

			oleDbConnection1 = new System.Data.OleDb.OleDbConnection();
			oleDbConnection1.ConnectionString = @"Provider=Microsoft.Jet.OLEDB.4.0;Password="""";User ID=Admin;Data Source=C:\\Inetpub\\wwwroot\\PCSWebApp3\\PCSWebApp3.mdb";
			String oleDbCommand = "INSERT INTO Events (Name, Room, AttendeeList," +
				" EventDate) VALUES ('" + eventName + "', '" +
				eventRoom + "', '" + eventAttendees + "', '" +
				eventDate + "')";
			System.Data.OleDb.OleDbCommand insertCommand =
				new System.Data.OleDb.OleDbCommand(oleDbCommand,
				oleDbConnection1);
			oleDbConnection1.Open();

			int queryResult = insertCommand.ExecuteNonQuery();
			if (queryResult == 1)
			{
				daEvents = new System.Data.OleDb.OleDbDataAdapter(
					"SELECT * FROM Events", oleDbConnection1);
				ds = (DataSet)Application["ds"];
				ds.Tables["Events"].Clear();
				daEvents.Fill(ds, "Events");
				Application.Lock();
				Application["ds"] = ds;
				Application.UnLock();
				oleDbConnection1.Close();
			}
			return queryResult;
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

//		[WebMethod]
//		public string HelloWorld()
//		{
//			return "Hello World";
//		}
	}
}
