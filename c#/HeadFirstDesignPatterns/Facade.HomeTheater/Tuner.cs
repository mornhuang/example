using System;

namespace HeadFirstDesignPatterns.Facade.HomeTheater
{
	/// <summary>
	/// Summary description for Tuner.
	/// </summary>
	public class Tuner 
	{
		string description;
		double frequency;

		public string Description
		{
			get { return description; }
		}
		
		public Tuner(string description, Amplifier amplifier) 
		{
			this.description = description;
		}
	 
		public string On() 
		{
			return description + " on\n";
		}
	 
		public string Off() 
		{
			return description + " off\n";
		}
	 
		public string SetFrequency(double frequency) 
		{
			this.frequency = frequency;
			return description + " setting frequency to " + frequency.ToString() + "\n";
		}
	 
		public string SetAm() 
		{
			return description + " setting AM mode\n";
		}
	 
		public string SetFm() 
		{
			return description + " setting FM mode\n";
		}
	}
}