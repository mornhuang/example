using System;

namespace HeadFirstDesignPatterns.Command.RemoteControl
{
	/// <summary>
	/// Summary description for GarageDoor.
	/// </summary>
	public class GarageDoor
	{
		string location;
		public GarageDoor(string location)
		{
			this.location = location;
		}
		
		public string Up()
		{
			return "Garage door is up";
		}

		public string Down()
		{
			return "Garage door is down";
		}

		public string Stop()
		{
			return "Garage door movement is stopped";
		}

		public string LightOn()
		{
			return "Garage door light is on";
		}

		public string LightOff()
		{
			return "Garage door light is off";
		}
	}
}
