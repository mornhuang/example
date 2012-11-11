using System;
using System.Collections;

namespace HeadFirstDesignPatterns.Iterator.Menu
{
	/// <summary>
	/// Summary description for PancakeHouseMenu.
	/// </summary>
	public class PancakeHouseMenu : Menu
	{
		ArrayList menuItems;

		public PancakeHouseMenu()
		{
			menuItems = new ArrayList();

			AddItem("K&B's Pancake Breakfast",
				"Pancakes with scrambled eggs, and toast",
				true,
				2.99);
			AddItem("Regular Pancake Breakfast",
				"Pancakes with fried eggs, and sausage",
				false,
				2.99);
			AddItem("Blueberry Pancakes",
				"Pancakes made with fresh blueberries",
				true,
				3.49);
			AddItem("Waffles",
				"Waffles, with your choice of blueberries or strawberries",
				true,
				3.59);
		}

		public void AddItem(string name, string description, 
			bool isVegetarian, double price)
		{
			MenuItem menuItem = new MenuItem(name, description,
				isVegetarian, price);
			menuItems.Add(menuItem);
		}

		public Iterator CreateIterator()
		{
			return new PancakeHouseIterator(menuItems);
		}
	}
}
