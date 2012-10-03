using System;

namespace HeadFirstDesignPatterns.State.GumballMachine
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	public class SoldState : State
	{
		#region Members
		GumballMachine gumballMachine;
		State noQuarterState;
		State soldOutState;
		#endregion//Members

		#region Constructor
		public SoldState(GumballMachine gumballMachine)
		{
			this.gumballMachine = gumballMachine;
			noQuarterState = new NoQuarterState(gumballMachine);
			soldOutState = new SoldOutState(gumballMachine);
		}
		#endregion//Constructor

		#region State Members

		public string InsertQuarter()
		{
			return "Please wait, we're already giving you a gumball\n";
		}

		public string EjectQuarter()
		{
			return "Sorry, you already turned the crank\n";
		}

		public string TurnCrank()
		{
			return "Turning twice doesn't get you another gumball!\n";
		}

		public string Dispense()
		{
			string outPut;
			outPut = gumballMachine.ReleaseBall();
			if(gumballMachine.Count > 0)
			{
				gumballMachine.StateOfMachine = noQuarterState;
			}
			else
			{
				gumballMachine.StateOfMachine = soldOutState;
				outPut += "Oops, out of gumballs!\n";
			}
			
			return outPut;
		}

		#endregion

		#region ToString override
		public override string ToString()
		{
			return "delivering a gumball";
		}
		#endregion//ToString override
	}
}
