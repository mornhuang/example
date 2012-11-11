using System;
using System.Windows.Forms;
using System.Data;
using System.Data.SqlClient;

public class ScrollingDataBinding : System.Windows.Forms.Form
{
	private Button		retrieveButton;
	private TextBox		textName;
	private TextBox		textQuan;
	private TrackBar	trackBar;
	private DataSet		ds;

	///	<summary>
	///	Construct the window.
	///	</summary>
	///	<remarks>
	///	This method	constructs the window by creating both the data	grid and the button.
	///	</remarks>
	public ScrollingDataBinding (	)
	{
		this.AutoScaleBaseSize = new System.Drawing.Size (5, 13);
		this.ClientSize	= new System.Drawing.Size (464,	253);
		this.Text =	"09_ScrollingDataBinding" ;

		this.retrieveButton = new Button();
		retrieveButton.Location = new System.Drawing.Point(4, 4);
		retrieveButton.Size = new System.Drawing.Size(75, 23);
		retrieveButton.TabIndex = 1;
		retrieveButton.Anchor = AnchorStyles.Top | AnchorStyles.Left ;
		retrieveButton.Text = "Retrieve";
		retrieveButton.Click += new System.EventHandler
			(this.retrieveButton_Click);
		this.Controls.Add (this.retrieveButton);

		this.textName = new TextBox();
		textName.Location = new System.Drawing.Point(4, 31);
		textName.Text = "Please click retrieve...";
		textName.TabIndex = 2;
		textName.Anchor = AnchorStyles.Top | AnchorStyles.Left | 
			AnchorStyles.Right ;
		textName.Size = new System.Drawing.Size(456, 20);
		textName.Enabled = false;
		this.Controls.Add(this.textName);

		this.textQuan = new TextBox();
		textQuan.Location = new System.Drawing.Point(4, 55);
		textQuan.Text = "";
		textQuan.TabIndex = 3;
		textQuan.Anchor = AnchorStyles.Top | AnchorStyles.Left | 
			AnchorStyles.Top;
		textQuan.Size = new System.Drawing.Size(456, 20);
		textQuan.Enabled = false;
		this.Controls.Add(this.textQuan);

		this.trackBar = new TrackBar();
		trackBar.BeginInit();
		trackBar.Dock = DockStyle.Bottom ;
		trackBar.Location = new System.Drawing.Point(0, 275);
		trackBar.TabIndex = 4;
		trackBar.Size = new System.Drawing.Size(504, 42);
		trackBar.Scroll += new System.EventHandler(this.trackBar_Scroll);
		trackBar.Enabled = false;
		this.Controls.Add(this.trackBar);

	}

	protected void retrieveButton_Click(object sender, System.EventArgs e)
	{
		retrieveButton.Enabled = false ;

		ds = CreateDataSet ( ) ;

		textName.DataBindings.Add ( "Text" , ds , "Products.ProductName" ) ;
		textQuan.DataBindings.Add ( "Text" , ds , "Products.QuantityPerUnit" ) ;

		trackBar.Minimum = 0 ;
		trackBar.Maximum = this.BindingContext[ds,"Products"].Count - 1 ;

		textName.Enabled = true ;
		textQuan.Enabled = true ;
		trackBar.Enabled = true ;
	}

	protected void trackBar_Scroll ( object sender , System.EventArgs e )
	{
		this.BindingContext[ds,"Products"].Position = trackBar.Value ;
	}

	/// <summary>
	/// Create a DataSet for the example
	/// </summary>
	/// <returns>The Products DataSet</returns>
	private DataSet CreateDataSet ( )
	{
		string	customers = "SELECT * FROM Products";

		SqlConnection	con	= new SqlConnection	( Login.Connection ) ;

		SqlDataAdapter	da	= new SqlDataAdapter ( customers , con ) ;

		DataSet	ds = new DataSet ( ) ;

		da.Fill	( ds , "Products" ) ;

		return ds ;
	}

	///	<summary>
	///	Display	the	application	window
	///	</summary>
	static void Main() 
	{
		Application.Run(new	ScrollingDataBinding());
	}
}