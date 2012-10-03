using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for MarinaraSauce.
	/// </summary>
	public class MarinaraSauce : ISauce
	{
		#region Constructor
		public MarinaraSauce()
		{}
		#endregion//Constructor
	
		#region ISauce Members
		public string toString()
		{
			return "Marinara Sauce";
		}
		#endregion
	}
}
