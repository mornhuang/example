using System;
using System.Collections;
using System.ComponentModel;
using System.Drawing;

using System.Windows.Forms;


namespace VisualInheritance
{
	public class Form4 : VisualInheritance.frmBase
	{
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.TextBox txtManufact;
		private System.Windows.Forms.TextBox txtModel;
		private System.Windows.Forms.TextBox txtYear;
		private System.Windows.Forms.TextBox txtColor;
		private System.ComponentModel.IContainer components = null;

		public Form4()
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
			this.txtManufact = new System.Windows.Forms.TextBox();
			this.txtModel = new System.Windows.Forms.TextBox();
			this.txtYear = new System.Windows.Forms.TextBox();
			this.txtColor = new System.Windows.Forms.TextBox();
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.label4 = new System.Windows.Forms.Label();
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
			// txtManufact
			// 
			this.txtManufact.Location = new System.Drawing.Point(104, 16);
			this.txtManufact.Name = "txtManufact";
			this.txtManufact.Size = new System.Drawing.Size(88, 20);
			this.txtManufact.TabIndex = 2;
			this.txtManufact.Text = "";
			// 
			// txtModel
			// 
			this.txtModel.Location = new System.Drawing.Point(104, 44);
			this.txtModel.Name = "txtModel";
			this.txtModel.Size = new System.Drawing.Size(88, 20);
			this.txtModel.TabIndex = 3;
			this.txtModel.Text = "";
			// 
			// txtYear
			// 
			this.txtYear.Location = new System.Drawing.Point(104, 72);
			this.txtYear.Name = "txtYear";
			this.txtYear.Size = new System.Drawing.Size(88, 20);
			this.txtYear.TabIndex = 4;
			this.txtYear.Text = "";
			// 
			// txtColor
			// 
			this.txtColor.Location = new System.Drawing.Point(104, 100);
			this.txtColor.Name = "txtColor";
			this.txtColor.Size = new System.Drawing.Size(88, 20);
			this.txtColor.TabIndex = 5;
			this.txtColor.Text = "";
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(16, 16);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(76, 16);
			this.label1.TabIndex = 6;
			this.label1.Text = "Manufacturer:";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(16, 104);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(76, 16);
			this.label2.TabIndex = 7;
			this.label2.Text = "Color:";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(16, 72);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(76, 16);
			this.label3.TabIndex = 8;
			this.label3.Text = "Year:";
			// 
			// label4
			// 
			this.label4.Location = new System.Drawing.Point(16, 48);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(76, 16);
			this.label4.TabIndex = 9;
			this.label4.Text = "Model:";
			// 
			// Form4
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(246, 227);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.label4,
																		  this.label3,
																		  this.label2,
																		  this.label1,
																		  this.txtColor,
																		  this.txtYear,
																		  this.txtModel,
																		  this.btnCancel,
																		  this.btnSave,
																		  this.txtManufact});
			this.Name = "Form4";
			this.Text = "Automobile Information";
			this.ResumeLayout(false);

		}
		#endregion

		private void btnSave_Click(object sender, System.EventArgs e)
		{
			//Save the values to an XML file
			//Could save to data source, Message Queue, etc.
			System.Xml.XmlDocument aDOM = new System.Xml.XmlDocument();
			System.Xml.XmlAttribute aAttribute;

			aDOM.LoadXml("<AutomobileData/>");

			//Add the First Name attribute to XML
			aAttribute = aDOM.CreateAttribute("Manufacturer");
			aAttribute.Value = txtManufact.Text;
			aDOM.DocumentElement.Attributes.Append(aAttribute);
			//Add the Last Name attribute to XML
			aAttribute = aDOM.CreateAttribute("Model");
			aAttribute.Value = txtModel.Text;
			aDOM.DocumentElement.Attributes.Append(aAttribute);
			//Add the DOB attribute to XML
			aAttribute = aDOM.CreateAttribute("Year");
			aAttribute.Value = txtYear.Text;
			aDOM.DocumentElement.Attributes.Append(aAttribute);
			//Add the SSN attribute to XML
			aAttribute = aDOM.CreateAttribute("Color");
			aAttribute.Value = txtColor.Text;
			aDOM.DocumentElement.Attributes.Append(aAttribute);

			//Save file to the file system
			aDOM.Save("AutomobileData.xml");
		}

		private void btnCancel_Click(object sender, System.EventArgs e)
		{
			txtManufact.Text = "";
			txtModel.Text = "";
			txtYear.Text = "";
			txtColor.Text = "";
		}
	}
}

