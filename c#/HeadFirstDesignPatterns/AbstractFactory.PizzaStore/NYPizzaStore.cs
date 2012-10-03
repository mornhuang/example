using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for NYPizzaStore.
	/// </summary>
	public class NYPizzaStore : PizzaStore
	{
		#region Constructor
		public NYPizzaStore()
		{}
		#endregion//Constructor

		#region CreatePizza
		protected override Pizza CreatePizza(string type)
		{
			Pizza pizza = null;
			IPizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

			switch(type)
			{
				case "cheese":
					pizza = new CheesePizza(ingredientFactory);
					pizza.Name = "New York Style Cheese Pizza";
					break;
				case "clam":
					pizza = new ClamPizza(ingredientFactory);
					pizza.Name = "New York Style Clam Pizza";
					break;
				case "pepperoni":
					pizza = new PepperoniPizza(ingredientFactory);
					pizza.Name = "New York Style Pepperoni Pizza";
					break;
			}
			return pizza;
		}
		#endregion//CreatePizza

	}
}
