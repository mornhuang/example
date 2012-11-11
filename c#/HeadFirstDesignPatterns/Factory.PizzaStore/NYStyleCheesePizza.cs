using System;
using System.Collections;

namespace HeadFirstDesignPatterns.Factory.PizzaStore
{
	/// <summary>
	/// Summary description for NYStyleCheesePizza.
	/// </summary>
	public class NYStyleCheesePizza : Pizza
	{
		#region Constructor
		public NYStyleCheesePizza()
		{
			name = "NY Style Sauce and Cheese Pizza";
			dough = "Thin Crust Dough";
			sauce = "Martinara Sauce";

			toppings.Add("Greated Reggiano Cheese");
		}
		#endregion
	}
}
