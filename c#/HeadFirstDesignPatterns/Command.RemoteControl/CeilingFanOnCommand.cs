using System;

namespace HeadFirstDesignPatterns.Command.RemoteControl
{
	/// <summary>
	/// Summary description for CeilingFanOnCommand.
	/// </summary>
	public class CeilingFanOnCommand : Command
	{
		CeilingFan ceilingFan;

		public CeilingFanOnCommand(CeilingFan ceilingFan)
		{
			this.ceilingFan = ceilingFan;
		}

		#region Command Members

		public object Execute()
		{
			return ceilingFan.High();
		}

		#endregion
	}
}
