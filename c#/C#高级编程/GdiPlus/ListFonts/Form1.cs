using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.Drawing.Text;

namespace ListFonts
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;


		

		private const int margin = 10;

		public Form1()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
			this.BackColor = Color.White;
			this.Text = "EnumFontFamilies";
			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}



		protected override void OnPaint(PaintEventArgs e)
		{
			base.OnPaint(e);
			int verticalCoordinate = margin;
			Point topLeftCorner;
			InstalledFontCollection insFont = new InstalledFontCollection();
			FontFamily [] families = insFont.Families;
			e.Graphics.TranslateTransform(AutoScrollPosition.X, 
				AutoScrollPosition.Y);
			foreach (FontFamily family in families)
			{
				if (family.IsStyleAvailable(FontStyle.Regular))
				{
					Font f = new Font(family.Name, 10);
					topLeftCorner = new Point(margin, verticalCoordinate);
					verticalCoordinate += f.Height;
					e.Graphics.DrawString (family.Name, f, 
						Brushes.Black,topLeftCorner);
					f.Dispose();
				}
			}
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
			this.components = new System.ComponentModel.Container();
			this.Size = new System.Drawing.Size(300,300);
			this.Text = "Form1";
		}
		#endregion

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			Application.Run(new Form1());
		}
	}
}
