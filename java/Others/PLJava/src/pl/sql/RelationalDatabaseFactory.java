package pl.sql;

import java.util.Properties;

import pl.PlException;

/**
 * This class creates RelatinalDatabase instances.
 *
 * @author  Artem Rudoy
 */
public class RelationalDatabaseFactory
{
    public final static RelationalDatabase getRelationalDatabase(
        String className, ClassLoader loader, Properties parameters) throws PlException
    {
        try
        {
            return getRelationalDatabase(Class.forName(className, true, loader), parameters);
        }
        catch(ClassNotFoundException e)
        {
            throw new PlException(e);
        }
    }

    public final static RelationalDatabase getRelationalDatabase(
        Class relationalDatabaseClass, Properties parameters) throws PlException
    {
        try
        {
            RelationalDatabase rd = (RelationalDatabase)relationalDatabaseClass.newInstance();
            rd.init(parameters);
            return rd;
        }
        catch(InstantiationException e)
        {
            throw new PlException(e);
        }
        catch(IllegalAccessException e)
        {
            throw new PlException(e);
        }
    }
}
