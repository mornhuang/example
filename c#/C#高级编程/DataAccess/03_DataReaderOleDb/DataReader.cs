using System;
using System.Data.OleDb;

public class DataReader
{
  public static int Main(string[] args)
  {
    string        source = "Provider=SQLOLEDB;" + Login.Connection ;

    string        select = "SELECT ContactName,CompanyName FROM Customers" ;

    OleDbConnection conn = new OleDbConnection ( source ) ;

    try
    {
      conn.Open ( ) ;

      OleDbCommand    cmd = new OleDbCommand ( select , conn ) ;

      OleDbDataReader aReader = cmd.ExecuteReader ( ) ;

      while ( aReader.Read ( ) )
        Console.WriteLine ( "'{0}' from {1}" , aReader.GetString(0) , aReader.GetString ( 1 ) ) ;

      aReader.Close ( ) ;

      conn.Close ( ) ;
    }
    catch ( Exception e )
    {
      Console.WriteLine ( e ) ;
      Console.WriteLine ( ) ;
      Console.WriteLine ( "Chances are your database does not have a user" ) ;
      Console.WriteLine ( "called QSUser, or you do not have the NetSDK database installed." ) ;
    }

    return 0;
  }
}
