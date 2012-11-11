using System;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.ComponentModel;
using PCSCustomWebControls;

namespace PCSCustomWebControls
{
	/// <summary>
	/// Summary description for WebCustomControl1.
	/// </summary>
	[DefaultProperty("Text"),
		ToolboxData("<{0}:WebCustomControl1 runat=server></{0}:WebCustomControl1>")]
	public class WebCustomControl1 : System.Web.UI.WebControls.WebControl
	{
		private string text;

		[Bindable(true),
			Category("Appearance"),
			DefaultValue("")]
		public string Text
		{
			get
			{
				return text;
			}

			set
			{
				text = value;
			}
		}

		/// <summary>
		/// Render this control to the output parameter specified.
		/// </summary>
		/// <param name="output"> The HTML writer to write out to </param>
		protected override void Render(HtmlTextWriter output)
		{
			output.Write(Text);
		}
	}
}
