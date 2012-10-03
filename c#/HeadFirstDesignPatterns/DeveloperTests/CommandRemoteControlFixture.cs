using System;
using HeadFirstDesignPatterns.Command.RemoteControl;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Command.RemoteControl
{
	/// <summary>
	/// CommandRemoteControlFixture tests the command pattern
	/// </summary>
	[TestFixture]
	public class CommandRemoteControlFixture
	{
		#region TestTurningSimpleOn
		[Test]
		public void TestTurningSimpleOn()//Command Pattern Client
		{
			//Command Pattern Invoker
			SimpleRemoteControl remote = new SimpleRemoteControl();

			//Command Pattern Receivers
			Light light = new Light("Kitchen");
			GarageDoor garageDoor = new GarageDoor("");

			//Commands for the receivers
			LightOnCommand lightOn = new LightOnCommand(light);
			GarageDoorUpCommand garageDoorOpen = 
				new GarageDoorUpCommand(garageDoor);
			
			//Passing the light on command to the invoker
			remote.SetCommand(lightOn);
			//Simulate the button being pressed on the invoker
			Assert.AreEqual("Kitchen light is on",remote.ButtonWasPressed());

			//Passing the garage door open command to the invoker
			remote.SetCommand(garageDoorOpen);
			//Simulate the button being pressed on the invoker
			Assert.AreEqual("Garage door is up",remote.ButtonWasPressed());
		}
		#endregion//TestTurningSimpleOn

		#region TestTurningOn
		[Test]
		public void TestTurningOn()//Command Pattern Client
		{
			//Command Pattern Invoker
			Remote remote = new Remote();

			Light livingRoomLight = new Light("Living Room");
			Light kitchenLight = new Light("Kitchen");
			CeilingFan ceilingFan= new CeilingFan("Living Room");
			GarageDoor garageDoor = new GarageDoor("");
			Stereo stereo = new Stereo("Living Room");
  
			LightOnCommand livingRoomLightOn = 
				new LightOnCommand(livingRoomLight);
			LightOffCommand livingRoomLightOff = 
				new LightOffCommand(livingRoomLight);
			LightOnCommand kitchenLightOn = 
				new LightOnCommand(kitchenLight);
			LightOffCommand kitchenLightOff = 
				new LightOffCommand(kitchenLight);
  
			CeilingFanOnCommand ceilingFanOn = 
				new CeilingFanOnCommand(ceilingFan);
			CeilingFanOffCommand ceilingFanOff = 
				new CeilingFanOffCommand(ceilingFan);
 
			GarageDoorUpCommand garageDoorUp =
				new GarageDoorUpCommand(garageDoor);
			GarageDoorDownCommand garageDoorDown =
				new GarageDoorDownCommand(garageDoor);
 
			StereoOnWithCDCommand stereoOnWithCD =
				new StereoOnWithCDCommand(stereo);
			StereoOffCommand  stereoOff =
				new StereoOffCommand(stereo);
 
			remote.SetCommand(0, livingRoomLightOn, livingRoomLightOff);
			remote.SetCommand(1, kitchenLightOn, kitchenLightOff);
			remote.SetCommand(2, ceilingFanOn, ceilingFanOff);
			remote.SetCommand(3, stereoOnWithCD, stereoOff);
  
			Assert.AreEqual("Living Room light is on",
				remote.OnButtonWasPushed(0));
			Assert.AreEqual("Living Room light is off",
				remote.OffButtonWasPushed(0));

			Assert.AreEqual("Kitchen light is on",
				remote.OnButtonWasPushed(1));
			Assert.AreEqual("Kitchen light is off",
				remote.OffButtonWasPushed(1));
			Assert.AreEqual("Living Room ceiling fan is on high",
				remote.OnButtonWasPushed(2));
			Assert.AreEqual("Living Room ceiling fan is off",
				remote.OffButtonWasPushed(2));
			Assert.AreEqual("Living Room stereo is on\n" +
				"Living Room stereo is set for CD input\n" +
				"Living Room Stereo volume set to 11",
				remote.OnButtonWasPushed(3));
			Assert.AreEqual("Living Room stereo is off",
				remote.OffButtonWasPushed(3));

//			Console.WriteLine(remote.toString());
		}
		#endregion
	}
}