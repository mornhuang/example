using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.DirectoryServices;
using DS = System.DirectoryServices;
using System.Collections.Specialized;
using System.Text;

namespace UserSearch
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
      private System.Windows.Forms.Label label1;
      private System.Windows.Forms.Label label2;
      private System.Windows.Forms.TextBox textBoxHostname;
      private System.Windows.Forms.TextBox textBoxUsername;
      private System.Windows.Forms.Label label3;
      private System.Windows.Forms.TextBox textBoxPassword;
      private System.Windows.Forms.GroupBox groupBox1;
      private System.Windows.Forms.Label label4;
      private System.Windows.Forms.ListBox listBoxProperties;
      private System.Windows.Forms.Label label5;
      private System.Windows.Forms.Label label6;
      private System.Windows.Forms.Label label7;
      private System.Windows.Forms.Label label8;
      private System.Windows.Forms.Label label9;
      private System.Windows.Forms.TextBox textBoxFilter;
      private System.Windows.Forms.Button buttonLoadProperties;
      private System.Windows.Forms.Button buttonSearch;
      private System.Windows.Forms.TextBox textBoxResults;
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
this.textBoxPassword = new System.Windows.Forms.TextBox();
this.label8 = new System.Windows.Forms.Label();
this.textBoxFilter = new System.Windows.Forms.TextBox();
this.label4 = new System.Windows.Forms.Label();
this.label5 = new System.Windows.Forms.Label();
this.textBoxUsername = new System.Windows.Forms.TextBox();
this.label7 = new System.Windows.Forms.Label();
this.label1 = new System.Windows.Forms.Label();
this.listBoxProperties = new System.Windows.Forms.ListBox();
this.label3 = new System.Windows.Forms.Label();
this.label9 = new System.Windows.Forms.Label();
this.label6 = new System.Windows.Forms.Label();
this.label2 = new System.Windows.Forms.Label();
this.buttonLoadProperties = new System.Windows.Forms.Button();
this.textBoxResults = new System.Windows.Forms.TextBox();
this.groupBox1 = new System.Windows.Forms.GroupBox();
this.buttonSearch = new System.Windows.Forms.Button();
this.textBoxHostname = new System.Windows.Forms.TextBox();
this.groupBox1.SuspendLayout();
this.SuspendLayout();
// 
// textBoxPassword
// 
this.textBoxPassword.Location = new System.Drawing.Point(152, 56);
this.textBoxPassword.Name = "textBoxPassword";
this.textBoxPassword.PasswordChar = '*';
this.textBoxPassword.Size = new System.Drawing.Size(120, 20);
this.textBoxPassword.TabIndex = 1;
this.textBoxPassword.Text = "";
// 
// label8
// 
this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
this.label8.ForeColor = System.Drawing.SystemColors.HotTrack;
this.label8.Location = new System.Drawing.Point(16, 352);
this.label8.Name = "label8";
this.label8.Size = new System.Drawing.Size(200, 23);
this.label8.TabIndex = 7;
this.label8.Text = "4. Enter the LDAP Filter";
// 
// textBoxFilter
// 
this.textBoxFilter.Location = new System.Drawing.Point(128, 384);
this.textBoxFilter.Name = "textBoxFilter";
this.textBoxFilter.Size = new System.Drawing.Size(200, 20);
this.textBoxFilter.TabIndex = 4;
this.textBoxFilter.Text = "(objectClass=user)";
// 
// label4
// 
this.label4.Location = new System.Drawing.Point(16, 384);
this.label4.Name = "label4";
this.label4.Size = new System.Drawing.Size(72, 23);
this.label4.TabIndex = 3;
this.label4.Text = "Filter:";
// 
// label5
// 
this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
this.label5.ForeColor = System.Drawing.SystemColors.HotTrack;
this.label5.Location = new System.Drawing.Point(16, 8);
this.label5.Name = "label5";
this.label5.Size = new System.Drawing.Size(288, 23);
this.label5.TabIndex = 7;
this.label5.Text = "1. Enter Domain Controller and Logon Information";
// 
// textBoxUsername
// 
this.textBoxUsername.Location = new System.Drawing.Point(152, 24);
this.textBoxUsername.Name = "textBoxUsername";
this.textBoxUsername.Size = new System.Drawing.Size(120, 20);
this.textBoxUsername.TabIndex = 1;
this.textBoxUsername.Text = "";
// 
// label7
// 
this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
this.label7.ForeColor = System.Drawing.SystemColors.HotTrack;
this.label7.Location = new System.Drawing.Point(16, 256);
this.label7.Name = "label7";
this.label7.Size = new System.Drawing.Size(104, 64);
this.label7.TabIndex = 7;
this.label7.Text = "3. Choose the Properties to Display";
// 
// label1
// 
this.label1.Location = new System.Drawing.Point(24, 48);
this.label1.Name = "label1";
this.label1.Size = new System.Drawing.Size(144, 24);
this.label1.TabIndex = 0;
this.label1.Text = "Domain Contoller [optional]:";
// 
// listBoxProperties
// 
this.listBoxProperties.Location = new System.Drawing.Point(144, 256);
this.listBoxProperties.Name = "listBoxProperties";
this.listBoxProperties.SelectionMode = System.Windows.Forms.SelectionMode.MultiSimple;
this.listBoxProperties.Size = new System.Drawing.Size(184, 82);
this.listBoxProperties.TabIndex = 6;
// 
// label3
// 
this.label3.Location = new System.Drawing.Point(8, 56);
this.label3.Name = "label3";
this.label3.Size = new System.Drawing.Size(96, 24);
this.label3.TabIndex = 0;
this.label3.Text = "Password:";
// 
// label9
// 
this.label9.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
this.label9.ForeColor = System.Drawing.SystemColors.HotTrack;
this.label9.Location = new System.Drawing.Point(16, 432);
this.label9.Name = "label9";
this.label9.Size = new System.Drawing.Size(128, 23);
this.label9.TabIndex = 7;
this.label9.Text = "5. Start the Search";
// 
// label6
// 
this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
this.label6.ForeColor = System.Drawing.SystemColors.HotTrack;
this.label6.Location = new System.Drawing.Point(16, 192);
this.label6.Name = "label6";
this.label6.Size = new System.Drawing.Size(144, 23);
this.label6.TabIndex = 7;
this.label6.Text = "2. Load the Properties";
// 
// label2
// 
this.label2.Location = new System.Drawing.Point(8, 24);
this.label2.Name = "label2";
this.label2.Size = new System.Drawing.Size(120, 24);
this.label2.TabIndex = 0;
this.label2.Text = "Username:";
// 
// buttonLoadProperties
// 
this.buttonLoadProperties.Location = new System.Drawing.Point(192, 192);
this.buttonLoadProperties.Name = "buttonLoadProperties";
this.buttonLoadProperties.Size = new System.Drawing.Size(112, 32);
this.buttonLoadProperties.TabIndex = 5;
this.buttonLoadProperties.Text = "Load Properties";
this.buttonLoadProperties.Click += new System.EventHandler(this.buttonLoadProperties_Click);
// 
// textBoxResults
// 
this.textBoxResults.Location = new System.Drawing.Point(376, 16);
this.textBoxResults.Multiline = true;
this.textBoxResults.Name = "textBoxResults";
this.textBoxResults.ReadOnly = true;
this.textBoxResults.ScrollBars = System.Windows.Forms.ScrollBars.Both;
this.textBoxResults.Size = new System.Drawing.Size(344, 464);
this.textBoxResults.TabIndex = 9;
this.textBoxResults.Text = "";
// 
// groupBox1
// 
this.groupBox1.Controls.AddRange(new System.Windows.Forms.Control[] {this.textBoxUsername,
            this.textBoxPassword,
            this.label2,
            this.label3});
