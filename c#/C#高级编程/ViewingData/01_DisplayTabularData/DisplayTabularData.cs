using System;
using System.Windows.Forms;
using System.Data;
using System.Data.SqlClient;

///	<summary>
///	This class provides	an example of creating and using a data	grid.
///	</summary>
public class DisplayTabularData	: System.Windows.Forms.Form
{
	private System.Windows.Forms.Button	retrieveButton ;
	private	System.Windows.Forms.DataGrid	dataGrid ;

	///	<summary>
	///	Construct the window.
	///	</summary>
	///	<remarks>
	///	This method	constructs the window by creating both the data	grid and the button.
	///	</remarks>
	public DisplayTabularData (	)
	{
		this.AutoScaleBaseSize = new System.Drawing.Size (5, 13);
		this.ClientSize	= new System.Drawing.Size (464,	253);
		this.Text =	"01_DisplayTabularData" ;
		this.dataGrid =	new	System.Windows.Forms.DataGrid ();
		dataGrid.BeginInit ();
		dataGrid.Location =	new	System.Drawing.Point (8, 8);
		dataGrid.Size =	new	System.Drawing.Size	(448, 208);
		dataGrid.TabIndex =	0;
		dataGrid.Anchor	= AnchorStyles.Bottom | AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Right ;
		this.Controls.Add (this.dataGrid);
		dataGrid.EndInit ();
		this.retrieveButton	= new System.Windows.Forms.Button ();
		retrieveButton.Location	= new System.Drawing.Point (384, 224);
		retrieveButton.Size	= new System.Drawing.Size (75, 23);
		retrieveButton.TabIndex	= 1;
		retrieveButton.Anchor =	AnchorStyles.Bottom | AnchorStyles.Right ;
		retrieveButton.Text	= "Retrieve";
		retrieveButton.Click +=	new	System.EventHandler	(this.retrieveButton_Click);
		this.Controls.Add (this.retrieveButton);
	}

	///	<summary>
	///	Retrieve the data
	///	</summary>
	///	<param name="sender"> </param>
	///	<param name="e"> </param>
	protected void retrieveButton_Click	(object	sender,	System.EventArgs e)
	{
		retrieveButton.Enabled = false ;

		string	source = Login.Connection ;
		string	select = "SELECT * FROM Customers" ;

		SqlConnection	con	= new SqlConnection	( source ) ;

		SqlDataAdapter	da	= new SqlDataAdapter	( select , con ) ;

		DataSet	ds = new DataSet ( ) ;

		da.Fill	( ds , "Customers" ) ;

		dataGrid.SetDataBinding	( ds , "Customers" ) ;
	}

	///	<summary>
	///	Display	the	application	window
	///	</summary>
	static void Main() 
	{
		Application.Run(new	DisplayTabularData());
	}
}