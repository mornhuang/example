using System;
using System.Text;

namespace HeadFirstDesignPatterns.Proxy.GumballMachine
{
	/// <summary>
	/// Summary description for GumballMonitor.
	/// </summary>
	public class GumballMonitor
	{
		GumballMachine machine;

		public GumballMonitor(GumballMachine machine)
		{
			this.machine = machine;
		}

		public string Report()
		{
			StringBuilder sb = new StringBuilder();

			sb.Append("Gumball Machine: " + machine.Location + "\n");
			sb.Append("Current Inventory: " + machine.Count + "\n");
			sb.Append("Current State: " + machine.StateOfMachine + "\n");

			return sb.ToString();
		}
	}
}
