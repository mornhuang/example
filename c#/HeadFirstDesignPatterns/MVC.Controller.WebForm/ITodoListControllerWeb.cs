using System;
using System.Data;
using HeadFirstDesignPatterns.MVC.Model;

namespace HeadFirstDesignPatterns.MVC.Controller.WebForm
{
	/// <summary>
	/// Summary description for ITodoListController.
	/// </summary>
	public interface ITodoListControllerWeb
	{
		DataSet GetTodoList();
		int AddTodo();
		bool RemoveTodo(int recordID);
		void UpdateTodoList(int theRecordNumber, string theTitle, string theText);
	}
}
