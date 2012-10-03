using System;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.ComponentModel;

namespace PCSCustomWebControls
{
	/// <summary>
	/// Summary description for Candidate.
	/// </summary>
	public class Candidate : System.Web.UI.WebControls.WebControl, INamingContainer
	{
		public Candidate()
		{
			//
			// TODO: Add constructor logic here
			//
		}

		public string Name
		{
			get
			{
				object rawName = ViewState["_name"];
				if (rawName != null)
				{
					return (string)rawName;
				}
				else
				{
					ViewState["_name"] = "Candidate";
					return "Candidate";
				}
			}
			set
			{
				ViewState["_name"] = value;
			}
		}

		public long Votes
		{
			get
			{
				object rawVotes = ViewState["_votes"];
				if (rawVotes != null)
				{
					return (long)rawVotes;
				}
				else
				{
					ViewState["_votes"] = (long)0;
					return 0;
				}
			}
			set
			{
				ViewState["_votes"] = value;
			}
		}

		public void Increment()
		{
			Votes += 1;
		}

		public void Reset()
		{
			Votes = 0;
		}

		protected override void CreateChildControls()
		{
			Button btnVote = new Button();
			btnVote.Text = "Vote";
			btnVote.Click += new System.EventHandler(btnVote_Click);
			Controls.Add(btnVote);
			base.CreateChildControls();
		}

		protected void btnVote_Click(object sender, System.EventArgs e)
		{
			Increment();
			StrawPoll parent = (StrawPoll)Parent;
			if (parent.PollStyle == pollStyle.voteonly)
			{
				parent.PollStyle = pollStyle.valuesonly;
			}
			CandidateEventArgs eCandidate = new CandidateEventArgs(this);
			parent.ChildVote(eCandidate);
		}

	}
}
