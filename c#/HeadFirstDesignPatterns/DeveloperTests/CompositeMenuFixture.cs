using System;
using System.Text;
using HeadFirstDesignPatterns.Composite.Menu;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Composite.Menus
{
	/// <summary>
	/// SingletonRateFixture tests the singleton pattern
	/// </summary>
	[TestFixture]
	public class CompositeMenuFixture
	{
		#region Members
		MenuComponent pancakeHouseMenu;
		MenuComponent dinnerMenu;
		MenuComponent cafeMenu;
		MenuComponent dessertMenu;
		MenuComponent allMenus;
		Waitress waitress;
		#endregion//Members

		#region Setup
		[SetUp]
		public void Init()
		{ 
			//create menus
			pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU","Breakfast");
			dinnerMenu = new Menu("DINNER MENU","Lunch");
			cafeMenu = new Menu("CAFE MENU","Dinner");
			dessertMenu = new Menu("DESSERT MENU","Dessert of course");

			//create the all menus to act as the container for the menus
			allMenus = new Menu("ALL MENUS", "All menus combined");

			//add the menus to the All Menus menu
			allMenus.Add(pancakeHouseMenu);
			allMenus.Add(dinnerMenu);
			allMenus.Add(cafeMenu);

			//breakfast menu
			pancakeHouseMenu.Add(new MenuItem("K&B's Pancake Breakfast",
				"Pancakes with scrambled eggs, and toast",
				true,
				2.99));
			pancakeHouseMenu.Add(new MenuItem("Regular Pancake Breakfast",
				"Pancakes with fried eggs, and sausage",
				false,
				2.99));
			pancakeHouseMenu.Add(new MenuItem("Blueberry Pancakes",
				"Pancakes made with fresh blueberries",
				true,
				3.49));
			pancakeHouseMenu.Add(new MenuItem("Waffles",
				"Waffles, with your choice of blueberries or strawberries",
				true,
				3.59));

			//dinner menu
			dinnerMenu.Add(new MenuItem("Vegetarian BLT",
				"(Fakin') Bacon with lettuce & tomato on whole wheat",
				true,
				2.99));
			dinnerMenu.Add(new MenuItem("Soup of the day",
				"Soup of the day, with a side of potato salad",
				false,
				3.29));
			dinnerMenu.Add(new MenuItem("Hotdog",
				"A hot dog with saurkraut, relish, onions, topped with cheese",
				false,
				3.05));
			dinnerMenu.Add(new MenuItem("Steamed Veggies and Brown Rice",
				"Steamed vegetables over brown rice",
				true,
				3.99));
			
			//cafe menu
			cafeMenu.Add(new MenuItem("Veggie Burger and Air Fries",
				"Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
				true,3.99));
			cafeMenu.Add(new MenuItem("Soup of the Day", 
				"A cup of the soup of the day, with a side salad",
				false, 3.69));
			cafeMenu.Add(new MenuItem("Burrito",
				"A large burrito, with whole pinto beans, salsa, guacamole",
				true, 4.29));

			//add the dessert as a child node off the dinner menu
			dinnerMenu.Add(dessertMenu);

			//dinner menu
			dessertMenu.Add(new MenuItem("Apple Pie",
				"Apple pie with a flakey crust, topped with vanilla icecream",
				true, 1.59));
			dessertMenu.Add(new MenuItem("Chocolate Cake",
				"Creamy chololate cake with caramel icing",
				true, 2.59));
			dessertMenu.Add(new MenuItem("Icecream Pie",
				"Icecream pie with crunchy crust",
				true, 1.50));

			//instantiate the waitress passing in all the menus
			waitress = new Waitress(allMenus);
		}
		#endregion//Setup

		#region TearDown Dispose()
		[TearDown]
		public void Dispose()
		{
			pancakeHouseMenu = null;
			dinnerMenu = null;
			cafeMenu = null;
			dessertMenu = null;
			allMenus = null;
		}
		#endregion//TearDown Dispose()

		#region TestWaitressPrint
		[Test]
		public void TestWaitressPrint()
		{
			StringBuilder testOutPut = new StringBuilder();
											 
			testOutPut.Append("\nALL MENUS, All menus combined\n");
			testOutPut.Append("-------------------------\n");
			testOutPut.Append("\nPANCAKE HOUSE MENU, Breakfast\n");
			testOutPut.Append("-------------------------\n");
			testOutPut.Append("\tK&B's Pancake Breakfast (v) , $2.99\n");
			testOutPut.Append("\t\t--Pancakes with scrambled eggs, and toast\n");
			testOutPut.Append("\tRegular Pancake Breakfast, $2.99\n");
			testOutPut.Append("\t\t--Pancakes with fried eggs, and sausage\n");
			testOutPut.Append("\tBlueberry Pancakes (v) , $3.49\n");
			testOutPut.Append("\t\t--Pancakes made with fresh blueberries\n");
			testOutPut.Append("\tWaffles (v) , $3.59\n");
			testOutPut.Append("\t\t--Waffles, with your choice of blueberries or strawberries\n");
			testOutPut.Append("\nDINNER MENU, Lunch\n");
			testOutPut.Append("-------------------------\n");
			testOutPut.Append("\tVegetarian BLT (v) , $2.99\n");
			testOutPut.Append("\t\t--(Fakin') Bacon with lettuce & tomato on whole wheat\n");
			testOutPut.Append("\tSoup of the day, $3.29\n");
			testOutPut.Append("\t\t--Soup of the day, with a side of potato salad\n");
			testOutPut.Append("\tHotdog, $3.05\n");
			testOutPut.Append("\t\t--A hot dog with saurkraut, relish, onions, topped with cheese\n");
			testOutPut.Append("\tSteamed Veggies and Brown Rice (v) , $3.99\n");
			testOutPut.Append("\t\t--Steamed vegetables over brown rice\n");
			testOutPut.Append("\nDESSERT MENU, Dessert of course\n");
			testOutPut.Append("-------------------------\n");
			testOutPut.Append("\tApple Pie (v) , $1.59\n");
			testOutPut.Append("\t\t--Apple pie with a flakey crust, topped with vanilla icecream\n");
			testOutPut.Append("\tChocolate Cake (v) , $2.59\n");
			testOutPut.Append("\t\t--Creamy chololate cake with caramel icing\n");
			testOutPut.Append("\tIcecream Pie (v) , $1.5\n");
			testOutPut.Append("\t\t--Icecream pie with crunchy crust\n");
			testOutPut.Append("\nCAFE MENU, Dinner\n");
			testOutPut.Append("-------------------------\n");
			testOutPut.Append("\tVeggie Burger and Air Fries (v) , $3.99\n");
			testOutPut.Append("\t\t--Veggie burger on a whole wheat bun, lettuce, tomato, and fries\n");
			testOutPut.Append("\tSoup of the Day, $3.69\n");
			testOutPut.Append("\t\t--A cup of the soup of the day, with a side salad\n");
			testOutPut.Append("\tBurrito (v) , $4.29\n");
			testOutPut.Append("\t\t--A large burrito, with whole pinto beans, salsa, guacamole\n");

			Assert.AreEqual(testOutPut.ToString(),waitress.PrintMenu());
		}
		#endregion//TestWaitressPrint

		#region TestVegetarianPrintMenu
		[Test]
		public void TestVegetarianPrintMenu()
		{
			StringBuilder testVegOutPut = new StringBuilder();
											 
			testVegOutPut.Append("\nVEGETARIAN MENU\n");
			testVegOutPut.Append("-------------------------\n");
			testVegOutPut.Append("\tK&B's Pancake Breakfast (v) , $2.99\n");
			testVegOutPut.Append("\t\t--Pancakes with scrambled eggs, and toast\n");
			testVegOutPut.Append("\tBlueberry Pancakes (v) , $3.49\n");
			testVegOutPut.Append("\t\t--Pancakes made with fresh blueberries\n");
			testVegOutPut.Append("\tWaffles (v) , $3.59\n");
			testVegOutPut.Append("\t\t--Waffles, with your choice of blueberries or strawberries\n");
			testVegOutPut.Append("\tVegetarian BLT (v) , $2.99\n");
			testVegOutPut.Append("\t\t--(Fakin') Bacon with lettuce & tomato on whole wheat\n");
			testVegOutPut.Append("\tSteamed Veggies and Brown Rice (v) , $3.99\n");
			testVegOutPut.Append("\t\t--Steamed vegetables over brown rice\n");
			testVegOutPut.Append("\tApple Pie (v) , $1.59\n");
			testVegOutPut.Append("\t\t--Apple pie with a flakey crust, topped with vanilla icecream\n");
			testVegOutPut.Append("\tChocolate Cake (v) , $2.59\n");
			testVegOutPut.Append("\t\t--Creamy chololate cake with caramel icing\n");
			testVegOutPut.Append("\tIcecream Pie (v) , $1.5\n");
			testVegOutPut.Append("\t\t--Icecream pie with crunchy crust\n");
			testVegOutPut.Append("\tVeggie Burger and Air Fries (v) , $3.99\n");
			testVegOutPut.Append("\t\t--Veggie burger on a whole wheat bun, lettuce, tomato, and fries\n");
			testVegOutPut.Append("\tBurrito (v) , $4.29\n");
			testVegOutPut.Append("\t\t--A large burrito, with whole pinto beans, salsa, guacamole\n");

			Assert.AreEqual(testVegOutPut.ToString(),
				waitress.PrintVegetarianMenu());
		}
		#endregion//TestVegetarianPrintMenu

	}
}
