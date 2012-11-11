using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for NYPizzaStore.
	/// </summary>
	public class ChicagoPizzaStore : PizzaStore
	{
		#region Constructor
		public ChicagoPizzaStore()
		{}
		#endregion//Constructor

		#region CreatePizza
		protected override Pizza CreatePizza(string type)
		{
			Pizza pizza = null;
			IPizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();

			switch(type)
			{
				case "cheese":
					pizza = new CheesePizza(ingredientFactory);
					pizza.Name = "Chicago Style Cheese Pizza";
					break;
				case "clam":
					pizza = new ClamPizza(ingredientFactory);
					pizza.Name = "Chicago Style Clam Pizza";
					break;
				case "pepperoni":
					pizza = new PepperoniPizza(ingredientFactory);
					pizza.Name = "Chicago Style Pepperoni Pizza";
					break;
			}
			return pizza;
		}
		#endregion//CreatePizza

	}
}
