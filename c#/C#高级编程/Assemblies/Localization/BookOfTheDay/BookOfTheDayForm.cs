using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.Globalization;
using System.Threading;
using System.Resources;
using System.Reflection;

namespace Wrox.ProCSharp
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class BookOfTheDayForm : System.Windows.Forms.Form
	{
		private System.Windows.Forms.TextBox textBoxItemsSold;
		private System.Windows.Forms.Label labelBooksSold;
		private System.Windows.Forms.TextBox textBoxTitle;
		private System.Windows.Forms.TextBox textBoxDate;
		private System.Windows.Forms.Label labelBookOfTheDay;
		private System.Windows.Forms.PictureBox pictureBoxLogo;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public BookOfTheDayForm(string culture)
		{
			if (culture != "")
			{
				CultureInfo cultureInfo = new CultureInfo(culture);

				// set culture for formatting

				Thread.CurrentThread.CurrentCulture = cultureInfo;

				// set culture for resources
				Thread.CurrentThread.CurrentUICulture = cultureInfo;
			}

			WelcomeMessage();

			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();

			SetDateAndNumber();
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if (components != null) 
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			System.Resources.ResourceManager resources = new System.Resources.ResourceManager(typeof(BookOfTheDayForm));
			this.textBoxItemsSold = new System.Windows.Forms.TextBox();
			this.labelBooksSold = new System.Windows.Forms.Label();
			this.textBoxTitle = new System.Windows.Forms.TextBox();
			this.textBoxDate = new System.Windows.Forms.TextBox();
			this.labelBookOfTheDay = new System.Windows.Forms.Label();
			this.pictureBoxLogo = new System.Windows.Forms.PictureBox();
			this.SuspendLayout();
			// 
			// textBoxItemsSold
			// 
			this.textBoxItemsSold.AccessibleDescription = ((string)(resources.GetObject("textBoxItemsSold.AccessibleDescription")));
			this.textBoxItemsSold.AccessibleName = ((string)(resources.GetObject("textBoxItemsSold.AccessibleName")));
			this.textBoxItemsSold.Anchor = ((System.Windows.Forms.AnchorStyles)(resources.GetObject("textBoxItemsSold.Anchor")));
			this.textBoxItemsSold.AutoSize = ((bool)(resources.GetObject("textBoxItemsSold.AutoSize")));
			this.textBoxItemsSold.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("textBoxItemsSold.BackgroundImage")));
			this.textBoxItemsSold.Cursor = System.Windows.Forms.Cursors.Default;
			this.textBoxItemsSold.Dock = ((System.Windows.Forms.DockStyle)(resources.GetObject("textBoxItemsSold.Dock")));
			this.textBoxItemsSold.Enabled = ((bool)(resources.GetObject("textBoxItemsSold.Enabled")));
			this.textBoxItemsSold.Font = ((System.Drawing.Font)(resources.GetObject("textBoxItemsSold.Font")));
			this.textBoxItemsSold.ImeMode = ((System.Windows.Forms.ImeMode)(resources.GetObject("textBoxItemsSold.ImeMode")));
			this.textBoxItemsSold.Location = ((System.Drawing.Point)(resources.GetObject("textBoxItemsSold.Location")));
			this.textBoxItemsSold.MaxLength = ((int)(resources.GetObject("textBoxItemsSold.MaxLength")));
			this.textBoxItemsSold.Multiline = ((bool)(resources.GetObject("textBoxItemsSold.Multiline")));
			this.textBoxItemsSold.Name = "textBoxItemsSold";
			this.textBoxItemsSold.PasswordChar = ((char)(resources.GetObject("textBoxItemsSold.PasswordChar")));
			this.textBoxItemsSold.RightToLeft = ((System.Windows.Forms.RightToLeft)(resources.GetObject("textBoxItemsSold.RightToLeft")));
			this.textBoxItemsSold.ScrollBars = ((System.Windows.Forms.ScrollBars)(resources.GetObject("textBoxItemsSold.ScrollBars")));
			this.textBoxItemsSold.Size = ((System.Drawing.Size)(resources.GetObject("textBoxItemsSold.Size")));
			this.textBoxItemsSold.TabIndex = ((int)(resources.GetObject("textBoxItemsSold.TabIndex")));
			this.textBoxItemsSold.Text = resources.GetString("textBoxItemsSold.Text");
			this.textBoxItemsSold.TextAlign = ((System.Windows.Forms.HorizontalAlignment)(resources.GetObject("textBoxItemsSold.TextAlign")));
			this.textBoxItemsSold.Visible = ((bool)(resources.GetObject("textBoxItemsSold.Visible")));
			this.textBoxItemsSold.WordWrap = ((bool)(resources.GetObject("textBoxItemsSold.WordWrap")));
			// 
			// labelBooksSold
			// 
			this.labelBooksSold.AccessibleDescription = ((string)(resources.GetObject("labelBooksSold.AccessibleDescription")));
			this.labelBooksSold.AccessibleName = ((string)(resources.GetObject("labelBooksSold.AccessibleName")));
			this.labelBooksSold.Anchor = ((System.Windows.Forms.AnchorStyles)(resources.GetObject("labelBooksSold.Anchor")));
			this.labelBooksSold.AutoSize = ((bool)(resources.GetObject("labelBooksSold.AutoSize")));
			this.labelBooksSold.Dock = ((System.Windows.Forms.DockStyle)(resources.GetObject("labelBooksSold.Dock")));
			this.labelBooksSold.Enabled = ((bool)(resources.GetObject("labelBooksSold.Enabled")));
			this.labelBooksSold.Font = ((System.Drawing.Font)(resources.GetObject("labelBooksSold.Font")));
			this.labelBooksSold.Image = ((System.Drawing.Image)(resources.GetObject("labelBooksSold.Image")));
			this.labelBooksSold.ImageAlign = ((System.Drawing.ContentAlignment)(resources.GetObject("labelBooksSold.ImageAlign")));
			this.labelBooksSold.ImageIndex = ((int)(resources.GetObject("labelBooksSold.ImageIndex")));
			this.labelBooksSold.ImeMode = ((System.Windows.Forms.ImeMode)(resources.GetObject("labelBooksSold.ImeMode")));
			this.labelBooksSold.Location = ((System.Drawing.Point)(resources.GetObject("labelBooksSold.Location")));
			this.labelBooksSold.Name = "labelBooksSold";
			this.labelBooksSold.RightToLeft = ((System.Windows.Forms.RightToLeft)(resources.GetObject("labelBooksSold.RightToLeft")));
			this.labelBooksSold.Size = ((System.Drawing.Size)(resources.GetObject("labelBooksSold.Size")));
			this.labelBooksSold.TabIndex = ((int)(resources.GetObject("labelBooksSold.TabIndex")));
			this.labelBooksSold.Text = resources.GetString("labelBooksSold.Text");
			this.labelBooksSold.TextAlign = ((System.Drawing.ContentAlignment)(resources.GetObject("labelBooksSold.TextAlign")));
			this.labelBooksSold.Visible = ((bool)(resources.GetObject("labelBooksSold.Visible")));
			// 
			// textBoxTitle
			// 
			this.textBoxTitle.AccessibleDescription = ((string)(resources.GetObject("textBoxTitle.AccessibleDescription")));
			this.textBoxTitle.AccessibleName = ((string)(resources.GetObject("textBoxTitle.AccessibleName")));
			this.textBoxTitle.Anchor = ((System.Windows.Forms.AnchorStyles)(resources.GetObject("textBoxTitle.Anchor")));
			this.textBoxTitle.AutoSize = ((bool)(resources.GetObject("textBoxTitle.AutoSize")));
			this.textBoxTitle.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("textBoxTitle.BackgroundImage")));
			this.textBoxTitle.Cursor = System.Windows.Forms.Cursors.Default;
			this.textBoxTitle.Dock = ((System.Windows.Forms.DockStyle)(resources.GetObject("textBoxTitle.Dock")));
			this.textBoxTitle.Enabled = ((bool)(resources.GetObject("textBoxTitle.Enabled")));
			this.textBoxTitle.Font = ((System.Drawing.Font)(resources.GetObject("textBoxTitle.Font")));
			this.textBoxTitle.ImeMode = ((System.Windows.Forms.ImeMode)(resources.GetObject("textBoxTitle.ImeMode")));
			this.textBoxTitle.Location = ((System.Drawing.Point)(resources.GetObject("textBoxTitle.Location")));
			this.textBoxTitle.MaxLength = ((int)(resources.GetObject("textBoxTitle.MaxLength")));
			this.textBoxTitle.Multiline = ((bool)(resources.GetObject("textBoxTitle.Multiline")));
			this.textBoxTitle.Name = "textBoxTitle";
			this.textBoxTitle.PasswordChar = ((char)(resources.GetObject("textBoxTitle.PasswordChar")));
			this.textBoxTitle.RightToLeft = ((System.Windows.Forms.RightToLeft)(resources.GetObject("textBoxTitle.RightToLeft")));
			this.textBoxTitle.ScrollBars = ((System.Windows.Forms.ScrollBars)(resources.GetObject("textBoxTitle.ScrollBars")));
			this.textBoxTitle.Size = ((System.Drawing.Size)(resources.GetObject("textBoxTitle.Size")));
			this.textBoxTitle.TabIndex = ((int)(resources.GetObject("textBoxTitle.TabIndex")));
			this.textBoxTitle.Text = resources.GetString("textBoxTitle.Text");
			this.textBoxTitle.TextAlign = ((System.Windows.Forms.HorizontalAlignment)(resources.GetObject("textBoxTitle.TextAlign")));
			this.textBoxTitle.Visible = ((bool)(resources.GetObject("textBoxTitle.Visible")));
			this.textBoxTitle.WordWrap = ((bool)(resources.GetObject("textBoxTitle.WordWrap")));
			// 
			// textBoxDate
			// 
			this.textBoxDate.AccessibleDescription = ((string)(resources.GetObject("textBoxDate.AccessibleDescription")));
			this.textBoxDate.AccessibleName = ((string)(resources.GetObject("textBoxDate.AccessibleName")));
			this.textBoxDate.Anchor = ((System.Windows.Forms.AnchorStyles)(resources.GetObject("textBoxDate.Anchor")));
			this.textBoxDate.AutoSize = ((bool)(resources.GetObject("textBoxDate.AutoSize")));
			this.textBoxDate.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("textBoxDate.BackgroundImage")));
			this.textBoxDate.Cursor = System.Windows.Forms.Cursors.Default;
			this.textBoxDate.Dock = ((System.Windows.Forms.DockStyle)(resources.GetObject("textBoxDate.Dock")));
			this.textBoxDate.Enabled = ((bool)(resources.GetObject("textBoxDate.Enabled")));
			this.textBoxDate.Font = ((System.Drawing.Font)(resources.GetObject("textBoxDate.Font")));
			this.textBoxDate.ImeMode = ((System.Windows.Forms.ImeMode)(resources.GetObject("textBoxDate.ImeMode")));
			this.textBoxDate.Location = ((System.Drawing.Point)(resources.GetObject("textBoxDate.Location")));
			this.textBoxDate.MaxLength = ((int)(resources.GetObject("textBoxDate.MaxLength")));
			this.textBoxDate.Multiline = ((bool)(resources.GetObject("textBoxDate.Multiline")));
			this.textBoxDate.Name = "textBoxDate";
			this.textBoxDate.PasswordChar = ((char)(resources.GetObject("textBoxDate.PasswordChar")));
			this.textBoxDate.RightToLeft = ((System.Windows.Forms.RightToLeft)(resources.GetObject("textBoxDate.RightToLeft")));
			this.textBoxDate.ScrollBars = ((System.Windows.Forms.ScrollBars)(resources.GetObject("textBoxDate.ScrollBars")));
			this.textBoxDate.Size = ((System.Drawing.Size)(resources.GetObject("textBoxDate.Size")));
			this.textBoxDate.TabIndex = ((int)(resources.GetObject("textBoxDate.TabIndex")));
			this.textBoxDate.Text = resources.GetString("textBoxDate.Text");
			this.textBoxDate.TextAlign = ((System.Windows.Forms.HorizontalAlignment)(resources.GetObject("textBoxDate.TextAlign")));
			this.textBoxDate.Visible = ((bool)(resources.GetObject("textBoxDate.Visible")));
			this.textBoxDate.WordWrap = ((bool)(resources.GetObject("textBoxDate.WordWrap")));
			// 
			// labelBookOfTheDay
			// 
			this.labelBookOfTheDay.AccessibleDescription = ((string)(resources.GetObject("labelBookOfTheDay.AccessibleDescription")));
			this.labelBookOfTheDay.AccessibleName = ((string)(resources.GetObject("labelBookOfTheDay.AccessibleName")));
			this.labelBookOfTheDay.Anchor = ((System.Windows.Forms.AnchorStyles)(resources.GetObject("labelBookOfTheDay.Anchor")));
			this.labelBookOfTheDay.AutoSize = ((bool)(resources.GetObject("labelBookOfTheDay.AutoSize")));
			this.labelBookOfTheDay.Dock = ((System.Windows.Forms.DockStyle)(resources.GetObject("labelBookOfTheDay.Dock")));
			this.labelBookOfTheDay.Enabled = ((bool)(resources.GetObject("labelBookOfTheDay.Enabled")));
			this.labelBookOfTheDay.Font = ((System.Drawing.Font)(resources.GetObject("labelBookOfTheDay.Font")));
			this.labelBookOfTheDay.Image = ((System.Drawing.Image)(resources.GetObject("labelBookOfTheDay.Image")));
			this.labelBookOfTheDay.ImageAlign = ((System.Drawing.ContentAlignment)(resources.GetObject("labelBookOfTheDay.ImageAlign")));
			this.labelBookOfTheDay.ImageIndex = ((int)(resources.GetObject("labelBookOfTheDay.ImageIndex")));
			this.labelBookOfTheDay.ImeMode = ((System.Windows.Forms.ImeMode)(resources.GetObject("labelBookOfTheDay.ImeMode")));
			this.labelBookOfTheDay.Location = ((System.Drawing.Point)(resources.GetObject("labelBookOfTheDay.Location")));
			this.labelBookOfTheDay.Name = "labelBookOfTheDay";
			this.labelBookOfTheDay.RightToLeft = ((System.Windows.Forms.RightToLeft)(resources.GetObject("labelBookOfTheDay.RightToLeft")));
			this.labelBookOfTheDay.Size = ((System.Drawing.Size)(resources.GetObject("labelBookOfTheDay.Size")));
			this.labelBookOfTheDay.TabIndex = ((int)(resources.GetObject("labelBookOfTheDay.TabIndex")));
			this.labelBookOfTheDay.Text = resources.GetString("labelBookOfTheDay.Text");
			this.labelBookOfTheDay.TextAlign = ((System.Drawing.ContentAlignment)(resources.GetObject("labelBookOfTheDay.TextAlign")));
			this.labelBookOfTheDay.Visible = ((bool)(resources.GetObject("labelBookOfTheDay.Visible")));
			// 
			// pictureBoxLogo
			// 
			this.pictureBoxLogo.AccessibleDescription = ((string)(resources.GetObject("pictureBoxLogo.AccessibleDescription")));
			this.pictureBoxLogo.AccessibleName = ((string)(resources.GetObject("pictureBoxLogo.AccessibleName")));
			this.pictureBoxLogo.Anchor = ((System.Windows.Forms.AnchorStyles)(resources.GetObject("pictureBoxLogo.Anchor")));
			this.pictureBoxLogo.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("pictureBoxLogo.BackgroundImage")));
			this.pictureBoxLogo.Dock = ((System.Windows.Forms.DockStyle)(resources.GetObject("pictureBoxLogo.Dock")));
			this.pictureBoxLogo.Enabled = ((bool)(resources.GetObject("pictureBoxLogo.Enabled")));
			this.pictureBoxLogo.Font = ((System.Drawing.Font)(resources.GetObject("pictureBoxLogo.Font")));
			this.pictureBoxLogo.Image = ((System.Drawing.Bitmap)(resources.GetObject("pictureBoxLogo.Image")));
			this.pictureBoxLogo.ImeMode = ((System.Windows.Forms.ImeMode)(resources.GetObject("pictureBoxLogo.ImeMode")));
			this.pictureBoxLogo.Location = ((System.Drawing.Point)(resources.GetObject("pictureBoxLogo.Location")));
			this.pictureBoxLogo.Name = "pictureBoxLogo";
			this.pictureBoxLogo.RightToLeft = ((System.Windows.Forms.RightToLeft)(resources.GetObject("pictureBoxLogo.RightToLeft")));
			this.pictureBoxLogo.Size = ((System.Drawing.Size)(resources.GetObject("pictureBoxLogo.Size")));
			this.pictureBoxLogo.SizeMode = ((System.Windows.Forms.PictureBoxSizeMode)(resources.GetObject("pictureBoxLogo.SizeMode")));
			this.pictureBoxLogo.TabIndex = ((int)(resources.GetObject("pictureBoxLogo.TabIndex")));
			this.pictureBoxLogo.TabStop = false;
			this.pictureBoxLogo.Text = resources.GetString("pictureBoxLogo.Text");
			this.pictureBoxLogo.Visible = ((bool)(resources.GetObject("pictureBoxLogo.Visible")));
			// 
			// BookOfTheDayForm
			// 
			this.AccessibleDescription = ((string)(resources.GetObject("$this.AccessibleDescription")));
			this.AccessibleName = ((string)(resources.GetObject("$this.AccessibleName")));
			this.Anchor = ((System.Windows.Forms.AnchorStyles)(resources.GetObject("$this.Anchor")));
			this.AutoScaleBaseSize = ((System.Drawing.Size)(resources.GetObject("$this.AutoScaleBaseSize")));
			this.AutoScroll = ((bool)(resources.GetObject("$this.AutoScroll")));
			this.AutoScrollMargin = ((System.Drawing.Size)(resources.GetObject("$this.AutoScrollMargin")));
			this.AutoScrollMinSize = ((System.Drawing.Size)(resources.GetObject("$this.AutoScrollMinSize")));
			this.BackColor = System.Drawing.Color.White;
			this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
			this.ClientSize = ((System.Drawing.Size)(resources.GetObject("$this.ClientSize")));
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.textBoxItemsSold,
																		  this.labelBooksSold,
																		  this.textBoxTitle,
																		  this.textBoxDate,
																		  this.labelBookOfTheDay,
																		  this.pictureBoxLogo});
			this.Dock = ((System.Windows.Forms.DockStyle)(resources.GetObject("$this.Dock")));
			this.Enabled = ((bool)(resources.GetObject("$this.Enabled")));
			this.Font = ((System.Drawing.Font)(resources.GetObject("$this.Font")));
			this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
			this.ImeMode = ((System.Windows.Forms.ImeMode)(resources.GetObject("$this.ImeMode")));
			this.Location = ((System.Drawing.Point)(resources.GetObject("$this.Location")));
			this.MaximumSize = ((System.Drawing.Size)(resources.GetObject("$this.MaximumSize")));
			this.MinimumSize = ((System.Drawing.Size)(resources.GetObject("$this.MinimumSize")));
			this.Name = "BookOfTheDayForm";
			this.RightToLeft = ((System.Windows.Forms.RightToLeft)(resources.GetObject("$this.RightToLeft")));
			this.StartPosition = ((System.Windows.Forms.FormStartPosition)(resources.GetObject("$this.StartPosition")));
			this.Text = resources.GetString("$this.Text");
			this.Visible = ((bool)(resources.GetObject("$this.Visible")));
			this.ResumeLayout(false);

		}
		#endregion

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main(string[] args) 
		{
			string culture = "";
			if (args.Length == 1)
			{
				culture = args[0];
			}

			Application.Run(new BookOfTheDayForm(culture));
		}

		public void WelcomeMessage()
		{
			ResourceManager resource = 
				new ResourceManager("Wrox.ProCSharp.Welcome", 
				Assembly.GetExecutingAssembly());

			DateTime time = DateTime.Now;
			string message;
			if (time.Hour <= 12)
			{
				message = resource.GetString("GoodMorning");
			}
			else if (time.Hour <= 19)
			{
				message = resource.GetString("GoodAfternoon");
			}
 			else
			{
				message = resource.GetString("GoodEvening");
			}
			MessageBox.Show(message + "\n\n" + resource.GetString("Description"));
		}

		public void SetDateAndNumber()
		{
			DateTime date = DateTime.Today;
			textBoxDate.Text = date.ToString("D");
			Int32 itemsSold = 327444;
			textBoxItemsSold.Text = itemsSold.ToString("###,###,###");
		}
	}
}
