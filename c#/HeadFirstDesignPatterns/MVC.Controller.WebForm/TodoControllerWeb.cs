using System;
using System.Data;
using HeadFirstDesignPatterns.MVC.Model;

namespace HeadFirstDesignPatterns.MVC.Controller.WebForm
{
	/// <summary>
	/// Summary description for TodoController.
	/// </summary>
	public class TodoControllerWeb : ITodoListControllerWeb
	{
		#region Members
		ITodoListModel todoModel;
		#endregion//Members

		#region Constructor
		public TodoControllerWeb()
		{
			this.todoModel = new TodoListModel();
		}
		#endregion//Constructor

		#region ITodoListController Members

		public System.Data.DataSet GetTodoList()
		{
			return todoModel.GetTodoList();
		}

		public int AddTodo()
		{
			return todoModel.AddTodoListItem();		
		}

		public bool RemoveTodo(int recordID)
		{
			return todoModel.RemoveTodoListItem(recordID);
		}

		public void UpdateTodoList(int theRecordNumber, string theTitle, string theText)
		{
			DataSet todoListDataSet = todoModel.GetTodoList();
			DataRow row = todoListDataSet.Tables[0].Rows[theRecordNumber];

			row["Title"] = theTitle;
			row["Text"] = theText;
			
			todoModel.UpdateXMLDoc(todoListDataSet);	
		}

		#endregion
	}
}
