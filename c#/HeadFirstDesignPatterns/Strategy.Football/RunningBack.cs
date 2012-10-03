using System;

namespace HeadFirstDesignPatterns.Strategy.Football
{
	/// <summary>
	/// Summary description for RunningBack.
	/// </summary>
	public class RunningBack : Player
	{
		public RunningBack()
		{
			pattern2Run = new BlockingBackPlay();
		}
	}
}
