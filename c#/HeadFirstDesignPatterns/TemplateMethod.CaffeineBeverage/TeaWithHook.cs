using System;
using System.Configuration;

namespace HeadFirstDesignPatterns.TemplateMethod.CaffeineBeverage
{
	/// <summary>
	/// Summary description for TeaWithHook.
	/// </summary>
	public class TeaWithHook : CaffeineBeverageWithHook
	{
		public TeaWithHook()
		{}

		public override string Brew()
		{
			return "Steeping the tea\n";
		}

		public override string AddCondiments()
		{
			return "Adding lemon\n";
		}

		public override bool CustomerWantsCondiments()
		{
			//Get user input from app.config file. This way developer tests can be run and asserted
			//results tested via a change in the HeadFirstDesignPatterns.DeveloperTests.dll.config 
			//file without a recompile needed.
			return Convert.ToBoolean(ConfigurationSettings.AppSettings["WantCodiments?"].ToString());;
		}

	}
}
