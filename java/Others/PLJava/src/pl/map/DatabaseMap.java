package pl.map;

/**
 * This class contains information of database map.
 * @author: Artyom Rudoy
 */
public class DatabaseMap
{
	private java.lang.String name = null;
/**
 * Creates DatabaseMap.
 */
public DatabaseMap(String name)
{
	super();

	this.name = name;
}
/**
 * Performs equality test.
 * @return boolean
 * @param obj java.lang.Object
 */
public boolean equals(Object obj)
{
	if(obj == null || obj.getClass() != getClass())
		return false;

	DatabaseMap databaseMap = (DatabaseMap)obj;
	if(name.equals(databaseMap.name))
		return true;
	else
		return false;
}
/**
 * Returns name of the database.
 * @return java.lang.String
 */
public java.lang.String getName()
{
	return name;
}
/**
 * Returns hashcode of the databaseMap.
 * @return int
 */
public int hashCode()
{
	return name.hashCode();
}
}
