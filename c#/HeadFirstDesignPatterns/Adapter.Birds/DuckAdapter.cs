using System;
using System.Text;

namespace HeadFirstDesignPatterns.Adapter.Birds
{
	/// <summary>
	/// Summary description for TurkeyAdapter.
	/// </summary>
	public class DuckAdapter : Turkey
	{
		Duck duck;

		public DuckAdapter(Duck duck)
		{
			this.duck = duck;
		}

		#region Turkey Members

		public string Gobble()
		{
			return duck.Quack();
		}

		public string Fly()
		{
			return duck.Fly();
		}

		#endregion
	}
}
