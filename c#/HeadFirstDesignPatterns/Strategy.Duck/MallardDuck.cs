using System;

namespace HeadFirstDesignPatterns.Strategy.Duck
{
	/// <summary>
	/// Summary description for MallardDuck.
	/// </summary>
	public class MallardDuck : Duck
	{
		public MallardDuck()
		{
			quackBehavior = new Quack();
			flyBehavior = new FlyWithWings();
		}

		public override object Display()
		{
			return "I'm a real Mallard duck";
			
		}
	}
}
