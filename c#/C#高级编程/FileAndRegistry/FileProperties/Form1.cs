using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.IO;

namespace Wrox.ProCSharp.FilesAndRegistry
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private string currentFolderPath;
		private System.Windows.Forms.Label label8;
		private System.Windows.Forms.Button buttonDisplay;
		private System.Windows.Forms.Button buttonUp;
		private System.Windows.Forms.TextBox txtBoxLastWriteTime;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.TextBox txtBoxInput;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.TextBox txtBoxCreationTime;
		private System.Windows.Forms.ListBox listBoxFiles;
		private System.Windows.Forms.ListBox listBoxFolders;
		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.Label label6;
		private System.Windows.Forms.TextBox txtBoxLastAccessTime;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.TextBox txtBoxFileSize;
		private System.Windows.Forms.Label label7;
		private System.Windows.Forms.TextBox txtBoxFileName;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.TextBox txtBoxFolder;
		private System.Windows.Forms.GroupBox groupBox2;
		private System.Windows.Forms.GroupBox groupBox3;
		private System.Windows.Forms.Label label9;
		private System.Windows.Forms.TextBox textBox1;
		private System.Windows.Forms.Label label10;
		private System.Windows.Forms.Label label11;
		private System.Windows.Forms.TextBox textBox2;
		private System.Windows.Forms.Label label12;
		private System.Windows.Forms.TextBox textBox3;
		private System.Windows.Forms.Label label13;
		private System.Windows.Forms.TextBox textBox4;
		private System.Windows.Forms.GroupBox groupBox4;
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.TextBox textBox5;
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
		protected void ClearAllFields()
		{
			listBoxFolders.Items.Clear();
			listBoxFiles.Items.Clear();
			txtBoxFolder.Text = "";
			txtBoxFileName.Text = "";
			txtBoxCreationTime.Text = "";
			txtBoxLastAccessTime.Text = "";
			txtBoxLastWriteTime.Text = "";
			txtBoxFileSize.Text = "";
		}

		protected void DisplayFileInfo(string fileFullName)
		{
			FileInfo theFile = new FileInfo(fileFullName);
			if (!theFile.Exists)
				throw new FileNotFoundException("File not found: " + fileFullName);
			txtBoxFileName.Text = theFile.Name;
			txtBoxCreationTime.Text = theFile.CreationTime.ToLongTimeString();
			txtBoxLastAccessTime.Text = theFile.LastAccessTime.ToLongDateString();
			txtBoxLastWriteTime.Text = theFile.LastWriteTime.ToLongDateString();
			txtBoxFileSize.Text = theFile.Length.ToString() + " bytes";
		}

		protected void DisplayFolderList(string folderFullName)
		{
			DirectoryInfo theFolder = new DirectoryInfo(folderFullName);
			if (!theFolder.Exists)
				throw new DirectoryNotFoundException("Folder not found: " 
					+ folderFullName);
			ClearAllFields();
			txtBoxFolder.Text = theFolder.FullName;
			currentFolderPath = theFolder.FullName; 

			// list all subfolders in folder

			foreach(DirectoryInfo nextFolder in theFolder.GetDirectories())
				listBoxFolders.Items.Add(nextFolder.Name);

			// list all files in folder

			foreach(FileInfo nextFile in theFolder.GetFiles())
				listBoxFiles.Items.Add(nextFile.Name);
		}

		protected void OnDisplayButtonClick(object sender, EventArgs e)
		{
			try
			{
				string folderPath = txtBoxInput.Text;
				DirectoryInfo theFolder = new DirectoryInfo(folderPath);
				if (theFolder.Exists)
				{
					DisplayFolderList(theFolder.FullName);
					return;
				}
				FileInfo theFile = new FileInfo(folderPath);
				if (theFile.Exists)
				{
					DisplayFolderList(theFile.Directory.FullName);
					int index = listBoxFiles.Items.IndexOf(theFile.Name);
					listBoxFiles.SetSelected(index, true);
					return;
				}
				throw new FileNotFoundException("There is no file or folder with "
					+ "this name: " + txtBoxInput.Text);
			}
			catch(Exception ex)
			{
				MessageBox.Show(ex.Message);
			}
		}

		protected void OnListBoxFilesSelected(object sender, EventArgs e)
		{
			try
			{
				string selectedString = listBoxFiles.SelectedItem.ToString();
				string fullFileName = Path.Combine(currentFolderPath, selectedString);
				DisplayFileInfo(fullFileName);
			}
			catch(Exception ex)
			{
				MessageBox.Show(ex.Message);
			}
		}

		protected void OnListBoxFoldersSelected(object sender, EventArgs e)
		{
			try
			{
				string selectedString = listBoxFolders.SelectedItem.ToString();
				string fullPathName = Path.Combine(currentFolderPath, selectedString);
				DisplayFolderList(fullPathName);
			}
			catch(Exception ex)
			{
				MessageBox.Show(ex.Message);
			}
		}

		protected void OnUpButtonClick(object sender, EventArgs e)
		{
			try
			{
				string folderPath = new FileInfo(currentFolderPath).DirectoryName;
				DisplayFolderList(folderPath);
			}
			catch(Exception ex)
			{
				MessageBox.Show(ex.Message);
			}
		}


		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.label8 = new System.Windows.Forms.Label();
			this.buttonDisplay = new System.Windows.Forms.Button();
			this.buttonUp = new System.Windows.Forms.Button();
			this.txtBoxLastWriteTime = new System.Windows.Forms.TextBox();
			this.label2 = new System.Windows.Forms.Label();
			this.txtBoxInput = new System.Windows.Forms.TextBox();
			this.label1 = new System.Windows.Forms.Label();
			this.txtBoxCreationTime = new System.Windows.Forms.TextBox();
			this.listBoxFiles = new System.Windows.Forms.ListBox();
			this.listBoxFolders = new System.Windows.Forms.ListBox();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			this.label6 = new System.Windows.Forms.Label();
			this.txtBoxLastAccessTime = new System.Windows.Forms.TextBox();
			this.label5 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.txtBoxFileSize = new System.Windows.Forms.TextBox();
			this.label7 = new System.Windows.Forms.Label();
			this.txtBoxFileName = new System.Windows.Forms.TextBox();
			this.label4 = new System.Windows.Forms.Label();
			this.txtBoxFolder = new System.Windows.Forms.TextBox();
			this.groupBox2 = new System.Windows.Forms.GroupBox();
			this.groupBox3 = new System.Windows.Forms.GroupBox();
			this.label9 = new System.Windows.Forms.Label();
			this.textBox1 = new System.Windows.Forms.TextBox();
			this.label10 = new System.Windows.Forms.Label();
			this.label11 = new System.Windows.Forms.Label();
			this.textBox2 = new System.Windows.Forms.TextBox();
			this.label12 = new System.Windows.Forms.Label();
			this.textBox3 = new System.Windows.Forms.TextBox();
			this.label13 = new System.Windows.Forms.Label();
			this.textBox4 = new System.Windows.Forms.TextBox();
			this.groupBox4 = new System.Windows.Forms.GroupBox();
			this.button1 = new System.Windows.Forms.Button();
			this.textBox5 = new System.Windows.Forms.TextBox();
			this.groupBox1.SuspendLayout();
			this.groupBox3.SuspendLayout();
			this.SuspendLayout();
			// 
			// label8
			// 
			this.label8.Location = new System.Drawing.Point(256, 104);
			this.label8.Name = "label8";
			this.label8.Size = new System.Drawing.Size(100, 16);
			this.label8.TabIndex = 14;
			this.label8.Text = "Folders";
			// 
			// buttonDisplay
			// 
			this.buttonDisplay.Location = new System.Drawing.Point(432, 24);
			this.buttonDisplay.Name = "buttonDisplay";
			this.buttonDisplay.TabIndex = 26;
			this.buttonDisplay.Text = "Display";
			this.buttonDisplay.Click += new System.EventHandler(this.OnDisplayButtonClick);
			// 
			// buttonUp
			// 
			this.buttonUp.Location = new System.Drawing.Point(408, 72);
			this.buttonUp.Name = "buttonUp";
			this.buttonUp.Size = new System.Drawing.Size(72, 24);
			this.buttonUp.TabIndex = 24;
			this.buttonUp.Text = "Up";
			this.buttonUp.Click += new System.EventHandler(this.OnUpButtonClick);
			// 
			// txtBoxLastWriteTime
			// 
			this.txtBoxLastWriteTime.Enabled = false;
			this.txtBoxLastWriteTime.Location = new System.Drawing.Point(32, 448);
			this.txtBoxLastWriteTime.Name = "txtBoxLastWriteTime";
			this.txtBoxLastWriteTime.Size = new System.Drawing.Size(184, 20);
			this.txtBoxLastWriteTime.TabIndex = 16;
			this.txtBoxLastWriteTime.Text = "";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(16, 104);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(136, 16);
			this.label2.TabIndex = 22;
			this.label2.Text = "Files";
			// 
			// txtBoxInput
			// 
			this.txtBoxInput.Location = new System.Drawing.Point(16, 24);
			this.txtBoxInput.Name = "txtBoxInput";
			this.txtBoxInput.Size = new System.Drawing.Size(408, 20);
			this.txtBoxInput.TabIndex = 19;
			this.txtBoxInput.Text = "";
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(16, 8);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(280, 16);
			this.label1.TabIndex = 20;
			this.label1.Text = "Enter name of folder to be examined and click Display";
			// 
			// txtBoxCreationTime
			// 
			this.txtBoxCreationTime.Enabled = false;
			this.txtBoxCreationTime.Location = new System.Drawing.Point(248, 400);
			this.txtBoxCreationTime.Name = "txtBoxCreationTime";
			this.txtBoxCreationTime.Size = new System.Drawing.Size(184, 20);
			this.txtBoxCreationTime.TabIndex = 15;
			this.txtBoxCreationTime.Text = "";
			// 
			// listBoxFiles
			// 
			this.listBoxFiles.Location = new System.Drawing.Point(16, 120);
			this.listBoxFiles.Name = "listBoxFiles";
			this.listBoxFiles.Size = new System.Drawing.Size(240, 199);
			this.listBoxFiles.TabIndex = 13;
			this.listBoxFiles.SelectedIndexChanged += new System.EventHandler(this.OnListBoxFilesSelected);
			// 
			// listBoxFolders
			// 
			this.listBoxFolders.Location = new System.Drawing.Point(264, 120);
			this.listBoxFolders.Name = "listBoxFolders";
			this.listBoxFolders.Size = new System.Drawing.Size(240, 199);
			this.listBoxFolders.TabIndex = 17;
			this.listBoxFolders.SelectedIndexChanged += new System.EventHandler(this.OnListBoxFoldersSelected);
			// 
			// groupBox1
			// 
			this.groupBox1.Controls.AddRange(new System.Windows.Forms.Control[] {
																					this.label6,
																					this.txtBoxLastAccessTime,
																					this.label5,
																					this.label3,
																					this.txtBoxFileSize,
																					this.label7,
																					this.txtBoxFileName,
																					this.label4,
																					this.txtBoxFolder,
																					this.groupBox2});
			this.groupBox1.Location = new System.Drawing.Point(8, 48);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(496, 432);
			this.groupBox1.TabIndex = 27;
			this.groupBox1.TabStop = false;
			this.groupBox1.Text = "Contents of folder";
			// 
			// label6
			// 
			this.label6.Location = new System.Drawing.Point(24, 384);
			this.label6.Name = "label6";
			this.label6.Size = new System.Drawing.Size(120, 16);
			this.label6.TabIndex = 8;
			this.label6.Text = "Last modification time";
			// 
			// txtBoxLastAccessTime
			// 
			this.txtBoxLastAccessTime.Enabled = false;
			this.txtBoxLastAccessTime.Location = new System.Drawing.Point(240, 400);
			this.txtBoxLastAccessTime.Name = "txtBoxLastAccessTime";
			this.txtBoxLastAccessTime.Size = new System.Drawing.Size(184, 20);
			this.txtBoxLastAccessTime.TabIndex = 1;
			this.txtBoxLastAccessTime.Text = "";
			// 
			// label5
			// 
			this.label5.Location = new System.Drawing.Point(240, 376);
			this.label5.Name = "label5";
			this.label5.Size = new System.Drawing.Size(100, 16);
			this.label5.TabIndex = 7;
			this.label5.Text = "Last access time";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(240, 328);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(100, 16);
			this.label3.TabIndex = 5;
			this.label3.Text = "Creation time";
			// 
			// txtBoxFileSize
			// 
			this.txtBoxFileSize.Enabled = false;
			this.txtBoxFileSize.Location = new System.Drawing.Point(24, 352);
			this.txtBoxFileSize.Name = "txtBoxFileSize";
			this.txtBoxFileSize.Size = new System.Drawing.Size(184, 20);
			this.txtBoxFileSize.TabIndex = 1;
			this.txtBoxFileSize.Text = "";
			// 
			// label7
			// 
			this.label7.Location = new System.Drawing.Point(24, 328);
			this.label7.Name = "label7";
			this.label7.Size = new System.Drawing.Size(100, 16);
			this.label7.TabIndex = 10;
			this.label7.Text = "File Size";
			// 
			// txtBoxFileName
			// 
			this.txtBoxFileName.Enabled = false;
			this.txtBoxFileName.Location = new System.Drawing.Point(80, 304);
			this.txtBoxFileName.Name = "txtBoxFileName";
			this.txtBoxFileName.Size = new System.Drawing.Size(400, 20);
			this.txtBoxFileName.TabIndex = 1;
			this.txtBoxFileName.Text = "";
			// 
			// label4
			// 
			this.label4.Location = new System.Drawing.Point(16, 304);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(64, 16);
			this.label4.TabIndex = 6;
			this.label4.Text = "File name";
			// 
			// txtBoxFolder
			// 
			this.txtBoxFolder.Enabled = false;
			this.txtBoxFolder.Location = new System.Drawing.Point(8, 24);
			this.txtBoxFolder.Name = "txtBoxFolder";
			this.txtBoxFolder.Size = new System.Drawing.Size(384, 20);
			this.txtBoxFolder.TabIndex = 2;
			this.txtBoxFolder.Text = "";
			// 
			// groupBox2
			// 
			this.groupBox2.Location = new System.Drawing.Point(8, 280);
			this.groupBox2.Name = "groupBox2";
			this.groupBox2.Size = new System.Drawing.Size(480, 144);
			this.groupBox2.TabIndex = 11;
			this.groupBox2.TabStop = false;
			this.groupBox2.Text = "Details of Selected File";
			// 
			// groupBox3
			// 
			this.groupBox3.Controls.AddRange(new System.Windows.Forms.Control[] {
																					this.label9,
																					this.textBox1,
																					this.label10,
																					this.label11,
																					this.textBox2,
																					this.label12,
																					this.textBox3,
																					this.label13,
																					this.textBox4,
																					this.groupBox4});
			this.groupBox3.Location = new System.Drawing.Point(8, 48);
			this.groupBox3.Name = "groupBox3";
			this.groupBox3.Size = new System.Drawing.Size(496, 432);
			this.groupBox3.TabIndex = 28;
			this.groupBox3.TabStop = false;
			this.groupBox3.Text = "Contents of folder";
			// 
			// label9
			// 
			this.label9.Location = new System.Drawing.Point(24, 384);
			this.label9.Name = "label9";
			this.label9.Size = new System.Drawing.Size(120, 16);
			this.label9.TabIndex = 8;
			this.label9.Text = "Last modification time";
			// 
			// textBox1
			// 
			this.textBox1.Enabled = false;
			this.textBox1.Location = new System.Drawing.Point(240, 400);
			this.textBox1.Name = "textBox1";
			this.textBox1.Size = new System.Drawing.Size(184, 20);
			this.textBox1.TabIndex = 1;
			this.textBox1.Text = "";
			// 
			// label10
			// 
			this.label10.Location = new System.Drawing.Point(240, 376);
			this.label10.Name = "label10";
			this.label10.Size = new System.Drawing.Size(100, 16);
			this.label10.TabIndex = 7;
			this.label10.Text = "Last access time";
			// 
			// label11
			// 
			this.label11.Location = new System.Drawing.Point(240, 328);
			this.label11.Name = "label11";
			this.label11.Size = new System.Drawing.Size(100, 16);
			this.label11.TabIndex = 5;
			this.label11.Text = "Creation time";
			// 
			// textBox2
			// 
			this.textBox2.Enabled = false;
			this.textBox2.Location = new System.Drawing.Point(24, 352);
			this.textBox2.Name = "textBox2";
			this.textBox2.Size = new System.Drawing.Size(184, 20);
			this.textBox2.TabIndex = 1;
			this.textBox2.Text = "";
			// 
			// label12
			// 
			this.label12.Location = new System.Drawing.Point(24, 328);
			this.label12.Name = "label12";
			this.label12.Size = new System.Drawing.Size(100, 16);
			this.label12.TabIndex = 10;
			this.label12.Text = "File Size";
			// 
			// textBox3
			// 
			this.textBox3.Enabled = false;
			this.textBox3.Location = new System.Drawing.Point(80, 304);
			this.textBox3.Name = "textBox3";
			this.textBox3.Size = new System.Drawing.Size(400, 20);
			this.textBox3.TabIndex = 1;
			this.textBox3.Text = "";
			// 
			// label13
			// 
			this.label13.Location = new System.Drawing.Point(16, 304);
			this.label13.Name = "label13";
			this.label13.Size = new System.Drawing.Size(64, 16);
			this.label13.TabIndex = 6;
			this.label13.Text = "File name";
			// 
			// textBox4
			// 
			this.textBox4.Enabled = false;
			this.textBox4.Location = new System.Drawing.Point(8, 24);
			this.textBox4.Name = "textBox4";
			this.textBox4.Size = new System.Drawing.Size(384, 20);
			this.textBox4.TabIndex = 2;
			this.textBox4.Text = "";
			// 
			// groupBox4
			// 
			this.groupBox4.Location = new System.Drawing.Point(8, 280);
			this.groupBox4.Name = "groupBox4";
			this.groupBox4.Size = new System.Drawing.Size(480, 144);
			this.groupBox4.TabIndex = 11;
			this.groupBox4.TabStop = false;
			this.groupBox4.Text = "If file selected ...";
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(400, 72);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(72, 24);
			this.button1.TabIndex = 23;
			this.button1.Text = "Up";
			// 
			// textBox5
			// 
			this.textBox5.Location = new System.Drawing.Point(16, 24);
			this.textBox5.Name = "textBox5";
			this.textBox5.Size = new System.Drawing.Size(408, 20);
			this.textBox5.TabIndex = 18;
			this.textBox5.Text = "";
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(520, 496);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.label8,
																		  this.buttonDisplay,
																		  this.buttonUp,
																		  this.txtBoxLastWriteTime,
																		  this.label2,
																		  this.txtBoxInput,
																		  this.label1,
																		  this.txtBoxCreationTime,
																		  this.listBoxFiles,
																		  this.listBoxFolders,
																		  this.groupBox1,
																		  this.groupBox3,
																		  this.button1,
																		  this.textBox5});
			this.Name = "Form1";
			this.Text = "FileProperties Sample";
			this.groupBox1.ResumeLayout(false);
			this.groupBox3.ResumeLayout(false);
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
