using System;

namespace HeadFirstDesignPatterns.TemplateMethod.CaffeineBeverage
{
	/// <summary>
	/// Summary description for Tea.
	/// </summary>
	public class Tea : CaffeineBeverage
	{
		public Tea()
		{}

		public override string Brew()
		{
			return "Steeping the tea\n";
		}

		public override string AddCondiments()
		{
			return "Adding lemon\n";
		}
	}
}
