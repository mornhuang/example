using System;
using System.Text;
using HeadFirstDesignPatterns.Facade.HomeTheater;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Facade.HomeTheater
{
	/// <summary>
	/// FacadeHomeTheaterFixture tests the facade pattern
	/// </summary>
	[TestFixture]
	public class FacadeHomeTheaterFixture
	{
		#region Members
		Amplifier amp;
		Tuner tuner;
		DvdPlayer dvd;
		CdPlayer cd;
		Projector projector;
		TheaterLights lights;
		Screen screen;
		PopcornPopper popper;
		HomeTheaterFacade homeTheater;
		StringBuilder expectedWatchMovieOutput;
		StringBuilder expectedEndMovieOutput;
		StringBuilder expectedListenToCDOutput;
		StringBuilder expectedEndCDOutput;
		StringBuilder expectedListenToRadioOutput;
		StringBuilder expectedEndRadioOutput;
		#endregion//Members

		#region Setup
		[SetUp]
		public void Init()
		{ 
			amp = new Amplifier("Top-O-Line Amplifier");
			tuner = new Tuner("Top-O-Line Tuner",amp);
			dvd = new DvdPlayer("Top-O-Line DVD Player",amp);
			cd = new CdPlayer("Top-O-Line CD Player",amp);
			projector = new Projector("Top-O-Line Projector",dvd);
			lights = new TheaterLights("Theater Ceiling Lights");
			screen = new Screen("Theater Screen");
			popper = new PopcornPopper("Popcorn Popper");
			expectedWatchMovieOutput = new StringBuilder();
			expectedEndMovieOutput = new StringBuilder();
			expectedListenToCDOutput = new StringBuilder();
			expectedEndCDOutput = new StringBuilder();
			expectedListenToRadioOutput = new StringBuilder();
			expectedEndRadioOutput = new StringBuilder();
			homeTheater = new HomeTheaterFacade(amp,tuner,
				dvd,cd,projector,screen,lights,popper);
		}
		#endregion//Setup

		#region TearDown Dispose()
		[TearDown]
		public void Dispose()
		{
			amp = null;
			tuner = null;
			dvd = null;
			cd = null;
			projector = null;
			lights = null;
			screen = null;
			popper = null;
			expectedWatchMovieOutput =  null;
			expectedEndMovieOutput = null;
			expectedListenToCDOutput = null;
			expectedEndCDOutput = null;
			expectedListenToRadioOutput = null;
			expectedEndRadioOutput = null;
			homeTheater = null;
		}
		#endregion//TearDown Dispose()

		#region TestHomeTheaterMovie
		[Test]
		public void TestHomeTheaterMovie()
		{
			expectedWatchMovieOutput.Append("Get ready to watch a movie...\n");
			expectedWatchMovieOutput.Append("Popcorn Popper on\n");
			expectedWatchMovieOutput.Append("Popcorn Popper popping popcorn!\n");
			expectedWatchMovieOutput.Append("Theater Ceiling Lights dimming to 10%\n");
			expectedWatchMovieOutput.Append("Theater Screen going down\n");
			expectedWatchMovieOutput.Append("Top-O-Line Projector on\n");
			expectedWatchMovieOutput.Append("Top-O-Line Projector in widescreen mode (16x9 aspect ratio)\n");
			expectedWatchMovieOutput.Append("Top-O-Line Amplifier on\n");
			expectedWatchMovieOutput.Append("Top-O-Line Amplifier setting DVD player to Top-O-Line DVD Player\n");
			expectedWatchMovieOutput.Append("Top-O-Line Amplifier surround sound on (5 speakers, 1 subwoofer)\n");
			expectedWatchMovieOutput.Append("Top-O-Line Amplifier setting volume to 5\n");
			expectedWatchMovieOutput.Append("Top-O-Line DVD Player on\n");
			expectedWatchMovieOutput.Append("Top-O-Line DVD Player playing \"Toy Story\"\n");

			expectedEndMovieOutput.Append("Shutting movie theater down...\n");
			expectedEndMovieOutput.Append("Popcorn Popper off\n");
			expectedEndMovieOutput.Append("Theater Ceiling Lights on\n");
			expectedEndMovieOutput.Append("Theater Screen going up\n");
			expectedEndMovieOutput.Append("Top-O-Line Projector off\n");
			expectedEndMovieOutput.Append("Top-O-Line Amplifier off\n");
			expectedEndMovieOutput.Append("Top-O-Line DVD Player stopped \"Toy Story\"\n");
			expectedEndMovieOutput.Append("Top-O-Line DVD Player eject\n");
			expectedEndMovieOutput.Append("Top-O-Line DVD Player off\n");

			Assert.AreEqual(expectedWatchMovieOutput.ToString(),
				homeTheater.WatchMovie("Toy Story"));
			Assert.AreEqual(expectedEndMovieOutput.ToString(),
				homeTheater.EndMovie());
		}
		#endregion//TestHomeTheaterMovie

		#region TestHomeTheaterCD
		[Test]
		public void TestHomeTheaterCD()
		{
			expectedListenToCDOutput.Append("Get ready for an audio experence...\n");
			expectedListenToCDOutput.Append("Theater Ceiling Lights on\n");
			expectedListenToCDOutput.Append("Top-O-Line Amplifier on\n");
			expectedListenToCDOutput.Append("Top-O-Line Amplifier setting volume to 5\n");
			expectedListenToCDOutput.Append("Top-O-Line Amplifier setting CD player to Top-O-Line CD Player\n");
			expectedListenToCDOutput.Append("Top-O-Line Amplifier stereo mode on\n");
			expectedListenToCDOutput.Append("Top-O-Line CD Player on\n");
			expectedListenToCDOutput.Append("Top-O-Line CD Player playing \"Dark Side of the Moon\"\n");

			expectedEndCDOutput.Append("Shutting down CD...\n");
			expectedEndCDOutput.Append("Top-O-Line Amplifier off\n");
			expectedEndCDOutput.Append("Top-O-Line Amplifier setting CD player to Top-O-Line CD Player\n");
			expectedEndCDOutput.Append("Top-O-Line CD Player eject\n");
			expectedEndCDOutput.Append("Top-O-Line CD Player off\n");

			Assert.AreEqual(expectedListenToCDOutput.ToString(),
				homeTheater.ListenToCd("Dark Side of the Moon"));
			Assert.AreEqual(expectedEndCDOutput.ToString(),
				homeTheater.EndCd());
		}
		#endregion//TestHomeTheaterCD

		#region TestHomeTheaterRadio
		[Test]
		public void TestHomeTheaterRadio()
		{
			expectedListenToRadioOutput.Append("Tuning in the airwaves...\n");
			expectedListenToRadioOutput.Append("Top-O-Line Tuner on\n");
			expectedListenToRadioOutput.Append("Top-O-Line Tuner setting frequency to 92.5\n");
			expectedListenToRadioOutput.Append("Top-O-Line Amplifier on\n");
			expectedListenToRadioOutput.Append("Top-O-Line Amplifier setting volume to 5\n");
			expectedListenToRadioOutput.Append("Top-O-Line Amplifier setting tuner to Top-O-Line Tuner\n");

			expectedEndRadioOutput.Append("Shutting down the tuner...\n");
			expectedEndRadioOutput.Append("Top-O-Line Tuner off\n");
			expectedEndRadioOutput.Append("Top-O-Line Amplifier off\n");


			Assert.AreEqual(expectedListenToRadioOutput.ToString(),
				homeTheater.ListenToRadio(92.5));
			Assert.AreEqual(expectedEndRadioOutput.ToString(),
				homeTheater.EndRadio());
		}
		#endregion//TestHomeTheaterRadio
	}
}
