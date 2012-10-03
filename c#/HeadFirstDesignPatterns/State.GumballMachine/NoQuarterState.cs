using System;

namespace HeadFirstDesignPatterns.State.GumballMachine
{
	/// <summary>
	/// Summary description for NoQuarterState.
	/// </summary>
	public class NoQuarterState : State
	{
		#region Members
		GumballMachine gumballMachine;
		State hasQuarterState;
		#endregion//Members

		#region Constructor
		public NoQuarterState(GumballMachine gumballMachine)
		{
			this.gumballMachine = gumballMachine;
			hasQuarterState = new HasQuarterState(gumballMachine);
		}
		#endregion//Constructor

		#region State Members

		public string InsertQuarter()
		{
			gumballMachine.StateOfMachine = hasQuarterState;
			return "You inserted a quarter\n";
		}

		public string EjectQuarter()
		{
			return "You haven't inserted a quarter\n";
		}

		public string TurnCrank()
		{
			return "You turned but there's no quarter\n";
		}

		public string Dispense()
		{
			string outPut;

			outPut = gumballMachine.ReleaseBall();
			outPut += "You need to pay first\n";

			return outPut;
		}

		#endregion

		#region ToString override
		public override string ToString()
		{
			return "waiting for quarter";
		}
		#endregion//ToString override
	}
}
