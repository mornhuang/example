using System;

namespace HeadFirstDesignPatterns.Strategy.Character
{
	/// <summary>n
	/// Character
	/// </summary>
	public abstract class Character
	{
		protected IWeaponBehavior weapon;

		public Character()
		{}

		public string Fight()
		{
			return weapon.UsingWeapon();
		}
	}
}
