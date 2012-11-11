using System;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for FrozenClams.
	/// </summary>
	public class FrozenClams : IClams
	{
		#region Constructor
		public FrozenClams()
		{}
		#endregion//Constructor

		#region toString
		public string toString()
		{
			return "Frozen Clams";
		}
		#endregion//toString

	}
}
