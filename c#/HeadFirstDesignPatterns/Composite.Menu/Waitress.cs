using System;
using System.Text;
using System.Collections;

namespace HeadFirstDesignPatterns.Composite.Menu
{
	/// <summary>
	/// Summary description for Waitress.
	/// </summary>
	public class Waitress
	{
		MenuComponent allMenus;

		public Waitress(MenuComponent allMenus)
		{
			this.allMenus = allMenus;
		}

		public string PrintMenu()
		{
			return allMenus.Print();
		}

		/// <summary>
		/// This is a hack! It works but does not offer the encapsulization of a true
		/// composite pattern implementation. Note I have to call the leaf or composite
		/// child menu items explicitly.
		/// I researched how to create an iterator similar to the iterator in the 
		/// java ArrayList and created an internal iterator using IEnumerator and IEnumerable 
		/// interfaces. See the IteratorCSharpFixture object in the Developer Test project.
		/// Needless to say this is where java's ArrayList iterator has one up on .Net's. However,
		/// I am looking forward to C# 2.0 and the use of IEnumerator and IEnumerable with generics.
		/// </summary>
		/// <returns></returns>
		public string PrintVegetarianMenu()
		{
			StringBuilder printOutPut = new StringBuilder();
			printOutPut.Append("\nVEGETARIAN MENU\n");
			printOutPut.Append("-------------------------\n");
			printOutPut.Append(GetChildMenuOutPutDown2Levels(allMenus.GetMenu()));
			return printOutPut.ToString();
		}

		/// <summary>
		/// Get the ArrayList structure down to 2 child node levels. 
		/// Not pretty, but it works
		/// </summary>
		/// <param name="menus"></param>
		/// <returns>Up to 2 child node levels</returns>
		private string GetChildMenuOutPutDown2Levels(ArrayList menus)
		{
			StringBuilder printChildMenuOutPut = new StringBuilder();

			foreach(MenuComponent menuComponent in menus)
			{
				for(int i = 0; i < menuComponent.Count(); i++)
				{
					if(menuComponent.GetChild(i).IsVegetarian)
					{
						printChildMenuOutPut.Append(menuComponent.GetChild(i).Print());
					}

					if(menuComponent.GetChild(i).GetType().Name == "Menu")
					{
						for(int j = 0; j < menuComponent.GetChild(i).Count(); j++)
						{
							printChildMenuOutPut.Append(menuComponent.GetChild(i).GetChild(j).Print());
						}
					}
				}
			}

			return printChildMenuOutPut.ToString();
		}
	}
}
