using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace HostApp
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private ControlLib.UserMenu userMenu1;
		private System.Windows.Forms.Button button1;
		private ControlLib.ActiveButton activeButton1;
		private ControlLib.ActiveButton activeButton2;
		private ControlLib.ActiveButton activeButton3;
		private ControlLib.ActiveLogin activeLogin1;
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
			this.userMenu1 = new ControlLib.UserMenu();
			this.button1 = new System.Windows.Forms.Button();
			this.activeButton1 = new ControlLib.ActiveButton();
			this.activeButton2 = new ControlLib.ActiveButton();
			this.activeButton3 = new ControlLib.ActiveButton();
			this.activeLogin1 = new ControlLib.ActiveLogin();
			this.SuspendLayout();
			// 
			// userMenu1
			// 
			this.userMenu1.Location = new System.Drawing.Point(24, 8);
			this.userMenu1.Name = "userMenu1";
			this.userMenu1.SelectedOption = ControlLib.SelectedOptionEnum.Amazing;
			this.userMenu1.Size = new System.Drawing.Size(96, 200);
			this.userMenu1.TabIndex = 0;
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(24, 216);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(96, 40);
			this.button1.TabIndex = 1;
			this.button1.Text = "Get that Ranking!";
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// activeButton1
			// 
			this.activeButton1.Location = new System.Drawing.Point(160, 216);
			this.activeButton1.Name = "activeButton1";
			this.activeButton1.Size = new System.Drawing.Size(160, 72);
			this.activeButton1.TabIndex = 2;
			this.activeButton1.Text = "activeButton1";
			// 
			// activeButton2
			// 
			this.activeButton2.Location = new System.Drawing.Point(160, 296);
			this.activeButton2.Name = "activeButton2";
			this.activeButton2.Size = new System.Drawing.Size(160, 48);
			this.activeButton2.TabIndex = 3;
			this.activeButton2.Text = "activeButton2";
			// 
			// activeButton3
			// 
			this.activeButton3.Location = new System.Drawing.Point(328, 216);
			this.activeButton3.Name = "activeButton3";
			this.activeButton3.Size = new System.Drawing.Size(80, 128);
			this.activeButton3.TabIndex = 4;
			this.activeButton3.Text = "activeButton3";
			// 
			// activeLogin1
			// 
			this.activeLogin1.Location = new System.Drawing.Point(144, 8);
			this.activeLogin1.Name = "activeLogin1";
			this.activeLogin1.Size = new System.Drawing.Size(280, 184);
			this.activeLogin1.TabIndex = 5;
			this.activeLogin1.GoodLogin += new ControlLib.GoodLoginEventHandler(this.activeLogin1_GoodLogin);
			this.activeLogin1.Cancel += new ControlLib.CancelEventHandler(this.activeLogin1_Cancel);
			this.activeLogin1.FailedThreeTimes += new ControlLib.FailedThreeTimesEventHandler(this.activeLogin1_FailedThreeTimes);
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(440, 357);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.activeLogin1,
																		  this.activeButton3,
																		  this.activeButton2,
																		  this.activeButton1,
																		  this.button1,
																		  this.userMenu1});
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

		private void button1_Click(object sender, System.EventArgs e)
		{
			MessageBox.Show(userMenu1.SelectedOption.ToString());
		}

		private void activeLogin1_GoodLogin(object sender, ControlLib.LoginEventArgs e)
		{
			MessageBox.Show("Welcome " + e.UserID);
		}

		private void activeLogin1_FailedThreeTimes(object sender, ControlLib.LoginEventArgs e)
		{
			MessageBox.Show("Failed to login three times.");
		}

		private void activeLogin1_Cancel(object sender, System.EventArgs e)
		{
			MessageBox.Show("Cancel");
		}
	}
}
