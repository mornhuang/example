using System;
using System.Text;
using System.Configuration;
using HeadFirstDesignPatterns.TemplateMethod.CaffeineBeverage;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.TemplateMethod.CaffeineBeverage
{
	/// <summary>
	/// TemplateMethodCaffeineFixture tests the Template Method pattern
	/// </summary>
	[TestFixture]
	public class TemplateMethodCaffeineFixture
	{
		#region Members
		Tea tea;
		Coffee coffee;
		CoffeeWithHook coffeeWithHook;
		TeaWithHook teaWithHook;
		StringBuilder teaResult;
		StringBuilder coffeeResult;
		StringBuilder coffeeWithHookYesResult;
		StringBuilder coffeeWithHookNoResult;
		StringBuilder teaWithHookYesResult;
		StringBuilder teaWithHookNoResult;
		#endregion//Members

		#region SetUp Init()
		[SetUp]
		public void Init()
		{
			tea = new Tea();
			coffee = new Coffee();
			coffeeWithHook = new CoffeeWithHook();
			teaWithHook = new TeaWithHook();
			teaResult = new StringBuilder();
			coffeeResult = new StringBuilder();
			coffeeWithHookYesResult = new StringBuilder();
			coffeeWithHookNoResult = new StringBuilder();
			teaWithHookYesResult = new StringBuilder();
			teaWithHookNoResult = new StringBuilder();
		}
		#endregion// SetUp Init()

		#region TearDown Dispose()
		[TearDown]
		public void Dispose()
		{
			tea = null;
			coffee = null;
			coffeeWithHook = null;
			teaWithHook = null;
			teaResult = null;
			coffeeResult = null;
			coffeeWithHookYesResult = null;
			coffeeWithHookNoResult = null;
			teaWithHookYesResult = null;
			teaWithHookNoResult = null;
		}
		#endregion//TearDown Dispose()

		#region TestTea
		[Test]
		public void TestTea()
		{
			teaResult.Append("Boiling water\n");
			teaResult.Append("Steeping the tea\n");
			teaResult.Append("Pouring into cup\n");
			teaResult.Append("Adding lemon\n");
			Assert.AreEqual(teaResult.ToString(),tea.PrepareRecipe());
		}
		#endregion//TestTea

		#region TestCoffee
		[Test]
		public void TestCoffee()
		{
			coffeeResult.Append("Boiling water\n");
			coffeeResult.Append("Dripping coffee through filter\n");
			coffeeResult.Append("Pouring into cup\n");
			coffeeResult.Append("Adding sugar and milk\n");
			Assert.AreEqual(coffeeResult.ToString(),coffee.PrepareRecipe());
		}
		#endregion//TestCoffee

		#region TestCoffeeWithHook
		[Test]
		public void TestCoffeeWithHook()
		{
			if(Convert.ToBoolean(ConfigurationSettings.AppSettings["WantCodiments?"].ToString()))
			{
				coffeeWithHookYesResult.Append("Boiling water\n");
				coffeeWithHookYesResult.Append("Dripping coffee through filter\n");
				coffeeWithHookYesResult.Append("Pouring into cup\n");
				coffeeWithHookYesResult.Append("Adding sugar and milk\n");
				Assert.AreEqual(coffeeWithHookYesResult.ToString(),
					coffeeWithHook.PrepareRecipe());
			}
			else
			{
				coffeeWithHookNoResult.Append("Boiling water\n");
				coffeeWithHookNoResult.Append("Dripping coffee through filter\n");
				coffeeWithHookNoResult.Append("Pouring into cup\n");
				Assert.AreEqual(coffeeWithHookNoResult.ToString(),
					coffeeWithHook.PrepareRecipe());
			}
		}
		#endregion//TestCoffeeWithHook

		#region TestTeaWithHook
		[Test]
		public void TestTeaWithHook()
		{
			if(Convert.ToBoolean(ConfigurationSettings.AppSettings["WantCodiments?"].ToString()))
			{
				teaWithHookYesResult.Append("Boiling water\n");
				teaWithHookYesResult.Append("Steeping the tea\n");
				teaWithHookYesResult.Append("Pouring into cup\n");
				teaWithHookYesResult.Append("Adding lemon\n");
				Assert.AreEqual(teaWithHookYesResult.ToString(),
					teaWithHook.PrepareRecipe());
			}
			else
			{
				teaWithHookNoResult.Append("Boiling water\n");
				teaWithHookNoResult.Append("Steeping the tea\n");
				teaWithHookNoResult.Append("Pouring into cup\n");
				Assert.AreEqual(teaWithHookNoResult.ToString(),
					teaWithHook.PrepareRecipe());
			}
		}
		#endregion//TestTeaWithHook
	}
}
