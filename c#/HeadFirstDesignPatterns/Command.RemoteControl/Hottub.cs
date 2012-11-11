using System;

namespace HeadFirstDesignPatterns.Command.RemoteControl
{
	/// <summary>
	/// Summary description for Hottub.
	/// </summary>
	public class Hottub
	{
		bool on;
		int temperature;

		public Hottub() 
		{
		}

		public bool On() 
		{
			on = true;
			return on;
		}

		public bool Off() 
		{
			on = false;
			return on;
		}

		public string BubblesOn() 
		{
			if (on) 
			{
				return "Hottub is bubbling!";
			}
			return null;
		}

		public string BubblesOff() 
		{
			if (on) 
			{
				return "Hottub is not bubbling";
			}
			return null;
		}

		public string JetsOn() 
		{
			if (on) 
			{
				return "Hottub jets are on";
			}
			return null;
		}

		public string JetsOff() 
		{
			if (on) 
			{
				return "Hottub jets are off";
			}
			return null;
		}

		public int SetTemperature(int temperature) 
		{
			this.temperature = temperature;
			return temperature;
		}

		public string Heat() 
		{
			temperature = 105;
			return "Hottub is heating to a steaming 105 degrees";
		}

		public string Cool() 
		{
			temperature = 98;
			return "Hottub is cooling to 98 degrees";
		}
	}
}
