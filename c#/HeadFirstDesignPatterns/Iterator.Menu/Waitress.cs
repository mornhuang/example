using System;
using System.Text;

namespace HeadFirstDesignPatterns.Iterator.Menu
{
	/// <summary>
	/// Summary description for Waitress.
	/// </summary>
	public class Waitress
	{
		Menu pancakeHouseMenu;
		Menu dinnerMenu;
		Menu cafeMenu;

		public Waitress(Menu pancakeHouseMenu,
			Menu dinnerMenu, Menu cafeMenu)
		{
			this.pancakeHouseMenu = pancakeHouseMenu;
			this.dinnerMenu = dinnerMenu;
			this.cafeMenu = cafeMenu;
		}

		public string PrintMenu()
		{
			StringBuilder sb = new StringBuilder();

			Iterator pancakeIterator = pancakeHouseMenu.CreateIterator();
			Iterator dinnerIterator = dinnerMenu.CreateIterator();
			Iterator cafeIterator = cafeMenu.CreateIterator();
			sb.Append("MENU\n----\nBREAKFAST\n");
			sb.Append(PrintMenu(pancakeIterator));
			sb.Append("\nLUNCH\n");
			sb.Append(PrintMenu(dinnerIterator));
			sb.Append("\nDinner\n");
			sb.Append(PrintMenu(cafeIterator));

			return sb.ToString();
		}

		public string PrintMenu(Iterator iterator)
		{
			StringBuilder sb = new StringBuilder();
			while(iterator.HasNext())
			{
				MenuItem menuItem = (MenuItem)iterator.Next();

				sb.Append(menuItem.Name + ", ");
				sb.Append(menuItem.Price + " -- ");
				sb.Append(menuItem.Description + "\n");
			}

			return sb.ToString();
		}
	}
}
