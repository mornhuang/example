using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for ThinCrustDough.
	/// </summary>
	public class ThinCrustDough : IDough
	{
		#region Constructor
		public ThinCrustDough()
		{}
		#endregion//Constructor

		#region IDough Members
		public string toString()
		{
			return "Thin Crust Dough";
		}
		#endregion
	}
}
