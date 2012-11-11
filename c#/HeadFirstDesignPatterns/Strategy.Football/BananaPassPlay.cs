using System;

namespace HeadFirstDesignPatterns.Strategy.Football
{
	/// <summary>
	/// Summary description for BananaPass.
	/// </summary>
	public class BananaPassPlay : IPlay
	{
		public BananaPassPlay()
		{}

		#region IPlay Members

		public string Movement()
		{
			return "I am running a banana pass pattern";
		}

		#endregion
	}
}
