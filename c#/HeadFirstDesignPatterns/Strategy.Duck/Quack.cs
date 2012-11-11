using System;

namespace HeadFirstDesignPatterns.Strategy.Duck
{
	/// <summary>
	/// Quack
	/// </summary>
	public class Quack : IQuackBehavior
	{
		public string Quacking()
		{
			return "Quack";
		}
	}
}
