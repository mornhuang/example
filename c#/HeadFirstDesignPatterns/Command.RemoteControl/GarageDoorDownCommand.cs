using System;

namespace HeadFirstDesignPatterns.Command.RemoteControl
{
	/// <summary>
	/// Summary description for GarageDoorOpenCommand.
	/// </summary>
	public class GarageDoorDownCommand : Command
	{
		GarageDoor garageDoor;

		public GarageDoorDownCommand(GarageDoor garageDoor)
		{
			this.garageDoor = garageDoor;
		}

		#region Command Members
		public object Execute()
		{
			return garageDoor.Down();
		}
		#endregion
	}
}
