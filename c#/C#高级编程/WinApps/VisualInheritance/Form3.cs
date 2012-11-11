using System;
using System.Collections;
using System.ComponentModel;
using System.Drawing;

using System.Windows.Forms;


namespace VisualInheritance
{
	public class Form3 : VisualInheritance.frmBase
	{
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.TextBox txtFName;
		private System.Windows.Forms.TextBox txtLName;
		private System.Windows.Forms.TextBox txtDOB;
		private System.Windows.Forms.TextBox txtSSN;
		private System.ComponentModel.IContainer components = null;

		public Form3()
		{
			// This call is required by the Windows Form Designer.
			InitializeComponent();

			// TODO: Add any initialization after the InitializeComponent call
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

		#region Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.label4 = new System.Windows.Forms.Label();
			this.txtFName = new System.Windows.Forms.TextBox();
			this.txtLName = new System.Windows.Forms.TextBox();
			this.txtDOB = new System.Windows.Forms.TextBox();
			this.txtSSN = new System.Windows.Forms.TextBox();
			this.SuspendLayout();
			// 
			// btnSave
			// 
			this.btnSave.Location = new System.Drawing.Point(161, 153);
			this.btnSave.Visible = true;
			this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
			// 
			// btnCancel
			// 
			this.btnCancel.Location = new System.Drawing.Point(161, 187);
			this.btnCancel.Visible = true;
			this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(16, 16);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(64, 16);
			this.label1.TabIndex = 2;
			this.label1.Text = "First Name:";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(16, 40);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(64, 16);
			this.label2.TabIndex = 3;
			this.label2.Text = "Last Name:";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(16, 64);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(64, 16);
			this.label3.TabIndex = 4;
			this.label3.Text = "DOB:";
			// 
			// label4
			// 
			this.label4.Location = new System.Drawing.Point(16, 88);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(64, 16);
			this.label4.TabIndex = 5;
			this.label4.Text = "SSN:";
			// 
			// txtFName
			// 
			this.txtFName.Location = new System.Drawing.Point(88, 16);
			this.txtFName.Name = "txtFName";
			this.txtFName.Size = new System.Drawing.Size(88, 20);
			this.txtFName.TabIndex = 6;
			this.txtFName.Text = "";
			// 
			// txtLName
			// 
			this.txtLName.Location = new System.Drawing.Point(88, 40);
			this.txtLName.Name = "txtLName";
			this.txtLName.Size = new System.Drawing.Size(88, 20);
			this.txtLName.TabIndex = 7;
			this.txtLName.Text = "";
			// 
			// txtDOB
			// 
			this.txtDOB.Location = new System.Drawing.Point(88, 64);
			this.txtDOB.Name = "txtDOB";
			this.txtDOB.Size = new System.Drawing.Size(88, 20);
			this.txtDOB.TabIndex = 8;
			this.txtDOB.Text = "";
			// 
			// txtSSN
			// 
			this.txtSSN.Location = new System.Drawing.Point(88, 88);
			this.txtSSN.Name = "txtSSN";
			this.txtSSN.Size = new System.Drawing.Size(88, 20);
			this.txtSSN.TabIndex = 9;
			this.txtSSN.Text = "";
			// 
			// frmPersonnel
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(246, 227);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.txtSSN,
																		  this.txtDOB,
																		  this.txtLName,
																		  this.txtFName,
																		  this.label4,
																		  this.label3,
																		  this.label2,
																		  this.btnCancel,
																		  this.btnSave,
																		  this.label1});
			this.Name = "Form3";
			this.Text = "Personnel Information";
			this.ResumeLayout(false);

		}
		#endregion

		private void btnSave_Click(object sender, System.EventArgs e)
		{
			//Save the values to an XML file
			//Could save to data source, Message Queue, etc.
			System.Xml.XmlDocument aDOM = new System.Xml.XmlDocument();
			System.Xml.XmlAttribute aAttribute;

			aDOM.LoadXml("<PersonnelData/>");

			//Add the First Name attribute to XML
			aAttribute = aDOM.CreateAttribute("FirstName");
			aAttribute.Value = txtFName.Text;
			aDOM.DocumentElement.Attributes.Append(aAttribute);
			//Add the Last Name attribute to XML
			aAttribute = aDOM.CreateAttribute("LastName");
			aAttribute.Value = txtLName.Text;
			aDOM.DocumentElement.Attributes.Append(aAttribute);
			//Add the DOB attribute to XML
			aAttribute = aDOM.CreateAttribute("DOB");
			aAttribute.Value = txtDOB.Text;
			aDOM.DocumentElement.Attributes.Append(aAttribute);
			//Add the SSN attribute to XML
			aAttribute = aDOM.CreateAttribute("SSN");
			aAttribute.Value = txtSSN.Text;
			aDOM.DocumentElement.Attributes.Append(aAttribute);

			//Save file to the file system
			aDOM.Save("PersonnelData.xml");
		}

		private void btnCancel_Click(object sender, System.EventArgs e)
		{
			txtLName.Text = "";
			txtFName.Text = "";
			txtDOB.Text = "";
			txtSSN.Text = "";
		}
	}
}

