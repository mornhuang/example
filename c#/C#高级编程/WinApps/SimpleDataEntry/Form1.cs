using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace Wrox.ProCSharp.WinApps.SimpleDataEntry
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class frmMain : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Button btnSave;
		private System.Windows.Forms.Button btnCancel;
		private System.Windows.Forms.TextBox txtFName;
		private System.Windows.Forms.TextBox txtLName;
		private System.Windows.Forms.TextBox txtSSN;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label label3;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public frmMain()
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
			this.btnSave = new System.Windows.Forms.Button();
			this.btnCancel = new System.Windows.Forms.Button();
			this.txtFName = new System.Windows.Forms.TextBox();
			this.txtLName = new System.Windows.Forms.TextBox();
			this.txtSSN = new System.Windows.Forms.TextBox();
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.SuspendLayout();
			// 
			// btnSave
			// 
			this.btnSave.Anchor = (System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right);
			this.btnSave.Location = new System.Drawing.Point(125, 157);
			this.btnSave.Name = "btnSave";
			this.btnSave.Size = new System.Drawing.Size(78, 25);
			this.btnSave.TabIndex = 0;
			this.btnSave.Text = "Save";
			this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
			// 
			// btnCancel
			// 
			this.btnCancel.Anchor = (System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right);
			this.btnCancel.Location = new System.Drawing.Point(210, 157);
			this.btnCancel.Name = "btnCancel";
			this.btnCancel.Size = new System.Drawing.Size(78, 25);
			this.btnCancel.TabIndex = 1;
			this.btnCancel.Text = "Cancel";
			this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
			// 
			// txtFName
			// 
			this.txtFName.Anchor = ((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
				| System.Windows.Forms.AnchorStyles.Right);
			this.txtFName.Location = new System.Drawing.Point(97, 25);
			this.txtFName.Name = "txtFName";
			this.txtFName.Size = new System.Drawing.Size(115, 20);
			this.txtFName.TabIndex = 2;
			this.txtFName.Text = "";
			// 
			// txtLName
			// 
			this.txtLName.Anchor = ((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
				| System.Windows.Forms.AnchorStyles.Right);
			this.txtLName.Location = new System.Drawing.Point(97, 61);
			this.txtLName.Name = "txtLName";
			this.txtLName.Size = new System.Drawing.Size(115, 20);
			this.txtLName.TabIndex = 3;
			this.txtLName.Text = "";
			// 
			// txtSSN
			// 
			this.txtSSN.Anchor = ((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
				| System.Windows.Forms.AnchorStyles.Right);
			this.txtSSN.Location = new System.Drawing.Point(97, 99);
			this.txtSSN.Name = "txtSSN";
			this.txtSSN.Size = new System.Drawing.Size(115, 20);
			this.txtSSN.TabIndex = 4;
			this.txtSSN.Text = "";
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(20, 25);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(70, 18);
			this.label1.TabIndex = 5;
			this.label1.Text = "First Name:";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(20, 62);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(70, 18);
			this.label2.TabIndex = 6;
			this.label2.Text = "Last Name:";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(20, 99);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(70, 18);
			this.label3.TabIndex = 7;
			this.label3.Text = "SSN:";
			// 
			// frmMain
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(292, 193);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.label3,
																		  this.label2,
																		  this.label1,
																		  this.txtSSN,
																		  this.txtLName,
																		  this.txtFName,
																		  this.btnCancel,
																		  this.btnSave});
			this.Name = "frmMain";
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
			this.Text = "Data Entry Form";
			this.ResumeLayout(false);

		}
		#endregion

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			Application.Run(new frmMain());
		}

		private void btnSave_Click(object sender, System.EventArgs e)
		{
			SaveFile();
		}

		private void btnCancel_Click(object sender, System.EventArgs e)
		{
			Clear();
		}

		private void SaveFile()
		{
			//Save the values to an XML file
			//Could save to data source, Message Queue etc.
			System.Xml.XmlDocument aDOM = new System.Xml.XmlDocument();
			System.Xml.XmlAttribute aAttribute;

			aDOM.LoadXml("<UserData/>");

			//Add the First Name attribute to XML
			aAttribute = aDOM.CreateAttribute("FirstName");
			aAttribute.Value = txtFName.Text;
			aDOM.DocumentElement.Attributes.Append(aAttribute);

			//Add the Last Name attribute to XML
			aAttribute = aDOM.CreateAttribute("LastName");
			aAttribute.Value = txtLName.Text;
			aDOM.DocumentElement.Attributes.Append(aAttribute);

			//Add the SSN attribute to XML
			aAttribute = aDOM.CreateAttribute("SSN");
			aAttribute.Value = txtSSN.Text;
			aDOM.DocumentElement.Attributes.Append(aAttribute);

			//Save the file to the file system
			aDOM.Save("../../UserData.xml");
		}

		private void Clear()
		{
			//Erase all the text
			txtFName.Text = "";
			txtLName.Text = "";
			txtSSN.Text = "";
		}


		
	}
}
