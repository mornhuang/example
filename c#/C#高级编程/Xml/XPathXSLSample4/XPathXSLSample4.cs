using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.IO;
using System.Data;
using System.Xml;
using System.Xml.XPath;
using System.Xml.Xsl;

namespace Wrox.ProCSharp.Xml.XPathXSLSample4
{
	/// <summary>
	///		Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.ListBox listBox1;
		private System.Windows.Forms.Button button1;
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
      this.listBox1.Anchor = ((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
        | System.Windows.Forms.AnchorStyles.Right);
      this.listBox1.Size = new System.Drawing.Size(336, 238);
      this.listBox1.TabIndex = 0;
      this.button1.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
      this.button1.Location = new System.Drawing.Point(136, 264);
      this.button1.TabIndex = 1;
      this.button1.Text = "Load XML";
      this.button1.Click += new System.EventHandler(this.button1_Click);
      this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
      this.ClientSize = new System.Drawing.Size(339, 320);
      this.Controls.AddRange(new System.Windows.Forms.Control[] {this.button1,
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
    //XPathXSLSample4/form1.cs
    private void button1_Click(object sender, System.EventArgs e)
    {
      //new XPathDocument
      XPathDocument doc=new XPathDocument("..\\booksxpath.xml");
      //new XslTransform
      XslTransform transForm=new XslTransform();
      transForm.Load("..\\booksarg.xsl");
      //new XmlTextWriter since we are creating a new xml document
      XmlWriter xw=new XmlTextWriter("..\\argSample.xml",null);
      //create the XslArgumentList and new BookUtils object
      XsltArgumentList argBook=new XsltArgumentList();
      BookUtils bu=new BookUtils();
      //this tells the argumentlist about BokUtils
      argBook.AddExtensionObject("urn:ProCSharp",bu);
      //new XPathNavigator
      XPathNavigator nav=((IXPathNavigable)doc).CreateNavigator();
      //do the transform
      transForm.Transform(nav,argBook,xw);
      xw.Close();
      MessageBox.Show("argSample.xml created");
 
    }
    
    //simple test class
    public class BookUtils
    {
      public BookUtils(){}
      
      public string ShowText()
      {
        return "This came from the ShowText method!"; 
      }
    }
    
	}
}
