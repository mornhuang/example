using System;
using HeadFirstDesignPatterns.Singleton.InterestRate;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Singleton.Rate
{
	/// <summary>
	/// SingletonRateFixture tests the singleton pattern
	/// </summary>
	[TestFixture]
	public class SingletonRateFixture
	{
		#region TestCurrentRate
		[Test]
		public void TestCurrentRate()
		{
			RateImplementation theRate = new RateImplementation();
			RateImplementation theRate2 = new RateImplementation();
			Assert.AreEqual(.015,theRate.GetRate());
			Assert.AreEqual(.015,theRate2.GetRate());
		}
		#endregion//TestCurrentRate
	}
}
