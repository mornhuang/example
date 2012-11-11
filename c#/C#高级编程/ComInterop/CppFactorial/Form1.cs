using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.Reflection;

namespace CppFactorial
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.TextBox textBox1;
		private System.Windows.Forms.TextBox txtOutput;
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
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
			this.txtOutput = new System.Windows.Forms.TextBox();
			this.button1 = new System.Windows.Forms.Button();
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.SuspendLayout();
			// 
			// textBox1
			// 
			this.textBox1.Location = new System.Drawing.Point(120, 16);
			this.textBox1.Name = "textBox1";
			this.textBox1.Size = new System.Drawing.Size(112, 20);
			this.textBox1.TabIndex = 0;
			this.textBox1.Text = "1";
			// 
			// txtOutput
			// 
			this.txtOutput.Location = new System.Drawing.Point(120, 56);
			this.txtOutput.Name = "txtOutput";
			this.txtOutput.Size = new System.Drawing.Size(112, 20);
			this.txtOutput.TabIndex = 1;
			this.txtOutput.Text = "";
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(76, 96);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(120, 24);
			this.button1.TabIndex = 2;
			this.button1.Text = "Calculate Factorial";
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(8, 13);
			this.label1.Name = "label1";
			this.label1.TabIndex = 3;
			this.label1.Text = "Enter Number";
			this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(8, 53);
			this.label2.Name = "label2";
			this.label2.TabIndex = 4;
			this.label2.Text = "Factorial =";
			this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(272, 133);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.label2,
																		  this.label1,
																		  this.button1,
																		  this.txtOutput,
																		  this.textBox1});
			this.Name = "Form1";
			this.Text = "CalculateFactorial";
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
			int number = int.Parse(this.textBox1.Text);
			
			
		
		//      For Early Binding, uncomment the next two lines, and comment the next block
		//	RCWTESTLib.FactorialClass factorial = new RCWTESTLib.FactorialClass();
		//	uint result = factorial.GetFactorial(number);
		
		//      End of Early Binding
		
		
		
		//      For Late Binding, use this section....
		
			Type factorialType;
			factorialType=Type.GetTypeFromProgID("RCWTEST.Factorial");

			object objFactorial;
			objFactorial=Activator.CreateInstance(factorialType);
			
			object[] aryInputArgs = new object[] {number};
		
			object objResult = factorialType.InvokeMember("GetFactorial",
				BindingFlags.InvokeMethod,
				null,
				objFactorial, aryInputArgs);
		
			uint result = (uint)objResult;
	
		//      End of Late Binding
		
			this.txtOutput.Text = result.ToString();

		}
	}
}
