using System;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.ComponentModel;

namespace PCSCustomWebControls
{
	/// <summary>
	/// Summary description for RainbowLabel2.
	/// </summary>
	public class RainbowLabel2 : System.Web.UI.WebControls.WebControl, INamingContainer
	{
		private RainbowLabel rainbowLabel = new RainbowLabel();
		private Button cycleButton = new Button();

		public RainbowLabel2()
		{
			//
			// TODO: Add constructor logic here
			//
		}

		protected override void CreateChildControls()
		{
			cycleButton.Text = "Cycle colors.";
			cycleButton.Click += new System.EventHandler(cycleButton_Click);
			Controls.Add(cycleButton);
			Controls.Add(rainbowLabel);
			base.CreateChildControls();
		}

		protected void cycleButton_Click(object sender, System.EventArgs e)
		{
			rainbowLabel.Cycle();
		}

		public string Text
		{
			get
			{
				return rainbowLabel.Text;
			}
			set
			{
				rainbowLabel.Text = value;
			}
		}

		protected override void Render(HtmlTextWriter output)
		{
			rainbowLabel.RenderControl(output);
			cycleButton.RenderControl(output);
		}
	}
}
