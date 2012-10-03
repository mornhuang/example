using System;
using HeadFirstDesignPatterns.Decorator.Starbuzz;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Decorator.Starbuzz
{
	/// <summary>
	/// DecoratorStarbuzzFixture tests the decorator pattern
	/// </summary>
	[TestFixture]
	public class DecoratorStarbuzzFixture
	{
		#region TestExpresso
		[Test]
		public void TestExpresso()
		{
			Beverage beverage = new Expresso();
			Assert.AreEqual("Expresso $1.5",beverage.GetDescription() +
				" $" + beverage.Cost());
		}
		#endregion//TestExpresso

		#region TestExpressoWithSize
		[Test]
		public void TestExpressoWithSize()
		{
			BeverageSize beverageSize = BeverageSize.GRANDE;
			Beverage beverage = new Expresso();
			beverage.Size = beverageSize;
			beverage = new Mocha(beverage);
			beverage.Size = beverageSize;
			Assert.AreEqual("Expresso, Mocha $1.9",beverage.GetDescription() +
				" $" + beverage.Cost());
		}
		#endregion//TestExpressoWithSize

		#region TestHouseBlend
		[Test]
		public void TestHouseBlend()
		{
			Beverage beverage = new HouseBlend();
			beverage = new Mocha(beverage);
			beverage = new SteamedMilk(beverage);
			Assert.AreEqual("House Blend Coffee, Mocha, Steamed Milk $1.19",beverage.GetDescription() +
				" $" + beverage.Cost());
		}
		#endregion//TestHouseBlend

		#region TestDarkRoast
		[Test]
		public void TestDarkRoast()
		{
			Beverage beverage = new DarkRoast();
			beverage = new Mocha(beverage);
			beverage = new Soy(beverage);
			Assert.AreEqual("Dark Roast Coffee, Mocha, Soy $1.34",beverage.GetDescription() +
				" $" + beverage.Cost());
		}
		#endregion//TestDarkRoast
	
		#region TestDecaf
		[Test]
		public void TestDecaf()
		{
			Beverage beverage = new Decaf();
			beverage = new Mocha(beverage);
			beverage = new Whip(beverage);
			Assert.AreEqual("Decaf Coffee, Mocha, Whip $1.35",beverage.GetDescription() +
				" $" + beverage.Cost());
		}
		#endregion//TestDecaf
	}
}
