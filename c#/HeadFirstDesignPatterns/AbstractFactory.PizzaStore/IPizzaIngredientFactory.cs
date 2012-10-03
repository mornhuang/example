using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for IPizzaIngredientFactory.
	/// </summary>
	public interface IPizzaIngredientFactory
	{
		IDough CreateDough();
		ISauce CreateSauce();
		ICheese CreateCheese();
		IVeggies[] CreateVeggies();
		IPepperoni CreatePepporoni();
		IClams CreateClam();
	}
}
