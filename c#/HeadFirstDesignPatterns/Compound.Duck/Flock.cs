using System;
using System.Text;
using System.Collections;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for Flock.
	/// </summary>
	public class Flock : IQuackable,  IQuackObservable
	{
		#region Members
		ArrayList quackers = new ArrayList();
		Observable observable;
		#endregion//Members

		#region Constructor
		public Flock()
		{
			observable = new Observable(this);
		}
		#endregion//Constructor

		#region Add
		public void Add(IQuackable quacker)
		{
			quackers.Add(quacker);
		}
		#endregion//Add

		#region Quack
		public string Quack()
		{

			StringBuilder sb = new StringBuilder();

			foreach(IQuackable quacker in quackers)
			{
				sb.Append(quacker.Quack());
				sb.Append("\n");
				sb.Append(NotifyObservers());
			}

			return sb.ToString();
		}
		#endregion//Quack

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
