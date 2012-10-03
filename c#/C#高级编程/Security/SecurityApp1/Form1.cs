using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.Security;
using System.Security.Permissions;

namespace SecurityApp1
{
   public class Form1 : System.Windows.Forms.Form
   {
      private System.Windows.Forms.Button button1;
      private System.ComponentModel.Container components;

      public Form1()
      {
         InitializeComponent();

         try
         {
            FileIOPermission fileioperm = new FileIOPermission(FileIOPermissionAccess.AllAccess,@"c:\");
            fileioperm.Demand();
            // System.Security.Permissions.
         }
         catch
         {
            button1.Enabled = false;
         }
      }

      protected override void Dispose(bool disposing)
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
         this.SuspendLayout();
         // 
         // button1
         // 
         this.button1.Location = new System.Drawing.Point(48, 8);
         this.button1.Name = "button1";
         this.button1.Size = new System.Drawing.Size(192, 23);
         this.button1.TabIndex = 0;
         this.button1.Text = "Button Requires FileIOPermission";
         // 
         // Form1
         // 
         this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
         this.ClientSize = new System.Drawing.Size(292, 37);
         this.Controls.AddRange(new System.Windows.Forms.Control[] {this.button1});
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
   }
}
