using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.IO;

namespace Wrox.ProCSharp.GdiPlus.CapsEditor
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
		


	  #region constant fields
		private const string standardTitle = "CapsEditor";
		// default text in titlebar
		private const uint margin = 10;
		// horizontal and vertical margin in client area
      #endregion

      #region Member fields
		private ArrayList documentLines = new ArrayList();   // the 'document'
		private uint lineHeight;        // height in pixels of one line
		private Size documentSize;      // how big a client area is needed to 
		// display document
		private uint nLines;            // number of lines in document
		private Font mainFont;          // font used to display all lines
		private Font emptyDocumentFont; // font used to display empty message
		private Brush mainBrush = Brushes.Blue; 
		// brush used to display document text
		private Brush emptyDocumentBrush = Brushes.Red;
		// brush used to display empty document message
		private Point mouseDoubleClickPosition;   
		// location mouse is pointing to when double-clicked
		private OpenFileDialog fileOpenDialog = new OpenFileDialog();
		private System.Windows.Forms.MainMenu mainMenu1;
		private System.Windows.Forms.MenuItem menuFile;
		private System.Windows.Forms.MenuItem menuOpen;
		private System.Windows.Forms.MenuItem menuExit; 
		// standard open file dialog
		private bool documentHasData = false; 
		// set to true if document has some data in it
      #endregion

		public Form1()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
			this.BackColor = Color.White; 
			this.Text = "Caps Editor";
			CreateFonts();
			fileOpenDialog.FileOk += new 
				System.ComponentModel.CancelEventHandler(
				this.OpenFileDialog_FileOk);
			fileOpenDialog.Filter =
				"Text files (*.txt)|*.txt|C# source files (*.cs)|*.cs";
 

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
		private void CreateFonts()
		{
			mainFont = new Font("Arial", 10);
			lineHeight = (uint)mainFont.Height;
			emptyDocumentFont = new Font("Verdana", 13, FontStyle.Bold);
		}


		class TextLineInformation
		{
			public string Text;
			public uint Width;
		}
		protected void OpenFileDialog_FileOk(object Sender, CancelEventArgs e)
		{
			this.LoadFile(fileOpenDialog.FileName);
		}

		protected void menuFileOpen_Click(object sender, EventArgs e)
		{
			fileOpenDialog.ShowDialog();
		}      
      

		protected void menuFileExit_Click(object sender, EventArgs e)
		{
			this.Close();
		}
		private void LoadFile(string FileName)
		{
			StreamReader sr = new StreamReader(FileName);
			string nextLine;
			documentLines.Clear();
			nLines = 0;
			TextLineInformation nextLineInfo;
			while ( (nextLine = sr.ReadLine()) != null)
			{
				nextLineInfo = new TextLineInformation();
				nextLineInfo.Text = nextLine;
				documentLines.Add(nextLineInfo);
				++nLines;
			}
			sr.Close();
			documentHasData = (nLines>0) ? true : false;

			CalculateLineWidths();
			CalculateDocumentSize();

			this.Text = standardTitle + " - " + FileName;
			this.Invalidate();
		}


		private void CalculateLineWidths()
		{
			Graphics dc = this.CreateGraphics();
			foreach (TextLineInformation nextLine in documentLines)
			{
				nextLine.Width = (uint)dc.MeasureString(nextLine.Text, 
					mainFont).Width;
			}
		}

		private void CalculateDocumentSize()
		{
			if (!documentHasData)
			{
				documentSize = new Size(100, 200);
			}
			else
			{
				documentSize.Height = (int)(nLines*lineHeight) + 2*(int)margin;
				uint maxLineLength = 0;
				foreach (TextLineInformation nextWord in documentLines)
				{
					uint tempLineLength = nextWord.Width + 2*margin;
					if (tempLineLength > maxLineLength)
						maxLineLength = tempLineLength;
				}
				documentSize.Width = (int)maxLineLength;
			}
			this.AutoScrollMinSize = documentSize;
		}

		protected override void OnPaint(PaintEventArgs e)
		{
			base.OnPaint(e);
			Graphics dc = e.Graphics;
			int scrollPositionX = this.AutoScrollPosition.X;
			int scrollPositionY = this.AutoScrollPosition.Y;
			dc.TranslateTransform(scrollPositionX, scrollPositionY);

			if (!documentHasData)
			{
				dc.DrawString("<Empty document>", emptyDocumentFont, 
					emptyDocumentBrush, new Point(20,20));
				base.OnPaint(e);
				return;
			}

			// work out which lines are in clipping rectangle
			int minLineInClipRegion = 
				WorldYCoordinateToLineIndex(e.ClipRectangle.Top - 
				scrollPositionY);
			if (minLineInClipRegion == -1)
				minLineInClipRegion = 0;
			int maxLineInClipRegion = 
				WorldYCoordinateToLineIndex(e.ClipRectangle.Bottom - 
				scrollPositionY);
			if (maxLineInClipRegion >= this.documentLines.Count ||
				maxLineInClipRegion == -1)
				maxLineInClipRegion = this.documentLines.Count-1;

			TextLineInformation nextLine;
			for (int i=minLineInClipRegion; i<=maxLineInClipRegion ; i++)
			{
				nextLine = (TextLineInformation)documentLines[i];
				dc.DrawString(nextLine.Text, mainFont, mainBrush, 
					this.LineIndexToWorldCoordinates(i));
			}
		}
		private Point LineIndexToWorldCoordinates(int index)
		{
			Point TopLeftCorner = new Point(
				(int)margin, (int)(lineHeight*index + margin));
			return TopLeftCorner;
		}

		private int WorldYCoordinateToLineIndex(int y)
		{
			if (y < margin)
				return -1;
			return (int)((y-margin)/lineHeight);
		}

		private int WorldCoordinatesToLineIndex(Point position)
		{
			if (!documentHasData)
				return -1;
			if (position.Y < margin || position.X < margin)
				return -1;
			int index = (int)(position.Y-margin)/(int)this.lineHeight;
			// check position isn't below document
			if (index >= documentLines.Count)
				return -1;
			// now check that horizontal position is within this line
			TextLineInformation theLine = 
				(TextLineInformation)documentLines[index];
			if (position.X > margin + theLine.Width)
				return -1;

			// all is OK. We can return answer
			return index;
		}

		private Point LineIndexToPageCoordinates(int index)
		{
			return LineIndexToWorldCoordinates(index) + 
				new Size(AutoScrollPosition);
		}

		private int PageCoordinatesToLineIndex(Point position)
		{
			return WorldCoordinatesToLineIndex(position - new 
				Size(AutoScrollPosition));
		}


		protected override void OnMouseDown(MouseEventArgs e)
		{
			base.OnMouseDown(e);
			this.mouseDoubleClickPosition = new Point(e.X, e.Y);
		}

		
		protected override void OnDoubleClick(EventArgs e)
		{
			int i = PageCoordinatesToLineIndex(this.mouseDoubleClickPosition);
			if (i >= 0)
			{
				TextLineInformation lineToBeChanged = 
					(TextLineInformation)documentLines[i];
				lineToBeChanged.Text = lineToBeChanged.Text.ToUpper();
				Graphics dc = this.CreateGraphics();
				uint newWidth =(uint)dc.MeasureString(lineToBeChanged.Text, 
					mainFont).Width;
				if (newWidth > lineToBeChanged.Width)
					lineToBeChanged.Width = newWidth;
				if (newWidth+2*margin > this.documentSize.Width)
				{
					this.documentSize.Width = (int)newWidth;
					this.AutoScrollMinSize = this.documentSize;
				}
				Rectangle changedRectangle = new Rectangle(
					LineIndexToPageCoordinates(i), 
					new Size((int)newWidth, 
					(int)this.lineHeight));
				this.Invalidate(changedRectangle);
			}
			base.OnDoubleClick(e);
		}


		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.mainMenu1 = new System.Windows.Forms.MainMenu();
			this.menuFile = new System.Windows.Forms.MenuItem();
			this.menuOpen = new System.Windows.Forms.MenuItem();
			this.menuExit = new System.Windows.Forms.MenuItem();
			// 
			// mainMenu1
			// 
			this.mainMenu1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					  this.menuFile});
			// 
			// menuFile
			// 
			this.menuFile.Index = 0;
			this.menuFile.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					 this.menuOpen,
																					 this.menuExit});
			this.menuFile.Text = "File";
			// 
			// menuOpen
			// 
			this.menuOpen.Index = 0;
			this.menuOpen.Text = "Open";
			this.menuOpen.Click += new System.EventHandler(this.menuFileOpen_Click);
			// 
			// menuExit
			// 
			this.menuExit.Index = 1;
			this.menuExit.Text = "Exit";
			this.menuExit.Click += new System.EventHandler(this.menuFileExit_Click);
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(292, 273);
			this.Menu = this.mainMenu1;
			this.Name = "Form1";
			this.Text = "Form1";

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
