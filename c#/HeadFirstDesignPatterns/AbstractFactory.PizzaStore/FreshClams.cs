using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for FreshClams.
	/// </summary>
	public class FreshClams : IClams
	{
		#region Constructor
		public FreshClams()
		{}
		#endregion//Constructor

		#region toString
		public string toString()
		{
			return "Fresh Clams";
		}
		#endregion//toString

	}
}
