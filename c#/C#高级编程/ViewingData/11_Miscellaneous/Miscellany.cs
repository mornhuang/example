using System;
using System.Windows.Forms;
using System.Data;
using System.Data.SqlClient;
using System.Reflection;

/// <summary>
/// This class provides an example of creating and using a data grid.
/// </summary>
public class Miscellany : System.Windows.Forms.Form
{
  private Button    retrieveButton ;
  private DataGrid  dataGrid ;

  /// <summary>
  /// Construct the window.
  /// </summary>
  /// <remarks>
  /// This method constructs the window by creating both the data grid and the button.
  /// </remarks>
  public Miscellany ( )
  {
    this.AutoScaleBaseSize = new System.Drawing.Size (5, 13);
    this.ClientSize = new System.Drawing.Size (464, 253);
    this.Text = "11_Miscellany" ;
    this.dataGrid = new DataGrid ();
    dataGrid.BeginInit ();
    dataGrid.Location = new System.Drawing.Point (8, 8);
    dataGrid.Size = new System.Drawing.Size (448, 208);
    dataGrid.TabIndex = 0;
    dataGrid.Anchor = AnchorStyles.Left | AnchorStyles.Right | AnchorStyles.Top | AnchorStyles.Bottom ;
    dataGrid.MouseUp += new MouseEventHandler (this.dataGrid_MouseUp);
    this.Controls.Add (this.dataGrid);
    dataGrid.EndInit ();
    this.retrieveButton = new Button ();
    retrieveButton.Location = new System.Drawing.Point (384, 224);
    retrieveButton.Size = new System.Drawing.Size (75, 23);
    retrieveButton.TabIndex = 1;
    retrieveButton.Anchor = AnchorStyles.Bottom | AnchorStyles.Right;
    retrieveButton.Text = "Retrieve";
    retrieveButton.Click += new System.EventHandler (this.retrieveButton_Click);
    this.Controls.Add (this.retrieveButton);
  }

  protected void dataGrid_MouseUp (object sender, MouseEventArgs e)
  {
    // Perform a hit test
    if ( e.Button == MouseButtons.Right )
    {
      // Find which row the user clicked on, if any
      DataGrid.HitTestInfo  hti = dataGrid.HitTest ( e.X , e.Y ) ;

      // Check if the user hit a cell
      if ( hti.Type == DataGrid.HitTestType.Cell )
      {
        // Find the DataRow that corresponds to the cell the user has clicked upon
        try
        {
          BindingManagerBase  bmb = this.BindingContext[dataGrid.DataSource, dataGrid.DataMember] ;

          bmb.Position = hti.Row ;

          DataRowView   drv = bmb.Current as DataRowView ;

          if ( drv != null )
          {
            ContextDataRow  ctx = drv.Row as ContextDataRow ;

            if ( ctx != null )
              ctx.PopupMenu ( dataGrid , e.X, e.Y ) ;
          }

        }
        catch ( Exception ex )
        {
          MessageBox.Show ( ex.ToString ( ) );
        }
      }
    }
  }

  /// <summary>
  /// Retrieve the data
  /// </summary>
  /// <param name="sender"> </param>
  /// <param name="e"> </param>
  protected void retrieveButton_Click (object sender, System.EventArgs e)
  {
    DataSet ds = this.ConstructDataSet ( ) ;

    dataGrid.SetDataBinding ( ds , "Customers" ) ;
  }

  public DataSet ConstructDataSet ( )
  {
    SqlConnection   con = new SqlConnection ( Login.Connection ) ;

    SqlDataAdapter  cmd = new SqlDataAdapter ( "SELECT OrderID, CustomerID, EmployeeID, NULL as ShippedDate from Orders" , con ) ;

    DataSet ds = new DataSet ( ) ;

    ds.Tables.Add ( new OrderTable ( ) ) ;

    cmd.Fill ( ds , "Orders" ) ;

    cmd = new SqlDataAdapter ( "SELECT CustomerID, CompanyName, ContactName from Customers" , con ) ;

    ds.Tables.Add ( new CustomerTable ( ) ) ;

    cmd.Fill ( ds , "Customers" ) ;

    // Create a relationship between tables
    ds.Relations.Add ( "CustomerOrders" ,
      ds.Tables["Customers"].Columns["CustomerID"] ,
      ds.Tables["Orders"].Columns["CustomerID"] ) ;

    return ds ;
  }

  /// <summary>
  /// Display the application window
  /// </summary>
  /// <param name="args">Command line arguments</param>
  public static void Main(string[] args) 
  {
    Application.Run(new Miscellany());
  }
}
