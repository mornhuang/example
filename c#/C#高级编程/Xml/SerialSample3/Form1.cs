using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.IO;
using System.Xml;
using System.Xml.Serialization;

namespace Wrox.ProCSharp.Xml.SerialSample3
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

    //SerialSample3\form1.cs
    private void button1_Click(object sender, System.EventArgs e)
    {
      //create new book and bookproducts objects
      Product newProd=new Product();
      BookProduct newBook=new BookProduct();
      //set som eproperties
      newProd.ProductID=100;
      newProd.ProductName="Product Thing";
      newProd.SupplierID=10;
      
      newBook.ProductID=101;
      newBook.ProductName="How to Use Your New Product Thing";
      newBook.SupplierID=10;
      newBook.ISBN="123456789";
      //add the items to an array
      Product[] addProd={newProd,newBook};
      //new inventory object using the addProd array
      Inventory inv=new Inventory();
      inv.InventoryItems=addProd;
      //serialize the Inventory object
      TextWriter tr=new StreamWriter("..\\..\\..\\order.xml");
      XmlSerializer sr=new XmlSerializer(typeof(Inventory));
      
      sr.Serialize(tr,inv);
      tr.Close();
		MessageBox.Show("Serialized!");
      
    }

    private void button2_Click(object sender, System.EventArgs e)
    {
      //create Inventory object
      Inventory newInv;
      
      FileStream f=new FileStream("..\\..\\..\\order.xml",FileMode.Open);
      //deserialize the order.xml doc
      XmlSerializer newSr=new XmlSerializer(typeof(Inventory));
      newInv=(Inventory)newSr.Deserialize(f);
      //load listbox with data from deserialized object
      foreach(Product prod in newInv.InventoryItems)
        listBox1.Items.Add(prod.ProductName);
        
      f.Close();
    }
    
    
    public class Inventory
    {

      private Product[] stuff;
      //ctor
      public Inventory() {}
      //need have an attribute entry for each data type
      [XmlArrayItem("Prod",typeof(Product)),
      XmlArrayItem("Book",typeof(BookProduct))]
     
      public Product[] InventoryItems {
        get {return stuff;}
        set {stuff=value;}
      }
    
    }
    
    //product class
    public class Product {
    
      private int prodId;
      private string prodName;
      private  int suppId;
     
      public Product() {}
       
    
      public int  ProductID {
        get {return prodId;}
        set {prodId=value;}
      }
      
    
      public string ProductName {
        get {return prodName;}
        set {prodName=value;}
      }
      
    
      public int SupplierID  {
        get {return suppId;}
        set {suppId=value;}
      }
     
      
    }
    //Bookproduct class
    public class BookProduct:Product
    {
      private string isbnNum;
      
      public BookProduct() {}
      
      public string ISBN  {
        get {return isbnNum;}
        set {isbnNum=value;}
      }
    
    }
    
    
    
	}
}
