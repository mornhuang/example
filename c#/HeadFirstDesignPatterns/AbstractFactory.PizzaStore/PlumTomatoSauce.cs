using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for PlumTomatoSauce.
	/// </summary>
	public class PlumTomatoSauce : ISauce
	{
		#region Constructor
		public PlumTomatoSauce()
		{}
		#endregion//Constructor
	
		#region ISauce Members
		public string toString()
		{
			return "Plum Tomato Sauce";
		}
		#endregion
	}
}
