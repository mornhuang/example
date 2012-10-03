using System;
using System.Windows.Forms;
using System.Data;
using System.Data.SqlClient;
using System.Reflection;
/// <summary>
/// Attribute used to name the context menu option
/// </summary>
[AttributeUsage(AttributeTargets.Method,AllowMultiple=false,Inherited=true)]
public class ContextMenuAttribute : System.Attribute
{
  /// <summary>
  /// Construct the attribute
  /// </summary>
  /// <param name="caption">The caption to be shown on the menu</param>
  public ContextMenuAttribute ( string caption )
  {
    Caption = caption ;
    Default = false ;
  }

  /// <summary>
  /// Caption shown on the menu
  /// </summary>
  public readonly string Caption ;

  /// <summary>
  /// Describes the default menu option
  /// </summary>
  public bool Default ;
}

/// <summary>
/// Callback class used by the popup menu
/// </summary>
public class MenuCommand
{
  /// <summary>
  /// Construct a menu command, which wraps the receiver and method
  /// </summary>
  /// <param name="receiver">The receiver of the method</param>
  /// <param name="method">The method to be invoked</param>
  public MenuCommand ( object receiver , MethodInfo method )
  {
    Receiver = receiver ;
    Method = method ;
  }

  /// <summary>
  /// The real delegate called by the menu code
  /// </summary>
  /// <param name="sender">The sender of the event</param>
  /// <param name="e">Any arguments passed through</param>
  public void Execute ( object sender , EventArgs e )
  {
    // Execute the method on the object
    Method.Invoke ( Receiver , new object[] { } ) ;
  }

  public readonly object      Receiver ;
  public readonly MethodInfo  Method ;
}
/// <summary>
/// The base class for all rows which have a context menu
/// </summary>
/// <remarks>
/// Derive from this class each DataRow class that requires a context
/// menu.  The code will automatically select all methods which conform
/// to the signature 'void <name> ( )', and display these on the popup
/// menu.
/// </remarks>
public class ContextDataRow : DataRow
{
  /// <summary>
  /// DataRows are never created directly - you always need a constructor
  /// which takes a DataRowBuilder, and passes this on to the base classes
  /// constructor.
  /// </summary>
  /// <param name="builder">The DataRowBuilder</param>
  public ContextDataRow ( DataRowBuilder builder )
    : base ( builder )
  {
  }

  /// <summary>
  /// Display the popup menu for this row
  /// </summary>
  /// <param name="parent">The parent control</param>
  /// <param name="x">X coordinate of menu</param>
  /// <param name="y">Y coordinate of menu</param>
  public void PopupMenu ( System.Windows.Forms.Control parent , int x , int y )
  {
    // Use reflection to get the list of popup menu commands
    MemberInfo[]  members = this.GetType().FindMembers ( MemberTypes.Method ,
      BindingFlags.Public | BindingFlags.Instance ,
      new System.Reflection.MemberFilter ( Filter ) ,
      null ) ;

    if ( members.Length > 0 )
    {
      // Create a context menu
      ContextMenu   menu = new ContextMenu ( ) ;

      // Now loop through those members and generate the popup menu
      // Note the cast to MethodInfo in the foreach
      foreach ( MethodInfo meth in members )
      {
        // Get the caption for the operation from the ContextMenuAttribute
        ContextMenuAttribute[]  ctx = (ContextMenuAttribute[]) meth.GetCustomAttributes ( typeof ( ContextMenuAttribute ) , true ) ;

        MenuCommand  callback = new MenuCommand ( this , meth ) ;
        MenuItem      item = new MenuItem ( ctx[0].Caption , new EventHandler ( callback.Execute ) ) ;

        item.DefaultItem = ctx[0].Default ;

        menu.MenuItems.Add ( item ) ;
      }

      System.Drawing.Point  pt = new System.Drawing.Point ( x , y ) ;
      menu.Show ( parent , pt ) ;
    }
  }

  /// <summary>
  /// Implementation of MemberFilter delegate
  /// </summary>
  /// <remarks>
  /// This method is called for each method on the object.  Here I test if it's a method
  /// that returns void and takes no parameters.  If those criteria are satisfied, it
  /// is a match and can be called from a context menu.
  /// </remarks>
  /// <param name="member">MemberInfo for the current method</param>
  /// <param name="criteria">Any criteria passed as the last parameter to FindMembers</param>
  private bool Filter ( MemberInfo member , object criteria )
  {
    bool  bInclude = false ;

    // Cast MemberInfo to MethodInfo
    MethodInfo  meth = member as MethodInfo ;

    if ( meth != null )
      if ( meth.ReturnType == typeof ( void ) )
      {
        ParameterInfo[] parms = meth.GetParameters ( ) ;

        if ( parms.Length == 0 )
        {
          // Lastly check if there is a ContextMenuAttribute on the method...
          object[]  atts = meth.GetCustomAttributes(typeof(ContextMenuAttribute), true);

          bInclude = ( atts.Length == 1 ) ;
        }
      }

    return bInclude ;
  }
}

