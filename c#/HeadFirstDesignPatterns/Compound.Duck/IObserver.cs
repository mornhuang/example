using System;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for IObserver.
	/// </summary>
	public interface IObserver
	{
		string Update(IQuackObservable duck);
	}
}
