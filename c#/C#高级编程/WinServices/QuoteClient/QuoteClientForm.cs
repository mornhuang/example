using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

using System.Net;
using System.Net.Sockets;
using System.Text;

namespace Wrox.ProfessionalCSharp
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
      private System.Windows.Forms.Label label1;
      private System.Windows.Forms.TextBox textBoxHostname;
      private System.Windows.Forms.Label label2;
      private System.Windows.Forms.Button buttonQuote;
      private System.Windows.Forms.TextBox textBoxQuote;
      private System.Windows.Forms.StatusBar statusBar;
      private System.Windows.Forms.TextBox textBoxPortNumber;
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

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose(bool disposing)
		{
			if (disposing)
			{
				if(components != null)
				{
					components.Dispose();
				}
			}
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
         this.statusBar = new System.Windows.Forms.StatusBar();
         this.textBoxHostname = new System.Windows.Forms.TextBox();
         this.textBoxQuote = new System.Windows.Forms.TextBox();
         this.buttonQuote = new System.Windows.Forms.Button();
         this.textBoxPortNumber = new System.Windows.Forms.TextBox();
         this.label1 = new System.Windows.Forms.Label();
         this.label2 = new System.Windows.Forms.Label();
         this.SuspendLayout();
         // 
         // statusBar
         // 
         this.statusBar.BackColor = System.Drawing.SystemColors.Control;
         this.statusBar.Location = new System.Drawing.Point(0, 289);
         this.statusBar.Name = "statusBar";
         this.statusBar.Size = new System.Drawing.Size(416, 20);
         this.statusBar.TabIndex = 6;
         // 
         // textBoxHostname
         // 
         this.textBoxHostname.Location = new System.Drawing.Point(96, 24);
         this.textBoxHostname.Name = "textBoxHostname";
         this.textBoxHostname.Size = new System.Drawing.Size(128, 20);
         this.textBoxHostname.TabIndex = 1;
         this.textBoxHostname.Text = "localhost";
         // 
         // textBoxQuote
         // 
         this.textBoxQuote.Location = new System.Drawing.Point(16, 64);
         this.textBoxQuote.Multiline = true;
         this.textBoxQuote.Name = "textBoxQuote";
         this.textBoxQuote.Size = new System.Drawing.Size(376, 160);
         this.textBoxQuote.TabIndex = 4;
         this.textBoxQuote.Text = "";
         // 
         // buttonQuote
         // 
         this.buttonQuote.Location = new System.Drawing.Point(16, 240);
         this.buttonQuote.Name = "buttonQuote";
         this.buttonQuote.Size = new System.Drawing.Size(384, 40);
         this.buttonQuote.TabIndex = 5;
         this.buttonQuote.Text = "Get Quote";
         this.buttonQuote.Click += new System.EventHandler(this.buttonQuote_Click);
         // 
         // textBoxPortNumber
         // 
         this.textBoxPortNumber.Location = new System.Drawing.Point(336, 24);
         this.textBoxPortNumber.Name = "textBoxPort";
         this.textBoxPortNumber.Size = new System.Drawing.Size(48, 20);
         this.textBoxPortNumber.TabIndex = 3;
         this.textBoxPortNumber.Text = "5678";
         // 
         // label1
         // 
         this.label1.Location = new System.Drawing.Point(24, 24);
         this.label1.Name = "label1";
         this.label1.Size = new System.Drawing.Size(64, 23);
         this.label1.TabIndex = 0;
         this.label1.Text = "Hostname:";
         // 
         // label2
         // 
         this.label2.Location = new System.Drawing.Point(272, 24);
         this.label2.Name = "label2";
         this.label2.Size = new System.Drawing.Size(40, 23);
         this.label2.TabIndex = 2;
         this.label2.Text = "Port:";
         // 
         // Form1
         // 
         this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
         this.ClientSize = new System.Drawing.Size(416, 309);
         this.Controls.AddRange(new System.Windows.Forms.Control[] {this.buttonQuote,
                                                                      this.statusBar,
                                                                      this.textBoxQuote,
                                                                      this.textBoxPortNumber,
                                                                      this.label2,
                                                                      this.textBoxHostname,
                                                                      this.label1});
         this.Name = "Form1";
         this.Text = "Quote of the Day";
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

      private void buttonQuote_Click(object sender, System.EventArgs e)
      {
         statusBar.Text = "";
         string server = textBoxHostname.Text;

         try
         {
            int port = Convert.ToInt32(textBoxPortNumber.Text);
         }
         catch (FormatException ex)
         {
            statusBar.Text = ex.Message;
            return;
         }

         TcpClient client = new TcpClient();

         try
         {
            client.Connect(textBoxHostname.Text,
               Convert.ToInt32(textBoxPortNumber.Text));

            NetworkStream stream = client.GetStream();
            byte[] buffer = new Byte[1024];
            int received = stream.Read(buffer, 0, 1024);
            if (received <= 0)
            {
               statusBar.Text = "Read failed";
               return;
            }

            textBoxQuote.Text = Encoding.Unicode.GetString(buffer);
         }
         catch (SocketException ex)
         {
            statusBar.Text = ex.Message;
         }
         finally
         {
            client.Close();
         }
      }
	}
}
