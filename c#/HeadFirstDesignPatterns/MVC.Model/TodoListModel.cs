using System;
using System.Configuration;
using System.Data;
using System.Xml;

namespace HeadFirstDesignPatterns.MVC.Model
{
	/// <summary>
	/// Summary description for TodoListModel.
	/// </summary>
	public class TodoListModel : ITodoListModel
	{
		public TodoListModel()
		{}

		#region ITodoListModel Members

		public DataSet GetTodoList()
		{
			XmlDataDocument myList = new XmlDataDocument();
			myList.DataSet.ReadXml(ConfigurationSettings.AppSettings["TodoXMLFilePath"]);
			return myList.DataSet;
		}

		public int AddTodoListItem()
		{
			DataSet ds = this.GetTodoList();
			DataRow newRow = ds.Tables[0].NewRow();
			newRow["Title"]= "Place New Todo Title Here";
			newRow["Text"]= "Place New Todo Text Here";
			ds.Tables[0].Rows.Add(newRow);

			ds.WriteXml(ConfigurationSettings.AppSettings["TodoXMLFilePath"]);

			//return int of rowcount - 1 to determine which record to edit
			return ds.Tables[0].Rows.Count - 1;
		}

		public bool RemoveTodoListItem(int recordID)
		{
			bool removeSuccessful = false;

			DataSet ds = this.GetTodoList();
			DataRow row = ds.Tables[0].Rows[recordID];
			try
			{
				row.Delete();
				removeSuccessful = true;
			}
			catch(Exception e)
			{
				throw new ApplicationException("Error Deleting Todo Item: " + e.Message + "\n" +
					e.StackTrace);
			}
			ds.WriteXml(ConfigurationSettings.AppSettings["TodoXMLFilePath"]);

			return removeSuccessful;
		}

		public void UpdateXMLDoc(DataSet newTodoListDataSet)
		{
			newTodoListDataSet.WriteXml(ConfigurationSettings.AppSettings["TodoXMLFilePath"]);
		}

		#endregion
	}
}
