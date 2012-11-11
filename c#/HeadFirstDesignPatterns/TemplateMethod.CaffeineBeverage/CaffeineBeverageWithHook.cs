using System;
using System.Text;

namespace HeadFirstDesignPatterns.TemplateMethod.CaffeineBeverage
{
	/// <summary>
	/// Summary description for CaffeineBeverageWithHook.
	/// </summary>
	public abstract class CaffeineBeverageWithHook
	{
		public string PrepareRecipe()
		{
			StringBuilder sb = new StringBuilder();

			sb.Append(BoilWater());
			sb.Append(Brew());
			sb.Append(PourInCup());
			if(CustomerWantsCondiments())
			{
				sb.Append(AddCondiments());
			}

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

		public virtual bool CustomerWantsCondiments()
		{
			return true;
		}
	}
}
