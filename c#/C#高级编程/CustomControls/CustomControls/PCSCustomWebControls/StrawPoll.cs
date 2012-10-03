using System;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.ComponentModel;

namespace PCSCustomWebControls
{
	public enum pollStyle
	{
		voteonly,
		valuesonly,
		voteandvalues
	}

	[ ControlBuilderAttribute(typeof(StrawPollControlBuilder)) ]
	[ ParseChildren(false) ]
	public class StrawPoll : System.Web.UI.WebControls.WebControl, INamingContainer
	{
		public event CandidateEventHandler Voted;

		protected void OnVoted(CandidateEventArgs e)
		{
			Voted(this, e);
		}

		public string Title
		{
			get
			{
				object rawTitle = ViewState["_title"];
				if (rawTitle != null)
				{
					return (string)rawTitle;
				}
				else
				{
					ViewState["_title"] = "Straw Poll";
					return "Straw Poll";
				}
			}
			set
			{
				ViewState["_title"] = value;
			}
		}

		public pollStyle PollStyle
		{
			get
			{
				object rawPollStyle = ViewState["_pollStyle"];
				if (rawPollStyle != null)
				{
					return (pollStyle)rawPollStyle;
				}
				else
				{
					ViewState["_pollStyle"] = pollStyle.voteandvalues;
					return pollStyle.voteandvalues;
				}
			}
			set
			{
				ViewState["_pollStyle"] = value;
			}
		}

		protected override void Render(HtmlTextWriter output)
		{
			Candidate currentCandidate;
			long iTotalVotes = 0;
			long iPercentage = 0;
			int iColumns = 2;

			// Start table, display title
			if (PollStyle == pollStyle.voteandvalues)
			{
				iColumns = 3;
			}
			output.Write("<TABLE border='1' bordercolor='black' bgcolor='#DDDDBB'"
				+ " width='90%' cellpadding='1' cellspacing='1'"
				+ " align='center'>");
			output.Write("<TR><TD colspan='" + iColumns + "' align='center'"
				+ " bgcolor='#FFFFDD'>");
			output.Write("<B>" + Title + "</B></TD></TR>");

			if (Controls.Count == 0)
			{
				// Default text when no options contained
				output.Write("<TR><TD bgcolor='#FFFFDD'>No options to"
					+ " display.</TR></TD>");
			}
			else
			{
				// Get total votes
				for (int iLoop = 0; iLoop < Controls.Count; iLoop++)
				{
					// Get option
					currentCandidate = (Candidate)Controls[iLoop];
					// Sum votes cast
					iTotalVotes += currentCandidate.Votes;
				}

				// Render each option
				for (int iLoop = 0; iLoop < Controls.Count; iLoop++)
				{
					// Get option
					currentCandidate = (Candidate)Controls[iLoop];
					// Place option name in first column
					output.Write("<TR><TD bgcolor='#FFFFDD' width='15%'> "
						+ currentCandidate.Name + " </TD>");
					// Add voting option to second column if required
					if (PollStyle != pollStyle.valuesonly)
					{
						output.Write("<TD width='1%' bgcolor='#FFFFDD'>"
							+ "<FONT color='#FFFFDD'>.</FONT>");
						currentCandidate.RenderControl(output);
						output.Write("<FONT color='#FFFFDD'>.</FONT></TD>");
					}

					// Place graph, value, and percentage in third column if required
					if (PollStyle != pollStyle.voteonly)
					{
						if (iTotalVotes > 0)
						{
							iPercentage = (currentCandidate.Votes * 100) / iTotalVotes;
						}
						else
						{
							iPercentage = 0;
						}
						output.Write("<TD bgcolor='#FFFFDD'><TABLE width='100%'>"
							+ "<TR><TD><TABLE border='1' bordercolor='black'"
							+ " width='100%' cellpadding='0'"
							+ " cellspacing='0'>");
						output.Write("<TR><TD bgcolor='red' width='" + iPercentage
							+ "%'><FONT color='red'>.</FONT></TD>");
						output.Write("<TD bgcolor='white' width='" + (100-iPercentage)
							+ "%'><FONT color='white'>."
							+ "</FONT></TD></TR></TABLE></TD>");
						output.Write("<TD width='75'>" + currentCandidate.Votes + " ("
							+ iPercentage + "%)</TD></TR></TABLE></TD>");
					}
					// End row
					output.Write("</TR>");
				}
				// Show total votes cast if values displayed
				if (PollStyle != pollStyle.voteonly)
				{
					output.Write("<TR><TD bgcolor='#FFFFDD' colspan='" + iColumns
						+ "'>Total votes cast: " + iTotalVotes
						+ "</TD></TR>");
				}
			}
			// Finish table
			output.Write("</TABLE>");
		}

		internal void ChildVote(CandidateEventArgs e)
		{
			OnVoted(e);
		}

	}
}