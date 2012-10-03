using System;

namespace HeadFirstDesignPatterns.Factory.PizzaStore
{
	/// <summary>
	/// Summary description for PizzaStore.
	/// </summary>
	public abstract class PizzaStore
	{
		#region Constructor
		public PizzaStore()
		{}
		#endregion//Constructor

		#region OrderPizza
		public Pizza OrderPizza(string type)
		{
			Pizza pizza;
			
			pizza = CreatePizza(type);

			pizza.Prepare();
			pizza.Bake();
			pizza.Cut();
			pizza.Box();

			return pizza;
		}
		#endregion//OrderPizza

		#region CreatePizza
		protected abstract Pizza CreatePizza(string type);
		#endregion//CreatePizza
	}
}
