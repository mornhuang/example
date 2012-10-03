using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace VisualInheritance
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Button btnPerson;
		private System.Windows.Forms.Button btnAuto;
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
			this.btnPerson = new System.Windows.Forms.Button();
			this.btnAuto = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// btnPerson
			// 
			this.btnPerson.Location = new System.Drawing.Point(56, 32);
			this.btnPerson.Name = "btnPerson";
			this.btnPerson.Size = new System.Drawing.Size(64, 24);
			this.btnPerson.TabIndex = 0;
			this.btnPerson.Text = "Person";
			this.btnPerson.Click += new System.EventHandler(this.btnPerson_Click);
			// 
			// btnAuto
			// 
			this.btnAuto.Location = new System.Drawing.Point(48, 80);
			this.btnAuto.Name = "btnAuto";
			this.btnAuto.Size = new System.Drawing.Size(80, 24);
			this.btnAuto.TabIndex = 1;
			this.btnAuto.Text = "Automobile";
			this.btnAuto.Click += new System.EventHandler(this.btnAuto_Click);
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(194, 175);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.btnAuto,
																		  this.btnPerson});
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
			this.MaximizeBox = false;
			this.MinimizeBox = false;
			this.Name = "Form1";
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
			this.Text = "Main Menu";
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

		private void btnPerson_Click(object sender, System.EventArgs e)
		{
			Form3 aForm = new Form3();
			aForm.ShowDialog();
		}

		private void btnAuto_Click(object sender, System.EventArgs e)
		{
			Form4 aForm = new Form4();
			aForm.ShowDialog();
		}

		

	}
}
