using System;
using WeatherDataImp = HeadFirstDesignPatterns.Observer.WeatherData;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Observer.WeatherData
{
	/// <summary>
	/// Summary description for ObserverCurrentConditionDisplayFixture.
	/// </summary>
	[TestFixture]
	public class ObserverCurrentConditionDisplayFixture
	{
		#region Members
		WeatherDataImp.WeatherData weatherData;
		WeatherDataImp.CurrentConditionsDisplay currentConditionsDisplay;
		WeatherDataImp.ForcastDisplay forcastDisplay;
		WeatherDataImp.StatisticsDisplay statisticsDisplay;
		#endregion//Members

		#region TestFixtureSetUp Init()
		[TestFixtureSetUp]
		public void Init()
		{
			weatherData = new WeatherDataImp.WeatherData();
			currentConditionsDisplay = 
				new WeatherDataImp.CurrentConditionsDisplay(weatherData);
			forcastDisplay = new WeatherDataImp.ForcastDisplay(weatherData);
			statisticsDisplay = new WeatherDataImp.StatisticsDisplay(weatherData);
		}
		#endregion// TestFixtureSetUp Init()

		#region TestFixtureTearDown Dispose()
		[TestFixtureTearDown]
		public void Dispose()
		{
			weatherData = null;
			currentConditionsDisplay = null;
			forcastDisplay = null;
			statisticsDisplay = null;
		}
		#endregion//TestFixtureTearDown Dispose()

		#region TestCurrentConditionsDisplay
		[Test]
		public void TestCurrentConditionsDisplay()
		{
			weatherData.SetMeasurements(80,65,30.4f);

			Assert.AreEqual("Current conditions: 80F degrees and 65% humidity",
				currentConditionsDisplay.Display());
		}
		#endregion//TestCurrentConditionsDisplay

		#region TestForecastDisplay
		[Test]
		public void TestForecastDisplay()
		{
			weatherData.SetMeasurements(81,63,31.2f);
			//lastPressure = 29.92f
			Assert.AreEqual("Forecast: Improving weather on the way!",
				forcastDisplay.Display());
			
			weatherData.SetMeasurements(81,63,29.92f);
			Assert.AreEqual("Forecast: Watch out for cooler, rainy weather",
				forcastDisplay.Display());

			weatherData.SetMeasurements(81,63,29.92f);
			Assert.AreEqual("Forecast: More of the same",
				forcastDisplay.Display());
		}
		#endregion//TestForecastDisplay

		#region TestStatisticsDisplay
		[Test]
		public void TestStatisticsDisplay()
		{
			weatherData.SetMeasurements(80,63,31.2f);			
			weatherData.SetMeasurements(81,63,29.92f);
			weatherData.SetMeasurements(84,63,29.92f);
			Assert.AreEqual("Avg/Max/Min temperature = 81.14F/84F/80F",
				statisticsDisplay.Display());
		}
		#endregion//TestStatisticsDisplay
	}
}
