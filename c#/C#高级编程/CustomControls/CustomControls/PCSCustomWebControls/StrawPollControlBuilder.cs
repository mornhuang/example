using System;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.ComponentModel;
using System.Collections;

namespace PCSCustomWebControls
{
	/// <summary>
	/// Summary description for StrawPollControlBuilder.
	/// </summary>
	internal class StrawPollControlBuilder : ControlBuilder
	{
		public override Type GetChildControlType(string tagName, IDictionary attribs)
		{
			if (tagName.ToLower().EndsWith("candidate"))
				return typeof(Candidate);
			return null;
		}

		public override void AppendLiteralString(string s)
		{
			// Do nothing, to avoid embedded text being added to control
		}
	}
}
