using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.IO;
using System.Xml;
using System.Xml.Schema;



namespace Wrox.ProCSharp.Xml.XmlReaderSample5
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.ListBox listBox1;
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
			this.button1 = new System.Windows.Forms.Button();
			this.listBox1 = new System.Windows.Forms.ListBox();
			this.SuspendLayout();
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(40, 256);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(200, 24);
			this.button1.TabIndex = 1;
			this.button1.Text = "Load XML";
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// listBox1
			// 
			this.listBox1.Location = new System.Drawing.Point(24, 16);
			this.listBox1.Name = "listBox1";
			this.listBox1.Size = new System.Drawing.Size(232, 225);
			this.listBox1.TabIndex = 0;
		// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(288, 293);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.listBox1,
																		  this.button1});
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

		
  
		

		// XmlReaderSample5/form.cs
		protected void button1_Click (object sender, System.EventArgs e)
		{
			//change this to match your path structure.
			string fileName = "..\\booksVal.xml";
			XmlTextReader tr=new XmlTextReader(fileName);
			XmlValidatingReader trv=new XmlValidatingReader(tr);
		
			//Set validation type
			trv.ValidationType=ValidationType.XDR;
			//Add in the Validation eventhandler
			trv.ValidationEventHandler += 
				new ValidationEventHandler(this.ValidationEvent);
			//Read in node at a time        
			while(trv.Read())  
			{
				if(trv.NodeType == XmlNodeType.Text)
					listBox1.Items.Add(trv.Value);
			}
		    
		}

		public void ValidationEvent (object sender, ValidationEventArgs args)
		{
			MessageBox.Show(args.Message);
		}

		  

	}
}
