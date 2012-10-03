using System;

namespace HeadFirstDesignPatterns.TemplateMethod.CaffeineBeverage
{
	/// <summary>
	/// Summary description for Coffee.
	/// </summary>
	public class Coffee : CaffeineBeverage
	{
		public Coffee()
		{}

		public override string Brew()
		{
			return "Dripping coffee through filter\n";
		}

		public override string AddCondiments()
		{
			return "Adding sugar and milk\n";
		}
	}
}
