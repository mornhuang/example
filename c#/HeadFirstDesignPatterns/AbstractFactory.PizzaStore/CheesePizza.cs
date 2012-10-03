using System;
using System.Text;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for CheesePizza.
	/// </summary>
	public class CheesePizza : Pizza
	{
		#region Members
		IPizzaIngredientFactory ingredientFactory;
		#endregion//Members

		#region Constructor
		public CheesePizza(IPizzaIngredientFactory ingredientFactory)
		{
			this.ingredientFactory = ingredientFactory;
		}
		#endregion//Constructor

		#region Prepare
		public override string Prepare()
		{
			dough = ingredientFactory.CreateDough();
			sauce = ingredientFactory.CreateSauce();
			cheese = ingredientFactory.CreateCheese();

			StringBuilder sb = new StringBuilder();
			sb.Append("Preparing " + Name + "\n");
			sb.Append(dough.toString() +"\n");
			sb.Append(sauce.toString() +"\n");
			sb.Append(cheese.toString());

			return sb.ToString();
		}
		#endregion//Prepare
	}
}
