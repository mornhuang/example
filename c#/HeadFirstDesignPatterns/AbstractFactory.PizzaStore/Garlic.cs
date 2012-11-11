using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for Garlic.
	/// </summary>
	public class Garlic :IVeggies
	{
		#region Constructor
		public Garlic()
		{}
		#endregion//Constructor
		
		#region IVeggies Members

		public string toString()
		{
			return "Garlic";
		}

		#endregion
	}
}
