using System;
using HeadFirstDesignPatterns.Strategy.Football;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.Strategy.Football
{
	/// <summary>
	/// Summary description for StrategyFootballFixture.
	/// </summary>
	[TestFixture]
	public class StrategyFootballFixture
	{
		#region TestPullingGuard
		[Test]
		public void TestPullingGuard()
		{
			Guard pullingGuard = new Guard();
			Assert.AreEqual("I am pulling to block",pullingGuard.Pattern());
		}
		#endregion//TestPullingGuard

		#region TestWideReceiver
		[Test]
		public void TestWideReceiver()
		{
			WideReceiver widereceiverPassPattern = new WideReceiver();
			Assert.AreEqual("I am running a banana pass pattern", widereceiverPassPattern.Pattern());
		}
		#endregion//TestWideReceiver

		#region TestRunningBack
		[Test]
		public void TestRunningBack()
		{
			RunningBack runneringBackPattern = new RunningBack();
			Assert.AreEqual("I am blocking any rushers that the line does not get", runneringBackPattern.Pattern());
		}
		#endregion//TestRunningBack
	}
}
