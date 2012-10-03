using System;

namespace HeadFirstDesignPatterns.Facade.HomeTheater
{
	/// <summary>
	/// Summary description for Screen.
	/// </summary>
	public class Screen 
	{
		string description;

		public string Description
		{
			get { return description; }
		}
		
		public Screen(string description) 
		{
			this.description = description;
		}
	 
		public string Up() 
		{
			return description + " going up\n";
		}
	 
		public string Down() 
		{
			return description + " going down\n";
		}
	}
}
