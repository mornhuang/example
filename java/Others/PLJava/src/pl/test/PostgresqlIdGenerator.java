
package pl.test;

import java.sql.Connection;

/**
 * ID generator for the postgres database.
 *
 */
public class PostgresqlIdGenerator extends AbstractHighLowIdGenerator
{
/**
 * PostgresqlIdGenerator constructor.
 */
public PostgresqlIdGenerator()
{
	super();
}
/**
 * Return new high value.
 *
 * @return new high value
 */
protected long getNextHighValue(pl.map.ClassMap classMap) throws pl.PlException
{
	Connection conn = null;
	long highValue = 1;
	try
	{
		conn = classMap.getRelationalDatabase().getConnection();
		java.sql.PreparedStatement pst =
            conn.prepareStatement("SELECT nextval('id generator')");
		java.sql.ResultSet rs = pst.executeQuery();
		if(rs.next())
		{
			highValue = rs.getLong(1);
		}
		rs.close();
		pst.close();
		conn.commit();
		classMap.getRelationalDatabase().freeConnection(conn);
	}
	catch (Throwable e)
	{
		throw new pl.PlException(e);
	}
	highValue <<= 16;
	highValue &= 0xffffffffffff0000L;
	return highValue;
}
}

