using System;

namespace PCSCustomWebControls
{
	/// <summary>
	/// Summary description for CandidateEventArgs.
	/// </summary>
	public class CandidateEventArgs : EventArgs
	{
		

		public Candidate OriginatingCandidate;

		public CandidateEventArgs(Candidate originator)
		{
			OriginatingCandidate = originator;
		}
	}
	public delegate void CandidateEventHandler(object sender, CandidateEventArgs e);
}
