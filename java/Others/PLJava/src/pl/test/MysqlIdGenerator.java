package pl.test;

import java.sql.Connection;

/**
 * ID generator for the MySql database.
 *
 * @author: 
 */
public class MysqlIdGenerator extends AbstractHighLowIdGenerator {
/**
 * MysqlIdGenerator constructor.
 */
public MysqlIdGenerator()
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
	java.sql.Statement st = null;
	try
	{
		conn = classMap.getRelationalDatabase().getConnection();
		st = conn.createStatement();
		st.execute("LOCK TABLES pl_id WRITE");
		java.sql.PreparedStatement pst = conn.prepareStatement("SELECT last_high_value FROM pl_id");
		java.sql.ResultSet rs = pst.executeQuery();
		if (rs.next())
		{
			highValue = rs.getLong(1);
			highValue++;
			java.sql.PreparedStatement tPst = conn.prepareStatement("UPDATE pl_id " +
														   "SET last_high_value=?");
			tPst.setLong(1, highValue);
			tPst.executeUpdate();
			tPst.close();
		}
		else
		{
			java.sql.PreparedStatement tPst = conn.prepareStatement("INSERT INTO pl_id " +
														   "(last_high_value) " +
														   "VALUES (?)");
			tPst.setLong(1, highValue);
			tPst.executeUpdate();
			tPst.close();
		}
		pst.close();
		st.execute("UNLOCK TABLES");
		st.close();
		rs.close();
		classMap.getRelationalDatabase().freeConnection(conn);
	}
	catch (Throwable e)
	{
		if(st != null)
		{
			try { st.execute("UNLOCK TABLES"); } catch(Throwable ex) {}
		}
		throw new pl.PlException(e);
	}
	highValue <<= 16;
	highValue &= 0xffffffffffff0000L;
	return highValue;
}
}
