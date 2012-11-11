namespace PCSUserCWebApp1
{
	using System;
	using System.Data;
	using System.Drawing;
	using System.Web;
	using System.Web.UI.WebControls;
	using System.Web.UI.HtmlControls;

	/// <summary>
	///		Summary description for PCSUserC1.
	/// </summary>
	 
	public enum suit
	{
		club, diamond, heart, spade
	}

	public abstract class PCSUserC1 : System.Web.UI.UserControl
	{
		protected System.Web.UI.WebControls.Image suitPic;
		protected System.Web.UI.WebControls.Label suitLabel;
		protected suit currentSuit;

		private void Page_Load(object sender, System.EventArgs e)
		{
			// Put user code to initialize the page here
		}

		#region Web Form Designer generated code
		override protected void OnInit(EventArgs e)
		{
			//
			// CODEGEN: This call is required by the ASP.NET Web Form Designer.
			//
			InitializeComponent();
			base.OnInit(e);
		}
		
		///		Required method for Designer support - do not modify
		///		the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.Load += new System.EventHandler(this.Page_Load);

		}
		#endregion

		public suit Suit
		{
			get
			{
				return currentSuit;
			}
			set
			{
				currentSuit = value;
				suitPic.ImageUrl = currentSuit.ToString() + ".bmp";
				suitLabel.Text = currentSuit.ToString();
			}
		}

		public void Club()
		{
			Suit = suit.club;
		}

		public void Diamond()
		{
			Suit = suit.diamond;
		}

		public void Heart()
		{
			Suit = suit.heart;
		}

		public void Spade()
		{
			Suit = suit.spade;
		}
	}
}
