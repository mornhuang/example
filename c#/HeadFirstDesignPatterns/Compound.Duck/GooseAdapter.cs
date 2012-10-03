using System;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for GooseAdapter.
	/// </summary>
	public class GooseAdapter : IQuackable, IQuackObservable
	{
		#region Members
		Goose goose;
		Observable observable;
		#endregion//Members

		#region Constructor
		public GooseAdapter(Goose goose)
		{
			this.goose = goose;
			observable = new Observable(this);
		}
		#endregion//Constructor

		#region IQuackable Members

		public string Quack()
		{
			NotifyObservers();
			return goose.Honk();
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
