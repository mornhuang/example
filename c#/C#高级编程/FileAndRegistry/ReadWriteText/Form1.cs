using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.IO;
using System.Collections.Specialized;
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
		private System.Windows.Forms.MenuItem menuFileSave;
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
		void OnFileSave(object Sender, EventArgs e)
		{
			SaveFile();
		}
		void OnOpenFileDialogOK(object Sender, CancelEventArgs e)
		{
			chosenFile = chooseOpenFileDialog.FileName;
			this.Text = Path.GetFileName(chosenFile);
			DisplayFile();
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

		void SaveFile()
		{
			StreamWriter sw = new StreamWriter(chosenFile, false, 
				Encoding.Unicode);
			foreach (string line in textBoxContents.Lines)
				sw.WriteLine(line);
			sw.Close();
		}

		void DisplayFile()
		{
			StringCollection linesCollection = ReadFileIntoStringCollection();
			string [] linesArray = new string[linesCollection.Count];
			linesCollection.CopyTo(linesArray, 0);
			this.textBoxContents.Lines = linesArray;
		}

		StringCollection ReadFileIntoStringCollection()
		{
			const int MaxBytes = 65536;
			StreamReader sr = new StreamReader(chosenFile);
			StringCollection result = new StringCollection();
			int nBytesRead = 0;
			string nextLine;
			while ( (nextLine = sr.ReadLine()) != null)
			{
				nBytesRead += nextLine.Length;
				if (nBytesRead > MaxBytes)
					break;
				result.Add(nextLine);
			}
			sr.Close();
			return result;
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
			this.menuFileSave = new System.Windows.Forms.MenuItem();
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
																					  this.menuFileOpen,
																					  this.menuFileSave});
			this.menuItem1.Text = "&File";
			// 
			// menuFileOpen
			// 
			this.menuFileOpen.Index = 0;
			this.menuFileOpen.Text = "&Open";
			// 
			// menuFileSave
			// 
			this.menuFileSave.Index = 1;
			this.menuFileSave.Text = "&Save";
			this.menuFileSave.Click += new System.EventHandler(this.OnFileSave);
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(292, 268);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.textBoxContents});
			this.Menu = this.mainMenu1;
			this.Name = "Form1";
			this.Text = "ReadWriteText";
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
	}
}
