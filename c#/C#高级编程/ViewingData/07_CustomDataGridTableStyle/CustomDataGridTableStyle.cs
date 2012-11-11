using System;
using System.Windows.Forms;
using System.Data;
using System.Data.SqlClient;

///	<summary>
///	This class provides	an example of creating and using a data	grid.
///	</summary>
public class CustomDataGridTableStyle : System.Windows.Forms.Form
{
	private System.Windows.Forms.Button		retrieveButton ;
	private	System.Windows.Forms.DataGrid	dataGrid ;

	///	<summary>
	///	Construct the window.
	///	</summary>
	///	<remarks>
	///	This method	constructs the window by creating both the data	grid and the button.
	///	</remarks>
	public CustomDataGridTableStyle (	)
	{
		this.AutoScaleBaseSize = new System.Drawing.Size (5, 13);
		this.ClientSize	= new System.Drawing.Size (464,	253);
		this.Text =	"07_CustomDataGridTableStyle" ;
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

		// Create the data that is to be used
		DataSet		ds = CreateDataSet ( ) ;

		// And create the styles...
		CreateStyles ( dataGrid ) ;

		// Then show the data
		dataGrid.SetDataBinding ( ds , "Customers" ) ;
	}

	/// <summary>
	/// Create the on screen representation of the data
	/// </summary>
	/// <param name="dg"></param>
	private void CreateStyles ( DataGrid dg )
	{
		// Create a DataGridTableStyle for the customers table
		DataGridTableStyle	style = new DataGridTableStyle ( ) ;

		// Map this style to the Customers DataTable
		style.MappingName = "Customers" ;

		// Setup each alternate row in a different colour
		style.AlternatingBackColor = System.Drawing.Color.Bisque ;

		// Add in a couple of columns.  First the ID column
		DataGridTextBoxColumn customerID = new DataGridTextBoxColumn ( ) ;

		customerID.HeaderText = "Customer ID" ;
		customerID.MappingName = "CustomerID" ;
		customerID.Width = 200 ;

		// Then the Name column
		DataGridTextBoxColumn name = new DataGridTextBoxColumn ( ) ;
		
		name.HeaderText = "Name" ;
		name.MappingName = "CompanyName" ;
		name.Width = 300 ;

		style.GridColumnStyles.AddRange ( new DataGridColumnStyle [] { customerID , name } ) ;
		
		dg.TableStyles.Add ( style ) ;
	}


	/// <summary>
	/// Create a DataSet for the example
	/// </summary>
	/// <returns>The Customers DataSet</returns>
	private DataSet CreateDataSet ( )
	{
		string	customers = "SELECT * FROM Customers";

		SqlConnection	con	= new SqlConnection	( Login.Connection ) ;

		SqlDataAdapter	da	= new SqlDataAdapter ( customers , con ) ;

		DataSet	ds = new DataSet ( ) ;

		da.Fill	( ds , "Customers" ) ;

		return ds ;
	}

	///	<summary>
	///	Display	the	application	window
	///	</summary>
	static void Main() 
	{
		Application.Run(new	CustomDataGridTableStyle());
	}
}