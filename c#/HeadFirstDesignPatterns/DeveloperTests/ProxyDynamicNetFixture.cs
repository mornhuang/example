using System;
using System.Reflection;
using HeadFirstDesignPatterns.Proxy.Dynamic.Net;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Proxy.Dynamic.Net
{
	/// <summary>
	/// ProxyDynamicNetFixture tests the proxy dynamic pattern
	/// </summary>
	[TestFixture]
	public class ProxyDynamicFixture
	{
		#region TestProxyOwner
		[Test]
		public void TestProxyOwner()
		{			
			Person person = new Person();
			IPerson personProxy = (IPerson)DynamicProxyFactory.Instance.CreateProxy(person, new InvocationDelegate(OwnerInvocationHandler));
			
			personProxy.Gender = "Male";
			personProxy.Name = "John Doe";
			personProxy.Interest = "Eating junkfood and watching talkshows";
			Assert.AreEqual("Name: John Doe","Name: " + person.Name);
			Assert.AreEqual("Gender: Male","Gender: " + person.Gender);
			Assert.AreEqual("Interest: Eating junkfood and watching talkshows",
				"Interest: " + person.Interest);

//			Console.WriteLine("Name: " + person.Name);
//			Console.WriteLine("Gender: " + person.Gender);
//			Console.WriteLine("Interest: " + person.Interest);
		}
		#endregion//TestProxyOwner

		#region TestProxyOwnerException
		[Test]
		[ExpectedException(typeof(UnauthorizedAccessException))]
		public void TestProxyOwnerException()
		{			
			Person person = new Person();
			IPerson personProxy = (IPerson)DynamicProxyFactory.Instance.CreateProxy(person, new InvocationDelegate(OwnerInvocationHandler));
			
			personProxy.Name = "John Doe";
			personProxy.HotOrNot = 10;
			
			Console.WriteLine("Name: " + person.Name);
			Console.WriteLine("Rating: " + person.HotOrNot);

		}
		#endregion//TestProxyOwnerException

		#region TestProxyNonOwnerSetRating
		[Test]
		public void TestProxyNonOwnerSetRating()
		{			
			Person person = new Person();
			person.Name = "John Doe";

			IPerson personProxy = (IPerson)DynamicProxyFactory.Instance.CreateProxy(person, new InvocationDelegate(NonOwnerInvocationHandler));
			
			personProxy.HotOrNot = 10;
			personProxy.HotOrNot = 5;
			
			Assert.AreEqual("Name: John Doe","Name: " + person.Name);
			Assert.AreEqual("Rating: 7","Rating: " + person.HotOrNot);

//			Console.WriteLine("Name: " + person.Name);
//			Console.WriteLine("Rating: " + person.HotOrNot);
		}
		#endregion//TestProxyNonOwnerSetRating

		#region TestProxyNonOwnerException
		[Test]
		[ExpectedException(typeof(UnauthorizedAccessException))]
		public void TestProxyNonOwnerException()
		{			
			Person person = new Person();
			person.Name = "John Doe";

			IPerson personProxy = (IPerson)DynamicProxyFactory.Instance.CreateProxy(person, new InvocationDelegate(NonOwnerInvocationHandler));
			
			personProxy.Name = "John Doe";
			personProxy.Interest = "This will not display due to exception";		
			
			Console.WriteLine("Name: " + person.Name);
			Console.WriteLine("Interest: " + person.Interest);
		}
		#endregion//TestProxyNonOwnerException

		#region OwnerInvocationHandler
		private object OwnerInvocationHandler(object target, MethodBase method, object[] parameters) 
		{
			object result = null;

			try
			{
				if(!method.Name.Equals("set_HotOrNot"))
				{
					result = method.Invoke(target, parameters);
				}
				else
				{
					throw new UnauthorizedAccessException("You are not permitted to rate yourself");
				}
			}
			catch(ApplicationException ex)
			{
				return ex.StackTrace;
			}
			
			return result;
		}
		#endregion//OwnerInvocationHandler

		#region NonOwnerInvocationHandler
		private object NonOwnerInvocationHandler(object target, MethodBase method, object[] parameters) 
		{
			object result = null;

			try
			{
				if(method.Name.Equals("set_HotOrNot"))
				{
					result = method.Invoke(target, parameters);
				}
				else
				{
					throw new UnauthorizedAccessException("You are not permitted to update another's personal information!");
				}
			}
			catch(ApplicationException ex)
			{
				return ex.StackTrace;
			}
			
			return result;
		}
		#endregion//NonOwnerInvocationHandler
	}
}
