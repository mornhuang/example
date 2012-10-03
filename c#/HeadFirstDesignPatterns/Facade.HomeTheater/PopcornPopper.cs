using System;

namespace HeadFirstDesignPatterns.Facade.HomeTheater
{
	/// <summary>
	/// Summary description for PopcornPopper.
	/// </summary>
	public class PopcornPopper 
	{
		string description;

		public string Description
		{
			get { return description; }
		}
		
		public PopcornPopper(string description) 
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

		public string Pop() 
		{
			return description + " popping popcorn!\n";
		}
	}
}