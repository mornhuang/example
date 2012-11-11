using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using Microsoft.Win32;

namespace Wrox.ProCSharp.FilesAndRegistry
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.ListBox listBoxMessages;
		private System.Windows.Forms.Button buttonChooseColor;
		private ColorDialog chooseColorDialog = new ColorDialog();
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public Form1()
		{
			InitializeComponent();
			buttonChooseColor.Click += new EventHandler(OnClickChooseColor);
			try
			{
				if (ReadSettings() == false)
					listBoxMessages.Items.Add("No information in registry");
				else
					listBoxMessages.Items.Add("Information read in from registry");
									StartPosition = FormStartPosition.Manual;
			}
			catch (Exception e)
			{
				listBoxMessages.Items.Add("A problem occurred reading in data from registry:");
									  listBoxMessages.Items.Add(e.Message);
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
			SaveSettings();
			base.Dispose( disposing );
		}

		void OnClickChooseColor(object Sender, EventArgs e)
		{
			if(chooseColorDialog.ShowDialog() == DialogResult.OK)
				BackColor = chooseColorDialog.Color;
		}

		void SaveSettings()
		{
			RegistryKey softwareKey = 
				Registry.LocalMachine.OpenSubKey("Software", true);
			RegistryKey wroxKey = softwareKey.CreateSubKey("WroxPress");
			RegistryKey selfPlacingWindowKey = 
				wroxKey.CreateSubKey("SelfPlacingWindow");
			selfPlacingWindowKey.SetValue("BackColor", 
				(object)BackColor.ToKnownColor());
			selfPlacingWindowKey.SetValue("Red", (object)(int)BackColor.R);
			selfPlacingWindowKey.SetValue("Green", (object)(int)BackColor.G);
			selfPlacingWindowKey.SetValue("Blue", (object)(int)BackColor.B);
			selfPlacingWindowKey.SetValue("Width", (object)Width);
			selfPlacingWindowKey.SetValue("Height", (object)Height);
			selfPlacingWindowKey.SetValue("X", (object)DesktopLocation.X);
			selfPlacingWindowKey.SetValue("Y", (object)DesktopLocation.Y);
			selfPlacingWindowKey.SetValue("WindowState", 
				(object)WindowState.ToString());
		}

		bool ReadSettings()
		{ 
			RegistryKey softwareKey = 
				Registry.LocalMachine.OpenSubKey("Software");
			RegistryKey wroxKey = softwareKey.OpenSubKey("WroxPress");
			if (wroxKey == null)
				return false;
			RegistryKey selfPlacingWindowKey = 
				wroxKey.OpenSubKey("SelfPlacingWindow");
			if (selfPlacingWindowKey == null)
				return false;
			else
				listBoxMessages.Items.Add("Successfully opened key " + 
					selfPlacingWindowKey.ToString());
			int redComponent = (int)selfPlacingWindowKey.GetValue("Red");
			int greenComponent = (int)selfPlacingWindowKey.GetValue("Green");
			int blueComponent = (int)selfPlacingWindowKey.GetValue("Blue");
			this.BackColor = Color.FromArgb(redComponent, greenComponent, 
				blueComponent);
			listBoxMessages.Items.Add("Background color: " + BackColor.Name);
			int X = (int)selfPlacingWindowKey.GetValue("X");
			int Y = (int)selfPlacingWindowKey.GetValue("Y");
			this.DesktopLocation = new Point(X, Y);
			listBoxMessages.Items.Add("Desktop location: " + 
				DesktopLocation.ToString());
			this.Height = (int)selfPlacingWindowKey.GetValue("Height");
			this.Width = (int)selfPlacingWindowKey.GetValue("Width");
			listBoxMessages.Items.Add("Size: " + new 
				Size(Width,Height).ToString());
			string initialWindowState = 
				(string)selfPlacingWindowKey.GetValue("WindowState");
			listBoxMessages.Items.Add("Window State: " + initialWindowState);
			this.WindowState = (FormWindowState)FormWindowState.Parse
				(WindowState.GetType(), initialWindowState);
			return true;
		}




		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.listBoxMessages = new System.Windows.Forms.ListBox();
			this.buttonChooseColor = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// listBoxMessages
			// 
			this.listBoxMessages.Name = "listBoxMessages";
			this.listBoxMessages.Size = new System.Drawing.Size(288, 199);
			this.listBoxMessages.TabIndex = 0;
			// 
			// buttonChooseColor
			// 
			this.buttonChooseColor.Location = new System.Drawing.Point(0, 208);
			this.buttonChooseColor.Name = "buttonChooseColor";
			this.buttonChooseColor.Size = new System.Drawing.Size(104, 23);
			this.buttonChooseColor.TabIndex = 1;
			this.buttonChooseColor.Text = "Choose Color";
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(292, 232);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.buttonChooseColor,
																		  this.listBoxMessages});
			this.Name = "Form1";
			this.Text = "SelfPlacingWindow";
			this.Load += new System.EventHandler(this.Form1_Load);
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

		private void Form1_Load(object sender, System.EventArgs e)
		{
		
		}
	}
}
