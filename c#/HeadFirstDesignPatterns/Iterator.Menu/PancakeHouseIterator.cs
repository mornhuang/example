using System;
using System.Collections;

namespace HeadFirstDesignPatterns.Iterator.Menu
{
	/// <summary>
	/// Summary description for PancakeHouseIterator.
	/// </summary>
	public class PancakeHouseIterator : Iterator
	{
		ArrayList menuItems = new ArrayList();
		int position = 0;

		public PancakeHouseIterator(ArrayList menuItems)
		{
			this.menuItems = menuItems;
		}

		#region Iterator Members

		public bool HasNext()
		{
			if(position >= menuItems.Count || menuItems[position] == null)
			{
				return false;
			}
			else
			{
				return true;
			}
		}

		public object Next()
		{
			MenuItem menuItem = (MenuItem)menuItems[position];
			position += 1;
			return menuItem;
		}

		#endregion
	}
}
