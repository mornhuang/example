using System;

namespace HeadFirstDesignPatterns.Strategy.Character
{
	/// <summary>
	/// Summary description for AxeBehavior.
	/// </summary>
	public class AxeBehavior : IWeaponBehavior
	{
		public AxeBehavior()
		{}

		#region IWeaponBehavior Members

		public string UsingWeapon()
		{
			return "I will chop thine head off!";
		}

		#endregion
	}
}
