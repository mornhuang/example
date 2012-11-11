using System;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for IQuackObservable.
	/// </summary>
	public interface IQuackObservable
	{
		void RegisterObserver(IObserver observer);
		string NotifyObservers();
	}
}
