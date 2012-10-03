using System;
using System.Collections;

namespace HeadFirstDesignPatterns.Iterator.CSharp
{
	/// <summary>
	/// Summary description for Iterator. Did not use this but here is a c# iterator.
	/// </summary>
	public abstract class Iterator
	{
		readonly IEnumerator enumerator;
		bool hasNext;

		public Iterator(IEnumerator enumerator)
		{
			this.enumerator = enumerator;
			this.hasNext = enumerator.MoveNext();
		}

		public bool HasNext
		{
			get { return hasNext; }
		}

		public object Current
		{
			get { return enumerator.Current; }
		}
	}
}
