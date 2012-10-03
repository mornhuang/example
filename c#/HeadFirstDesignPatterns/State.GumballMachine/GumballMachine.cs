using System;
using System.Text;

namespace HeadFirstDesignPatterns.State.GumballMachine
{
	/// <summary>
	/// Summary description for GumballMachine.
	/// </summary>
	public class GumballMachine
	{
		#region Members
		State soldOutState;
		State noQuarterState;
		State hasQuarterState;
		State soldState;
		State state;		

		int count = 0;
		#endregion//Members

		#region Properties

		public State StateOfMachine
		{
			get {return state;}
			set {state = value;}
		}

		public int Count
		{
			get {return count;}
			set {count = value;}
		}

		#endregion//Properties

		#region Constructor
		public GumballMachine(int numberOfGumballs)
		{
			soldOutState = new SoldOutState(this);
			noQuarterState = new NoQuarterState(this);
			hasQuarterState = new HasQuarterState(this);
			soldState = new SoldState(this);
			this.count = numberOfGumballs; 
			if(numberOfGumballs > 0)
			{
				state = noQuarterState;
			}
			else
			{
				state = soldOutState;
			}
		}
		#endregion//Constructor

		#region InsertQuarter
		public string InsertQuarter()
		{
			return state.InsertQuarter();
		}
		#endregion//InsertQuarter

		#region EjectQuarter
		public string EjectQuarter()
		{
			return state.EjectQuarter();
		}
		#endregion//EjectQuarter
		
		#region TurnCrank
		public string TurnCrank()
		{
			return state.TurnCrank() + 
				state.Dispense();
		}
		#endregion//

		#region ReleaseBall
		public string ReleaseBall()
		{
			if(count != 0)
			{
				count -= 1;
			}

			return "A gumball comes rolling out the slot...\n";
		}
		#endregion//ReleaseBall

		#region Refill
		public string Refill(int numberOfGumballs)
		{
			this.count += numberOfGumballs;
			state = noQuarterState;

			return "\nRefill: " + numberOfGumballs + " gumballs were added. " +
				"The gumball count in now: " + count + "\n";
		}
		#endregion//Refill

		#region MachineStateHeader
		public string MachineStateHeader()
		{
			StringBuilder result = new StringBuilder();
			result.Append("Mighty Gumball, Inc.");
			result.Append("\nC# Enabled Standing Gumball Model #2005\n");
			result.Append("Inventory: " + count + " gumball");
			if (count != 1) 
			{
				result.Append("s");
			}
			result.Append("\nMachine is " + state.ToString());

			return result.ToString();
		}
		#endregion//MachineStateHeader
	}
}
