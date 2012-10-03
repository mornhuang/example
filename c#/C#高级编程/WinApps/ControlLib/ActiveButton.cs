using System;
using System.Collections;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Windows.Forms;

namespace ControlLib
{
	/// <summary>
	/// Summary description for ActiveButton.
	/// </summary>
	public class ActiveButton : System.Windows.Forms.Button
	{
		private bool m_bActive;
		private int m_nSteps;
		private System.Windows.Forms.Timer timer1;
		
		private System.ComponentModel.IContainer components;

		public ActiveButton()
		{
			// This call is required by the Windows.Forms Form Designer.
			InitializeComponent();
			

			m_nSteps = 0;
			m_bActive = false;

			// the next line is to enable the timer if you have not done so from
			// its properties!

			this.timer1.Enabled = true;

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
			this.components = new System.ComponentModel.Container();
			this.timer1 = new System.Windows.Forms.Timer(this.components);
			// 
			// timer1
			// 
			this.timer1.Enabled = true;
			this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
			// 
			// ActiveButton
			// 
			this.MouseEnter += new System.EventHandler(this.ActiveButton_MouseEnter);
			this.MouseLeave += new System.EventHandler(this.ActiveButton_MouseLeave);

		}
		#endregion

		private void ActiveButton_MouseEnter(object sender, System.EventArgs e)
		{
			m_bActive = true;
			timer1.Interval = 50;
		}

		private void ActiveButton_MouseLeave(object sender, System.EventArgs e)
		{
			m_bActive = false;
			timer1.Interval = 150;
		}

		

		private void timer1_Tick(object sender, System.EventArgs e)
		{
			
			if(m_bActive && m_nSteps != 10)
			{
				try
				{
					BackColor = Color.FromArgb(BackColor.R - 10, BackColor.G - 10, BackColor.B - 10);
				}
				catch(Exception)
				{
				}
				finally
				{
					m_nSteps++;
				}
			}
			else if (!m_bActive && m_nSteps > 0)
			{
				try
				{
					BackColor = Color.FromArgb(BackColor.R + 10, BackColor.G + 10, BackColor.B + 10);
				}
				catch(Exception)
				{
				}
				finally
				{
					m_nSteps--;
				}
			}
		}

		
	}
}
