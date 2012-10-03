using System;

namespace HeadFirstDesignPatterns.Adapter.Birds
{
	/// <summary>
	/// Summary description for MallardDuck.
	/// </summary>
	public class MallardDuck : Duck
	{
		public MallardDuck()
		{}

		#region Duck Members

		public string Quack()
		{
			return "Quack";
		}

		public string Fly()
		{
			return "I'm flying";
		}

		#endregion
	}
}
