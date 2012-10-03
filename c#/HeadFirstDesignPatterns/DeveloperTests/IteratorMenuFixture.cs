using System;
using System.Text;
using HeadFirstDesignPatterns.Iterator.Menu;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Iterator.Menu
{
	/// <summary>
	/// IteratorMenuFixture tests the iterator pattern
	/// </summary>
	[TestFixture]
	public class IteratorMenuFixture
	{
		#region TestWaitressPrintMenu
		[Test]
		public void TestWaitressPrintMenu()
		{
			StringBuilder printMenuOutput = new StringBuilder();

			PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
			DinnerMenu dinnerMenu = new DinnerMenu();
			CafeMenu cafeMenu = new CafeMenu();

			Waitress waitress = new Waitress(pancakeHouseMenu,dinnerMenu,cafeMenu);
			
			printMenuOutput.Append("MENU\n");
			printMenuOutput.Append("----\n");
			printMenuOutput.Append("BREAKFAST\n");
			printMenuOutput.Append("K&B's Pancake Breakfast, 2.99 -- Pancakes with scrambled eggs, and toast\n");
			printMenuOutput.Append("Regular Pancake Breakfast, 2.99 -- Pancakes with fried eggs, and sausage\n");
			printMenuOutput.Append("Blueberry Pancakes, 3.49 -- Pancakes made with fresh blueberries\n");
			printMenuOutput.Append("Waffles, 3.59 -- Waffles, with your choice of blueberries or strawberries\n");
			printMenuOutput.Append("\n");
			printMenuOutput.Append("LUNCH\n");
			printMenuOutput.Append("Vegetarian BLT, 2.99 -- (Fakin') Bacon with lettuce & tomato on whole wheat\n");
			printMenuOutput.Append("BLT, 2.99 -- Bacon with lettuce & tomato on whole wheat\n");
			printMenuOutput.Append("Soup of the day, 3.29 -- Soup of the day, with a side of potato salad\n");
			printMenuOutput.Append("Hotdog, 3.05 -- A hot dog with saurkraut, relish, onions, topped with cheese\n");
			printMenuOutput.Append("Steamed Veggies and Brown Rice, 3.99 -- Steamed vegetables over brown rice\n");
			printMenuOutput.Append("Pasta, 3.89 -- Spaghetti with Marina Sauce and a slice of sourdough bread\n");
			printMenuOutput.Append("\nDinner\n");
			printMenuOutput.Append("Veggie Burger and Air Fries, 3.99 -- Veggie burger on a whole wheat bun, lettuce, tomato, and fries\n");
			printMenuOutput.Append("Soup of the Day, 3.69 -- A cup of the soup of the day, with a side salad\n");
			printMenuOutput.Append("Burrito, 4.29 -- A large burrito, with whole pinto beans, salsa, guacamole\n");
			
			Assert.AreEqual(printMenuOutput.ToString(),waitress.PrintMenu());
		}
		#endregion//TestWaitressPrintMenu
	}
}
