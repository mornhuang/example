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

namespace typeAheadCSharp
{
	/// <summary>
	/// Summary description for typeAheadData.
	/// </summary>
	public class typeAheadData : System.Web.UI.Page
	{
		private void Page_Load(object sender, System.EventArgs e)
		{

			Response.ContentType = "text/xml";
//			string strId=Request.Form.Get("id").ToString();
//			string strQuery = Request.Form.Get("q").ToString();
			string strId=Request.QueryString["id"];
			string strQuery = Request.QueryString["query"];
			string strAny  = "";
			if (Request.QueryString["where"] == null ||
				Request.QueryString["where"].ToLower() == "true")
			{
				strAny = "%";
			}

			string strSql = "Select top 15 ProductName, ProductId FROM Products WHERE ProductName like '" + strAny + strQuery + "%' ORDER BY ProductName";
			DataTable dtQuestions  = FillDataTable(strSql);
			System.Text.StringBuilder strXML = new System.Text.StringBuilder("<ajax-response>");
			strXML.Append("<response type='object' id='");
			strXML.Append(strId);
			strXML.Append("_updater'>");
			strXML.Append("<matches>");
			int iCount = 0;
			foreach (DataRow row in dtQuestions.Rows)
			{
				strXML.Append("<entry>");
				strXML.Append("<text>" + row["ProductName"].ToString() + "</text>");
				strXML.Append("<value>" +  row["Productid"].ToString() + "</value>");
				strXML.Append("</entry>");
				iCount++;
			}
			strXML.Append("</matches>");
			strXML.Append("</response>");
			strXML.Append("</ajax-response>");

			Response.Write(strXML.ToString());
		}

		public static DataTable 
			FillDataTable(string sqlQuery)
		{
			string strConn  = "Initial Catalog = Northwind; Data Source=127.0.0.1; Integrated Security=true";
			SqlConnection conn = new SqlConnection(strConn);
			SqlDataAdapter cmd1 = new SqlDataAdapter();
			cmd1.SelectCommand = new SqlCommand(sqlQuery,conn);
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