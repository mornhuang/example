using System;
using System.Text;
using HeadFirstDesignPatterns.State.GumballMachine;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.State.GumballMachine
{
	/// <summary>
	/// StateGumballMachineFixture tests the state pattern
	/// </summary>
	[TestFixture]
	public class StateGumballMachineFixture
	{
		#region TestGumballMachineStart
		[Test]
		public void TestGumballMachineStart()
		{
			StringBuilder gumballMachineOutput = new StringBuilder();
			StringBuilder stringToMatch = new StringBuilder();
			GumballMachineStart gumballMachine = new GumballMachineStart(5);
			
			stringToMatch.Append("\nMighty Gumball, Inc.\n");
			stringToMatch.Append("C# Enabled Standing Gumball Model #2005\n");
			stringToMatch.Append("Inventory: 5 gumballs\n");
			stringToMatch.Append("Machine is waiting for quarter\n");
			stringToMatch.Append("You inserted a quarter\n");
			stringToMatch.Append("You turned...\n");
			stringToMatch.Append("A gumball comes rolling out the slot\n\n");

			stringToMatch.Append("Mighty Gumball, Inc.\n");
			stringToMatch.Append("C# Enabled Standing Gumball Model #2005\n");
			stringToMatch.Append("Inventory: 4 gumballs\n");
			stringToMatch.Append("Machine is waiting for quarter\n");
			stringToMatch.Append("You inserted a quarter\n");
			stringToMatch.Append("Quarter returned\n");
			stringToMatch.Append("You turned but there's no quarter\n\n");

			stringToMatch.Append("Mighty Gumball, Inc.\n");
			stringToMatch.Append("C# Enabled Standing Gumball Model #2005\n");
			stringToMatch.Append("Inventory: 4 gumballs\n");
			stringToMatch.Append("Machine is waiting for quarter\n");
			stringToMatch.Append("You inserted a quarter\n");
			stringToMatch.Append("You turned...\n");
			stringToMatch.Append("A gumball comes rolling out the slot\n");
			stringToMatch.Append("You inserted a quarter\n");
			stringToMatch.Append("You turned...\n");
			stringToMatch.Append("A gumball comes rolling out the slot\n");
			stringToMatch.Append("You haven't inserted a quarter\n\n");

			stringToMatch.Append("Mighty Gumball, Inc.\n");
			stringToMatch.Append("C# Enabled Standing Gumball Model #2005\n");
			stringToMatch.Append("Inventory: 2 gumballs\n");
			stringToMatch.Append("Machine is waiting for quarter\n");
			stringToMatch.Append("You inserted a quarter\n");
			stringToMatch.Append("You can't insert another quarter\n");
			stringToMatch.Append("You turned...\n");
			stringToMatch.Append("A gumball comes rolling out the slot\n");
			stringToMatch.Append("You inserted a quarter\n");
			stringToMatch.Append("You turned...\n");
			stringToMatch.Append("Now out of gumballs!\n");
			stringToMatch.Append("You can't insert a quarter, the machine is sold out\n");
			stringToMatch.Append("You turned, but there are no gumballs\n\n");

			stringToMatch.Append("Mighty Gumball, Inc.\n");
			stringToMatch.Append("C# Enabled Standing Gumball Model #2005\n");
			stringToMatch.Append("Inventory: 0 gumballs\n");
			stringToMatch.Append("Machine is sold out\n");
			 
			gumballMachineOutput.Append(gumballMachine.MachineState() + "\n");
			gumballMachineOutput.Append(gumballMachine.InsertQuarter() + "\n");
			gumballMachineOutput.Append(gumballMachine.TurnCrank() + "\n");

			gumballMachineOutput.Append(gumballMachine.MachineState() + "\n");
			gumballMachineOutput.Append(gumballMachine.InsertQuarter() + "\n");
			gumballMachineOutput.Append(gumballMachine.EjectQuarter() + "\n");
			gumballMachineOutput.Append(gumballMachine.TurnCrank() + "\n");

			gumballMachineOutput.Append(gumballMachine.MachineState() + "\n");
			gumballMachineOutput.Append(gumballMachine.InsertQuarter() + "\n");
			gumballMachineOutput.Append(gumballMachine.TurnCrank() + "\n");
			gumballMachineOutput.Append(gumballMachine.InsertQuarter() + "\n");
			gumballMachineOutput.Append(gumballMachine.TurnCrank() + "\n");
			gumballMachineOutput.Append(gumballMachine.EjectQuarter() + "\n");

			gumballMachineOutput.Append(gumballMachine.MachineState() + "\n");
			gumballMachineOutput.Append(gumballMachine.InsertQuarter() + "\n");
			gumballMachineOutput.Append(gumballMachine.InsertQuarter() + "\n");
			gumballMachineOutput.Append(gumballMachine.TurnCrank() + "\n");
			gumballMachineOutput.Append(gumballMachine.InsertQuarter() + "\n");
			gumballMachineOutput.Append(gumballMachine.TurnCrank() + "\n");
			gumballMachineOutput.Append(gumballMachine.InsertQuarter() + "\n");
			gumballMachineOutput.Append(gumballMachine.TurnCrank() + "\n");

			gumballMachineOutput.Append(gumballMachine.MachineState() + "\n");

			Assert.AreEqual(stringToMatch.ToString(),gumballMachineOutput.ToString());
		}
		#endregion//TestGumballMachineStart

		#region TestGumballMachine
		[Test]
		public void TestGumballMachine()
		{
			HeadFirstDesignPatterns.State.GumballMachine.GumballMachine gumballMachine = new HeadFirstDesignPatterns.State.GumballMachine.GumballMachine(5);
			StringBuilder gumballMachineOutput = new StringBuilder();

			gumballMachineOutput.Append(gumballMachine.MachineStateHeader() + "\n");

			gumballMachineOutput.Append(gumballMachine.InsertQuarter());
			gumballMachineOutput.Append(gumballMachine.TurnCrank() + "\n");

			gumballMachineOutput.Append(gumballMachine.MachineStateHeader() + "\n");

			gumballMachineOutput.Append(gumballMachine.InsertQuarter());
			gumballMachineOutput.Append(gumballMachine.TurnCrank());
			gumballMachineOutput.Append(gumballMachine.InsertQuarter());
			gumballMachineOutput.Append(gumballMachine.TurnCrank() + "\n");

			gumballMachineOutput.Append(gumballMachine.MachineStateHeader() + "\n");

			gumballMachineOutput.Append(gumballMachine.Refill(5) + "\n");
			
			gumballMachineOutput.Append(gumballMachine.MachineStateHeader() + "\n");


			
			//Because of the System.Random object, there is no way to know
			//the outcome of the turn of the crank. Therefore, I am writing
			//to the console. Use the Console.Out tab of NUnit
			Console.WriteLine(gumballMachineOutput.ToString());
		}
		#endregion//TestGumballMachine
	}
}
