using System;
using HeadFirstDesignPatterns.Iterator.CSharp;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Iterator.CSharp
{
	/// <summary>
	/// Summary description for IteratorCSharpFixture.
	/// </summary>
	[TestFixture]
	public class IteratorCSharpFixture
	{
		#region TestMyIntArray
		[Test]
		public void TestMyIntArray()
		{
			int[] items = new int[6] {2,4,6,8,10,12};
			MyIntArray myIntArray = new MyIntArray(items);
			//Now we can use foreach to iterate through the array
			foreach(int i in myIntArray)
			{
				Assert.IsTrue(i % 2 == 0);
				Assert.IsTrue(i > 0);
				Assert.IsTrue(i <= 12);
			}
		}
		#endregion//TestMyIntArray
	}
}
