using System;
using System.Data;

namespace HeadFirstDesignPatterns.MVC.Controller.WinForm
{
	/// <summary>
	/// Summary description for ITodoListController.
	/// </summary>
	public interface ITodoListControllerWin
	{
		DataSet GetTodoList();
		int AddTodo();
		bool RemoveTodo(int recordID);
		void UpdateTodoList(DataSet newTodoListDataSet);
	}
}
