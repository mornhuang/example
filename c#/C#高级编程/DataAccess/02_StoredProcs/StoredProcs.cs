using System;
using System.Data;
using System.Xml;
using System.Data.SqlClient;
using System.Data.OleDb;

/// <summary>
/// Corresponds to section titled 'Calling Stored Procedures' in Chapter 9
/// </summary>
public class StoredProcs
{
	/// <summary>
	/// StoredProcs - show SQL & Stored Procs
	/// </summary>
	public static void Main ( )
	{
		// The following is the database connection string
		string source = Login.Connection;

		// Create & open the database connection
		using ( SqlConnection	conn = new SqlConnection ( source ) )
		{
			conn.Open ( ) ;

			// Generate the update command
			SqlCommand		updateCommand = GenerateUpdateCommand ( conn ) ;

			// Generate the delete command
			SqlCommand		deleteCommand = GenerateDeleteCommand ( conn ) ;

			// And the insert command
			SqlCommand		insertCommand = GenerateInsertCommand ( conn ) ;

			DumpRegions ( conn , "Regions prior to any stored procedure calls" ) ;

			// Insert a new region.
			// First set the @RegionDescription parameter to the new value to insert
			insertCommand.Parameters["@RegionDescription"].Value = "South West" ;

			// Then execute the command
			insertCommand.ExecuteNonQuery() ;

			// And then get the value returned from the stored proc
			int	newRegionID = (int)insertCommand.Parameters["@RegionID"].Value ;

			DumpRegions ( conn , "Regions after inserting 'South West'" ) ;

			// Update the new region...
			updateCommand.Parameters[0].Value = newRegionID ;
			updateCommand.Parameters[1].Value = "South Western England" ;
			updateCommand.ExecuteNonQuery ( ) ;

			DumpRegions ( conn , "Regions after updating 'South West' to 'South Western England'" ) ;

			// Delete the newly created record
			deleteCommand.Parameters["@RegionID"].Value= newRegionID ;
			deleteCommand.ExecuteNonQuery ( ) ;

			DumpRegions ( conn , "Regions after deleting 'South Western England'" ) ; 

			conn.Close ( ) ;
		}
	}

	/// <summary>
	/// Create a command that will update a region record
	/// </summary>
	/// <param name="conn">The database connection</param>
	/// <returns>A SqlCommand</returns>
	private static SqlCommand GenerateUpdateCommand ( SqlConnection conn )
	{
		SqlCommand  aCommand = new SqlCommand ( "RegionUpdate" , conn ) ;

		aCommand.CommandType = CommandType.StoredProcedure ;
		aCommand.Parameters.Add ( new SqlParameter ( "@RegionID" , SqlDbType.Int , 0 , "RegionID" ) ) ;
		aCommand.Parameters.Add ( new SqlParameter ( "@RegionDescription" , SqlDbType.NChar , 50 , "RegionDescription" ) ) ;
		aCommand.UpdatedRowSource = UpdateRowSource.None ;

		return aCommand ;
	}

	/// <summary>
	/// Create a command that will insert a region record
	/// </summary>
	/// <param name="conn">The database connection</param>
	/// <returns>A SqlCommand</returns>
	private static SqlCommand GenerateInsertCommand ( SqlConnection conn )
	{
		SqlCommand  aCommand = new SqlCommand ( "RegionInsert" , conn ) ;

		aCommand.CommandType = CommandType.StoredProcedure ;
		aCommand.Parameters.Add ( new SqlParameter ( "@RegionDescription" , SqlDbType.NChar , 50 , "RegionDescription" ) ) ;
		aCommand.Parameters.Add ( new SqlParameter ( "@RegionID" , SqlDbType.Int, 0 , ParameterDirection.Output ,
			false , 0 , 0 , "RegionID" , DataRowVersion.Default , null ) ) ;
		aCommand.UpdatedRowSource = UpdateRowSource.OutputParameters ;

		return aCommand ;
	}

	/// <summary>
	/// Create a command that will delete a region record
	/// </summary>
	/// <param name="conn">The database connection</param>
	/// <returns>A SqlCommand</returns>
	private static SqlCommand GenerateDeleteCommand ( SqlConnection conn )
	{
		SqlCommand  aCommand = new SqlCommand ( "RegionDelete" , conn ) ;

		aCommand.CommandType = CommandType.StoredProcedure ;
		aCommand.Parameters.Add ( new SqlParameter ( "@RegionID" , SqlDbType.Int , 0 , "RegionID" ) ) ;
		aCommand.UpdatedRowSource = UpdateRowSource.None ;

		return aCommand ;
	}

	/// <summary>
	/// Dump out the region records within the database
	/// </summary>
	/// <param name="conn">Database Connection</param>
	/// <param name="message">A brief message to display</param>
	private static void DumpRegions ( SqlConnection conn , string message )
	{
		SqlCommand	aCommand = new SqlCommand ( "SELECT RegionID , RegionDescription From Region" , conn ) ;

		// Note the use of CommandBehaviour.KeyInfo.
		// If this is not set, the default seems to be CommandBehavior.CloseConnection,
		// which is an odd default if there ever was one.  Oh well.
		SqlDataReader	aReader = aCommand.ExecuteReader ( CommandBehavior.KeyInfo ) ;

		Console.WriteLine ( message ) ;

		while ( aReader . Read ( ) )
		{
			Console.WriteLine ( "  {0,-20} {1,-40}" , aReader[0] , aReader[1] ) ;
		}

		aReader.Close ( ) ;
	}

}