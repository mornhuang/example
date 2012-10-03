using System;
using System.Text;

namespace HeadFirstDesignPatterns.Observer.WeatherData
{
	/// <summary>
	/// Summary description for HeatIndexDisplay.
	/// </summary>
	public class HeatIndexDisplay : IObserver, IDisplayElement
	{
		private float heatIndex = 0.0f;
		private ISubject weatherData;

		public HeatIndexDisplay(ISubject weatherData)
		{
			this.weatherData = weatherData;
			weatherData.RegisterObserver(this);
		}

		#region IObserver Members

		public void Update(float temperature, float relativeHumidity, float pressure)
		{
			heatIndex = computeHeatIndex(temperature, relativeHumidity);
		}

		#endregion

		#region IDisplayElement Members

		public object Display()
		{
			return "Heat index is " + heatIndex;
		}

		#endregion

		#region computeHeatIndex
		private float computeHeatIndex(float temperature, 
			float relativeHumidity) 
		{
			float heatIndex = (float)((16.923 + (0.185212 * temperature) + 
				(5.37941 * relativeHumidity) - (0.100254 * temperature * relativeHumidity) +
				(0.00941695 * (temperature * temperature)) + 
				(0.00728898 * (relativeHumidity * relativeHumidity)) +
				(0.000345372 * (temperature * temperature * relativeHumidity)) - 
				(0.000814971 * (temperature * relativeHumidity * relativeHumidity)) +
				(0.0000102102 * (temperature * temperature * relativeHumidity * relativeHumidity)) - 
				(0.000038646 * (temperature * temperature * temperature)) + 
				(0.0000291583 * (relativeHumidity * relativeHumidity * relativeHumidity)) + 
				(0.00000142721 * (temperature * temperature * temperature * relativeHumidity)) +
				(0.000000197483 * (temperature * relativeHumidity * relativeHumidity * relativeHumidity)) - 
				(0.0000000218429 * (temperature * temperature * temperature * relativeHumidity * relativeHumidity)) +     
				0.000000000843296 * (temperature * temperature * relativeHumidity * relativeHumidity * relativeHumidity)) -
				(0.0000000000481975 * (temperature * temperature * temperature * relativeHumidity * relativeHumidity * relativeHumidity)));
			return heatIndex;
		}
		#endregion//computeHeatIndex

	}
}
