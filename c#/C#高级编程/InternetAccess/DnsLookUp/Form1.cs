using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.Net;

namespace Wrox.ProCSharp.InternetAccess.DnsLookUp
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.TextBox txtBoxInput;
		private System.Windows.Forms.TextBox txtBoxHostName;
		private System.Windows.Forms.ListBox listBoxIPs;
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
			this.txtBoxInput = new System.Windows.Forms.TextBox();
			this.txtBoxHostName = new System.Windows.Forms.TextBox();
			this.listBoxIPs = new System.Windows.Forms.ListBox();
			this.SuspendLayout();
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(192, 232);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(96, 32);
			this.button1.TabIndex = 0;
			this.button1.Text = "button1";
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// txtBoxInput
			// 
			this.txtBoxInput.Location = new System.Drawing.Point(8, 16);
			this.txtBoxInput.Name = "txtBoxInput";
			this.txtBoxInput.Size = new System.Drawing.Size(272, 20);
			this.txtBoxInput.TabIndex = 1;
			this.txtBoxInput.Text = "textBox1";
			// 
			// txtBoxHostName
			// 
			this.txtBoxHostName.Location = new System.Drawing.Point(8, 72);
			this.txtBoxHostName.Name = "txtBoxHostName";
			this.txtBoxHostName.Size = new System.Drawing.Size(280, 20);
			this.txtBoxHostName.TabIndex = 2;
			this.txtBoxHostName.Text = "textBox2";
			// 
			// listBoxIPs
			// 
			this.listBoxIPs.Location = new System.Drawing.Point(8, 112);
			this.listBoxIPs.Name = "listBoxIPs";
			this.listBoxIPs.Size = new System.Drawing.Size(272, 108);
			this.listBoxIPs.TabIndex = 3;
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(292, 273);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.listBoxIPs,
																		  this.txtBoxHostName,
																		  this.txtBoxInput,
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

		private void button1_Click(object sender, System.EventArgs e)
		{
			
				try
				{
					IPHostEntry iphost = Dns.Resolve(txtBoxInput.Text);
					foreach (IPAddress ip in iphost.AddressList)
					{
						long ipaddress = ip.Address;
						listBoxIPs.Items.Add(ipaddress);
						listBoxIPs.Items.Add("   " + ip.ToString());
                        
					}
					txtBoxHostName.Text = iphost.HostName;
				}
				catch(Exception ex)
				{
					MessageBox.Show("Unable to process the request because " +
						"the following problem occurred:\n" + 
						ex.Message, "Exception occurred");
				}
			

		}

		private void button2_Click(object sender, System.EventArgs e)
		{
		
		}
	}
}
