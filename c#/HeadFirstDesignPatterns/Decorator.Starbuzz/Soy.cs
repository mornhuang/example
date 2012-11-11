using System;

namespace HeadFirstDesignPatterns.Decorator.Starbuzz
{
	/// <summary>
	/// Summary description for Soy.
	/// </summary>
	public class Soy : CondimentDecorator
	{
		Beverage beverage;
		
		public Soy(Beverage beverage)
		{
			this.beverage = beverage;
		}

		public override string GetDescription()
		{
			return beverage.GetDescription() + ", Soy";
		}

		public override double Cost()
		{
			return .15 + beverage.Cost();
		}
	}
}
