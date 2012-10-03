using System;
using System.Collections;
using HeadFirstDesignPatterns.Iterator.CSharp;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Iterator.CSharp
{
	/*
	 -- Test List --
	 -instantiate the collection object and check to see if list is not null - DONE
	 -instantiate the collection object and see if the list can be added to and checked for 1 item - DONE
	 -remove an item from the collection - DONE
	 -add 3items and check - DONE
	 -add 3 items and sort and check - DONE
	 -send in null input and expect exception - DONE
	 -send in wrong sort input and expect exception - DONE
	 -Add item to list that is dupe. Make sure dupe does not get added. - DONE
	 -Add item and get back int value of item. - DONE
	 -Take collection and page collection by returning the proper items in the particular page - DONE
	 */
	/// <summary>
	/// Summary description for IteratorCSharpCollectionFixture.
	/// </summary>
	[TestFixture]
	public class IteratorCSharpCollectionFixture
	{
		CollectionImplementation collectionObj;

		[SetUp]
		public void Init()
		{
			collectionObj = new CollectionImplementation();
		}

		[TearDown]
		public void Dispose()
		{
			collectionObj = null;
		}

		[Test]
		public void TestIsNotNull()
		{
			Assert.IsNotNull(collectionObj);
		}

		[Test]
		public void TestAddToListAndCheck()
		{
			collectionObj.Add("New Item");
			Assert.AreEqual(collectionObj.Count,1);
			//Console.WriteLine(collectionObj.Count);
		}

		[Test]
		public void TestAddToListThenRemoveAndCheck()
		{
			collectionObj.Add("New Item");
			Assert.AreEqual(collectionObj.Count,1);
			collectionObj.RemoveAt(0);
			Assert.AreEqual(collectionObj.Count,0);
			//Console.WriteLine(collectionObj.Count);
		}

		[Test]
		public void TestAdd3ToListThenCheck()
		{
			collectionObj.Add("New 1");
			collectionObj.Add("New 2");
			collectionObj.Add("New 3");
			Assert.AreEqual(collectionObj.Count,3);
			//Console.WriteLine(collectionObj.Count);
		}

		[Test]
		public void TestAdd3ToListThenSortAndCheck()
		{
			collectionObj.Add("New 3");
			collectionObj.Add("New 1");
			collectionObj.Add("New 2");

			collectionObj.Sort("Asc");
			Assert.AreEqual("New 1",collectionObj.GetList()[0].ToString());
			
			collectionObj.Sort("Desc");
			Assert.AreEqual("New 3",collectionObj.GetList()[0].ToString());
		}

		[Test]
		[ExpectedException(typeof(ArgumentNullException))]
		public void TestSendInNullToSortAndCheck()
		{
			collectionObj.Add("New 1");
			collectionObj.Add("New 2");
			collectionObj.Add("New 3");

			collectionObj.Sort(null);
		}

		[Test]
		[ExpectedException(typeof(ArgumentException))]
		public void TestSendInSomethingToSortAndCheck()
		{
			collectionObj.Add("New 1");
			collectionObj.Add("New 2");
			collectionObj.Add("New 3");

			collectionObj.Sort("something");
		}

		[Test]
		public void TestSendInDupesAndCheck()
		{
			collectionObj.Add("New 1");
			collectionObj.Add("New 2");
			collectionObj.Add("New 1");
			collectionObj.Add("New 2");
			collectionObj.Add("New 3");

			Assert.AreEqual(collectionObj.Count,3);
		}

		[Test]
		public void TestAddItemGetIntValueOfItem()
		{
			collectionObj.Add("New 1");
			collectionObj.Add("New 2");
			collectionObj.Add("New 3");

			Assert.AreEqual(collectionObj.Add("New 4"),3);
		}

		[Test]
		public void TestPagingOfCollection()
		{
			collectionObj.Add("New 1");
			collectionObj.Add("New 2");
			collectionObj.Add("New 3");
			collectionObj.Add("New 4");
			collectionObj.Add("New 5");
			collectionObj.Add("New 6");
			collectionObj.Add("New 7");
			collectionObj.Add("New 8");
			collectionObj.Add("New 9");
			collectionObj.Add("New 10");
			collectionObj.Add("New 11");

			ArrayList fullList = collectionObj.GetList();
			ArrayList pageList = new ArrayList();
			int pageNumber = 3;
			int pageSize = 3;
			object[] listItems;

			pageList = collectionObj.GetPagedList(pageNumber,pageSize,fullList);

			listItems = pageList.ToArray();
			
			Assert.AreEqual(pageList.Count,pageSize);
			Assert.AreEqual(listItems[0].ToString(),"New 7");
			Assert.AreEqual(listItems[1].ToString(),"New 8");
			Assert.AreEqual(listItems[2].ToString(),"New 9");

//			foreach(string item in pageList)
//			{
//				Console.WriteLine(item);
//			}
		}
	}
}
