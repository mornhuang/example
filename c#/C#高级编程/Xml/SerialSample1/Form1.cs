using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.IO;
using System.Xml;
using System.Xml.Serialization;

namespace Wrox.ProCSharp.Xml.SerialSample1
{

	/// <summary>
	///		Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.ListBox listBox1;
		private System.Windows.Forms.Button button1;
    private System.Windows.Forms.Button button2;
		/// <summary>
		///		Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components=null;

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
      this.listBox1 = new System.Windows.Forms.ListBox();
      this.button1 = new System.Windows.Forms.Button();
      this.button2 = new System.Windows.Forms.Button();
      this.listBox1.Anchor = ((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
        | System.Windows.Forms.AnchorStyles.Right);
      this.listBox1.Size = new System.Drawing.Size(336, 238);
      this.listBox1.TabIndex = 0;
      this.button1.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
      this.button1.Location = new System.Drawing.Point(64, 264);
      this.button1.TabIndex = 1;
      this.button1.Text = "Serialize";
      this.button1.Click += new System.EventHandler(this.button1_Click);
      this.button2.Location = new System.Drawing.Point(152, 264);
      this.button2.TabIndex = 2;
      this.button2.Text = "Deserialize";
      this.button2.Click += new System.EventHandler(this.button2_Click);
      this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
      this.ClientSize = new System.Drawing.Size(339, 320);
      this.Controls.AddRange(new System.Windows.Forms.Control[] {this.button2,
                                                                  this.button1,
                                                                  this.listBox1});
      this.Text = "Form1";

    }
		#endregion

		/// <summary>
		/// 	The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			Application.Run(new Form1());
		}
    //SerialSample1\form1.cs
    private void button1_Click(object sender, System.EventArgs e)
    {
      //new products object
      Products pd=new Products();
      //set some properties
      pd.ProductID=200;
      pd.CategoryID=100;
      pd.Discontinued=false;
      pd.ProductName="Serialize Objects";
      pd.QuantityPerUnit="6";
      pd.ReorderLevel=1;
      pd.SupplierID=1;
      pd.UnitPrice=1000;
      pd.UnitsInStock=10;
      pd.UnitsOnOrder=0;
      //new TextWriter and XmlSerializer
      TextWriter tr=new StreamWriter("..\\..\\..\\serialprod.xml");
      XmlSerializer sr=new XmlSerializer(typeof(Products));
      //serialize object
      sr.Serialize(tr,pd);
      tr.Close();
		MessageBox.Show("Serialized!");
      
    }

    private void button2_Click(object sender, System.EventArgs e)
    {
      //create a reference to producst type
      Products newPd;
      //new filestream to open serialized object
      FileStream f=new FileStream("..\\..\\..\\serialprod.xml",FileMode.Open);
      //new serializer
      XmlSerializer newSr=new XmlSerializer(typeof(Products));
      //deserialize the object
      newPd=(Products)newSr.Deserialize(f);
      //load it in the list box.
      listBox1.Items.Add(newPd.ProductName);
      f.Close();
    }
    
    //class that will be serialized.
    //attributes determine how object is serialized
    [System.Xml.Serialization.XmlRootAttribute(Namespace="", IsNullable=false)]
    public class Products {
      
      [System.Xml.Serialization.XmlElementAttribute(IsNullable=false)]
      public int ProductID;
      
      [System.Xml.Serialization.XmlElementAttribute(IsNullable=false)]
      public string ProductName;
      
      [System.Xml.Serialization.XmlElementAttribute(IsNullable=false)]
      public int SupplierID;
      
      [System.Xml.Serialization.XmlElementAttribute(IsNullable=false)]
      public int CategoryID;
      
      [System.Xml.Serialization.XmlElementAttribute(IsNullable=false)]
      public string QuantityPerUnit;
      
      [System.Xml.Serialization.XmlElementAttribute(IsNullable=false)]
      public System.Decimal UnitPrice;
      
      [System.Xml.Serialization.XmlElementAttribute(IsNullable=false)]
      public short UnitsInStock;
      
      [System.Xml.Serialization.XmlElementAttribute(IsNullable=false)]
      public short UnitsOnOrder;
      
      [System.Xml.Serialization.XmlElementAttribute(IsNullable=false)]
      public short ReorderLevel;
      
      [System.Xml.Serialization.XmlElementAttribute(IsNullable=false)]
      public bool Discontinued;
      
    }

	}
}
