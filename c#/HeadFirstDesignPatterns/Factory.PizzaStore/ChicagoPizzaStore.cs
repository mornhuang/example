using System;

namespace HeadFirstDesignPatterns.Factory.PizzaStore
{
	/// <summary>
	/// Summary description for ChicagoPizzaStore.
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
			if(type.Equals("cheese"))
			{
				return new ChicagoStyleCheesePizza();
			}
			else return null;
		}
		#endregion//CreatePizza
	}
}
