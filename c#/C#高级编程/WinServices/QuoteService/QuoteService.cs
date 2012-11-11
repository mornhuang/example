using System;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.ServiceProcess;

namespace Wrox.ProCSharp.WinServices
{
	public class QuoteService : System.ServiceProcess.ServiceBase
	{
		/// <summary> 
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
		private QuoteServer quoteServer;

		public QuoteService()
		{
			// This call is required by the Windows.Forms Component Designer.
			InitializeComponent();

			// TODO: Add any initialization after the InitComponent call
		}

		// The main entry point for the process
		static void Main()
		{
			System.ServiceProcess.ServiceBase[] ServicesToRun;
	
			// More than one user Service may run within the same process. To add
			// another service to this process, change the following line to
			// create a second service object. For example,
			//
			//   ServicesToRun = New System.ServiceProcess.ServiceBase[] {new Service1(), new MySecondUserService()};
			//
			ServicesToRun = new System.ServiceProcess.ServiceBase[] { new QuoteService() };

			System.ServiceProcess.ServiceBase.Run(ServicesToRun);
		}

		/// <summary> 
		/// Required method for Designer support - do not modify 
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			components = new System.ComponentModel.Container();
			// 
			// QuoteService
			// 
			this.CanPauseAndContinue = true;
			this.ServiceName = "QuoteService";

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

		/// <summary>
		/// Set things in motion so your service can do its work.
		/// </summary>
		protected override void OnStart(string[] args)
		{
			quoteServer = new QuoteServer(@"C:\ProCSharp\WinServices\quotes.txt", 5678);
			quoteServer.Start();			
		}
 
		/// <summary>
		/// Stop this service.
		/// </summary>
		protected override void OnStop()
		{
			quoteServer.Stop();
		}

		protected override void OnPause()
		{
			quoteServer.Suspend();
		}

		protected override void OnContinue()
		{
			quoteServer.Resume();
		}

		protected override void OnShutdown()
		{
			OnStop();
		}

		public const int commandRefresh = 128;
		protected override void OnCustomCommand(int command)
		{
			switch (command)
			{
				case commandRefresh:
					quoteServer.RefreshQuotes();
					break;
				default:
					break;
			}
		}
		

	}
}
