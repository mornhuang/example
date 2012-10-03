using System;

namespace HeadFirstDesignPatterns.Strategy.Football
{
	/// <summary>
	/// Summary description for WideReceiver.
	/// </summary>
	public class WideReceiver : Player
	{
		public WideReceiver()
		{
			pattern2Run = new BananaPassPlay();
		}
	}
}
