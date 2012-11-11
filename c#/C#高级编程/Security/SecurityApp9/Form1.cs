using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.IO;
using System.Windows.Forms;
using System.Data;
using System.Threading;
using System.Security;

namespace SecurityApp9
{
   /// <summary>
   /// Summary description for Form1.
   /// </summary>
   public class Form1 : System.Windows.Forms.Form
   {
      private System.Windows.Forms.ListBox listBox1;
      private System.Windows.Forms.Button button1;
      /// <summary>
      /// Required designer variable.
      /// </summary>
      private System.ComponentModel.Container components;

      public Form1()
      {
         //
         // Required for Windows Form Designer support
         //
         InitializeComponent();

      }

      /// <summary>
      /// Clean up any resources being used.
      /// </summary>
      protected override void Dispose(bool disposing)
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
         this.listBox1 = new System.Windows.Forms.ListBox();
         this.button1 = new System.Windows.Forms.Button();
         this.SuspendLayout();
         // 
         // listBox1
         // 
         this.listBox1.Location = new System.Drawing.Point(8, 8);
         this.listBox1.Name = "listBox1";
         this.listBox1.Size = new System.Drawing.Size(280, 199);
         this.listBox1.TabIndex = 0;
         // 
         // button1
         // 
         this.button1.Location = new System.Drawing.Point(104, 224);
         this.button1.Name = "button1";
         this.button1.TabIndex = 1;
         this.button1.Text = "Load Data";
         this.button1.Click += new System.EventHandler(this.button1_Click);
         // 
         // Form1
         // 
         this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
         this.ClientSize = new System.Drawing.Size(296, 253);
         this.Controls.AddRange(new System.Windows.Forms.Control[] {this.button1,
                                                                      this.listBox1});
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

      private void button1_Click(object sender, System.EventArgs e)
      {
         try
         {
            StreamReader din = File.OpenText(@"c:\animals.txt");
            String str;
            while ((str=din.ReadLine()) != null) 
            {
               listBox1.Items.Add(str);
            }
         }
         catch
         {
            listBox1.Items.Add("Permission denied accessing file");
         }
      }
   }
}
