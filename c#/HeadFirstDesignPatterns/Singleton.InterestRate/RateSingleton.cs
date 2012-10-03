using System;
using System.Configuration;

namespace HeadFirstDesignPatterns.Singleton.InterestRate
{
	/// <summary>
	/// Summary description for RateSingleton.
	/// </summary>
	public class RateSingleton
	{
		private volatile static RateSingleton uniqueInstance;
		private static object syncRoot = new Object();

		private double currentRate = 
			Convert.ToDouble(ConfigurationSettings.AppSettings["CurrentInterestRate"]);

		public double CurrentRate
		{
			get
			{
				return currentRate;
			}
			set
			{
				currentRate = value;
			}
		}

		private RateSingleton()
		{}

		public static RateSingleton GetInstance()
		{
			//The approach below ensures that only one instance is created and only 
			//when the instance is needed. Also, the uniqueInstance variable is 
			//declared to be volatile to ensure that assignment to the instance variable 
			//completes before the instance variable can be accessed. Lastly, 
			//this approach uses a syncRoot instance to lock on, rather than 
			//locking on the type itself, to avoid deadlocks.

			if(uniqueInstance == null)
			{
				lock (syncRoot)
				{
					if(uniqueInstance == null)
					{
						uniqueInstance = new RateSingleton();
					}
				}
			}
			return uniqueInstance;
		}
	}
}