this.groupBox1.Location = new System.Drawing.Point(24, 80);
this.groupBox1.Name = "groupBox1";
this.groupBox1.Size = new System.Drawing.Size(328, 96);
this.groupBox1.TabIndex = 2;
this.groupBox1.TabStop = false;
this.groupBox1.Text = "Logon [optional]";
// 
// buttonSearch
// 
this.buttonSearch.Location = new System.Drawing.Point(176, 424);
this.buttonSearch.Name = "buttonSearch";
this.buttonSearch.Size = new System.Drawing.Size(160, 64);
this.buttonSearch.TabIndex = 8;
this.buttonSearch.Text = "Search";
this.buttonSearch.Click += new System.EventHandler(this.buttonSearch_Click);
// 
// textBoxHostname
// 
this.textBoxHostname.Location = new System.Drawing.Point(184, 48);
this.textBoxHostname.Name = "textBoxHostname";
this.textBoxHostname.Size = new System.Drawing.Size(160, 20);
this.textBoxHostname.TabIndex = 1;
this.textBoxHostname.Text = "";
// 
// Form1
// 
this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
this.ClientSize = new System.Drawing.Size(736, 517);
this.Controls.AddRange(new System.Windows.Forms.Control[] {this.textBoxResults,
            this.buttonSearch,
            this.label9,
            this.label8,
            this.label7,
            this.label6,
            this.label5,
            this.listBoxProperties,
            this.buttonLoadProperties,
            this.textBoxFilter,
            this.label4,
            this.groupBox1,
            this.textBoxHostname,
            this.label1});
