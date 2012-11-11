using System;
using System.Windows.Forms;

namespace WindowsFormsApp
{
	class MyForm : System.Windows.Forms.Form
	{
		//Data member to hold Button control
		private Button BigButton;

		public MyForm()
		{
			//Set the properties for the Button
			this.BigButton = new Button();
			this.BigButton.Location = new System.Drawing.Point(50, 50);
			this.BigButton.Name = "BigButton";
			this.BigButton.Size = new System.Drawing.Size(100, 100);
			this.BigButton.Text = "Click Me!";
			this.BigButton.Click += new System.EventHandler(this.ClickHandler);

			//Set properties for the Form itself
			this.ClientSize = new System.Drawing.Size(200, 200);
			this.Controls.Add(this.BigButton);
			this.Text = "My Windows Form!";
		}

		static void Main(string[] args)
		{
			MyForm aForm = new MyForm();
			Application.Run(aForm);
		}

		private void ClickHandler(object sender, System.EventArgs e)
		{
			MessageBox.Show("Clicked!","My Windows Form",MessageBoxButtons.OK);
		}
	}
}
