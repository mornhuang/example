using System;
using System.Data;
using System.Xml;
using System.Data.SqlClient;
using System.Data.OleDb;

/// <summary>
/// Corresponds to section titled 'Executing Commands' in Chapter 11
/// </summary>
public class ExecutingCommands
{
	/// <summary>
	/// SimpleDataAccess - show SQL & Stored Procs
	/// </summary>
	public static void Main ( )
	{
		// The following is the database connection string
                string source = Login.Connection ;

		// First section of code - using a SQL statement to select records
		ExecuteSql ( source ) ;

		// Second section - calling a stored procedure
		ExecuteStoredProc ( source ) ;

		// Third - batch statements
		ExecuteBatch ( source ) ;

		// Fourth - Return XML from SqlServer...
		ExecuteXml ( source ) ;

		// Fifth - full table
		ExecuteFullTable ( source ) ;

	}

	public static void ExecuteSql ( string source )
	{
		// And this is the SQL statement that will be issued
		string select = "SELECT ContactName,CompanyName FROM Customers";

		try
		{
			// Connect to the database...
			using ( SqlConnection conn=new SqlConnection(source) )
			{
				// Open the database connection
				conn.Open ( ) ;

				// Create the SQL command...
				SqlCommand		cmd = new SqlCommand ( select , conn ) ;

				// Construct the data reader
				using ( SqlDataReader	reader = cmd.ExecuteReader ( ) )
				{
					// Output headings...
					Console.WriteLine ( "*** SqlProvider ***" ) ;
					Console.WriteLine ( "Output from direct SQL statement..." ) ;
					Console.WriteLine ( ) ;
					Console.WriteLine ( "CONTACT                        COMPANY" ) ;
					Console.WriteLine ( "---------------------------------------------------------------------" ) ;

					// And iterate through the data
					while ( reader.Read ( ) )
					{
						Console.WriteLine ( "{0,-30} {1}" , reader[0] , reader[1] ) ;
					}

					reader.Close ( ) ;
				}

				conn.Close ( ) ;
			}
		}
		catch ( Exception e )
		{
			Console.WriteLine ( e.ToString( ) ) ;
		}
	}

	public static void ExecuteStoredProc ( string source )
	{
		// Connect to the database...
		using ( SqlConnection	conn = new SqlConnection(source) )
		{
			// Open the database connection
			conn.Open ( ) ;

			// Create the SQL command that links to a stored procedure
			SqlCommand	cmd = new SqlCommand ( "CustOrderHist" , conn ) ;

			// Set the type to stored procedure
			cmd.CommandType = CommandType.StoredProcedure ;

			// And add the parameter to the stored proc...
			cmd.Parameters.Add ( "@CustomerID" , "QUICK" ) ;

			// Construct the data reader
			using ( SqlDataReader	reader = cmd.ExecuteReader ( ) )
			{
				Console.WriteLine ( "" ) ;
				Console.WriteLine ( "*** SqlProvider ***" ) ;
				Console.WriteLine ( "Call NorthWind CustOrderHist stored proc for customer 'QUICK'..." ) ;
				Console.WriteLine ( ) ;
				Console.WriteLine ( "Product Name                       Quantity" ) ;
				Console.WriteLine ( "---------------------------------------------------------------------" ) ;

				// Iterate through the data
				while ( reader.Read ( ) )
				{
					Console.WriteLine ( "{0,-34} {1}" , reader[0] , reader[1] ) ;
				}

				reader.Close ( ) ;

				Console.WriteLine ( ) ;
			}

			// Close the connection
			conn.Close ( ) ;
		}
	}

	protected static void ExecuteFullTable ( string source ) 
	{
		// Connect to the database...
		using ( OleDbConnection	conn = new OleDbConnection("Provider=SQLOLEDB;" + source) )
		{
			// Open the database connection
			conn.Open ( ) ;

			// Create the SQL command that links to a stored procedure
			OleDbCommand	cmd = new OleDbCommand ( "Categories" , conn ) ;

			// Set the type to TableDirect
			cmd.CommandType = CommandType.TableDirect;

			// Construct the data reader
			using ( OleDbDataReader	reader = cmd.ExecuteReader ( ) )
			{
				Console.WriteLine ( "" ) ;
				Console.WriteLine ( "*** OleDbProvider ***" ) ;
				Console.WriteLine ( "Listing all records in Categories table..." ) ;
				Console.WriteLine ( ) ;
				Console.WriteLine ( "ID  Name            Description" ) ;
				Console.WriteLine ( "---------------------------------------------------------------------" ) ;

				// Iterate through the data
				while ( reader.Read ( ) )
				{
					Console.WriteLine ( "{0,-3} {1,-15} {2}" , reader[0] , reader[1], reader[2] ) ;
				}

				reader.Close ( ) ;

				Console.WriteLine ( ) ;
			}

			// Close the connection
			conn.Close ( ) ;
		}
	}

	protected static void ExecuteBatch ( string source )
	{
		string			select = "SELECT COUNT(*) FROM Customers;SELECT COUNT(*) FROM Products";

		// Connect to the database...
		using ( SqlConnection	conn = new SqlConnection(source) )
		{
			// Open the database connection
			conn.Open ( ) ;

			// Create the SQL command...
			SqlCommand		cmd = new SqlCommand ( select , conn ) ;

			// Construct the data reader
			using ( SqlDataReader	reader = cmd.ExecuteReader ( ) )
			{
				// Output headings...
				Console.WriteLine ( "*** SqlProvider ***" ) ;
				Console.WriteLine ( "Output from batched SQL statements" ) ;
				Console.WriteLine ( ) ;

				int		statement = 0 ;

				do
				{
					statement++ ;

				while ( reader.Read ( ) )
				{
					Console.WriteLine ( "Output from batch statement {0} is {1}" , statement , reader[0] ) ;
				}
				} while ( reader.NextResult ( ) ) ;

				reader.Close ( ) ;
			}

			conn.Close ( ) ;
		}
	}

	protected static void ExecuteXml ( string source )
	{
		string			select = "SELECT ContactName,CompanyName FROM Customers FOR XML AUTO";

		using ( SqlConnection	conn = new SqlConnection(source) )
		{
			// Open the database connection
			conn.Open ( ) ;

			// Create the SQL command...
			SqlCommand		cmd = new SqlCommand ( select , conn ) ;

			// Construct an Xml Reader
			XmlReader		xr = cmd.ExecuteXmlReader ( ) ;

			Console.WriteLine ( "" ) ;
			Console.WriteLine ( "*** SqlProvider ***" ) ;
			Console.WriteLine ( "Use ExecuteXmlReader with a FOR XML AUTO SQL clause" ) ;
			Console.WriteLine ( ) ;

			// Do something useful with the xml
			while ( xr.Read() )
			{
				Console.WriteLine ( xr.ReadOuterXml ( ) ) ;
			}

			// And close the connection
			conn.Close ( ) ;
		}
	}
}