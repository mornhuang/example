using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for SlicedPepperoni.
	/// </summary>
	public class SlicedPepperoni : IPepperoni
	{
		#region Constructor
		public SlicedPepperoni()
		{}
		#endregion//Constructor

		#region toString
		public string toString()
		{
			return "Sliced Pepperoni";
		}
		#endregion//toString
	}
}
