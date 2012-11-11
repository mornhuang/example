using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;



namespace Wrox.ProCSharp.InternetAccess.Messenger
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.TextBox txtHost;
		private System.Windows.Forms.Button button2;
		private System.Windows.Forms.RichTextBox txtInput;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.RichTextBox txtDisplay;
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
			Thread thread = new Thread(new ThreadStart(Listen));
			thread.Start();
			
			

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
			this.txtHost = new System.Windows.Forms.TextBox();
			this.button2 = new System.Windows.Forms.Button();
			this.txtInput = new System.Windows.Forms.RichTextBox();
			this.txtDisplay = new System.Windows.Forms.RichTextBox();
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.SuspendLayout();
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(232, 192);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(208, 56);
			this.button1.TabIndex = 1;
			this.button1.Text = "Send Message";
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// txtHost
			// 
			this.txtHost.Location = new System.Drawing.Point(16, 224);
			this.txtHost.Name = "txtHost";
			this.txtHost.Size = new System.Drawing.Size(200, 20);
			this.txtHost.TabIndex = 2;
			this.txtHost.Text = "localhost";
			this.txtHost.TextChanged += new System.EventHandler(this.txtHost_TextChanged);
			// 
			// button2
			// 
			this.button2.Location = new System.Drawing.Point(480, 208);
			this.button2.Name = "button2";
			this.button2.Size = new System.Drawing.Size(88, 24);
			this.button2.TabIndex = 3;
			this.button2.Text = "End Session";
			this.button2.Click += new System.EventHandler(this.button2_Click);
			// 
			// txtInput
			// 
			this.txtInput.Location = new System.Drawing.Point(8, 8);
			this.txtInput.Name = "txtInput";
			this.txtInput.Size = new System.Drawing.Size(272, 144);
			this.txtInput.TabIndex = 4;
			this.txtInput.Text = "Start....";
			// 
			// txtDisplay
			// 
			this.txtDisplay.Location = new System.Drawing.Point(304, 8);
			this.txtDisplay.Name = "txtDisplay";
			this.txtDisplay.Size = new System.Drawing.Size(272, 144);
			this.txtDisplay.TabIndex = 5;
			this.txtDisplay.Text = "Start....";
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(16, 160);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(248, 16);
			this.label1.TabIndex = 6;
			this.label1.Text = "ENTER MESSAGE HERE";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(304, 160);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(248, 16);
			this.label2.TabIndex = 7;
			this.label2.Text = "RECEIVED TEXT HERE.....";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(16, 200);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(200, 16);
			this.label3.TabIndex = 8;
			this.label3.Text = "SERVER";
			this.label3.Click += new System.EventHandler(this.label3_Click);
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(592, 269);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.label3,
																		  this.label2,
																		  this.label1,
																		  this.txtDisplay,
																		  this.txtInput,
																		  this.button2,
																		  this.txtHost,
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


		/// 
		public void Listen()
		{
			

			TcpListener tcpListener = new TcpListener(2112);
			string text = "<START>";

			do 
			{
				
					tcpListener.Start();
					TcpClient tcpClient = tcpListener.AcceptTcpClient();
   
					NetworkStream ns = tcpClient.GetStream();
			
					byte[] bytes = new byte[tcpClient.ReceiveBufferSize];
					// Read can return anything from 0 to numBytesToRead. 
					// This method blocks until at least one byte is read.
					ns.Read(bytes, 0, (int) tcpClient.ReceiveBufferSize);
             
					// Returns the data received from the host to the console.
					text = Encoding.ASCII.GetString(bytes);
					txtDisplay.Text = text;
		
					ns.Close();

		


					tcpClient.Close();
				
			} while (text.Substring(0,5)!= "<END>");

			tcpListener.Stop();
			txtDisplay.Text = "Everything has finished.";
			
		}

		public void UpdateDisplay(string text)
		{
			txtDisplay.Text = text;
		}

		protected delegate void UpdateDisplayDelegate(string text);

		public void SendMessage(string text)
		{
		try{
			TcpClient tcpClient = new TcpClient(txtHost.Text, 2112);
			NetworkStream networkStream = tcpClient.GetStream();

			Byte[] sendBytes = 	Encoding.ASCII.GetBytes(text);
			networkStream.Write(sendBytes, 0, sendBytes.Length);
			
		}
		catch(SocketException e) {MessageBox.Show("A Socket Error : "+e.ToString());}
		catch(Exception e) {MessageBox.Show(e.ToString());}
		

		}

		private void button1_Click(object sender, System.EventArgs e )
		{
			
			SendMessage(txtInput.Text);

			
			
		
		}

		private void button2_Click(object sender, System.EventArgs e)
		{
			SendMessage("<END>");

		}

		private void label3_Click(object sender, System.EventArgs e)
		{
		
		}

		private void txtHost_TextChanged(object sender, System.EventArgs e)
		{
		
		}
	}
}
