
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.Xml;
using System.Data.SqlClient;
using System.IO;


namespace Wrox.ProCSharp.Xml.ADOSample2 
{
	/// <summary>
	///		Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.ListBox listBox1;
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.DataGrid dataGrid1;
		/// <summary>
		///		Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
		private XmlDocument doc=new XmlDocument();
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
		///		Clean up any resources being used.
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
		///		Required method for Designer support - do not modify
		///		the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.dataGrid1 = new System.Windows.Forms.DataGrid();
			this.button1 = new System.Windows.Forms.Button();
			this.listBox1 = new System.Windows.Forms.ListBox();
			((System.ComponentModel.ISupportInitialize)(this.dataGrid1)).BeginInit();
			this.SuspendLayout();
			// 
			// dataGrid1
			// 
			this.dataGrid1.Anchor = (((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
				| System.Windows.Forms.AnchorStyles.Left) 
				| System.Windows.Forms.AnchorStyles.Right);
			this.dataGrid1.DataMember = "";
			this.dataGrid1.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.dataGrid1.Location = new System.Drawing.Point(0, 248);
			this.dataGrid1.Name = "dataGrid1";
			this.dataGrid1.Size = new System.Drawing.Size(392, 176);
			this.dataGrid1.TabIndex = 2;
			// 
			// button1
			// 
			this.button1.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
			this.button1.Location = new System.Drawing.Point(164, 432);
			this.button1.Name = "button1";
			this.button1.TabIndex = 1;
			this.button1.Text = "Load Data";
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// listBox1
			// 
			this.listBox1.Anchor = ((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
				| System.Windows.Forms.AnchorStyles.Right);
			this.listBox1.Name = "listBox1";
			this.listBox1.Size = new System.Drawing.Size(392, 238);
			this.listBox1.TabIndex = 0;
			this.listBox1.SelectedIndexChanged += new System.EventHandler(this.listBox1_SelectedIndexChanged);
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(395, 488);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.dataGrid1,
																		  this.button1,
																		  this.listBox1});
			this.Name = "Form1";
			this.Text = "Form1";
			((System.ComponentModel.ISupportInitialize)(this.dataGrid1)).EndInit();
			this.ResumeLayout(false);

		}
		#endregion

		/// <summary>
		/// 	The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			try
			{
				Application.Run(new Form1());
			}
			catch(System.IO.FileNotFoundException){}
		}

		// ADOSample2/form.cs
		private void button1_Click(object sender, System.EventArgs e)
		{
			//create a dataset
			DataSet ds=new DataSet("XMLProducts");
			//connect to the northwind database and 
			//select all of the rows from products table
			
			string source = Login.Connection;
			SqlConnection conn=new SqlConnection(source);
			
			
			SqlDataAdapter da=new SqlDataAdapter("SELECT * FROM Products",conn);

			//fill the dataset
			da.Fill(ds,"products");
			ds.WriteXml("c:\\ProCSharp\\Xml\\sample.xml", XmlWriteMode.WriteSchema);
			//load data into grid
			//dataGrid1.DataSource=ds;
			//dataGrid1.DataMember="products";
			doc=new XmlDataDocument(ds);
			//get all of the products elements
			XmlNodeList nodeLst=doc.GetElementsByTagName("ProductName");
			//load them into the list box
			for(int ctr=0;ctr<nodeLst.Count;ctr++)
				//foreach(XmlNode node in nodeLst)
				// listBox1.Items.Add(node.InnerText);
				listBox1.Items.Add(nodeLst[ctr].InnerText);
		}
    
		private void listBox1_SelectedIndexChanged(object sender, System.EventArgs e)
		{
			//when you click on the listbox.
			//a message box appears with unit price
			string srch="XMLProducts/products[ProductName=" + '"' + listBox1.SelectedItem.ToString() + '"' + "]";
			XmlNode foundNode=doc.SelectSingleNode(srch);
			if(foundNode!=null)
				MessageBox.Show(foundNode.SelectSingleNode("UnitPrice").InnerText);
			else
				MessageBox.Show("Not found");
      
		}

	}
}
