using System;
using System.Data;

namespace HeadFirstDesignPatterns.MVC.Model
{
	/// <summary>
	/// Summary description for ITodoListModel.
	/// </summary>
	public interface ITodoListModel
	{
		DataSet GetTodoList();
		int AddTodoListItem();
		bool RemoveTodoListItem(int recordID);
		void UpdateXMLDoc(DataSet newTodoListDataSet);
	}
}
