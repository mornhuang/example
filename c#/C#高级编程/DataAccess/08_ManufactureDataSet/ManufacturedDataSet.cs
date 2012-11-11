using System;
using System.Data;
using System.Data.SqlClient;

public class ManufacturedDataset
{
	public static void Main ( )
	{
		string		   source = Login.Connection ;
		string         select = "SELECT * FROM Products" ;
		string         sel2   = "SELECT * FROM Categories" ;

		using ( SqlConnection  conn = new SqlConnection ( source ) )
		{
			SqlDataAdapter da = new SqlDataAdapter ( select , conn ) ;

			DataSet       ds = new DataSet ( ) ;

			// Create the products table
			ManufactureProductDataTable ( ds ) ;
		    
			da.Fill ( ds , "Products" ) ;

			foreach ( DataRow row in ds.Tables["Products"].Rows )
				Console.WriteLine ( "'{0}' from {1}" , 
					row[0] ,
					row[1] ) ;

			SqlDataAdapter da2 = new SqlDataAdapter ( sel2 , conn ) ;

			// Now create the category table
			ManufactureCategoryTable ( ds ) ;

			da2.Fill ( ds , "Categories" ) ;

			// And add a foreign key constraint between the products & categories tables
			AddForeignKeyConstraint ( ds ) ;

			conn.Close ( ) ;
		}
	}

	public static void ManufactureProductDataTable ( DataSet ds )
	{
		DataTable   products = new DataTable ( "Products" ) ;

		products.Columns.Add ( new DataColumn ( "ProductID" ,       typeof ( int ) ) ) ;
		products.Columns.Add ( new DataColumn ( "ProductName" ,     typeof ( string ) ) ) ;
		products.Columns.Add ( new DataColumn ( "SupplierID" ,      typeof ( int ) ) ) ;
		products.Columns.Add ( new DataColumn ( "CategoryID" ,      typeof ( int ) ) ) ;
		products.Columns.Add ( new DataColumn ( "QuantityPerUnit" , typeof ( string ) ) ) ;
		products.Columns.Add ( new DataColumn ( "UnitPrice" ,       typeof ( decimal ) ) ) ;
		products.Columns.Add ( new DataColumn ( "UnitsInStock" ,    typeof ( short ) ) ) ;
		products.Columns.Add ( new DataColumn ( "UnitsOnOrder" ,    typeof ( short ) ) ) ;
		products.Columns.Add ( new DataColumn ( "ReorderLevel" ,    typeof ( short ) ) ) ;
		products.Columns.Add ( new DataColumn ( "Discontinued" ,    typeof ( bool ) ) ) ;

		ManufacturePrimaryKey ( products ) ;

		ds.Tables.Add ( products ) ;
	}

	public static void ManufacturePrimaryKey ( DataTable dt )
	{
		DataColumn[]  pk = new DataColumn[1] ;
	    
		pk[0] = dt.Columns["ProductID"] ;

		dt.Constraints.Add ( new UniqueConstraint ( "PK_Products" , pk[0] ) ) ;

		dt.PrimaryKey = pk ;
	    
	}

	public static void ManufactureCategoryTable ( DataSet ds )
	{
		DataTable   categories = new DataTable ( "Categories" ) ;

		categories.Columns.Add ( new DataColumn ( "CategoryID" ,   typeof ( int )    ) ) ;
		categories.Columns.Add ( new DataColumn ( "CategoryName" , typeof ( string ) ) ) ;
		categories.Columns.Add ( new DataColumn ( "Description" ,  typeof ( string ) ) ) ;

		categories.Constraints.Add ( new UniqueConstraint ( "PK_Categories" , categories.Columns["CategoryID"] ) ) ;

		categories.PrimaryKey = new DataColumn[1] { categories.Columns["CategoryID"] } ;

		ds.Tables.Add ( categories ) ;

	}

	public static void AddForeignKeyConstraint ( DataSet ds )
	{
		DataColumn            parent = ds.Tables["Categories"].Columns["CategoryID"] ;
		DataColumn            child  = ds.Tables["Products"].Columns["CategoryID"] ;

		ForeignKeyConstraint  fk = new ForeignKeyConstraint ( "FK_Product_CategoryID" , parent , child ) ;
	    
		fk.UpdateRule = Rule.Cascade ;
		fk.DeleteRule = Rule.SetNull ;

		// Create the constraint
		// If this fails, you have a row in the products table with no associated category
		ds.Tables["Products"].Constraints.Add ( fk ) ;

	}
}
