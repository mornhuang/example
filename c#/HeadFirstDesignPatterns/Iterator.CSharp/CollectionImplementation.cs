using System;
using System.Collections;

namespace HeadFirstDesignPatterns.Iterator.CSharp
{
	/// <summary>
	/// Summary description for CollectionImplementation.
	/// </summary>
	public class CollectionImplementation : CollectionBase
	{
		public CollectionImplementation()
		{}

		public int Add(object item)
		{
			if(!InnerList.Contains(item))
			{
				this.InnerList.Add(item);
			}
			return InnerList.IndexOf(item);
		}

		/// <summary>
		/// Sort method
		/// </summary>
		/// <param name="sortDirection">
		/// Pass in "Asc" for ascending or "Desc" for descending
		/// </param>
		public void Sort(string sortDirection)
		{
			if(sortDirection == null)
			{
				throw new ArgumentNullException("You must pass in \"Asc\" for ascending or \"Desc\" for descending");
			}
			else if(!sortDirection.Equals("Asc") && !sortDirection.Equals("Desc"))
			{
				throw new ArgumentException("You must pass in \"Asc\" for ascending or \"Desc\" for descending");
			}

			if(sortDirection.Equals("Asc"))
			{
				InnerList.Sort();
			}
			else if(sortDirection.Equals("Desc"))
			{
				IComparer myComparer = new CollectionReverseSort();
				InnerList.Sort(myComparer);
			}
		}

		public ArrayList GetList()
		{
			return InnerList;
		}

		public ArrayList GetPagedList(int pageNumber, int pageSize, ArrayList fullList)
		{
			ArrayList newList = new ArrayList();
		
			int startRecord;
			int endRecord;

			startRecord = (pageNumber - 1) * pageSize;
			endRecord = startRecord + pageSize - 1;
			
			for(int i = startRecord; i < endRecord + 1; i++)
			{
				newList.Add(fullList[i]);
			}

			return newList;
		}
	}
}
