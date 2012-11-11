using System;

namespace HeadFirstDesignPatterns.Facade.HomeTheater
{
	/// <summary>
	/// Summary description for Amplifier.
	/// </summary>
	public class Amplifier 
	{
		string description;
		Tuner tuner;
		DvdPlayer dvd;
		CdPlayer cd;

		public string Description
		{
			get { return description; }
		}
		
		public Amplifier(string description) 
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
	 
		public string SetStereoSound() 
		{
			return description + " stereo mode on\n";
		}
	 
		public string SetSurroundSound() 
		{
			return description + " surround sound on (5 speakers, 1 subwoofer)\n";
		}
	 
		public string SetVolume(int level) 
		{
			return description + " setting volume to " + level + "\n";
		}

		public string SetTuner(Tuner tuner) 
		{
			this.tuner = tuner;
			return description + " setting tuner to " + tuner.Description + "\n";
		}
	  
		public string SetDvd(DvdPlayer dvd) 
		{
			this.dvd = dvd;
			return description + " setting DVD player to " + dvd.Description + "\n";
		}
	 
		public string SetCd(CdPlayer cd) 
		{
			this.cd = cd;
			return description + " setting CD player to " + cd.Description + "\n";
		}
	}
}