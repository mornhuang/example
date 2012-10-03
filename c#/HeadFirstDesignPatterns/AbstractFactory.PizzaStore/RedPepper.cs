using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for RedPepper.
	/// </summary>
	public class RedPepper :IVeggies
	{
		#region Constructor
		public RedPepper()
		{}
		#endregion//Constructor

		#region IVeggies Members

		public string toString()
		{
			return "Red Pepper";
		}

		#endregion
	}
}
