using System;

namespace HeadFirstDesignPatterns.Strategy.Duck
{
	/// <summary>
	/// ModelDuck Object
	/// </summary>
	public class ModelDuck : Duck
	{
		public ModelDuck()
		{
			flyBehavior = new FlyNoWay();
			quackBehavior = new Quack();
		}

		public override object Display()
		{
			return "I'm a model duck";
		}

	}
}
