using System;

namespace HeadFirstDesignPatterns.Observer.WeatherData
{
	/// <summary>
	/// IObserver
	/// </summary>
	public interface IObserver
	{
		void Update(float temperature, float humidity,
			float pressure);
	}
}
