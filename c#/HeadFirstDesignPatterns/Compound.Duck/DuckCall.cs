using System;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for MallardDuck.
	/// </summary>
	public class DuckCall : IQuackable, IQuackObservable
	{
		#region Members
		Observable observable;
		#endregion//Members

		#region Constructor
		public DuckCall()
		{
			observable = new Observable(this);
		}
		#endregion//Constructor

		#region IQuackable Members

		public string Quack()
		{
			NotifyObservers();
			return "Kwak";
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
