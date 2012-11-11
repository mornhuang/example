using System;
using System.Collections;
using System.ComponentModel;
using System.Web;
using System.Web.SessionState;
using System.Data;

namespace PCSWebSrv2 
{
	/// <summary>
	/// Summary description for Global.
	/// </summary>
	public class Global : System.Web.HttpApplication
	{
		public Global()
		{
			InitializeComponent();
		}	
		
		protected void Application_Start(Object sender, EventArgs e)
		{
			System.Data.DataSet ds;
			System.Data.OleDb.OleDbConnection oleDbConnection1;
			System.Data.OleDb.OleDbDataAdapter daAttendees;
			System.Data.OleDb.OleDbDataAdapter daRooms;
			System.Data.OleDb.OleDbDataAdapter daEvents;

			oleDbConnection1 = new System.Data.OleDb.OleDbConnection();
			oleDbConnection1.ConnectionString = @"Provider=Microsoft.Jet.OLEDB.4.0;Password="""";User ID=Admin;Data Source=C:\Inetpub\wwwroot\PCSWebApp3\PCSWebApp3.mdb;Mode=ReadWrite|Share Deny None;Extended Properties="""";Jet OLEDB:System database="""";Jet OLEDB:Registry Path="""";Jet OLEDB:Database Password="""";Jet OLEDB:Engine Type=5;Jet OLEDB:Database Locking Mode=0;Jet OLEDB:Global Partial Bulk Ops=2;Jet OLEDB:Global Bulk Transactions=1;Jet OLEDB:New Database Password="""";Jet OLEDB:Create System Database=False;Jet OLEDB:Encrypt Database=False;Jet OLEDB:Don't Copy Locale on Compact=False;Jet OLEDB:Compact Without Replica Repair=False;Jet OLEDB:SFP=False";
			oleDbConnection1.Open();
			ds = new DataSet();
			daAttendees = new System.Data.OleDb.OleDbDataAdapter(
				"SELECT * FROM Attendees", oleDbConnection1);
			daRooms = new System.Data.OleDb.OleDbDataAdapter(
				"SELECT * FROM Rooms", oleDbConnection1);

			daEvents = new System.Data.OleDb.OleDbDataAdapter(
				"SELECT * FROM Events", oleDbConnection1);
			daAttendees.Fill(ds, "Attendees");
			daRooms.Fill(ds, "Rooms");
			daEvents.Fill(ds, "Events");
			oleDbConnection1.Close();

			Application["ds"] = ds;
		}
 
		protected void Session_Start(Object sender, EventArgs e)
		{

		}

		protected void Application_BeginRequest(Object sender, EventArgs e)
		{

		}

		protected void Application_EndRequest(Object sender, EventArgs e)
		{

		}

		protected void Application_AuthenticateRequest(Object sender, EventArgs e)
		{

		}

		protected void Application_Error(Object sender, EventArgs e)
		{

		}

		protected void Session_End(Object sender, EventArgs e)
		{

		}

		protected void Application_End(Object sender, EventArgs e)
		{

		}
			
		#region Web Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{    
		}
		#endregion
	}
}

