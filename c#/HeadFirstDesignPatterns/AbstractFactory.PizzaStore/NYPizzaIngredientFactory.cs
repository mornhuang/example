using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for NYPizzaIngredientFactory.
	/// </summary>
	public class NYPizzaIngredientFactory : IPizzaIngredientFactory
	{
		#region Constructor
		public NYPizzaIngredientFactory()
		{}
		#endregion//Constructor

		#region IPizzaIngredientFactory Members

		public IDough CreateDough()
		{
			return new ThinCrustDough();
		}

		public ISauce CreateSauce()
		{
			return new MarinaraSauce();
		}

		public ICheese CreateCheese()
		{
			return new ReggianoCheese();
		}

		public IVeggies[] CreateVeggies()
		{
			IVeggies[] veggies = {new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
			return veggies;
		}

		public IPepperoni CreatePepporoni()
		{
			return new SlicedPepperoni();
		}

		public IClams CreateClam()
		{
			return new FreshClams();
		}

		#endregion
	}
}
