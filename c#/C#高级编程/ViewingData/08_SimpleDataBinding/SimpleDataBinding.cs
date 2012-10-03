using System;
using System.Windows.Forms;
using System.Data;
using System.Data.SqlClient;

public class SimpleDataBinding : System.Windows.Forms.Form
{
	private System.Windows.Forms.TextBox	textbox ;

	///	<summary>
	///	Construct the window.
	///	</summary>
	///	<remarks>
	///	This method	constructs the window by creating both the data	grid and the button.
	///	</remarks>
	public SimpleDataBinding (	)
	{
		this.AutoScaleBaseSize = new System.Drawing.Size (5, 13);
		this.ClientSize	= new System.Drawing.Size (464,	253);
		this.Text =	"08_SimpleDataBinding" ;

		this.textbox = new System.Windows.Forms.TextBox ( ) ;
		textbox.Location = new	System.Drawing.Point (8, 8);
		textbox.Size = new System.Drawing.Size ( 448 , 20 ) ;
		textbox.Anchor = AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Right ;
		textbox.TabIndex = 0 ;

		this.Controls.Add ( textbox ) ;

		DataSet	ds = CreateDataSet ( ) ;

		textbox.DataBindings.Add ( "Text" , ds.Tables["Products"] , "ProductName" ) ;
	}

	/// <summary>
	/// Create a DataSet for the example
	/// </summary>
	/// <returns>The Products DataSet</returns>
	private DataSet CreateDataSet ( )
	{
		string	source = Login.Connection;
		string	customers = "SELECT * FROM Products";

		SqlConnection	con	= new SqlConnection	( source ) ;

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
		Application.Run(new	SimpleDataBinding());
	}
}