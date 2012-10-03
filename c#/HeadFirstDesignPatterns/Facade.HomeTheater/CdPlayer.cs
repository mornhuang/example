using System;

namespace HeadFirstDesignPatterns.Facade.HomeTheater
{
	/// <summary>
	/// Summary description for CdPlayer.
	/// </summary>
	public class CdPlayer 
	{
		string description;
		int currentTrack;
		Amplifier amplifier;
		string title;
			
		public string Description
		{
			get { return description; }
		}

		public CdPlayer(string description, Amplifier amplifier) 
		{
			this.description = description;
			this.amplifier = amplifier;
		}
		 
		public string On() 
		{
			return description + " on\n";
		}
		 
		public string Off() 
		{
			return description + " off\n";
		}

		public string Eject() 
		{
			title = null;
			return description + " eject\n";
		}
		 
		public string Play(string title) 
		{
			this.title = title;
			currentTrack = 0;
			return description + " playing \"" + title + "\"\n";
		}

		public string Play(int track) 
		{
			if (title == null) 
			{
				return description + " can't play track " + currentTrack + 
					", no cd inserted\n";
			} 
			else 
			{
				currentTrack = track;
				return description + " playing track " + currentTrack + "\n";
			}
		}

		public string Stop() 
		{
			currentTrack = 0;
			return description + " stopped\n";
		}
		 
		public string Pause() 
		{
			return description + " paused \"" + title + "\"\n";
		}
	}
}
