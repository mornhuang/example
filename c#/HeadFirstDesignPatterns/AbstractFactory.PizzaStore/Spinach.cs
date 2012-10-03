using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for Spinach.
	/// </summary>
	public class Spinach :IVeggies
	{
		#region Constructor
		public Spinach()
		{}
		#endregion//Constructor
		
		#region IVeggies Members

		public string toString()
		{
			return "Spinach";
		}

		#endregion
	}
}
