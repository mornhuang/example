using System;

namespace HeadFirstDesignPatterns.Strategy.Character
{
	/// <summary>
	/// King
	/// </summary>
	public class King : Character
	{
		public King()
		{
			weapon = new SwordBehavior();
		}
	}
}
