package pl.test;

import pl.sql.*;

/**
 * This class represents abstract ID generator
 * using high/low values.
 *
 * @author: Artem Rudoy
 */
public abstract class AbstractHighLowIdGenerator implements IdGenerator
{
	private long currentLowValue = 0;
	private long currentHighVaue = -1;
/**
 * AbstractHighLowIdGenerator constructor comment.
 */
public AbstractHighLowIdGenerator() {
	super();
}
/**
 * Return new ID.
 *
 * @return new ID
 */
public java.lang.Object getNewId(pl.map.ClassMap classMap) throws pl.PlException
{
	currentLowValue++;
	if (currentHighVaue < 0 || currentLowValue > 0xffff)
	{
		currentLowValue = 0;
		currentHighVaue = getNextHighValue(classMap);
	}
	return new Long(currentHighVaue | currentLowValue);
}
/**
 * Return next high value.
 *
 * @return next high value
 */
protected abstract long getNextHighValue(pl.map.ClassMap classMap) throws pl.PlException;
/**
 * init method comment.
 */
public void init(java.util.Properties properties) throws pl.PlException
{
}
}
