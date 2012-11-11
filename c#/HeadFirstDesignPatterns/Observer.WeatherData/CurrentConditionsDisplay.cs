using System;

namespace HeadFirstDesignPatterns.Observer.WeatherData
{
	/// <summary>
	/// Summary description for CurrentConditionsDisplay.
	/// </summary>
	public class CurrentConditionsDisplay : IObserver, IDisplayElement
	{
		private float temperature;
		private float humidity;
		private float pressure;
		private ISubject weatherData;

		public CurrentConditionsDisplay(ISubject weatherData)
		{
			this.weatherData = weatherData;
			weatherData.RegisterObserver(this);
		}

		#region IObserver Members

		public void Update(float temperature, float humidity, float pressure)
		{
			this.temperature = temperature;
			this.humidity = humidity;
			this.pressure = pressure;
		}

		#endregion

		#region IDisplayElement Members

		public object Display()
		{
			return "Current conditions: " + temperature +
				"F degrees and " + humidity + "% humidity";
		}

		#endregion
	}
}
