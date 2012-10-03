using System;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.ComponentModel;
using System.Drawing;

namespace PCSCustomWebControls
{
	public class RainbowLabel : System.Web.UI.WebControls.Label
	{
		private Color[] colors = new Color[] {Color.Red, Color.Orange,
												 Color.Yellow, Color.GreenYellow,
												 Color.Blue, Color.Indigo,
												 Color.Violet};

		private int offset
		{
			get
			{
				object rawOffset = ViewState["_offset"];
				if (rawOffset != null)
				{
					return (int)rawOffset;
				}
				else
				{
					ViewState["_offset"] = 0;
					return 0;
				}
			}
			set
			{
				ViewState["_offset"] = value;
			}
		}


		protected override void Render(HtmlTextWriter output)
		{
			string text = Text;

			for (int pos=0; pos < text.Length; pos++)
			{
				int rgb = colors[(pos + offset) % colors.Length].ToArgb() & 0xFFFFFF;
				output.Write("<font color='#" + rgb.ToString("X6") + "'>"
					+ text[pos] + "</font>");
			}
		}

		public void Cycle()
		{
			offset = ++offset;
		}
	}
}