using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace Wrox.ProCSharp.WinServices
{

	/// <summary>
    ///    Summary description for Form1.
    /// </summary>
    public class EventListener : System.Windows.Forms.Form
    {
        /// <summary>
        ///    Required designer variable.
        /// </summary>
        private System.ComponentModel.Container components = null;
        private System.Diagnostics.EventLog eventLogQuote;
        private System.Windows.Forms.Button buttonExit;
        private System.Windows.Forms.ListBox listBoxEvents;

        public EventListener()
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
        ///    Clean up any resources being used.
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

        /// <summary>
        ///    Required method for Designer support - do not modify
        ///    the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
           this.eventLogQuote = new System.Diagnostics.EventLog();
           this.buttonExit = new System.Windows.Forms.Button();
           this.listBoxEvents = new System.Windows.Forms.ListBox();
           ((System.ComponentModel.ISupportInitialize)(this.eventLogQuote)).BeginInit();
           this.SuspendLayout();
           // 
           // eventLogQuote
           // 
           this.eventLogQuote.EnableRaisingEvents = true;
           this.eventLogQuote.Log = "Application";
           this.eventLogQuote.Source = "QuoteService";
           this.eventLogQuote.SynchronizingObject = this;
           this.eventLogQuote.EntryWritten += new System.Diagnostics.EntryWrittenEventHandler(this.OnEntryWritten);
           // 
           // buttonExit
           // 
           this.buttonExit.Location = new System.Drawing.Point(160, 232);
           this.buttonExit.Name = "buttonExit";
           this.buttonExit.Size = new System.Drawing.Size(88, 24);
           this.buttonExit.TabIndex = 1;
           this.buttonExit.Text = "Exit";
           this.buttonExit.Click += new System.EventHandler(this.buttonExit_Click);
           // 
           // listBoxEvents
           // 
           this.listBoxEvents.Location = new System.Drawing.Point(16, 8);
           this.listBoxEvents.Name = "listBoxEvents";
           this.listBoxEvents.Size = new System.Drawing.Size(368, 212);
           this.listBoxEvents.TabIndex = 0;
           // 
           // EventListener
           // 
           this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
           this.ClientSize = new System.Drawing.Size(400, 273);
           this.Controls.AddRange(new System.Windows.Forms.Control[] {this.buttonExit,
                                                                        this.listBoxEvents});
           this.Name = "EventListener";
           this.Text = "Quote Service Event Listener";
           ((System.ComponentModel.ISupportInitialize)(this.eventLogQuote)).EndInit();
           this.ResumeLayout(false);

        }

        protected void buttonExit_Click (object sender, System.EventArgs e)
        {
            Application.Exit();
        }

        protected void OnEntryWritten (object sender, System.Diagnostics.EntryWrittenEventArgs e)
        {
            DateTime time = e.Entry.TimeGenerated;
            string message = e.Entry.Message;
            listBoxEvents.Items.Add(time + " " + message);
        }

        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main(string[] args) 
        {
            Application.Run(new EventListener());
        }
    }
}
