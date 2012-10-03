using System;

namespace HeadFirstDesignPatterns.Decorator.Starbuzz
{
	/// <summary>
	/// Summary description for Whip.
	/// </summary>
	public class Whip : CondimentDecorator
	{
		Beverage beverage;
		
		public Whip(Beverage beverage)
		{
			this.beverage = beverage;
		}

		public override string GetDescription()
		{
			return beverage.GetDescription() + ", Whip";
		}

		public override double Cost()
		{
			return .10 + beverage.Cost();
		}
	}
}
