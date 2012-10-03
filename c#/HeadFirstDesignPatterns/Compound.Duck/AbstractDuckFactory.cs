using System;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for AbstractDuckFactory.
	/// </summary>
	public abstract class AbstractDuckFactory
	{
		public abstract IQuackable CreateMallardDuck();
		public abstract IQuackable CreateRedheadDuck();
		public abstract IQuackable CreateDuckCall();
		public abstract IQuackable CreateRubberDuck();
	}
}
