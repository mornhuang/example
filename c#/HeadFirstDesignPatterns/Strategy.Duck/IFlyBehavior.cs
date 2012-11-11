using System;

namespace HeadFirstDesignPatterns.Strategy.Duck
{
	/// <summary>
	/// IFlyBehavior interface for flying behaviors
	/// </summary>
	public interface IFlyBehavior
	{
		object Fly();
	}
}
