using System;
using System.Configuration;

namespace HeadFirstDesignPatterns.Decorator.Starbuzz
{
	/// <summary>
	/// Summary description for Expresso.
	/// </summary>
	public class Expresso: Beverage
	{
		public Expresso()
		{}

		public override double Cost()
		{
			return GetSize(base.Size);
		}

		public override string GetDescription()
		{
			return "Expresso";
		}

		private double GetSize(BeverageSize size)
		{
			switch(size)
			{
				case BeverageSize.TALL:
					return Convert.ToDouble(ConfigurationSettings.AppSettings["ExpressoSizeTall"]);
				case BeverageSize.GRANDE:
					return Convert.ToDouble(ConfigurationSettings.AppSettings["ExpressoSizeGrande"]);
				case BeverageSize.VENTI:
					return Convert.ToDouble(ConfigurationSettings.AppSettings["ExpressoSizeVenti"]);
				default:
					return 1.50;
			}
		}

	}
}
