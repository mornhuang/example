using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.Net;
using System.Net.Sockets;
using System.IO;

namespace Wrox.ProCSharp.InternetAccess.TcpSend
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.TextBox txtHost;
		private System.Windows.Forms.TextBox txtPort;
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
			this.txtHost = new System.Windows.Forms.TextBox();
			this.txtPort = new System.Windows.Forms.TextBox();
			this.button1 = new System.Windows.Forms.Button();
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.SuspendLayout();
			// 
			// txtHost
			// 
			this.txtHost.Location = new System.Drawing.Point(8, 32);
			this.txtHost.Name = "txtHost";
			this.txtHost.Size = new System.Drawing.Size(104, 20);
			this.txtHost.TabIndex = 0;
			this.txtHost.Text = "localhost";
			// 
			// txtPort
			// 
			this.txtPort.Location = new System.Drawing.Point(136, 32);
			this.txtPort.Name = "txtPort";
			this.txtPort.Size = new System.Drawing.Size(40, 20);
			this.txtPort.TabIndex = 1;
			this.txtPort.Text = "2112";
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(56, 64);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(88, 32);
			this.button1.TabIndex = 2;
			this.button1.Text = "Send File";
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(136, 8);
			this.label1.Name = "label1";
			this.label1.TabIndex = 3;
			this.label1.Text = "Port";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(8, 8);
			this.label2.Name = "label2";
			this.label2.TabIndex = 4;
			this.label2.Text = "Hostname";
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(200, 109);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.label2,
																		  this.label1,
																		  this.button1,
																		  this.txtPort,
																		  this.txtHost});
			this.Name = "Form1";
			this.Text = "TcpSend";
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
			TcpClient tcpClient = new TcpClient(txtHost.Text, Int32.Parse(txtPort.Text));

			NetworkStream ns = tcpClient.GetStream();
			FileStream fs = File.Open("..\\..\\form1.cs", FileMode.Open);
    
			int data = fs.ReadByte();
			while(data != -1)
			{
				ns.WriteByte((byte)data);
				data = fs.ReadByte();
			}

			fs.Close();    
			ns.Close();
			tcpClient.Close();

		}
	}
}
