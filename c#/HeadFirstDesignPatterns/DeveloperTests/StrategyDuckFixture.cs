using System;
using HeadFirstDesignPatterns.Strategy.Duck;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Strategy.Duck
{
	/// <summary>a
	/// DuckTestFixture tests the strategy pattern
	/// </summary>
	[TestFixture]
	public class StrategyDuckFixture
	{
		#region TestMallardDuck
		[Test]
		public void TestMallardDuck()
		{
			HeadFirstDesignPatterns.Strategy.Duck.Duck Mallard = new MallardDuck();
			Assert.AreEqual("Quack",Mallard.PerformQuack());
			Assert.AreEqual("I'm flying!!",Mallard.PerformFly());
		}
		#endregion//TestMallardDuck

		#region TestRubberDuck
		[Test]
		public void TestRubberDuck()
		{
			HeadFirstDesignPatterns.Strategy.Duck.Duck RubberDuck = new RubberDuck();
			Assert.AreEqual("Squeak",RubberDuck.PerformQuack());
			Assert.AreEqual("I can't fly.",RubberDuck.PerformFly());
		}
		#endregion//TestRubberDuck

		#region TestModelDuck
		[Test]
		public void TestModelDuck()
		{
			HeadFirstDesignPatterns.Strategy.Duck.Duck ModelDuck = new ModelDuck();
			Assert.AreEqual("Quack",ModelDuck.PerformQuack());
			Assert.AreEqual("I can't fly.",ModelDuck.PerformFly());
			
			ModelDuck.FlyBehavoir = new FlyRocketPowered();
			Assert.AreEqual("I'm flying with a rocket!", ModelDuck.FlyBehavoir.Fly());
			Assert.AreEqual("I'm flying with a rocket!",ModelDuck.PerformFly());

			ModelDuck.QuackBehavior = new MuteQuack();
			Assert.AreEqual("<<silence>>",ModelDuck.QuackBehavior.Quacking());
			Assert.AreEqual("<<silence>>",ModelDuck.PerformQuack());
		}
		#endregion//TestModelDuck
	}
}
