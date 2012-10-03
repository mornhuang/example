using System;

namespace HeadFirstDesignPatterns.Decorator.Starbuzz
{
	/// <summary>
	/// Summary description for DarkRoast.
	/// </summary>
	public class DarkRoast : Beverage
	{
		public DarkRoast()
		{}

		public override double Cost()
		{
			return .99;
		}

		public override string GetDescription()
		{
			return "Dark Roast Coffee";
		}

	}
}
