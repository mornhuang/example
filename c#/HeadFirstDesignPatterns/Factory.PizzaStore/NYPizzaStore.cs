using System;

namespace HeadFirstDesignPatterns.Factory.PizzaStore
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
			if(type.Equals("cheese"))
			{
				return new NYStyleCheesePizza();
			}
			else return null;
		}
		#endregion//CreatePizza
	}
}
