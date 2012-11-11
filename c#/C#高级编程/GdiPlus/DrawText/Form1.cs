using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace DrawText
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

		public Form1()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
this.BackColor = Color.White;
			this.Text = "DisplayText";
			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}
		
		private Brush blackBrush = Brushes.Black;
		private Brush blueBrush = Brushes.Blue;
		private Font haettenschweilerFont = new Font("Haettenschweiler", 12);
		private Font boldTimesFont = new Font("Times New Roman", 10, FontStyle.Bold);
		private Font italicCourierFont = new Font("Courier", 11, FontStyle.Italic | 
			FontStyle.Underline);

		protected override void OnPaint(PaintEventArgs e)
		{
			base.OnPaint(e);
			Graphics dc = e.Graphics;
			dc.DrawString("This is a groovy string", haettenschweilerFont, blackBrush, 
				10, 10);
			dc.DrawString("This is a groovy string " +
				"with some very long text that will never fit in the box", 
				boldTimesFont, blueBrush, 
				new Rectangle(new Point(10, 40), new Size(100, 40)));
			dc.DrawString("This is a groovy string", italicCourierFont, blackBrush, 
				new Point(10, 100));
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
