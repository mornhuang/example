using System;

namespace HeadFirstDesignPatterns.Strategy.Football
{
	/// <summary>
	/// Summary description for PullingBlock.
	/// </summary>
	public class PullingBlockPlay : IPlay
	{
		public PullingBlockPlay()
		{}

		#region IPlay Members

		public string Movement()
		{
			return "I am pulling to block";
		}

		#endregion
	}
}
