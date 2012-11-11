using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for ReggianoCheese.
	/// </summary>
	public class ReggianoCheese : ICheese
	{
		#region Constructor
		public ReggianoCheese()
		{}
		#endregion//Constructor
		
		#region ICheese Members

		public string toString()
		{
			return "Reggiano Cheese";
		}

		#endregion
	}
}
