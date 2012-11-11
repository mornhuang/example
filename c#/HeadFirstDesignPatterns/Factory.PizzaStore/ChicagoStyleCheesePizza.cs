using System;
using System.Collections;

namespace HeadFirstDesignPatterns.Factory.PizzaStore
{
	/// <summary>
	/// Summary description for ChicagoStyleCheesePizza.
	/// </summary>
	public class ChicagoStyleCheesePizza : Pizza
	{
		#region Constructor
		public ChicagoStyleCheesePizza()
		{
			name = "Chicago Style Deep Dish Cheese Pizza";
			dough = "Extra Thick Crust Dough";
			sauce = "Plum Tomato Sauce";

			toppings.Add("Shredded Mozzarella Cheese");
		}
		#endregion//Constructor

		#region Cut
		public override string Cut()
		{
			//base.Cut ();
			return "Cutting the pizza into square slices \n";
		}
		#endregion//Cut

	}
}
