using System;

namespace HeadFirstDesignPatterns.Command.RemoteControl
{
	/// <summary>
	/// Summary description for GarageDoorOpenCommand.
	/// </summary>
	public class GarageDoorUpCommand : Command
	{
		GarageDoor garageDoor;

		public GarageDoorUpCommand(GarageDoor garageDoor)
		{
			this.garageDoor = garageDoor;
		}

		#region Command Members

		public object Execute()
		{
			return garageDoor.Up();
		}

		#endregion
	}
}
