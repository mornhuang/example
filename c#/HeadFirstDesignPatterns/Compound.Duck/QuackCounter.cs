using System;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for QuackCounter.
	/// </summary>
	public class QuackCounter : IQuackable, IQuackObservable
	{
		#region Members
		IQuackable duck;
		static int numberOfQuacks;
		Observable observable;
		#endregion//Members

		#region QuackCount
		public static int QuackCount
		{
			get 
			{
				return numberOfQuacks + 1;
			}
			set
			{
				numberOfQuacks = value;
			}
		}
		#endregion//QuackCount

		#region Constructor
		public QuackCounter(IQuackable duck)
		{
			this.duck = duck;
			observable = new Observable(this);
		}
		#endregion//Constructor

		#region IQuackable Members

		public string Quack()
		{
			NotifyObservers();
			numberOfQuacks++;
			return duck.Quack();
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
