using System;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for MallardDuck.
	/// </summary>
	public class RubberDuck : IQuackable, IQuackObservable
	{
		#region Members
		Observable observable;
		#endregion//Members

		#region Constructor
		public RubberDuck()
		{
			observable = new Observable(this);
		}
		#endregion//Constructor

		#region IQuackable Members

		public string Quack()
		{
			NotifyObservers();
			return "Squeak";
		}

		#endregion

		#region IQuackObservable Members

		public void RegisterObserver(IObserver observer)
		{
			observable.RegisterObserver(observer);
		}

		public string NotifyObservers()
		{
			return observable.NotifyObservers();
		}
		#endregion
	}
}
