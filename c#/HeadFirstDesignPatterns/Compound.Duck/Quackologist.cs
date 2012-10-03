using System;
using System.Collections;
using System.Text;

namespace HeadFirstDesignPatterns.Compound.Duck
{
	/// <summary>
	/// Summary description for Quackologist.
	/// </summary>
	public class Quackologist : IObserver
	{
		#region Constructor
		public Quackologist()
		{}
		#endregion//Constructor

		#region IObserver Members

		public string Update(IQuackObservable duck)
		{
			return "Quackologist: the " + duck.GetType().Name + " just quacked";
		}

		#endregion
	}
}
