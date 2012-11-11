using System;

namespace HeadFirstDesignPatterns.State.GumballMachine
{
	/// <summary>
	/// Summary description for SoldOutState.
	/// </summary>
	public class SoldOutState : State
	{
		#region Members
		GumballMachine gumballMachine;
		#endregion//Members

		#region Constructor
		public SoldOutState(GumballMachine gumballMachine)
		{
			this.gumballMachine = gumballMachine;
		}
		#endregion//Constructor

		#region State Members

		public string InsertQuarter()
		{
			return "You can't insert a quarter, the machine is sold out\n";
		}

		public string EjectQuarter()
		{
			return "You can't eject, you haven't inserted a quarter yet\n";
		}

		public string TurnCrank()
		{
			return "Sorry, you already turned the crank\n";
		}

		public string Dispense()
		{
			string outPut;
			
			outPut = gumballMachine.ReleaseBall();
			outPut += "You turned, but there are no gumballs\n";
			
			return outPut;
		}
		#endregion
		
		#region ToString override
		public override string ToString()
		{
			return "sold out";
		}
		#endregion//ToString override
	}
}
