using System;

namespace HeadFirstDesignPatterns.Strategy.Character
{
	/// <summary>
	/// Summary description for Queen.
	/// </summary>
	public class Queen : Character
	{
		public Queen()
		{
			weapon = new KnifeBehavior();
		}
	}
}
