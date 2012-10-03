using System;
using System.Collections;

namespace HeadFirstDesignPatterns.Iterator.Menu
{
	/// <summary>
	/// Summary description for CafeMenuIterator.
	/// </summary>
	public class CafeMenuIterator : Iterator
	{
		Hashtable menuItems = new Hashtable();
		int position = 1;
		
		public CafeMenuIterator(Hashtable menuItems)
		{
			this.menuItems = menuItems;
		}

		#region Iterator Members
		public bool HasNext()
		{
			return position <= menuItems.Count ? true : false ;
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
