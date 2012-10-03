using System;
using System.Text;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Web;
using System.Web.SessionState;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.HtmlControls;
using HeadFirstDesignPatterns.MVC.Controller.WebForm;

namespace MVCWebFormView
{
	/// <summary>
	/// Summary description for Default.
	/// </summary>
	public class Default : System.Web.UI.Page
	{
		#region Members
		protected System.Web.UI.WebControls.DataGrid dgTodoList;
		protected System.Web.UI.WebControls.Button btnAddTodo;
		protected System.Web.UI.WebControls.Label lblTitle;
		protected System.Web.UI.WebControls.Label lblText;
		protected System.Web.UI.WebControls.TextBox txtText;
		protected System.Web.UI.WebControls.TextBox txtTitle;
		ITodoListControllerWeb controller;
		int bookmarkIndex = 0;//index of the datagrid row that should be scrolled too
		bool bookMark = true;
		int itemCount = 0;//Counter for the amount of items on the page
		#endregion//Members
	
		#region Page_Load
		private void Page_Load(object sender, System.EventArgs e)
		{
			controller = new TodoControllerWeb();

			if(!IsPostBack)
			{
				DataBindTheDataGrid(dgTodoList);
			}
		}
		#endregion//Page_Load

		#region protected methods
		//*********************************************************************

		#region EditData protected method
		protected void EditData(object source, DataGridCommandEventArgs e)
		{
			
			GetDataGridEditItem(dgTodoList,e.Item.ItemIndex);

			DataBindTheDataGrid(dgTodoList);

			if(bookMark)
			{
				bookmarkIndex = e.Item.ItemIndex;
				this.InsertScriptBlock();
			}
		}
		#endregion//EditData protected method

		#region CancelData protected method
		protected void CancelData(Object sender, DataGridCommandEventArgs e) 
		{
			GetDataGridEditItem(dgTodoList,-1);

			DataBindTheDataGrid(dgTodoList);

			if(bookMark)
			{
				bookmarkIndex = e.Item.ItemIndex;
				this.InsertScriptBlock();
			}
		}
		#endregion//CancelData protected method

		#region UpdateData protected method
		protected void UpdateData(Object sender, DataGridCommandEventArgs e) 
		{

			string Title = ((TextBox)e.Item.Cells[1].Controls[1]).Text;
			string Text = ((TextBox)e.Item.Cells[2].Controls[1]).Text;

			GetDataGridEditItem(dgTodoList,-1);

			controller.UpdateTodoList(e.Item.ItemIndex,Title,Text);

			DataBindTheDataGrid(dgTodoList);

			if(bookMark)
			{
				bookmarkIndex = e.Item.ItemIndex;
				this.InsertScriptBlock();
			}
		}
		#endregion//UpdateData protected method

		#region DeleteData protected method
		protected void DeleteData(Object sender, DataGridCommandEventArgs e) 
		{
			GetDataGridEditItem(dgTodoList,-1);

			controller.RemoveTodo(e.Item.ItemIndex);
			
			DataBindTheDataGrid(dgTodoList);
		}
		#endregion//DeleteData protected method

		//*********************************************************************
		#endregion//protected methods

		#region private helper methods
		//***********************************************************************************

		#region GetDataGridEditItem private method
		private void GetDataGridEditItem(DataGrid theGrid, int theID)
		{
			theGrid.EditItemIndex = theID;
		}
		#endregion//GetDataGridEditItem private method

		#region DataBindTheDataGrid private method
		private void DataBindTheDataGrid(DataGrid theGrid)
		{
			theGrid.DataSource = controller.GetTodoList();
			theGrid.DataBind();
		}
		#endregion//DataBindTheDataGrid private method

		#region btnAddTodo_Click
		private void btnAddTodo_Click(object sender, System.EventArgs e)
		{
			int dataSetRowCount = controller.AddTodo();

			DataBindTheDataGrid(dgTodoList);
			
			if(bookMark)
			{
				bookmarkIndex = dataSetRowCount;
				this.InsertScriptBlock();
			}
		}
		#endregion//btnAddTodo_Click

		#region dgTodoList_ItemDataBound private method
		private void dgTodoList_ItemDataBound(object sender, System.Web.UI.WebControls.DataGridItemEventArgs e)
		{
			if(bookMark)
			{
				LiteralControl anchor = new LiteralControl();
				anchor.Text = "<a name=\"" + itemCount.ToString() + "\">";
				itemCount++;
				e.Item.Cells[0].Controls.Add(anchor);
			}
		}
		#endregion//dgTodoList_ItemDataBound

		//***********************************************************************************
		#endregion//private helper methods

		#region InsertScriptBlock Method
		/// <summary>
		/// InsertScriptBlock Method - used to create a hyperlink
		/// bookmark reference
		/// </summary>
		private void InsertScriptBlock()
		{
			StringBuilder jScript = new StringBuilder();
			jScript.Append("<script language=\"JavaScript\">");
			jScript.Append("location.href=\"#");
			jScript.Append(this.bookmarkIndex.ToString());
			jScript.Append("\";");
			jScript.Append("</script>");

			this.RegisterClientScriptBlock("Bookmark",jScript.ToString());
		}
		#endregion

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
			this.btnAddTodo.Click += new System.EventHandler(this.btnAddTodo_Click);
			this.dgTodoList.ItemDataBound += new System.Web.UI.WebControls.DataGridItemEventHandler(this.dgTodoList_ItemDataBound);
			this.Load += new System.EventHandler(this.Page_Load);

		}
		#endregion

	}
}
