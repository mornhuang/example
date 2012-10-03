using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.ServiceProcess;

namespace Wrox.ProCSharp.WinServices
{
    /// <summary>
    ///    Summary description for Form1.
    /// </summary>
    public class ServiceControlForm : System.Windows.Forms.Form
    {
        /// <summary>
        ///    Required designer variable.
        /// </summary>
        private System.ComponentModel.Container components;
        private System.Windows.Forms.Button buttonRefresh;
        private System.Windows.Forms.Button buttonExit;
        private System.Windows.Forms.Button buttonContinue;
        private System.Windows.Forms.Button buttonPause;
        private System.Windows.Forms.Button buttonStop;
        private System.Windows.Forms.Button buttonStart;
        private System.Windows.Forms.TextBox textBoxServiceName;
        private System.Windows.Forms.TextBox textBoxServiceType;
        private System.Windows.Forms.TextBox textBoxServiceStatus;
        private System.Windows.Forms.TextBox textBoxDisplayName;
        private System.Windows.Forms.ListBox listBoxServices;
        private System.ServiceProcess.ServiceController[] services;

        public ServiceControlForm()
        {
            //
            // Required for Windows Form Designer support
            //
            InitializeComponent();

            RefreshServiceList();
        }

        protected void RefreshServiceList()
        {
           services = ServiceController.GetServices();

           listBoxServices.DisplayMember = "DisplayName";
           listBoxServices.DataSource = services;      
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

        #region Windows Form Designer generated code
        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
			this.buttonContinue = new System.Windows.Forms.Button();
			this.buttonStart = new System.Windows.Forms.Button();
			this.buttonExit = new System.Windows.Forms.Button();
			this.listBoxServices = new System.Windows.Forms.ListBox();
			this.textBoxServiceType = new System.Windows.Forms.TextBox();
			this.textBoxServiceName = new System.Windows.Forms.TextBox();
			this.textBoxDisplayName = new System.Windows.Forms.TextBox();
			this.buttonRefresh = new System.Windows.Forms.Button();
			this.textBoxServiceStatus = new System.Windows.Forms.TextBox();
			this.buttonPause = new System.Windows.Forms.Button();
			this.buttonStop = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// buttonContinue
			// 
			this.buttonContinue.Enabled = false;
			this.buttonContinue.Location = new System.Drawing.Point(408, 160);
			this.buttonContinue.Name = "buttonContinue";
			this.buttonContinue.Size = new System.Drawing.Size(96, 24);
			this.buttonContinue.TabIndex = 8;
			this.buttonContinue.Text = "Continue";
			this.buttonContinue.Click += new System.EventHandler(this.buttonCommand_Click);
			// 
			// buttonStart
			// 
			this.buttonStart.Enabled = false;
			this.buttonStart.Location = new System.Drawing.Point(296, 120);
			this.buttonStart.Name = "buttonStart";
			this.buttonStart.Size = new System.Drawing.Size(96, 24);
			this.buttonStart.TabIndex = 5;
			this.buttonStart.Text = "Start";
			this.buttonStart.Click += new System.EventHandler(this.buttonCommand_Click);
			// 
			// buttonExit
			// 
			this.buttonExit.Location = new System.Drawing.Point(408, 224);
			this.buttonExit.Name = "buttonExit";
			this.buttonExit.Size = new System.Drawing.Size(96, 24);
			this.buttonExit.TabIndex = 10;
			this.buttonExit.Text = "Exit";
			this.buttonExit.Click += new System.EventHandler(this.buttonExit_Click);
			// 
			// listBoxServices
			// 
			this.listBoxServices.Location = new System.Drawing.Point(16, 16);
			this.listBoxServices.Name = "listBoxServices";
			this.listBoxServices.Size = new System.Drawing.Size(256, 238);
			this.listBoxServices.TabIndex = 0;
			this.listBoxServices.SelectedIndexChanged += new System.EventHandler(this.OnSelectedIndexChanged);
			// 
			// textBoxServiceType
			// 
			this.textBoxServiceType.Location = new System.Drawing.Point(296, 64);
			this.textBoxServiceType.Name = "textBoxServiceType";
			this.textBoxServiceType.Size = new System.Drawing.Size(208, 20);
			this.textBoxServiceType.TabIndex = 3;
			this.textBoxServiceType.Text = "";
			// 
			// textBoxServiceName
			// 
			this.textBoxServiceName.Location = new System.Drawing.Point(296, 88);
			this.textBoxServiceName.Name = "textBoxServiceName";
			this.textBoxServiceName.Size = new System.Drawing.Size(208, 20);
			this.textBoxServiceName.TabIndex = 4;
			this.textBoxServiceName.Text = "";
			// 
			// textBoxDisplayName
			// 
			this.textBoxDisplayName.Location = new System.Drawing.Point(296, 16);
			this.textBoxDisplayName.Name = "textBoxDisplayName";
			this.textBoxDisplayName.Size = new System.Drawing.Size(208, 20);
			this.textBoxDisplayName.TabIndex = 1;
			this.textBoxDisplayName.Text = "";
			// 
			// buttonRefresh
			// 
			this.buttonRefresh.Location = new System.Drawing.Point(296, 224);
			this.buttonRefresh.Name = "buttonRefresh";
			this.buttonRefresh.Size = new System.Drawing.Size(96, 24);
			this.buttonRefresh.TabIndex = 9;
			this.buttonRefresh.Text = "Refresh";
			this.buttonRefresh.Click += new System.EventHandler(this.buttonRefresh_Click);
			// 
			// textBoxServiceStatus
			// 
			this.textBoxServiceStatus.Location = new System.Drawing.Point(296, 40);
			this.textBoxServiceStatus.Name = "textBoxServiceStatus";
			this.textBoxServiceStatus.Size = new System.Drawing.Size(208, 20);
			this.textBoxServiceStatus.TabIndex = 2;
			this.textBoxServiceStatus.Text = "";
			// 
			// buttonPause
			// 
			this.buttonPause.Enabled = false;
			this.buttonPause.Location = new System.Drawing.Point(296, 160);
			this.buttonPause.Name = "buttonPause";
			this.buttonPause.Size = new System.Drawing.Size(96, 24);
			this.buttonPause.TabIndex = 7;
			this.buttonPause.Text = "Pause";
			this.buttonPause.Click += new System.EventHandler(this.buttonCommand_Click);
			// 
			// buttonStop
			// 
			this.buttonStop.Enabled = false;
			this.buttonStop.Location = new System.Drawing.Point(408, 120);
			this.buttonStop.Name = "buttonStop";
			this.buttonStop.Size = new System.Drawing.Size(96, 24);
			this.buttonStop.TabIndex = 6;
			this.buttonStop.Text = "Stop";
			this.buttonStop.Click += new System.EventHandler(this.buttonCommand_Click);
			// 
			// ServiceControlForm
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(512, 273);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.buttonRefresh,
																		  this.buttonExit,
																		  this.buttonContinue,
																		  this.buttonPause,
																		  this.buttonStop,
																		  this.buttonStart,
																		  this.textBoxServiceName,
																		  this.textBoxServiceType,
																		  this.textBoxServiceStatus,
																		  this.textBoxDisplayName,
																		  this.listBoxServices});
			this.Name = "ServiceControlForm";
			this.Text = "Wrox Service Control";
			this.ResumeLayout(false);

		}
       #endregion

        protected void buttonCommand_Click (object sender, System.EventArgs e)
        {
            Cursor.Current = Cursors.WaitCursor;
            ServiceController controller = (ServiceController)listBoxServices.SelectedItem;

            if (sender == this.buttonStart)
            {
                controller.Start();
                controller.WaitForStatus(ServiceControllerStatus.Running);
            }
            else if (sender == this.buttonStop)
            {
                controller.Stop();
                controller.WaitForStatus(ServiceControllerStatus.Stopped);
            }
            else if (sender == this.buttonPause)
            {
                controller.Pause();
                controller.WaitForStatus(ServiceControllerStatus.Paused);
            }
            else if (sender == this.buttonContinue)
            {
                controller.Continue();
                controller.WaitForStatus(ServiceControllerStatus.Running);
            }

            int index =listBoxServices.SelectedIndex;
            RefreshServiceList();
            listBoxServices.SelectedIndex = index;
            Cursor.Current = Cursors.Default;
        }

        protected void buttonExit_Click (object sender, System.EventArgs e)
        {
            Application.Exit();
        }

        protected void buttonRefresh_Click (object sender, System.EventArgs e)
        {
            RefreshServiceList();
        }

        protected string GetServiceTypeName(ServiceType type)
        {
            string serviceType = "";
            if ((type & ServiceType.InteractiveProcess) != 0)
            {
                serviceType = "Interactive ";
                type -= ServiceType.InteractiveProcess;
            }
            switch (type)
            {
                case ServiceType.Adapter:
                    serviceType += "Adapter";
                    break;
                case ServiceType.FileSystemDriver:
                case ServiceType.KernelDriver:
                case ServiceType.RecognizerDriver:
                    serviceType += "Driver";
                    break;
                case ServiceType.Win32OwnProcess:
                    serviceType += "Win32 Service Process";
                    break;
                case ServiceType.Win32ShareProcess:
                    serviceType += "Win32 Shared Process";
                    break;
                default:
                    serviceType += "unknown type " + type.ToString();
                    break;
            }
            return serviceType;
        }

        protected void SetServiceStatus(ServiceController controller)
        {
            buttonStart.Enabled = true;
            buttonStop.Enabled = true;
            buttonPause.Enabled = true;
            buttonContinue.Enabled = true;

            if (!controller.CanPauseAndContinue)
            {
                buttonPause.Enabled = false;
                buttonContinue.Enabled = false;
            }
            if (!controller.CanStop)
            {
                buttonStop.Enabled = false;
            }

            ServiceControllerStatus status = controller.Status;
            switch (status)
            {
                case ServiceControllerStatus.ContinuePending:
                    textBoxServiceStatus.Text = "Continue Pending";
                    buttonContinue.Enabled = false;
                    break;
                case ServiceControllerStatus.Paused:
                    textBoxServiceStatus.Text = "Paused";
                    buttonPause.Enabled = false;
                    buttonStart.Enabled = false;
                    break;
                case ServiceControllerStatus.PausePending:
                    textBoxServiceStatus.Text = "Pause Pending";
                    buttonPause.Enabled = false;
                    buttonStart.Enabled = false;
                    break;
                case ServiceControllerStatus.StartPending:
                    textBoxServiceStatus.Text = "Start Pending";
                    buttonStart.Enabled = false;                    
                    break;
                case ServiceControllerStatus.Running:
                    textBoxServiceStatus.Text = "Running";
                    buttonStart.Enabled = false;
                    buttonContinue.Enabled = false;
                    break;
                case ServiceControllerStatus.Stopped:
                    textBoxServiceStatus.Text = "Stopped";
                    buttonStop.Enabled = false;
                    break;
                case ServiceControllerStatus.StopPending:
                    textBoxServiceStatus.Text = "Stop Pending";
                    buttonStop.Enabled = false;
                    break;
                default:
                    textBoxServiceStatus.Text = "Unknown status";
                    break;
            }            
        }

        protected void OnSelectedIndexChanged (object sender, System.EventArgs e)
        {
            ServiceController controller = (ServiceController)listBoxServices.SelectedItem;
            textBoxDisplayName.Text = controller.DisplayName;
            textBoxServiceType.Text = GetServiceTypeName(controller.ServiceType);
            textBoxServiceName.Text = controller.ServiceName;

            SetServiceStatus(controller);
        }

        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main(string[] args) 
        {
            Application.Run(new ServiceControlForm());
        }
    }
}
