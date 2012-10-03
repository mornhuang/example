using System;
using System.Text;
using System.Collections;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for Observable.
	/// </summary>
	public class Observable : IQuackObservable
	{
		#region Members
		ArrayList observers = new ArrayList();
		IQuackObservable duck;
		#endregion//Members

		#region Constructor
		public Observable(IQuackObservable duck)
		{
			this.duck = duck;
		}
		#endregion//Constructor

		#region IQuackObservable Members

		public void RegisterObserver(IObserver observer)
		{
			observers.Add(observer);
		}

		public string NotifyObservers()
		{
			StringBuilder sb = new StringBuilder();
			foreach(IObserver observer in observers)
			{
				sb.Append(observer.Update(duck));
				sb.Append("\n");
			}

			return sb.ToString();
		}

		#endregion
	}
}
