using System;

namespace HeadFirstDesignPatterns.Facade.HomeTheater
{
	/// <summary>
	/// Summary description for DvdPlayer.
	/// </summary>
	public class DvdPlayer 
	{
		string description;
		int currentTrack;
		Amplifier amplifier;
		string movie;

		public string Description
		{
			get { return description; }
		}
			
		public DvdPlayer(string description, Amplifier amplifier) 
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
			movie = null;
			return description + " eject\n";
		}
		 
		public string Play(string movie) 
		{
			this.movie = movie;
			currentTrack = 0;
			return description + " playing \"" + movie + "\"\n";
		}

		public string Play(int track) 
		{
			if (movie == null) 
			{
				return description + " can't play track " + track.ToString() + " no dvd inserted\n";
			} 
			else 
			{
				currentTrack = track;
				return description + " playing track " + currentTrack.ToString() + " of \"" + movie + "\"\n";
			}
		}

		public string Stop() 
		{
			currentTrack = 0;
			return description + " stopped \"" + movie + "\"\n";
		}
		 
		public string Pause() 
		{
			return description + " paused \"" + movie + "\"\n";
		}

		public string SetTwoChannelAudio() 
		{
			return description + " set two channel audio\n";
		}
		 
		public string SetSurroundAudio() 
		{
			return description + " set surround audio\n";
		}
	}
}