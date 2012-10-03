using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace Wrox.ProCSharp.WinApps.DynamicUI
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Button btnComp;
		private System.Windows.Forms.Button btnSoft;
		private System.Windows.Forms.Button btnFurn;
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

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.btnComp = new System.Windows.Forms.Button();
			this.btnSoft = new System.Windows.Forms.Button();
			this.btnFurn = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// btnComp
			// 
			this.btnComp.Location = new System.Drawing.Point(24, 24);
			this.btnComp.Name = "btnComp";
			this.btnComp.Size = new System.Drawing.Size(88, 32);
			this.btnComp.TabIndex = 0;
			this.btnComp.Text = "Computer";
			this.btnComp.Click += new System.EventHandler(this.btnComp_Click);
			// 
			// btnSoft
			// 
			this.btnSoft.Location = new System.Drawing.Point(24, 88);
			this.btnSoft.Name = "btnSoft";
			this.btnSoft.Size = new System.Drawing.Size(88, 32);
			this.btnSoft.TabIndex = 1;
			this.btnSoft.Text = "Software";
			this.btnSoft.Click += new System.EventHandler(this.btnSoft_Click);
			// 
			// btnFurn
			// 
			this.btnFurn.Location = new System.Drawing.Point(24, 152);
			this.btnFurn.Name = "btnFurn";
			this.btnFurn.Size = new System.Drawing.Size(88, 32);
			this.btnFurn.TabIndex = 2;
			this.btnFurn.Text = "Furniture";
			this.btnFurn.Click += new System.EventHandler(this.btnFurn_Click);
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(480, 229);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.btnFurn,
																		  this.btnSoft,
																		  this.btnComp});
			this.Name = "Form1";
			this.Text = "Form1";
			this.ResumeLayout(false);

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

		private void AddControl(Control aControl, Point Location, Size Size, String strText, int TabIndex, string strName)
		{
			aControl.Location = Location;
			aControl.Size = Size;
			aControl.Text = strText; 
			aControl.TabIndex = TabIndex;
			aControl.Name = strName;
			this.Controls.Add(aControl);
		}

		private void btnComp_Click(object sender, System.EventArgs e)
		{
			TextBox aTextBox;
			Controls.Clear();
			InitializeComponent();

			AddControl(new Label(), new Point(125,24), new Size(45,20), "ID:", 0, "");
			aTextBox = new TextBox();
			aTextBox.KeyPress += new KeyPressEventHandler(ID_Validate);
			AddControl(aTextBox, new Point(174,21), new Size(125,20), "", 0, "txtID");
			AddControl(new Label(), new Point(125,54), new Size(45,20), "OS:", 0, "");
			AddControl(new TextBox(), new Point(174,50), new Size(125,20), "", 1, "txtOS");
			AddControl(new Label(), new Point(125,84), new Size(45,20), "Speed:", 0, "");
			AddControl(new TextBox(), new Point(174,78), new Size(125,20), "", 2, "txtSpeed");
		}

		private void btnSoft_Click(object sender, System.EventArgs e)
		{
			TextBox aTextBox;
			Controls.Clear();
			InitializeComponent();

			AddControl(new Label(), new Point(125,24), new Size(45,20), "ID:", 0, "");
			aTextBox = new TextBox();
			aTextBox.KeyPress += new KeyPressEventHandler(ID_Validate);
			AddControl(aTextBox, new Point(174,21), new Size(125,20), "", 0, "txtID");
			AddControl(new Label(), new Point(125,54), new Size(45,20), "Vendor:", 0, "");
			AddControl(new TextBox(), new Point(174,50), new Size(125,20), "", 1, "txtVendor");
			AddControl(new Label(), new Point(125,84), new Size(45,20), "Name:", 0, "");
			AddControl(new TextBox(), new Point(174,78), new Size(125,20), "", 2, "txtName");
		}

		private void btnFurn_Click(object sender, System.EventArgs e)
		{
			TextBox aTextBox;
			Controls.Clear();
			InitializeComponent();

			AddControl(new Label(), new Point(125,24), new Size(45,20), "ID:", 0, "");
			aTextBox = new TextBox();
			aTextBox.KeyPress += new KeyPressEventHandler(ID_Validate);
			AddControl(aTextBox, new Point(174,21), new Size(125,20), "", 0, "txtID");
			AddControl(new Label(), new Point(125,54), new Size(45,20), "Color:", 0, "");
			AddControl(new TextBox(), new Point(174,50), new Size(125,20), "", 1, "txtColor");
			AddControl(new Label(), new Point(125,84), new Size(45,20), "Type:", 0, "");
			ComboBox aCombo = new ComboBox();
			aCombo.Items.AddRange(new Object[] {"Desk", "Chair", "Whiteboard"});
			AddControl(aCombo, new Point(174,78), new Size(125,20),"",2,"");

		}

		private void ID_Validate(object sender, System.Windows.Forms.KeyPressEventArgs e)
		{
			if (!Char.IsNumber(e.KeyChar) && e.KeyChar != (char)8)
				e.Handled = true;
		
		}

	}
}
