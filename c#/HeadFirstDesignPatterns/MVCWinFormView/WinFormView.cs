using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using HeadFirstDesignPatterns.MVC.Controller.WinForm;

namespace HeadFirstDesignPatterns.MVC.MVCWinFormView
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class WinFormView : System.Windows.Forms.Form
	{
		#region Members
		private System.Windows.Forms.DataGrid dgTodoList;
		private System.Windows.Forms.Button btnUpdateTodoList;
		private System.Windows.Forms.Button btnRemoveTodo;
		ITodoListControllerWin controller;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
		#endregion//Members

		#region Constructor
		public WinFormView()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
			LoadDataGrid();
		}
		#endregion//Constructor

		#region Dispose
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if (components != null) 
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}
		#endregion//Dispose

		#region InitializeComponent
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.dgTodoList = new System.Windows.Forms.DataGrid();
			this.btnUpdateTodoList = new System.Windows.Forms.Button();
			this.btnRemoveTodo = new System.Windows.Forms.Button();
			((System.ComponentModel.ISupportInitialize)(this.dgTodoList)).BeginInit();
			this.SuspendLayout();
			// 
			// dgTodoList
			// 
			this.dgTodoList.AlternatingBackColor = System.Drawing.Color.Gainsboro;
			this.dgTodoList.BackColor = System.Drawing.Color.Silver;
			this.dgTodoList.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.dgTodoList.CaptionBackColor = System.Drawing.Color.DarkSlateBlue;
			this.dgTodoList.CaptionFont = new System.Drawing.Font("Tahoma", 8F);
			this.dgTodoList.CaptionForeColor = System.Drawing.Color.White;
			this.dgTodoList.CaptionText = "Todo List";
			this.dgTodoList.DataMember = "";
			this.dgTodoList.FlatMode = true;
			this.dgTodoList.ForeColor = System.Drawing.Color.Black;
			this.dgTodoList.GridLineColor = System.Drawing.Color.White;
			this.dgTodoList.HeaderBackColor = System.Drawing.Color.DarkGray;
			this.dgTodoList.HeaderForeColor = System.Drawing.Color.Black;
			this.dgTodoList.LinkColor = System.Drawing.Color.DarkSlateBlue;
			this.dgTodoList.Location = new System.Drawing.Point(32, 80);
			this.dgTodoList.Name = "dgTodoList";
			this.dgTodoList.ParentRowsBackColor = System.Drawing.Color.Black;
			this.dgTodoList.ParentRowsForeColor = System.Drawing.Color.White;
			this.dgTodoList.PreferredColumnWidth = 250;
			this.dgTodoList.SelectionBackColor = System.Drawing.Color.DarkSlateBlue;
			this.dgTodoList.SelectionForeColor = System.Drawing.Color.White;
			this.dgTodoList.Size = new System.Drawing.Size(592, 368);
			this.dgTodoList.TabIndex = 0;
			// 
			// btnUpdateTodoList
			// 
			this.btnUpdateTodoList.Location = new System.Drawing.Point(32, 24);
			this.btnUpdateTodoList.Name = "btnUpdateTodoList";
			this.btnUpdateTodoList.Size = new System.Drawing.Size(120, 23);
			this.btnUpdateTodoList.TabIndex = 1;
			this.btnUpdateTodoList.Text = "Update Todo List";
			this.btnUpdateTodoList.Click += new System.EventHandler(this.btnUpdateTodoList_Click);
			// 
			// btnRemoveTodo
			// 
			this.btnRemoveTodo.Location = new System.Drawing.Point(200, 24);
			this.btnRemoveTodo.Name = "btnRemoveTodo";
			this.btnRemoveTodo.Size = new System.Drawing.Size(120, 23);
			this.btnRemoveTodo.TabIndex = 2;
			this.btnRemoveTodo.Text = "Remove Todo";
			this.btnRemoveTodo.Click += new System.EventHandler(this.btnRemoveTodo_Click);
			// 
			// WinFormView
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(648, 486);
			this.Controls.Add(this.btnRemoveTodo);
			this.Controls.Add(this.btnUpdateTodoList);
			this.Controls.Add(this.dgTodoList);
			this.Name = "WinFormView";
			this.Text = "HeadFirstPattern WinForm MVC View";
			((System.ComponentModel.ISupportInitialize)(this.dgTodoList)).EndInit();
			this.ResumeLayout(false);
			//
			//Controller
			//
			controller = new TodoControllerWin();

		}
		#endregion

		#region Main
		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			Application.Run(new WinFormView());
		}
		#endregion//Main

		#region LoadDataGrid
		private void LoadDataGrid()
		{
			this.dgTodoList.SetDataBinding(this.GetTodoListDataSet(),"Entry");
		}
		#endregion//LoadDataGrid
		
		#region GetTodoListDataSet
		private DataSet GetTodoListDataSet()
		{
			return controller.GetTodoList();
		}
		#endregion//GetTodoListDataSet

		#region GetTodoListDataView - deprecated
		private DataView GetTodoListDataView()
		{
			DataView dvTodoList = new DataView(GetTodoListDataSet().Tables[0]);
			return dvTodoList;
		}
		#endregion//GetTodoListDataView

		#region btnUpdateTodoList_Click
		private void btnUpdateTodoList_Click(object sender, System.EventArgs e)
		{
			controller.UpdateTodoList((DataSet)this.dgTodoList.DataSource);
		}
		#endregion//btnUpdateTodoList_Click

		#region btnRemoveTodo_Click
		private void btnRemoveTodo_Click(object sender, System.EventArgs e)
		{
			//get selected row ID
			int selectedRowID = this.dgTodoList.CurrentRowIndex;

			//get DataSet and delete selected row
			DataSet todoDataSet = GetTodoListDataSet();
			DataTable todoDataTable = todoDataSet.Tables[0];//new DataTable("todoDataTable");
			todoDataTable.Rows[selectedRowID].Delete();

			//update XML doc with new data via Controller
			controller.UpdateTodoList(todoDataSet);

			//reload DataGrid
			LoadDataGrid();
		}
		#endregion//btnRemoveTodo_Click
	}
}
