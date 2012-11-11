
namespace HeadFirstDesignPatterns.Strategy.Duck
{
	/// <summary>
	/// abstract base class of Ducks
	/// </summary>
	public abstract class Duck
	{
		protected IFlyBehavior flyBehavior;
		protected IQuackBehavior quackBehavior;
		
		public IQuackBehavior QuackBehavior
		{
			get
			{
				return quackBehavior;
			}
			set
			{
				quackBehavior = value;
			}
		}

		public IFlyBehavior FlyBehavoir
		{
			get
			{
				return flyBehavior;
			}
			set
			{
				flyBehavior = value;
			}
		}

		
		public abstract object Display();

		public object PerformFly()
		{
			return FlyBehavoir.Fly();
		}

		public object PerformQuack()
		{
			return QuackBehavior.Quacking();
		}

		public string Swim()
		{
			return "All ducks float, even decoys!";
		}
	}
}
