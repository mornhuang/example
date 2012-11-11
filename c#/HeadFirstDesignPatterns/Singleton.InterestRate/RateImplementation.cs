using System;

namespace HeadFirstDesignPatterns.Singleton.InterestRate
{
	/// <summary>
	/// Summary description for RateImplementation.
	/// </summary>
	public class RateImplementation
	{
		public RateImplementation()
		{}

		public double GetRate()
		{
			RateSingleton rate = RateSingleton.GetInstance();
			return rate.CurrentRate;
		}
	}
}
