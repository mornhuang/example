using System;

namespace HeadFirstDesignPatterns.Adapter.Birds
{
	/// <summary>
	/// Summary description for WildTurkey.
	/// </summary>
	public class WildTurkey : Turkey
	{
		public WildTurkey()
		{}

		#region Turkey Members

		public string Gobble()
		{
			return "Gooble, gooble";
		}

		public string Fly()
		{
			return "I'm flying a short distance";
		}

		#endregion
	}
}
