using System;

namespace HeadFirstDesignPatterns.Strategy.Character
{
	/// <summary>
	/// Summary description for Troll.
	/// </summary>
	public class Troll : Character
	{
		public Troll()
		{
			weapon = new AxeBehavior();
		}
	}
}
