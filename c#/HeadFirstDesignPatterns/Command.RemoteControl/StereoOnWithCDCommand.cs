using System;

namespace HeadFirstDesignPatterns.Command.RemoteControl
{
	/// <summary>
	/// Summary description for StereoOnWithCDCommand.
	/// </summary>
	public class StereoOnWithCDCommand :Command
	{
		Stereo stereo;
		public StereoOnWithCDCommand(Stereo stereo)
		{
			this.stereo = stereo;
		}

		#region Command Members

		public object Execute()
		{
			return stereo.On() + 
				"\n" + stereo.SetCD() +
				"\n" + stereo.SetVolume(11);
		}

		#endregion
	}
}
