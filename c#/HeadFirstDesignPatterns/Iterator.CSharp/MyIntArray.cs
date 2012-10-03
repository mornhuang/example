using System;
using System.Collections;

namespace HeadFirstDesignPatterns.Iterator.CSharp
{
	/// <summary>
	/// MyIntArray implements System.Collections.IEnumerable so 
	/// that it can be used by foreach. This will be more versatile
	/// with C# 2.0 and generics with the IEnumerable and IEnumerator
	/// interfaces.
	/// </summary>
	public class MyIntArray: IEnumerable 
	{
		int[] items;
		public MyIntArray(int[] items) 
		{
			this.items = items;
		}

		#region IEnumerable implementation
		IEnumerator IEnumerable.GetEnumerator() 
		{
			return new ArrayEnumerator(this);
		}
		#endregion//IEnumerable implementation

		/// <summary>
		/// ArrayEnumerator - Internal iterator that implements IEnumerator
		/// </summary>
		public class ArrayEnumerator: IEnumerator 
		{
			int index;
			MyIntArray myIntArray;

			public ArrayEnumerator(MyIntArray myIntArray) 
			{
				this.myIntArray = myIntArray;
				index = -1;
			}

			#region IEnumerable implementation
			public void Reset() 
			{
				index = -1;
			}

			public bool MoveNext() 
			{
				index++;
				return(index < myIntArray.items.GetLength(0));
			}

			object IEnumerator.Current 
			{
				get 
				{
					return myIntArray.items[index];
				}
			}
			#endregion//IEnumerable implementation
		}
	}
}
