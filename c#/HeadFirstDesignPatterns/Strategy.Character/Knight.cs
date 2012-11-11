using System;

namespace HeadFirstDesignPatterns.Strategy.Character
{
	/// <summary>
	/// Summary description for Knight.
	/// </summary>
	public class Knight : Character
	{
		public Knight()
		{
			weapon = new BowAndArrowBehavior();
		}
	}
}
