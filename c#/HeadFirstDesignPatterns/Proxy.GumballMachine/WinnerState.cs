using System;

namespace HeadFirstDesignPatterns.Proxy.GumballMachine
{
	/// <summary>
	/// Summary description for WinnerState.
	/// </summary>
	public class WinnerState : State
	{
		
		#region Members
		GumballMachine gumballMachine;
		State soldOutState;
		State noQuarterState;
		#endregion//Members

		#region Constructor
		public WinnerState(GumballMachine gumballMachine)
		{
			this.gumballMachine = gumballMachine;
			soldOutState = new SoldOutState(gumballMachine);
			noQuarterState = new NoQuarterState(gumballMachine);
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
			outPut = "YOUR A WINNER! You get two gumballs for your quarter\n";
			outPut += gumballMachine.ReleaseBall();
			if(gumballMachine.Count == 0)
			{
				gumballMachine.StateOfMachine = soldOutState;
			}
			else
			{
				outPut += gumballMachine.ReleaseBall();
				if(gumballMachine.Count > 0)
				{
					gumballMachine.StateOfMachine = noQuarterState;
				}
				else
				{
					outPut += "Oops, out of gumballs!\n";
					gumballMachine.StateOfMachine = soldOutState;
				}
			}
			return outPut;
		}

		#endregion

		#region ToString override
		public override string ToString()
		{
			return "";
		}
		#endregion//ToString override
	}
}
