package pl.map;

/**
 * This class contains information about table map.
 * @author: Artyom Rudoy
 */
public class TableMap
{
	private java.lang.String name = null;
	private DatabaseMap databaseMap = null;
/**
 * Creates TableMap.
 */
public TableMap()
{
	super();
}
/**
 * This method performs equality test.
 * @return boolean
 * @param obj java.lang.Object
 */
public boolean equals(Object obj)
{
	if(obj == null || obj.getClass() != getClass())
		return false;

	TableMap tableMap = (TableMap)obj;
	if(name.equals(tableMap.name) && databaseMap.equals(tableMap.databaseMap))
		return true;
	else
		return false;
}
/**
 * Returns database map for this table.
 * @return pl.map.DatabaseMap
 */
public DatabaseMap getDatabaseMap()
{
	return databaseMap;
}
/**
 * Returns name of this table.
 * @return java.lang.String
 */
public java.lang.String getName()
{
	return name;
}
/**
 * Returns hashcode of the object.
 * @return int
 */
public int hashCode()
{
	return name.hashCode() + databaseMap.hashCode();
}
/**
 * Sets DatabaseMap for this table.
 * @param newDatabaseMap pl.map.DatabaseMap
 */
public void setDatabaseMap(DatabaseMap databaseMap)
{
	this.databaseMap = databaseMap;
}
/**
 * Sets name of this table.
 * @param newName java.lang.String
 */
public void setName(java.lang.String newName)
{
	name = newName;
}
}
