using System;

namespace HeadFirstDesignPatterns.Command.RemoteControl
{
	/// <summary>
	/// Summary description for StereoOffCommand.
	/// </summary>
	public class StereoOffCommand :Command
	{
		Stereo stereo;
		public StereoOffCommand(Stereo stereo)
		{
			this.stereo = stereo;
		}

		#region Command Members

		public object Execute()
		{
			return stereo.Off();
		}

		#endregion
	}
}
