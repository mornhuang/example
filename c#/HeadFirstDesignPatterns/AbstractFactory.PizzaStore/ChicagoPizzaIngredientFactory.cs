using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for ChicagoPizzaIngredientFactory.
	/// </summary>
	public class ChicagoPizzaIngredientFactory : IPizzaIngredientFactory
	{
		#region Constructor
		public ChicagoPizzaIngredientFactory()
		{}
		#endregion//Constructor

		#region ChicagoPizzaIngredientFactory Members

		public IDough CreateDough()
		{
			return new ThickCrustDough();
		}

		public ISauce CreateSauce()
		{
			return new PlumTomatoSauce();
		}

		public ICheese CreateCheese()
		{
			return new Mozzerella();
		}

		public IVeggies[] CreateVeggies()
		{
			IVeggies[] veggies = {new BlackOlives(), new Spinach(), new EggPlant()};
			return veggies;
		}

		public IPepperoni CreatePepporoni()
		{
			return new SlicedPepperoni();
		}

		public IClams CreateClam()
		{
			return new FrozenClams();
		}

		#endregion
	}
}
