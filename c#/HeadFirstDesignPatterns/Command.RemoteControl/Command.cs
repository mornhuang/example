using System;

namespace HeadFirstDesignPatterns.Command.RemoteControl
{
	/// <summary>
	/// Command Pattern implemented interface
	/// </summary>
	public interface Command
	{
		object Execute();
	}
}
