using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace DisplayPicture
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
		Image piccy;
		private Point [] piccyBounds; 

		public Form1()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
			piccy = 
				Image.FromFile(
				@"C:\ProCSharp\GdiPlus\Images\cf4group.bmp");
			this.AutoScrollMinSize = piccy.Size;
			this.Text = "Display COMFest Image";
			piccyBounds = new Point[3];
			piccyBounds[0] = new Point(0,0);      // top left
			piccyBounds[1] = new Point(piccy.Width,0);   // top right
			piccyBounds[2] = new Point(0,piccy.Height);   // bottom left

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}


		protected override void OnPaint(PaintEventArgs e)
		{
			base.OnPaint(e);
			Graphics dc = e.Graphics;
			dc.ScaleTransform(1.0f, 1.0f);
			dc.TranslateTransform(this.AutoScrollPosition.X, this.AutoScrollPosition.Y);
			dc.DrawImage(piccy, piccyBounds);
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			         piccy.Dispose();
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
