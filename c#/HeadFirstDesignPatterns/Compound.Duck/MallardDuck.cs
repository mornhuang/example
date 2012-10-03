using System;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for MallardDuck.
	/// </summary>
	public class MallardDuck : IQuackable, IQuackObservable
	{
		#region Members
		Observable observable;
		#endregion//Members

		#region Constructor
		public MallardDuck()
		{
			observable = new Observable(this);
		}
		#endregion//Constructor

		#region IQuackable Members

		public string Quack()
		{
			NotifyObservers();
			return "Quack";
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
