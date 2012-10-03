using System;

namespace HeadFirstDesignPatterns.Decorator.Starbuzz
{
	/// <summary>
	/// Summary description for Beverage.
	/// </summary>
	public abstract class Beverage
	{
		private BeverageSize size;

		public virtual string GetDescription()
		{
			return "Unknown Beverage";
		}

		public BeverageSize Size
		{
			get
			{
				return size;
			}
			set
			{
				size = value;
			}
		}

		public abstract double Cost();
	}
}
