using System;
using System.Data;
using HeadFirstDesignPatterns.MVC.Model;

namespace HeadFirstDesignPatterns.MVC.Controller.WinForm
{
	/// <summary>
	/// Summary description for TodoWinFormConstroller.
	/// </summary>
	public class TodoControllerWin : ITodoListControllerWin
	{
		#region Members
		ITodoListModel todoModel;
		#endregion//Members

		#region Constructor
		public TodoControllerWin()
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
		public void UpdateTodoList(DataSet newTodoListDataSet)
		{
			todoModel.UpdateXMLDoc(newTodoListDataSet);
		}

		#endregion
	}
}
