using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.IO;
using System.Text;

namespace Wrox.ProCSharp.FilesAndRegistry
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private OpenFileDialog chooseOpenFileDialog = new OpenFileDialog();
		private string chosenFile;
		private System.Windows.Forms.MainMenu mainMenu1;
		private System.Windows.Forms.MenuItem menuItem1;
		private System.Windows.Forms.TextBox textBoxContents;
		private System.Windows.Forms.MenuItem menuFileOpen;
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
			menuFileOpen.Click += new EventHandler(OnFileOpen);
			chooseOpenFileDialog.FileOk += new 
				CancelEventHandler(OnOpenFileDialogOK);
		}
		void OnFileOpen(object Sender, EventArgs e)
		{
			chooseOpenFileDialog.ShowDialog();
		}
		void OnOpenFileDialogOK(object Sender, CancelEventArgs e)
		{
			chosenFile = chooseOpenFileDialog.FileName;
			this.Text = Path.GetFileName(chosenFile);
			DisplayFile();

		}

		void DisplayFile()
		{
			int nCols = 16;
			FileStream inStream = new FileStream(chosenFile, FileMode.Open, 
				FileAccess.Read);
			long nBytesToRead = inStream.Length;
			if (nBytesToRead > 65536/4) 
				nBytesToRead = 65536/4;
			int nLines = (int)(nBytesToRead/nCols) + 1;
			string [] lines = new string[nLines];
			int nBytesRead = 0;
			for (int i=0 ; i<nLines ; i++)
			{
				StringBuilder nextLine = new StringBuilder();
				nextLine.Capacity = 4*nCols;
				for (int j = 0 ; j<nCols ; j++)
				{
					int nextByte = inStream.ReadByte();
					nBytesRead++;
					if (nextByte < 0 || nBytesRead > 65536)
						break;
					char nextChar = (char)nextByte;
					if (nextChar < 16)
						nextLine.Append(" x0" + string.Format("{0,1:X}", 
							(int)nextChar));
					else if 
						(char.IsLetterOrDigit(nextChar) || 
						char.IsPunctuation(nextChar))
						nextLine.Append("  " + nextChar + " ");
					else
						nextLine.Append(" x" + string.Format("{0,2:X}", 
							(int)nextChar));
				}
				lines[i] = nextLine.ToString();
			}
			inStream.Close();
			this.textBoxContents.Lines = lines;
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
			this.textBoxContents = new System.Windows.Forms.TextBox();
			this.mainMenu1 = new System.Windows.Forms.MainMenu();
			this.menuItem1 = new System.Windows.Forms.MenuItem();
			this.menuFileOpen = new System.Windows.Forms.MenuItem();
			this.SuspendLayout();
			// 
			// textBoxContents
			// 
			this.textBoxContents.Dock = System.Windows.Forms.DockStyle.Fill;
			this.textBoxContents.Font = new System.Drawing.Font("Courier New", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.textBoxContents.Multiline = true;
			this.textBoxContents.Name = "textBoxContents";
			this.textBoxContents.ScrollBars = System.Windows.Forms.ScrollBars.Both;
			this.textBoxContents.Size = new System.Drawing.Size(292, 268);
			this.textBoxContents.TabIndex = 0;
			this.textBoxContents.Text = "";
			// 
			// mainMenu1
			// 
			this.mainMenu1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					  this.menuItem1});
			// 
			// menuItem1
			// 
			this.menuItem1.Index = 0;
			this.menuItem1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					  this.menuFileOpen});
			this.menuItem1.Text = "&File";
			// 
			// menuFileOpen
			// 
			this.menuFileOpen.Index = 0;
			this.menuFileOpen.Text = "&Open";
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(292, 268);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.textBoxContents});
			this.Menu = this.mainMenu1;
			this.Name = "Form1";
			this.Text = "BinaryFileReader Sample";
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
