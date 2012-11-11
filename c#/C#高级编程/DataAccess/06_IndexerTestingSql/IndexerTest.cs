using System;
using System.Data.SqlClient;

public class SimpleSelect
{
	public static int Main(string[] args)
	{
		string        source = Login.Connection ;

		string        select = "SELECT CategoryID,CategoryName FROM Categories" ;

		using ( SqlConnection conn = new SqlConnection ( source ) )
		{
			conn.Open() ;

			SqlCommand    cmd = new SqlCommand ( select , conn ) ;

			SqlDataReader aReader = cmd.ExecuteReader ( ) ;

			aReader.Read ( ) ;

			// Now time access to the first field in the reader...
			long timeInitial , timeElapsed , iterations , maxIterations = 1000000;

			int   theCategoryID ;

			timeInitial = DateTime.Now.Ticks ;

			for ( iterations = 0 ; iterations < maxIterations ; iterations++ )
				theCategoryID = (int) aReader[0] ;

			timeElapsed = DateTime.Now.Ticks ;    

			Console.WriteLine ( "{0} iterations using numeric indexer : {1}s" , 
				iterations ,
				new TimeSpan ( timeElapsed - timeInitial ) ) ;

			timeInitial = DateTime.Now.Ticks ;

			for ( iterations = 0 ; iterations < maxIterations ; iterations++ )
				theCategoryID = (int) aReader["CategoryID"] ;

			timeElapsed = DateTime.Now.Ticks ;    

			Console.WriteLine ( "{0} iterations using textual indexer : {1}s" , 
				iterations ,
				new TimeSpan ( timeElapsed - timeInitial )  ) ;

			timeInitial = DateTime.Now.Ticks ;

			for ( iterations = 0 ; iterations < maxIterations ; iterations++ )
				theCategoryID = aReader.GetInt32 (0);

			timeElapsed = DateTime.Now.Ticks ;    

			Console.WriteLine ( "{0} iterations using GetInt32(0)     : {1}s" , 
				iterations ,
				new TimeSpan ( timeElapsed - timeInitial )  ) ;

			conn.Close ( ) ;

			return 0;
		}
	}
}
