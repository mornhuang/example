package pl.sql;

import java.sql.Connection;

/**
 * Represents a relational database. Performs connection pooling.
 * Methods getNewConnection() and getNewId() should be implemented by subclasses
 *
 * @author: Artyom Rudoy
 */
public abstract class RelationalDatabase
{
    private java.util.Stack connectionPool = new java.util.Stack();
    private java.lang.String name = null;
    private java.util.Hashtable idGenerators = new java.util.Hashtable();

    /**
     * RelationalDatabase constructor.
     */
    public RelationalDatabase()
    {
        super();
    }

    /**
     * Destroy this database.
     */
    public abstract void destroy();

    /**
     * Return a connection back to the pool.
     *
     * @param conn connection to be returned
     */
    public void freeConnection(Connection conn)
    {
        synchronized(connectionPool)
        {
            connectionPool.push(conn);
        }
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringAnd()
    {
        return "AND";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringAscend()
    {
        return "ASC";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringBetween()
    {
        return null;
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringDelete()
    {
        return "DELETE";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringDescend()
    {
        return "DESC";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringEqualTo()
    {
        return "=";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringForUpdate()
    {
        return "FOR UPDATE";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringFrom()
    {
        return "FROM";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringGroupBy()
    {
        return "GROUP BY";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringHaving()
    {
        return "HAVING";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringIn()
    {
        return "IN";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringInsert()
    {
        return "INSERT INTO";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringIs()
    {
        return "IS";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringLike()
    {
        return "LIKE";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringLimit()
    {
        return "LIMIT";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringNot()
    {
        return "NOT";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringOr()
    {
        return "OR";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringOrderBy()
    {
        return "ORDER BY";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringSelect()
    {
        return "SELECT";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringSet()
    {
        return "SET";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringUpdate()
    {
        return "UPDATE";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringValues()
    {
        return "VALUES";
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getClauseStringWhere()
    {
        return "WHERE";
    }

    /**
     * Returns a connection to this relational database.
     * Connection will be taken from the pool or new connection will be created if the pool is empty.
     *
     * @return java.sql.Connection
     */
    public Connection getConnection() throws pl.PlException
    {
        Connection conn = null;

        synchronized(connectionPool)
        {
            while(!connectionPool.isEmpty() && conn == null)
            {
                conn = (Connection)connectionPool.pop();
                try
                {
                    if(conn.isClosed())
                        conn = null;
                }
                catch(java.sql.SQLException e)
                {
                    conn = null;
                }
            }
        }

        if(conn == null)
            conn = getNewConnection();

        return conn;
    }

    /**
     * Return instance of the ID generator with the
     * specified name.
     *
     * @return pl.sql.IdGenerator
     * @param name name of the ID generator
     */
    public IdGenerator getIdGenerator(String name, java.util.Properties additionalParameters) throws pl.PlException
    {
        IdGeneratorHolder holder = (IdGeneratorHolder)idGenerators.get(name);
        if(holder == null)
            throw new pl.PlException("ID generator with name " + name +
            " was not found in the " + getName() + " database");
        return holder.getInstance(additionalParameters);
    }

    /**
     * Return the name of this database.
     *
     * @return the name of this database
     */
    public java.lang.String getName()
    {
        return name;
    }

    /**
     * Create and returns new connection to this database.
     *
     * @return java.sql.Connection
     */
    protected abstract Connection getNewConnection() throws pl.PlException;

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public String getStringGroupBy()
    {
        return "GROUP BY";
    }

    /**
     * Init this database with the specified parameters.
     *
     * @param properties the properties for the initialisation
     */
    public abstract void init(java.util.Properties parameters) throws pl.PlException;

    /**
     * Put new ID generator class.
     *
     * @param name
     * @param idGeneratorClass
     * @param isSingleInstance
     * @param parameters
     */
    public void putIdGenerator(String name, Class idGeneratorClass, boolean isSingleInstance, java.util.Properties parameters)
    {
        idGenerators.put(name, new IdGeneratorHolder(idGeneratorClass, parameters, isSingleInstance));
    }

    /**
     * Set name of this database
     *
     * @param name new name for this database
     */
    public void setName(java.lang.String name)
    {
        this.name = name;
    }

    public int hashCode()
    {
        return name.hashCode();
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;

        if(!obj.getClass().equals(getClass()))
            return false;

        return name.equals(((RelationalDatabase)obj).name);
    }
}
