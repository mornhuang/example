using System;

namespace HeadFirstDesignPatterns.Decorator.Starbuzz
{
	/// <summary>
	/// Summary description for SteamedMilk.
	/// </summary>
	public class SteamedMilk : CondimentDecorator
	{
		Beverage beverage;
		
		public SteamedMilk(Beverage beverage)
		{
			this.beverage = beverage;
		}

		public override string GetDescription()
		{
			return beverage.GetDescription() + ", Steamed Milk";
		}

		public override double Cost()
		{
			return .10 + beverage.Cost();
		}
	}
}
