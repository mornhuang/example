using System;
using System.Text;

namespace HeadFirstDesignPatterns.TemplateMethod.CaffeineBeverage
{
	/// <summary>
	/// Summary description for CaffeineBeverage.
	/// </summary>
	
	//TODO: will need to copy this project to the laptop, the new test fixture file and the DeveloperTest.dll.config file in the bin/debug folder
	public abstract class CaffeineBeverage
	{
		public string PrepareRecipe()
		{
			StringBuilder sb = new StringBuilder();
			sb.Append(BoilWater());
			sb.Append(Brew());
			sb.Append(PourInCup());
			sb.Append(AddCondiments());

			return sb.ToString();
		}

		public abstract string Brew();
		public abstract string AddCondiments();

		string BoilWater()
		{
			return "Boiling water\n";
		}

		string PourInCup()
		{
			return "Pouring into cup\n";
		}

	}
}
