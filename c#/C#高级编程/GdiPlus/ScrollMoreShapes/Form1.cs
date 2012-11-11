using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.Drawing.Drawing2D;

namespace ScrollMoreShapes
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
		private Rectangle rectangleBounds = new Rectangle(new Point(0,0), 
			new Size(200,200));
		private Rectangle ellipseBounds = new Rectangle(new Point(50,200), 
			new Size(200,150));
		private Pen bluePen = new Pen(Color.Blue, 3);
		private Pen redPen = new Pen(Color.Red, 2);
		private Brush solidAzureBrush = Brushes.Azure;
		private Brush solidYellowBrush = new SolidBrush(Color.Yellow);
		static private Brush brickBrush = new HatchBrush(HatchStyle.DiagonalBrick,
			Color.DarkGoldenrod, Color.Cyan);
		private Pen brickWidePen = new Pen(brickBrush, 10); 

		public Form1()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
			this.BackColor = Color.White;
			this.Text = "Scroll More Shapes";
			//
			// TODO: Add any constructor code after InitializeComponent call
			//
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

		protected override void OnPaint( PaintEventArgs e )
		{
			base.OnPaint(e);
			Graphics dc = e.Graphics;
			Point scrollOffset = this.AutoScrollPosition;
			dc.TranslateTransform(scrollOffset.X, scrollOffset.Y);
			if (e.ClipRectangle.Top+scrollOffset.X < 350 || 
				e.ClipRectangle.Left+scrollOffset.Y < 250)
			{
				dc.DrawRectangle(bluePen, rectangleBounds);
				dc.FillRectangle(solidYellowBrush, rectangleBounds);
				dc.DrawEllipse(redPen, ellipseBounds);
				dc.FillEllipse(solidAzureBrush, ellipseBounds);
				dc.DrawLine(brickWidePen, rectangleBounds.Location, 
					ellipseBounds.Location+ellipseBounds.Size);
			}
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(292, 273);
			this.Name = "Form1";
			this.Text = "Form1";
			this.Load += new System.EventHandler(this.Form1_Load);

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

		private void Form1_Load(object sender, System.EventArgs e)
		{
		
		}
	}
}
