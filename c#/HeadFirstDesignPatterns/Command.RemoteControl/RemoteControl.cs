using System;
using System.Text;

namespace HeadFirstDesignPatterns.Command.RemoteControl
{
	/// <summary>
	/// Summary description for RemoteControl.
	/// </summary>
	//
	// This is the invoker
	//
	public class Remote 
	{
		Command[] onCommands;
		Command[] offCommands;
 
		public Remote() 
		{
			onCommands = new Command[7];
			offCommands = new Command[7];
 
			Command noCommand = new NoCommand();
			for (int i = 0; i < 7; i++) 
			{
				onCommands[i] = noCommand;
				offCommands[i] = noCommand;
			}
		}
  
		public void SetCommand(int slot, Command onCommand, Command offCommand) 
		{
			onCommands[slot] = onCommand;
			offCommands[slot] = offCommand;
		}
 
		public object OnButtonWasPushed(int slot)
		{
			return onCommands[slot].Execute();
		}
 
		public object OffButtonWasPushed(int slot) 
		{
			return offCommands[slot].Execute();
		}
  
		public string toString() 
		{
			StringBuilder sb = new StringBuilder();
			sb.Append("\n------ Remote Control -------\n");
			for (int i = 0; i < onCommands.Length; i++) 
			{
				sb.Append("[slot " + i + "] " + onCommands[i].GetType().Name
					+ "    " + offCommands[i].GetType().Name + "\n");
			}
			return sb.ToString();
		}
	}
}
