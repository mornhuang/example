using System;

namespace HeadFirstDesignPatterns.Strategy.Character
{
	/// <summary>
	/// Summary description for SwordBehavior.
	/// </summary>
	public class KnifeBehavior : IWeaponBehavior
	{
		public KnifeBehavior()
		{}

		#region IWeaponBehavior Members

		public string UsingWeapon()
		{
			return "I will knife thee, nave!";
		}

		#endregion
	}
}
