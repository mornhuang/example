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
using System.Text;
using System.Data.SqlClient;

namespace Chapter10CS
{
	/// <summary>
	/// Summary description for typeAheadData.
	/// </summary>
	public class typeAheadData : System.Web.UI.Page
	{
		private void Page_Load(object sender,
			System.EventArgs e)
		{
			Response.ContentType = "text/html";
			string strQuery =
				Request.Form.Get("q").ToString();
			string strAny = "";
			if (Request.Form.Get("where").ToLower()
				== "true")
			{
				strAny = "%";
			}
			string strSql = "Select top 15 " +
				"ProductName, " +
				"ProductId FROM Products " +
				"WHERE ProductName like '" +
				strAny + strQuery + "%" + 
				"' ORDER BY ProductName";
			DataTable dtQuestions = FillDataTable(
				strSql);
			System.Text.StringBuilder strJSArr =
				new System.Text.StringBuilder(
				"arrOptions = new Array(");
			int iCount = 0;

			foreach (DataRow row in
				dtQuestions.Rows)
			{
				if (iCount > 0)
				{
					strJSArr.Append(",");
				}
				strJSArr.Append("[");
				strJSArr.Append("\"" +
					row["ProductName"].ToString()
					+ "\",");
				strJSArr.Append("\"" +
					row["Productid"].ToString()
					+ "\"");
				strJSArr.Append("]");
				iCount++;
			}

			strJSArr.Append(");");							
			Response.Write(strJSArr.ToString());
		}
		public static DataTable
			FillDataTable(string sqlQuery)
		{
			string strConn = "Initial Catalog = "+
				"Northwind;Data Source=127.0.0.1; " + 
				"Integrated Security=true;";
			SqlConnection conn = new
				SqlConnection(strConn);
			SqlDataAdapter cmd1 = new
				SqlDataAdapter();
			cmd1.SelectCommand = new
				SqlCommand(sqlQuery,conn);
			DataSet dataSet1 = new DataSet();
			cmd1.Fill(dataSet1);
			cmd1.Dispose();
			conn.Close();
			return dataSet1.Tables[0];
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
			this.Load += new System.EventHandler(this.Page_Load);
		}
		#endregion
	}
}
