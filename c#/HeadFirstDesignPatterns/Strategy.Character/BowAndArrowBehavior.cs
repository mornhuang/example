using System;

namespace HeadFirstDesignPatterns.Strategy.Character
{
	/// <summary>
	/// Summary description for BowAndArrowBehavior.
	/// </summary>
	public class BowAndArrowBehavior : IWeaponBehavior
	{
		public BowAndArrowBehavior()
		{}

		#region IWeaponBehavior Members

		public string UsingWeapon()
		{
			return "I shot my arrow at thee!";
		}

		#endregion
	}
}
