using System;

namespace HeadFirstDesignPatterns.Iterator.Menu
{
	/// <summary>
	/// Summary description for DinnerMenuIterator.
	/// </summary>
	public class DinnerMenuIterator :Iterator
	{
		MenuItem[] items;
		int position = 0;

		public DinnerMenuIterator(MenuItem[] items)
		{
			this.items = items;
		}

		#region Iterator Members

		public bool HasNext()
		{
			if(position >= items.Length || items[position] == null)
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
			MenuItem menuItem = items[position];
			position += 1;
			return menuItem;
		}

		#endregion
	}
}
