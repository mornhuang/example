using System;
using System.Collections;

namespace HeadFirstDesignPatterns.Iterator.CSharp
{
	/// <summary>
	/// Summary description for CollectionReverseSort.
	/// </summary>
	public class CollectionReverseSort : IComparer
	{
		// Calls CaseInsensitiveComparer.Compare with the parameters reversed.
		public int Compare( Object x, Object y )  
		{
			return( (new CaseInsensitiveComparer()).Compare( y, x ) );
		}
	}
}
