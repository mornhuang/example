using System;
using System.Windows.Forms;
using System.Data;
using System.Data.SqlClient;

///	<summary>
///	This class provides	an example of creating and using a data	grid.
///	</summary>
public class DataSourceDataView : System.Windows.Forms.Form
{
	private System.Windows.Forms.Button		retrieveButton ;
	private	System.Windows.Forms.DataGrid	dataGrid ;
	private System.Windows.Forms.DataGrid	dataGrid2;
	private System.Windows.Forms.ComboBox	comboBox1;

	///	<summary>
	///	Construct the window.
	///	</summary>
	///	<remarks>
	///	This method	constructs the window by creating both the data	grid and the button.
	///	</remarks>
	public DataSourceDataView (	)
	{
		/*		this.AutoScaleBaseSize = new System.Drawing.Size (5, 13);
				this.ClientSize	= new System.Drawing.Size (464,	253);
				this.Text =	"04_DataSourceDataView" ;
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
				this.Controls.Add (this.retrieveButton); */

		this.AutoScaleBaseSize = new System.Drawing.Size (5, 13);
		this.ClientSize = new System.Drawing.Size (464, 493);
		this.Text = "04_DataSourceDataView" ;
		this.dataGrid = new System.Windows.Forms.DataGrid ();
		dataGrid.BeginInit ();
		dataGrid.CaptionText = "From Database";
		dataGrid.Font = new System.Drawing.Font ("Tahoma", 8);
		dataGrid.Location = new System.Drawing.Point (8, 8);
		dataGrid.Size = new System.Drawing.Size (448, 208);
		dataGrid.TabIndex = 0;
		dataGrid.Anchor = AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Right ;
		this.Controls.Add (this.dataGrid);
		dataGrid.EndInit ();

		this.retrieveButton = new System.Windows.Forms.Button ();
		retrieveButton.Location = new System.Drawing.Point (8, 224);
		retrieveButton.Size = new System.Drawing.Size (75, 23);
		retrieveButton.TabIndex = 1;
		retrieveButton.Anchor = AnchorStyles.Top | AnchorStyles.Left ;
		retrieveButton.Text = "Retrieve";
		retrieveButton.Click += new System.EventHandler (this.retrieveButton_Click);
		this.Controls.Add (this.retrieveButton);

		this.comboBox1 = new System.Windows.Forms.ComboBox ();
		comboBox1.Location = new System.Drawing.Point (96, 224);
		comboBox1.Size = new System.Drawing.Size (360, 21);
		comboBox1.DropDownStyle = ComboBoxStyle.DropDownList;
		comboBox1.TabIndex = 2;
		comboBox1.Anchor = AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Right ;
		comboBox1.SelectedIndexChanged += new System.EventHandler (this.comboBox1_SelectedIndexChanged);
		comboBox1.Items.AddRange ( new object[8] { "Added" , "CurrentRows", "Deleted", "ModifiedCurrent", "ModifiedOriginal", "None", "OriginalRows", "Unchanged"} ) ;
		this.Controls.Add (this.comboBox1);

		this.dataGrid2 = new System.Windows.Forms.DataGrid ();
		dataGrid2.BeginInit ();
		dataGrid2.Location = new System.Drawing.Point (8, 256);
		dataGrid2.Size = new System.Drawing.Size (448, 232);
		dataGrid2.DataMember = "";
		dataGrid2.CaptionText = "Filtered";
		dataGrid2.Font = new System.Drawing.Font ("Tahoma", 8);
		dataGrid2.TabIndex = 3;
		dataGrid2.Anchor = AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Bottom | AnchorStyles.Right ;
		this.Controls.Add (this.dataGrid2);
		dataGrid2.EndInit();

	}

	protected void comboBox1_SelectedIndexChanged (object sender, System.EventArgs e)
	{
		DataViewRowState  state ;

		switch ( comboBox1.Text )
		{
			case "Added":
				state = DataViewRowState.Added ;
				break ;
			case "CurrentRows":
				state = DataViewRowState.CurrentRows ;
				break ;
			case "Deleted":
				state = DataViewRowState.Deleted ;
				break ;
			case "ModifiedCurrent":
				state = DataViewRowState.ModifiedCurrent ;
				break ;
			case "ModifiedOriginal":
				state = DataViewRowState.ModifiedOriginal ;
				break ;
			case "None":
				state = DataViewRowState.None ;
				break ;
			case "OriginalRows":
				state = DataViewRowState.OriginalRows ;
				break ;
			case "Unchanged":
				state = DataViewRowState.Unchanged ;
				break ;
			default:
				state = DataViewRowState.OriginalRows ;
				break;
		}

		try 
		{
			((DataView) dataGrid2.DataSource).RowStateFilter = state ;
		}
		catch ( Exception ex )
		{
			System.Windows.Forms.MessageBox.Show ( ex.ToString ( ) ) ;

		}
	}

	///	<summary>
	///	Retrieve the data
	///	</summary>
	///	<param name="sender"> </param>
	///	<param name="e"> </param>
	protected void retrieveButton_Click	(object	sender,	System.EventArgs e)
	{
		retrieveButton.Enabled = false ;

		string	select = "SELECT * FROM products" ;

		SqlConnection	con	= new SqlConnection	( Login.Connection ) ;

		SqlDataAdapter	da	= new SqlDataAdapter	( select , con ) ;

		DataSet	ds = new DataSet ( ) ;

		da.Fill	( ds , "Products" ) ;

		dataGrid.SetDataBinding	( ds , "Products" ) ;

		DataView  dv = new DataView ( ds.Tables["Products"] ) ;

		dataGrid2.SetDataBinding ( dv , null ) ;

		comboBox1.Text = "OriginalRows" ;
	}

	///	<summary>
	///	Display	the	application	window
	///	</summary>
	static void Main() 
	{
		Application.Run(new	DataSourceDataView());
	}
}