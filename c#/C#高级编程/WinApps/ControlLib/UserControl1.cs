using System;
using System.Collections;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Windows.Forms;

namespace ControlLib
{
	public enum SelectedOptionEnum
	{
		Amazing,
		Good,
		OK,
		Bad,
		Terrible,
		None
	}

	/// <summary>
	/// Summary description for UserControl1.
	/// </summary>
	public class UserMenu : System.Windows.Forms.UserControl
	{
		private System.Windows.Forms.RadioButton rbtnAmazing;
		private System.Windows.Forms.RadioButton rbtnGood;
		private System.Windows.Forms.RadioButton rbtnOK;
		private System.Windows.Forms.RadioButton rbtnBad;
		private System.Windows.Forms.RadioButton rbtnTerrible;

		public SelectedOptionEnum SelectedOption
		{
			get
			{
				if(rbtnAmazing.Checked)
					return SelectedOptionEnum.Amazing;
				else if(rbtnGood.Checked)
					return SelectedOptionEnum.Good;
				else if(rbtnOK.Checked)
					return SelectedOptionEnum.OK;
				else if(rbtnBad.Checked)
					return SelectedOptionEnum.Bad;
				else if(rbtnTerrible.Checked)
					return SelectedOptionEnum.Terrible;
				else
					return SelectedOptionEnum.None;
			}
			set
			{
				if (value == SelectedOptionEnum.Amazing)
					rbtnAmazing.Checked = true;
				else if (value == SelectedOptionEnum.Good)
					rbtnGood.Checked = true;
				else if (value == SelectedOptionEnum.OK)
					rbtnOK.Checked = true;
				else if (value == SelectedOptionEnum.Bad)
					rbtnBad.Checked = true;
				else if (value == SelectedOptionEnum.Terrible)
					rbtnTerrible.Checked = true;
				else
				{
					rbtnAmazing.Checked = false;
					rbtnGood.Checked = false;
					rbtnOK.Checked = false;
					rbtnBad.Checked = false;
					rbtnTerrible.Checked = false;
				}
			}
		}

		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public UserMenu()
		{
			// This call is required by the Windows.Forms Form Designer.
			InitializeComponent();

			// TODO: Add any initialization after the InitForm call

		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if( components != null )
					components.Dispose();
			}
			base.Dispose( disposing );
		}

		#region Component Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify 
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.rbtnAmazing = new System.Windows.Forms.RadioButton();
			this.rbtnGood = new System.Windows.Forms.RadioButton();
			this.rbtnOK = new System.Windows.Forms.RadioButton();
			this.rbtnBad = new System.Windows.Forms.RadioButton();
			this.rbtnTerrible = new System.Windows.Forms.RadioButton();
			this.SuspendLayout();
			// 
			// rbtnAmazing
			// 
			this.rbtnAmazing.Location = new System.Drawing.Point(16, 16);
			this.rbtnAmazing.Name = "rbtnAmazing";
			this.rbtnAmazing.TabIndex = 0;
			this.rbtnAmazing.Text = "Amazing";
			// 
			// rbtnGood
			// 
			this.rbtnGood.Location = new System.Drawing.Point(16, 48);
			this.rbtnGood.Name = "rbtnGood";
			this.rbtnGood.TabIndex = 1;
			this.rbtnGood.Text = "Good";
			// 
			// rbtnOK
			// 
			this.rbtnOK.Location = new System.Drawing.Point(16, 80);
			this.rbtnOK.Name = "rbtnOK";
			this.rbtnOK.TabIndex = 2;
			this.rbtnOK.Text = "OK";
			// 
			// rbtnBad
			// 
			this.rbtnBad.Location = new System.Drawing.Point(16, 112);
			this.rbtnBad.Name = "rbtnBad";
			this.rbtnBad.TabIndex = 3;
			this.rbtnBad.Text = "Bad";
			// 
			// rbtnTerrible
			// 
			this.rbtnTerrible.Location = new System.Drawing.Point(16, 144);
			this.rbtnTerrible.Name = "rbtnTerrible";
			this.rbtnTerrible.TabIndex = 4;
			this.rbtnTerrible.Text = "Terrible";
			// 
			// UserMenu
			// 
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.rbtnTerrible,
																		  this.rbtnBad,
																		  this.rbtnOK,
																		  this.rbtnGood,
																		  this.rbtnAmazing});
			this.Name = "UserMenu";
			this.Size = new System.Drawing.Size(136, 184);
			this.ResumeLayout(false);

		}
		#endregion
	}
}
