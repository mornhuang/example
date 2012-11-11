using System;
using System.Collections;

namespace HeadFirstDesignPatterns.Composite.Menu
{
	/// <summary>
	/// Summary description for NullIterator.
	/// </summary>
	public class NullIterator : IEnumerator, IEnumerable
	{
		public NullIterator()
		{}

		#region IEnumerator Members

		public void Reset()
		{
			throw new UnsupportedOperationException("We ain't doing it");
		}

		public object Current
		{
			get
			{
				return null;
			}
		}

		public bool MoveNext()
		{
			return false;
		}

		#endregion

		#region IEnumerable Members

		public IEnumerator GetEnumerator()
		{
			return (IEnumerator)this;
		}

		#endregion
	}
}
