using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for BlackOlives.
	/// </summary>
	public class BlackOlives :IVeggies
	{
		#region Constructor
		public BlackOlives()
		{}
		#endregion//Constructor
		
		#region IVeggies Members

		public string toString()
		{
			return "Black Olives";
		}

		#endregion
	}
}
