using System;
using System.Text;

namespace HeadFirstDesignPatterns.Observer.WeatherData
{
	/// <summary>
	/// Summary description for ForcastDisplay.
	/// </summary>
	public class ForcastDisplay : IObserver, IDisplayElement
	{
		private float currentPressure = 29.92f;  
		private float lastPressure;
		private ISubject weatherData;

		public ForcastDisplay(ISubject weatherData)
		{
			this.weatherData = weatherData;
			weatherData.RegisterObserver(this);
		}

		#region IObserver Members

		public void Update(float temperature, float humidity, float pressure)
		{
			lastPressure = currentPressure;
			currentPressure = pressure;
		}

		#endregion

		#region IDisplayElement Members

		public object Display()
		{
			StringBuilder sb = new StringBuilder();

			sb.Append("Forecast: ");
			
			if(currentPressure > lastPressure) 
			{
				sb.Append("Improving weather on the way!");
			}
			else if (currentPressure == lastPressure)
			{
				sb.Append("More of the same");
			} 
			else if (currentPressure < lastPressure)
			{
				sb.Append("Watch out for cooler, rainy weather");
			}
			return sb.ToString();
		}

		#endregion
	}
}
