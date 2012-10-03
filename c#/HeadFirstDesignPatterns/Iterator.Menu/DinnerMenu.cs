using System;

namespace HeadFirstDesignPatterns.Iterator.Menu
{
	/// <summary>
	/// Summary description for DinnerMenu.
	/// </summary>
	public class DinnerMenu : Menu
	{
		static int MAX_ITEMS = 6;
		int numberOfItems = 0;
		MenuItem[] menuItems;

		public DinnerMenu()
		{
			menuItems = new MenuItem[MAX_ITEMS];

			AddItem("Vegetarian BLT",
				"(Fakin') Bacon with lettuce & tomato on whole wheat",
				true,
				2.99);
			AddItem("BLT",
				"Bacon with lettuce & tomato on whole wheat",
				false,
				2.99);
			AddItem("Soup of the day",
				"Soup of the day, with a side of potato salad",
				false,
				3.29);
			AddItem("Hotdog",
				"A hot dog with saurkraut, relish, onions, topped with cheese",
				false,
				3.05);
			AddItem("Steamed Veggies and Brown Rice",
				"Steamed vegetables over brown rice",
				true,
				3.99);
			AddItem("Pasta",
				"Spaghetti with Marina Sauce and a slice of sourdough bread",
				true,
				3.89);
		}

		public void AddItem(string name, string description, 
			bool isVegetarian, double price)
		{
			MenuItem menuItem = new MenuItem(name,description,
				isVegetarian,price);
			if(numberOfItems >= MAX_ITEMS)
			{
				Console.WriteLine("Sorry, menu is full! Can't add item to menu");
			}
			else
			{
				menuItems[numberOfItems] = menuItem;
				numberOfItems += 1;
			}
		}

		public Iterator CreateIterator()
		{
			return new DinnerMenuIterator(menuItems);
		}
	}
}
