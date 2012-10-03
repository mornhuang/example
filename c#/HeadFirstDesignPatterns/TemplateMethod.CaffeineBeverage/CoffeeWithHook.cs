using System;
using System.Configuration;

namespace HeadFirstDesignPatterns.TemplateMethod.CaffeineBeverage
{
	/// <summary>
	/// Summary description for CoffeeWithHook.
	/// </summary>
	public class CoffeeWithHook : CaffeineBeverageWithHook
	{
		public CoffeeWithHook()
		{}

		public override string Brew()
		{
			return "Dripping coffee through filter\n";
		}

		public override string AddCondiments()
		{
			return "Adding sugar and milk\n";
		}

		public override bool CustomerWantsCondiments()
		{
			//Get user input from app.config file. This way developer tests can be run and asserted
			//results tested via a change in the HeadFirstDesignPatterns.DeveloperTests.dll.config 
			//file without a recompile needed.
			return Convert.ToBoolean(ConfigurationSettings.AppSettings["WantCodiments?"].ToString());
		}

	}
}
