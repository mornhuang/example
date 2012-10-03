using System;

namespace HeadFirstDesignPatterns.Strategy.Character
{
	/// <summary>
	/// Summary description for SwordBehavior.
	/// </summary>
	public class SwordBehavior : IWeaponBehavior
	{
		public SwordBehavior()
		{}

		#region IWeaponBehavior Members

		public string UsingWeapon()
		{
			return "I swing at thee with this sword!";
		}

		#endregion
	}
}
