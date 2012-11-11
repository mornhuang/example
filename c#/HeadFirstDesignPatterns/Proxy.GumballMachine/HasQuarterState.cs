using System;

namespace HeadFirstDesignPatterns.Proxy.GumballMachine
{
	/// <summary>
	/// Summary description for HasQuarterState.
	/// </summary>
	public class HasQuarterState : State
	{
		#region Members
		GumballMachine gumballMachine;
		Random randomWinner = new Random(System.DateTime.Now.Millisecond);
		//When I was using the winnerState and soldState instance variables I was
		//getting "An unhandled exception of type 'System.StackOverflowException' 
		//occurred in mscorlib.dll" error?
//		State winnerState;
//		State soldState;
		#endregion//Members

		#region Constructor
		public HasQuarterState(GumballMachine gumballMachine)
		{
			this.gumballMachine = gumballMachine;
//			winnerState = new WinnerState(gumballMachine);
//			soldState = new SoldState(gumballMachine);
		}
		#endregion//Constructor

		#region State Members

		public string InsertQuarter()
		{
			return "You can't insert another quarter\n";
		}

		public string EjectQuarter()
		{
			return "Quarter returned\n";
		}

		public string TurnCrank()
		{
			string outPut;
			outPut = "You turned...\n";
			
			int winner = randomWinner.Next(10);
			if(winner == 0 && gumballMachine.Count > 1)
			{
				gumballMachine.StateOfMachine = new WinnerState(gumballMachine);
//				gumballMachine.StateOfMachine = winnerState;
			}
			else
			{
				gumballMachine.StateOfMachine = new SoldState(gumballMachine);
//				gumballMachine.StateOfMachine = soldState;
			}

			return outPut;
		}

		public string Dispense()
		{
			string outPut;

			outPut = gumballMachine.ReleaseBall();
			outPut += "No gumball dispensed\n";

			return outPut;
		}

		#endregion

		#region ToString override
		public override string ToString()
		{
			return "waiting for turn of crank";
		}
		#endregion//ToString override
	}
}
