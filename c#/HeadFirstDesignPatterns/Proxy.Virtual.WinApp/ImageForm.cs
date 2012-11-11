using System;
using System.Threading;
using System.Net;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;

namespace HeadFirstDesignPatterns.Proxy.Virtual.WinApp
{
	/// <summary>
	/// Summary description for ImageForm.
	/// </summary>
	public class ImageForm : System.Windows.Forms.Form
	{
		#region Members
		private System.Windows.Forms.Button btnLoadImage;
		private System.Windows.Forms.PictureBox picBox;
		private Image image = null;
		private UriBuilder uriBuilder = null;
		private HttpWebRequest httpWebRequest = null;
		private HttpWebResponse httpWebResponse = null;
		private bool retrieving = false;

		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
		#endregion//GetImage

		#region Main
		static void Main(string[] args) 
		{
			Application.Run(new ImageForm());
		} 
		#endregion//Main

		#region Constructor
		public ImageForm()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}
		#endregion//Constructor

		#region Dispose
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
		#endregion//Dispose

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.btnLoadImage = new System.Windows.Forms.Button();
			this.picBox = new System.Windows.Forms.PictureBox();
			this.SuspendLayout();
			// 
			// btnLoadImage
			// 
			this.btnLoadImage.Location = new System.Drawing.Point(16, 8);
			this.btnLoadImage.Name = "btnLoadImage";
			this.btnLoadImage.TabIndex = 0;
			this.btnLoadImage.Text = "Load Image";
			this.btnLoadImage.Click += new System.EventHandler(this.btnLoadImage_Click);
			// 
			// picBox
			// 
			this.picBox.Location = new System.Drawing.Point(24, 40);
			this.picBox.Name = "picBox";
			this.picBox.Size = new System.Drawing.Size(800, 600);
			this.picBox.TabIndex = 1;
			this.picBox.TabStop = false;
			// 
			// ImageForm
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(840, 654);
			this.Controls.Add(this.picBox);
			this.Controls.Add(this.btnLoadImage);
			this.Name = "ImageForm";
			this.Text = "HeadFirst Virtual Proxy ImageForm";
			this.ResumeLayout(false);

		}
		#endregion

		#region btnLoadImage_Click
		private void btnLoadImage_Click(object sender, System.EventArgs e)
		{	
			if(image != null)
			{
				picBox.Width = image.Width;
				picBox.Height = image.Height;
				picBox.Image = image;
			}
			else
			{
				picBox.Image = GetTemporaryImage();

				if(!retrieving)
				{
					retrieving = true; 
					Thread retrievalThread = new Thread(new ThreadStart(GetImage));
					retrievalThread.Start();
				}
			}
		}
		#endregion//btnLoadImage_Click

		#region GetImage
		private void GetImage()
		{
			try
			{
				uriBuilder = new UriBuilder();
				uriBuilder.Scheme = "http";
				uriBuilder.Port = 80;
				uriBuilder.Path = "gallery/press/spirit/20050113a/site_A89_CY_navcam_360_cyp_A-A365R1.jpg";
				uriBuilder.Host = "origin.mars5.jpl.nasa.gov";

				httpWebRequest = (HttpWebRequest)HttpWebRequest.Create(uriBuilder.Uri);
				httpWebRequest.Method = "GET";
				httpWebRequest.UserAgent = "Marks";
//				httpWebRequest.Timeout = 60000;
				httpWebResponse = (HttpWebResponse)httpWebRequest.GetResponse();
				image = Image.FromStream(httpWebResponse.GetResponseStream());
			}
			catch(Exception ex)
			{
				throw new ApplicationException(ex.Message);
			}

//			picBox.Width = image.Width;
//			picBox.Height = image.Height;
			picBox.Width = 800;
			picBox.Height = 600;
			picBox.Image = image;
		}
		#endregion//GetImage

		#region GetTemporaryImage
		private Bitmap GetTemporaryImage()
		{
			Rectangle rectangle = new Rectangle(10,10,700,500);
			Pen pen = new Pen(Color.Black);
			Bitmap bitmap = new Bitmap(800,600);
			Graphics g = Graphics.FromImage(bitmap);
			Font font = new Font("Arial",24);
			SolidBrush brush = new SolidBrush(Color.Black);
			g.DrawRectangle(pen, rectangle);
			g.DrawString("Image Downloading....", font, brush, 200,220);

			return bitmap;
		}
		#endregion//GetTemporaryImage
	}
}