this.Name = "Form1";
this.Text = "User Search";
this.groupBox1.ResumeLayout(false);
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

      private void buttonSearch_Click(object sender, System.EventArgs e)
      {
         try
         {
            FillResult();
         }
         catch (Exception ex)
         {
            MessageBox.Show("Check your input: " + ex.Message);
         }
      }

      private void buttonLoadProperties_Click(object sender, System.EventArgs e)
      {
         try
         {
            SetLogonInformation();
            SetNamingContext();

            SetUserProperties(schemaNamingContext);
         }
         catch (Exception ex)
         {
            MessageBox.Show("Check your inputs! " + ex.Message);
         }
      }

      protected string[] GetSchemaProperties(string schemaNamingContext, string objectType)
      {
         string[] data;
         using (DirectoryEntry de = new DirectoryEntry())
         {
            de.Username = username;
            de.Password = password;
			
            de.Path = "LDAP://" + hostname + "CN=" + objectType + "," + schemaNamingContext;

            DS.PropertyCollection properties = de.Properties;
            DS.PropertyValueCollection values = properties["systemMayContain"];

            data = new String[values.Count];
            values.CopyTo(data, 0);
         }
         return data;
      }

      protected void SetUserProperties(string schemaNamingContext)
      {
         StringCollection properties = new StringCollection();
         string[] data = GetSchemaProperties(schemaNamingContext, "User");
         properties.AddRange(GetSchemaProperties(schemaNamingContext, "Organizational-Person"));
         properties.AddRange(GetSchemaProperties(schemaNamingContext, "Person"));
         properties.AddRange(GetSchemaProperties(schemaNamingContext, "Top"));
        
         listBoxProperties.Items.Clear();
         foreach (string s in properties)
         {
            listBoxProperties.Items.Add(s);
         }
      }

      protected void SetNamingContext()
      {
         using (DirectoryEntry de = new DirectoryEntry())
         {
            string path = "LDAP://" + hostname + "rootDSE";
            de.Username = username;
            de.Password = password;
            de.Path = path;

            schemaNamingContext = de.Properties["schemaNamingContext"][0].ToString();
            defaultNamingContext = de.Properties["defaultNamingContext"][0].ToString();
         }
      }

      protected void SetLogonInformation()
      {
         username = (textBoxUsername.Text == "" ? null : textBoxUsername.Text);
         password = (textBoxPassword.Text == "" ? null : textBoxPassword.Text);
         hostname = textBoxHostname.Text;
         if (hostname != "") hostname += "/";
      }

      protected string[] GetProperties()
      {
         string[] properties = new string[listBoxProperties.SelectedItems.Count];
         int i=0;
         foreach (string s in listBoxProperties.SelectedItems)
         {
            properties[i++] = s;
         }
         return properties;
      }

      protected void FillResult()
      {
         using (DirectoryEntry root = new DirectoryEntry())
         {
            root.Username = username;
            root.Password = password;
            root.Path = "LDAP://" + hostname + defaultNamingContext;

            using (DirectorySearcher searcher = new DirectorySearcher())
            {
               searcher.SearchRoot = root;
               searcher.SearchScope = SearchScope.Subtree;
               searcher.Filter = textBoxFilter.Text;
               searcher.PropertiesToLoad.AddRange(GetProperties());

               SearchResultCollection results = searcher.FindAll();
               StringBuilder summary = new StringBuilder();
               foreach (SearchResult result in results)
               {
                  foreach (string propName in result.Properties.PropertyNames)
                  {
                     foreach (string s in result.Properties[propName])
                     {
                        summary.Append(" " + propName + ": " + s + "\r\n");
                     }
                  }
                  summary.Append("\r\n");
               }
               textBoxResults.Text = summary.ToString();
            }
         }
      }

      private string username;
      private string password;
      private string hostname;
      private string schemaNamingContext;
      private string defaultNamingContext;
	}
}