/// <summary>
/// Class which wraps access to the customers table
/// </summary>
public class CustomerTable : DataTable
{
  public CustomerTable ( )
    : base ( "Customers" )
  {
    this.Columns.Add ( "CustomerID" , typeof ( string ) ) ;
    this.Columns.Add ( "CompanyName" , typeof ( string ) ) ;
    this.Columns.Add ( "ContactName" , typeof ( string ) ) ;
  }

  /// <summary>
  /// Return the typeof each row
  /// </summary>
  protected override System.Type GetRowType ( )
  {
    return typeof ( CustomerRow ) ;
  }

  /// <summary>
  /// Create a new row
  /// </summary>
  /// <param name="builder">A DataRowBuilder</param>
  protected override DataRow NewRowFromBuilder ( DataRowBuilder builder )
  {
    return ( DataRow ) new CustomerRow ( builder ) ;
  }
}

/// <summary>
/// Wrap a row in the Customers table
/// </summary>
public class CustomerRow : ContextDataRow
{
  public CustomerRow ( DataRowBuilder builder )
    : base ( builder )
  {
  }

  /// <summary>
  /// Get/Set the CustomerID
  /// </summary>
  public string CustomerID
  {
    get { return ( string ) this["CustomerID"] ; }
    set { this["CustomerID"] = value ; }
  }

  /// <summary>
  /// Get/Set the CompanyName
  /// </summary>
  public string CompanyName
  {
    get { return ( string ) this["CompanyName"] ; }
    set { this["CompanyName"] = value ; }
  }

  /// <summary>
  /// Get/Set the ContactName
  /// </summary>
  public string ContactName
  {
    get { return ( string ) this["ContactName"] ; }
    set { this["ContactName"] = value ; }
  }

  /// <summary>
  /// First menu option - blacklist a customer
  /// </summary>
  [ContextMenu("Blacklist Customer")]
  public void Blacklist ( )
  {
    DialogResult  r = MessageBox.Show ( String.Format ( "Are you sure you wish to blacklist customer {0}" , CustomerID ) , "BlackList" , MessageBoxButtons.YesNo ) ;

    if ( r == DialogResult.Yes )
    {
      // Do something...
    }
  }

  /// <summary>
  /// Menu option to display contact details for a customer
  /// </summary>
  [ContextMenu("Get Contact",Default=true)]
  public void GetContact ( )
  {
    MessageBox.Show ( String.Format ( "Customer {0} contact is {1}" , CompanyName , ContactName ) ) ;
  }
}

/// <summary>
/// Wrap the Northwind orders table
/// </summary>
public class OrderTable : DataTable
{
  /// <summary>
  /// Construct the order table by adding in all columns
  /// </summary>
  public OrderTable ( )
    : base ( "Orders" )
  {
    this.Columns.Add ( "OrderID" , typeof ( int ) ) ;
    this.Columns.Add ( "CustomerID" , typeof ( string ) ) ;
    this.Columns.Add ( "EmployeeID" , typeof ( int ) ) ;
    this.Columns.Add ( "ShippedDate" , typeof ( DateTime ) ) ;
  }

  /// <summary>
  /// Return the typeof each row
  /// </summary>
  protected override System.Type GetRowType ( )
  {
    return typeof ( OrderRow ) ;
  }

  /// <summary>
  /// Create a new row
  /// </summary>
  /// <param name="builder">A DataRowBuilder</param>
  protected override DataRow NewRowFromBuilder ( DataRowBuilder builder )
  {
    return ( DataRow ) new OrderRow ( builder ) ;
  }
}

/// <summary>
/// Wrap a row in the orders table
/// </summary>
public class OrderRow : ContextDataRow
{
  public OrderRow ( DataRowBuilder builder )
    : base ( builder )
  {
  }

  /// <summary>
  /// Get/Set the OrderID
  /// </summary>
  public int OrderID
  {
    get { return ( int ) this["OrderID"] ; }
    set { this["OrderID"] = value ; }
  }

  /// <summary>
  /// Get/Set the CustomerID
  /// </summary>
  public string CustomerID
  {
    get { return ( string ) this["CustomerID"] ; }
    set { this["CustomerID"] = value ; }
  }

  /// <summary>
  /// Get/Set the EmployeeID
  /// </summary>
  public int EmployeeID
  {
    get { return ( int ) this["EmployeeID"] ; }
    set { this["EmployeeID"] = value ; }
  }

  /// <summary>
  /// Get/Set the ShippedDate
  /// </summary>
  public DateTime ShippedDate
  {
    get { return ( DateTime ) this["ShippedDate"] ; }
    set { this["ShippedDate"] = value ; }
  }

  /// <summary>
  /// First menu option - cancel (i.e. delete) the order
  /// </summary>
  [ContextMenu("Cancel Order")]
  public void CancelOrder ( )
  {
    DialogResult  r = MessageBox.Show ( String.Format ( "Are you sure you wish to cancel order {0}" , OrderID ) , "Cancel Order" , MessageBoxButtons.YesNo ) ;

    if ( r == DialogResult.Yes )
      // Delete this row...
      this.Delete();
  }

  /// <summary>
  /// Menu option to ship an order
  /// </summary>
  [ContextMenu("Ship It",Default=true)]
  public void Ship ( )
  {
    // Set the ship date to now
    this.ShippedDate = DateTime.Now ;
  }
}
