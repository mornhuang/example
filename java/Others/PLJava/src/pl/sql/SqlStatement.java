package pl.sql;

import java.util.*;
import java.sql.*;

import pl.*;

/**
 * This class contains an information about sql statement.
 *
 * @author: Artyom Rudoy
 */
public class SqlStatement implements Cloneable
{
    private StringBuffer sqlString = new StringBuffer();
    private Vector parameters = new Vector();
    private java.sql.Statement statement = null;

    /**
     * Creates SqlStatement.
     */
    public SqlStatement()
    {
        super();
    }

    /**
     * Adds a parameter for this statement.
     *
     * @param obj a parameter
     */
    public void addParameter(Object obj, int plType)
    {
        parameters.add(new Parameter(obj, plType));
    }

    /**
     * Adds an sql clause to this statement.
     *
     * @param sqlClause an sql clause
     */
    public void addSqlClause(String sqlClause)
    {
        sqlString.append(sqlClause);
    }

    /**
     * Adds an sql statement to this statement.
     *
     * @param statement a statement to add
     */
    public void addSqlStatement(SqlStatement statement)
    {
        addSqlClause(statement.getSqlString());
        for(int i = 0; i < statement.getSize(); i++)
        {
            parameters.add(statement.parameters.get(i));
        }
    }

    /**
     * Creates a copy of this sql statement.
     *
     * @return java.lang.Object
     */
    public Object clone()
    {
        try
        {
            SqlStatement cloneObject = (SqlStatement)super.clone();
            cloneObject.parameters = (Vector)parameters.clone();
            cloneObject.statement = null;
            return cloneObject;
        }
        catch(CloneNotSupportedException e)
        {
            return null;
        }
    }

    /**
     * Releases resources of the corresponding java.sql.Statement object.
     */
    public void close() throws PlException
    {
        if(statement != null)
        {
            try
            {
                statement.close();
                statement = null;
            }
            catch(SQLException e)
            {
                throw new PlException(e);
            }
        }
    }

    /**
     * Insert the method's description here.
     * Creation date: (09.12.2000 23:32:07)
     * @exception java.lang.Throwable The exception description.
     */
    public void finalize() throws java.lang.Throwable
    {
        close();
    }

    /**
     * Returns a parameter with the given index.
     *
     * @return java.lang.Object
     * @param index index of the parameter
     */
    public Parameter getParameter(int index)
    {
        return (Parameter)parameters.get(index);
    }

    /**
     * Returns number of parameters.
     *
     * @return int
     */
    public int getSize()
    {
        return parameters.size();
    }

    /**
     * Returns the sql string for this statement.
     *
     * @return java.lang.String
     */
    public String getSqlString()
    {
        return sqlString.toString();
    }

    /**
     * Returns corresponding java.sql.Statement object or null
     * if it does not exist.
     *
     * @return java.sql.Statement
     */
    public java.sql.Statement getStatement()
    {
        return statement;
    }

    /**
     * Sets corresponding java.sql.Statement object.
     *
     * @param statement corresponding java.sql.Statement object
     */
    public void setStatement(java.sql.Statement statement)
    {
        this.statement = statement;
    }

    /**
     * Returns the string representation of this sql statement.
     *
     * @return java.lang.String
     */
    public String toString()
    {
        return getSqlString();
    }
    
    public static class Parameter
    {
        private Object value = null;
        private int plType = PlTypes.UNDEFINED;
        
        public Parameter(Object value, int plType)
        {
            this.value = value;
            this.plType = plType;
        }
        
        public Object getValue()
        {
            return value;
        }
        
        public int getPlType()
        {
            return plType;
        }
    }
}
