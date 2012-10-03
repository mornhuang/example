using System;
using System.Data;
using HeadFirstDesignPatterns.MVC.Model;
using HeadFirstDesignPatterns.MVC.Controller.WinForm;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.MVC
{
	/// <summary>
	/// CompoundMVCFixture tests the MVC compound pattern
	/// </summary>
	[TestFixture]
	public class MVCFixture
	{
		#region Members
		ITodoListModel model;
		ITodoListControllerWin controller;
		DataSet modelDS;
		DataSet newModelDS;
		DataSet controllerDS;
		#endregion//Members

		#region SetUp
		[SetUp]
		public void Init()
		{
			model = new TodoListModel();
			controller = new TodoControllerWin();
			modelDS = model.GetTodoList();
			controllerDS = controller.GetTodoList();
		}
		#endregion//SetUp

		#region TearDown
		[TearDown]
		public void Dispose()
		{
			model = null;
			modelDS = null;
			controller = null;
			controllerDS = null;
		}
		#endregion//SetUp

		#region TestMVCModelGetData
		[Test]
		public void TestMVCModelGetData()
		{
			Console.WriteLine(modelDS.Tables[0].Rows[modelDS.Tables[0].Rows.Count - 1]["Title"] + 
				" - " + modelDS.Tables[0].Rows[modelDS.Tables[0].Rows.Count - 1]["Text"]);
		}
		#endregion//TestMVCModelGetData

		#region TestMVCModelAddItem
		[Test]
		public void TestMVCModelAddItem()
		{
			Console.WriteLine("Number of Records Before: " + modelDS.Tables[0].Rows.Count);
			model.AddTodoListItem();
			newModelDS = model.GetTodoList();
			Console.WriteLine("Number of Records After: " + newModelDS.Tables[0].Rows.Count);
			Console.WriteLine(newModelDS.Tables[0].Rows[newModelDS.Tables[0].Rows.Count - 1]["Title"] + 
				" - " + newModelDS.Tables[0].Rows[newModelDS.Tables[0].Rows.Count - 1]["Text"]);
		}
		#endregion//TestMVCModelAddItem

		#region TestMVCModelRemoveItem
		[Test]
		public void TestMVCModelRemoveItem()
		{
			bool removeSuccessful = false;
			Console.WriteLine("Number of Records Before: " + modelDS.Tables[0].Rows.Count);
			removeSuccessful = model.RemoveTodoListItem(modelDS.Tables[0].Rows.Count - 1);

			if(removeSuccessful)
			{
				newModelDS = model.GetTodoList();
				Console.WriteLine("Number of Records After: " + newModelDS.Tables[0].Rows.Count);
			}
			else
			{
				Assert.Fail("There was an error removing the Todo");
			}
		}
		#endregion//TestMVCControllerGetData

		#region TestMVCControllerGetData
		[Test]
		public void TestMVCControllerGetData()
		{
			Console.WriteLine(controllerDS.Tables[0].Rows[controllerDS.Tables[0].Rows.Count - 1]["Title"] + 
				" - " + controllerDS.Tables[0].Rows[controllerDS.Tables[0].Rows.Count - 1]["Text"]);
		}
		#endregion//TestMVCControllerGetData

	}
}
