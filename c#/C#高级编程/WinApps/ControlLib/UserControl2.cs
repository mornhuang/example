using System;
using System.Collections;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Windows.Forms;

namespace ControlLib
{
	public class LoginEventArgs : System.EventArgs
	{
		public LoginEventArgs(string sUserID, string sPassword, bool bValid)
		{
			UserID = sUserID;
			Password = sPassword;
			Valid = bValid;
		}

		public string UserID;
		public string Password;
		public bool Valid;
	}

	//Delegate declarations
	public delegate void GoodLoginEventHandler(object sender, LoginEventArgs e);
	public delegate void FailedThreeTimesEventHandler(object sender, LoginEventArgs e);
	public delegate void CancelEventHandler(object sender, EventArgs e);

	/// <summary>
	/// Summary description for UserControl2.
	/// </summary>
	public class ActiveLogin : System.Windows.Forms.UserControl
	{
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.TextBox txtUserID;
		private System.Windows.Forms.TextBox txtPass;
		private System.Windows.Forms.Button btnLogin;
		private System.Windows.Forms.Button btnCancel;
		/// <summary> 
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		//Event declarations
		public event GoodLoginEventHandler GoodLogin;
		public event FailedThreeTimesEventHandler FailedThreeTimes;
		public event CancelEventHandler Cancel;

		private int m_nLoginTimes;

		public ActiveLogin()
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
				if(components != null)
				{
					components.Dispose();
				}
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
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.txtUserID = new System.Windows.Forms.TextBox();
			this.txtPass = new System.Windows.Forms.TextBox();
			this.btnLogin = new System.Windows.Forms.Button();
			this.btnCancel = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(24, 40);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(56, 23);
			this.label1.TabIndex = 0;
			this.label1.Text = "UserID:";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(16, 72);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(64, 23);
			this.label2.TabIndex = 1;
			this.label2.Text = "Password:";
			// 
			// txtUserID
			// 
			this.txtUserID.Location = new System.Drawing.Point(96, 40);
			this.txtUserID.Name = "txtUserID";
			this.txtUserID.TabIndex = 2;
			this.txtUserID.Text = "";
			// 
			// txtPass
			// 
			this.txtPass.Location = new System.Drawing.Point(96, 72);
			this.txtPass.Name = "txtPass";
			this.txtPass.PasswordChar = '*';
			this.txtPass.TabIndex = 3;
			this.txtPass.Text = "";
			// 
			// btnLogin
			// 
			this.btnLogin.Location = new System.Drawing.Point(184, 112);
			this.btnLogin.Name = "btnLogin";
			this.btnLogin.TabIndex = 4;
			this.btnLogin.Text = "Login";
			this.btnLogin.Click += new System.EventHandler(this.btnLogin_Click);
			// 
			// btnCancel
			// 
			this.btnCancel.Location = new System.Drawing.Point(184, 144);
			this.btnCancel.Name = "btnCancel";
			this.btnCancel.TabIndex = 5;
			this.btnCancel.Text = "Cancel";
			this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
			// 
			// ActiveLogin
			// 
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.btnCancel,
																		  this.btnLogin,
																		  this.txtPass,
																		  this.txtUserID,
																		  this.label2,
																		  this.label1});
			this.Name = "ActiveLogin";
			this.Size = new System.Drawing.Size(280, 192);
			this.ResumeLayout(false);

		}
		#endregion

		protected virtual void OnGoodLogin(LoginEventArgs e)
		{
			if (GoodLogin != null)
			{
				//Invokes the delegates
				GoodLogin(this, e);
			}
		}

		protected virtual void OnFailedThreeTimes(LoginEventArgs e)
		{
			if (FailedThreeTimes != null)
			{
				//Invokes the delegates
				FailedThreeTimes(this, e);
			}
		}

		protected virtual void OnCancel(EventArgs e)
		{
			if (Cancel != null)
			{
				//Invokes the delegates
				Cancel(this, e);
				}
		}

		private void btnLogin_Click(object sender, System.EventArgs e)
		{
			//Increment this attempt
			m_nLoginTimes++;

			//Check userid and password
			if(txtUserID.Text == "Wrox" && txtPass.Text == "Wrox")
			{
				//Successful login
				OnGoodLogin(new LoginEventArgs(txtUserID.Text, txtPass.Text, true));
				m_nLoginTimes = 0;
			}
			else if(m_nLoginTimes >=3)
			{
				//failed three times
				OnFailedThreeTimes(new LoginEventArgs(txtUserID.Text, txtPass.Text, false));
				m_nLoginTimes = 0;
			}
			else
				MessageBox.Show("Invalid Login", "Login", MessageBoxButtons.OK);
		}

		private void btnCancel_Click(object sender, System.EventArgs e)
		{
			//Raise the cancel event
			OnCancel(new EventArgs());
		}


		

	}
}
