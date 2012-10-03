using System;
using System.Data;

public class DataRelationships
{
	public static void Main()
	{
		// Create a dataset, add a couple of tables & a relationship
		DataSet   ds = CreateDataSetWithRelationships ( ) ;

		// Add some data to the tables
		DataRow   aBuilding = ds.Tables["Building"].NewRow() ;

		// Create a building
		aBuilding["BuildingID"] = 1 ;
		aBuilding["Name"] = "The Lowry" ;

		ds.Tables["Building"].Rows.Add ( aBuilding ) ;

		// Add a new room
		DataRow aRoom = ds.Tables["Room"].NewRow ( ) ;

		aRoom["RoomID"] = 1 ;
		aRoom["Name"] = "Reception" ;
		aRoom["BuildingID"] = 1 ;

		ds.Tables["Room"].Rows.Add ( aRoom ) ;

		// And add another room
		aRoom = ds.Tables["Room"].NewRow ( ) ;
		aRoom["RoomID"] = 2 ;
		aRoom["Name"] = "The Modern Art Gallery" ;
		aRoom["BuildingID"] = 1 ;

		ds.Tables["Room"].Rows.Add ( aRoom ) ;

		// Now use the relationships to loop through the data
		foreach ( DataRow theBuilding in ds.Tables["Building"].Rows )
		{
			DataRow[]  children = theBuilding.GetChildRows("Rooms") ;
			int   roomCount = children.Length ;

			Console.WriteLine ( "Building {0} contains {1} room{2}" , 
				theBuilding["Name"] ,
				roomCount ,
				roomCount > 1 ? "s" : "" ) ;

			// Loop through the rooms
			foreach ( DataRow theRoom in children )
				Console.WriteLine ( "Room: {0}" , theRoom["Name"]);
		}

		// And go the other way too...
		foreach ( DataRow theRoom in ds.Tables["Room"].Rows)
		{
			DataRow[]   parents = theRoom.GetParentRows ( "Rooms" ) ;

			foreach ( DataRow theBuilding in parents )
				Console.WriteLine ( "Room {0} is contained in building {1}" , theRoom["Name"] , theBuilding["Name"] ) ;
		}
	}

	public static DataSet CreateDataSetWithRelationships ( )
	{
		DataSet   ds = new DataSet ( "Relationships" ) ;
    
		ds.Tables.Add ( CreateBuildingTable ( ) ) ;
		ds.Tables.Add ( CreateRoomTable ( ) ) ;

		// Create a simple relationship...
		ds.Relations.Add ( "Rooms" , ds.Tables["Building"].Columns["BuildingID"],ds.Tables["Room"].Columns["BuildingID"]) ;

		return ds ;
	}

	public static DataTable CreateBuildingTable ( ) 
	{
		DataTable  aBuilding = new DataTable ( "Building" ) ;
		aBuilding.Columns.Add ( new DataColumn ( "BuildingID" , typeof ( int ) ) ) ;
		aBuilding.Columns.Add ( new DataColumn ( "Name" , typeof ( string ) ) ) ;
		aBuilding.Constraints.Add ( new UniqueConstraint ( "PK_Building" , aBuilding.Columns[0] ) ) ;
		aBuilding.PrimaryKey = new DataColumn[] { aBuilding.Columns[0] } ;

		return aBuilding ;
	}

	public static DataTable CreateRoomTable ( )
	{
		DataTable  aRoom = new DataTable ( "Room" ) ;
		aRoom.Columns.Add ( new DataColumn ( "RoomID" , typeof ( int ) ) ) ;
		aRoom.Columns.Add ( new DataColumn ( "Name" , typeof ( string ) ) ) ;
		aRoom.Columns.Add ( new DataColumn ( "BuildingID" , typeof ( int ) ) ) ;
		aRoom.Constraints.Add ( new UniqueConstraint ( "PK_Room" , aRoom.Columns[0] ) ) ;
		aRoom.PrimaryKey = new DataColumn[] { aRoom.Columns[0] } ;

		return aRoom ;
	}
}
