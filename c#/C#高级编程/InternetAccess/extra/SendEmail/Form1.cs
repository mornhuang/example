using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

using System.Web.Mail;

namespace SendEmail
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.TextBox textBox1;
		private System.Windows.Forms.TextBox textBox2;
		private System.Windows.Forms.RichTextBox richTextBox1;
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label label3;
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
			this.textBox1 = new System.Windows.Forms.TextBox();
			this.textBox2 = new System.Windows.Forms.TextBox();
			this.richTextBox1 = new System.Windows.Forms.RichTextBox();
			this.button1 = new System.Windows.Forms.Button();
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.SuspendLayout();
			// 
			// textBox1
			// 
			this.textBox1.Location = new System.Drawing.Point(168, 24);
			this.textBox1.Name = "textBox1";
			this.textBox1.Size = new System.Drawing.Size(216, 20);
			this.textBox1.TabIndex = 0;
			this.textBox1.Text = "someone@somewhere.com";
			// 
			// textBox2
			// 
			this.textBox2.Location = new System.Drawing.Point(168, 64);
			this.textBox2.Name = "textBox2";
			this.textBox2.Size = new System.Drawing.Size(216, 20);
			this.textBox2.TabIndex = 1;
			this.textBox2.Text = "Subject";
			// 
			// richTextBox1
			// 
			this.richTextBox1.Location = new System.Drawing.Point(16, 128);
			this.richTextBox1.Name = "richTextBox1";
			this.richTextBox1.Size = new System.Drawing.Size(368, 208);
			this.richTextBox1.TabIndex = 2;
			this.richTextBox1.Text = "From the C# Email Test Program";
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(40, 344);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(312, 40);
			this.button1.TabIndex = 3;
			this.button1.Text = "Send Mail";
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(48, 24);
			this.label1.Name = "label1";
			this.label1.TabIndex = 4;
			this.label1.Text = "To:";
			this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(56, 64);
			this.label2.Name = "label2";
			this.label2.TabIndex = 5;
			this.label2.Text = "Subject:";
			this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(16, 96);
			this.label3.Name = "label3";
			this.label3.TabIndex = 6;
			this.label3.Text = "Body:";
			this.label3.TextAlign = System.Drawing.ContentAlignment.BottomCenter;
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(400, 389);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.label3,
																		  this.label2,
																		  this.label1,
																		  this.button1,
																		  this.richTextBox1,
																		  this.textBox2,
																		  this.textBox1});
			this.Name = "Form1";
			this.Text = "SendEmail";
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

			/* This example illstrates sending email from C# with the MailMessage class
			 * 
			 * The properties of your email are set from the properties of the MailMessage object
			 * (To, Subject, Body, From and many more)
			 * 
			 * 
			 * The static Send() method of the SmtpMail class sends the message
			 * 
			 * */
             
			 
			MailMessage theMessage = new MailMessage();
			
			// Insert the from address here
			theMessage.From = @"somebody@somewhere.com";
			
			// Set the message properties from the form
			theMessage.To = this.textBox1.Text;
			theMessage.Subject = this.textBox2.Text;
			theMessage.Body = this.richTextBox1.Text;
			
			// Insert your mail server here
			SmtpMail.SmtpServer = "MAILSERVER";
			
			// Send the message!
			SmtpMail.Send(theMessage);


		}
	}
}
