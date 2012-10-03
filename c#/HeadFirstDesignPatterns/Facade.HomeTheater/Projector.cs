using System;

namespace HeadFirstDesignPatterns.Facade.HomeTheater
{
	/// <summary>
	/// Summary description for Projector.
	/// </summary>
	public class Projector 
	{
		string description;
		DvdPlayer dvdPlayer;

		public string Description
		{
			get { return description; }
		}
		
		public Projector(string description, DvdPlayer dvdPlayer) 
		{
			this.description = description;
			this.dvdPlayer = dvdPlayer;
		}
	 
		public string On() 
		{
			return description + " on\n";
		}
	 
		public string Off() 
		{
			return description + " off\n";
		}

		public string WideScreenMode() 
		{
			return description + " in widescreen mode (16x9 aspect ratio)\n";
		}

		public string TvMode() 
		{
			return description + " in tv mode (4x3 aspect ratio)\n";
		}
	}
}
