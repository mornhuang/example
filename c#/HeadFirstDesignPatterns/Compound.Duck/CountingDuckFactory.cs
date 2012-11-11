using System;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for DuckFactory.
	/// </summary>
	public class CountingDuckFactory : AbstractDuckFactory
	{
		#region Constructor
		public CountingDuckFactory()
		{}
		#endregion//Constructor

		#region CreateMallardDuck
		public override IQuackable CreateMallardDuck()
		{
			return new QuackCounter(new MallardDuck());
		}
		#endregion//CreateMallardDuck

		#region CreateRedheadDuck
		public override IQuackable CreateRedheadDuck()
		{
			return new QuackCounter(new RedheadDuck());
		}
		#endregion//CreateRedheadDuck

		#region CreateDuckCall
		public override IQuackable CreateDuckCall()
		{
			return new QuackCounter(new DuckCall());
		}
		#endregion//CreateDuckCall

		#region CreateRubberDuck
		public override IQuackable CreateRubberDuck()
		{
			return new QuackCounter(new RubberDuck());
		}
		#endregion//CreateRubberDuck
	}
}
