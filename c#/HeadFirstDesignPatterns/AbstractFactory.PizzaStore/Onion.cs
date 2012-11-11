using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for Onion.
	/// </summary>
	public class Onion :IVeggies
	{
		#region Constructor
		public Onion()
		{}
		#endregion//Constructor
		
		#region IVeggies Members

		public string toString()
		{
			return "Onion";
		}

		#endregion
	}
}
