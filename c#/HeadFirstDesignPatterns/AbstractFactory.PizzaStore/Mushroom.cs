using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for Mushroom.
	/// </summary>
	public class Mushroom :IVeggies
	{
		#region Constructor
		public Mushroom()
		{}
		#endregion//Constructor
		
		#region IVeggies Members

		public string toString()
		{
			return "Mushroom";
		}

		#endregion
	}
}
