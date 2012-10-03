package pl.sql;

public class ConnectionUtils
{
    /**
     * Commit the transaction associated with the connection connection.
     */
    public static void commit(java.sql.Connection connection) throws java.sql.SQLException
    {
        if (connection.getMetaData().supportsTransactions())
            connection.commit();
    }

    /**
     * Rollback the transaction associated with this connection.
     */
    public static void rollback(java.sql.Connection connection) throws java.sql.SQLException
    {
        if(connection.getMetaData().supportsTransactions())
            connection.rollback();
    }

    /**
     * Processe the specified SQL statement.
     *
     * @return java.sql.ResultSet
     * @param statement the statement to process
     */
    public static java.sql.ResultSet processQuery(pl.sql.SqlStatement statement, java.sql.Connection connection) throws pl.PlException
    {
        try
        {
            // Print statement's SQL string if the Persistence Layer is in the debug mode
/*            if(pl.PersistenceBroker.getInstance().isDebug())
            {
                System.out.println(statement.getSqlString());
            }*/

            java.sql.ResultSet rs = null;
            java.sql.PreparedStatement pst = connection.prepareStatement(statement.getSqlString());
            for (int i = 0; i < statement.getSize(); i++)
            {
                SqlStatement.Parameter parameter = statement.getParameter(i);
                PlTypes.setParameter(pst, parameter.getValue(), parameter.getPlType(), i + 1);
            }
            rs = pst.executeQuery();
            pst.clearParameters();
            statement.setStatement(pst);
            return rs;
        }
        catch(java.sql.SQLException e)
        {
            throw pl.PlException.toPlException(e);
        }
    }

    /**
     * Processe the specified SQL statement.
     *
     * @return java.sql.ResultSet
     * @param statement the statement to process
     */
    public static int processUpdate(pl.sql.SqlStatement statement, java.sql.Connection connection) throws pl.PlException
    {
        try
        {
            // Print statement's SQL string if the Persistence Layer is in the debug mode
/*            if(pl.PersistenceBroker.getInstance().isDebug())
            {
                System.out.println(statement.getSqlString());
            }*/

            int result = 0;
            java.sql.PreparedStatement pst = connection.prepareStatement(statement.getSqlString());
            for (int i = 0; i < statement.getSize(); i++)
            {
                SqlStatement.Parameter parameter = statement.getParameter(i);
                PlTypes.setParameter(pst, parameter.getValue(), parameter.getPlType(), i + 1);
            }
            result = pst.executeUpdate();
            pst.clearParameters();
            statement.setStatement(pst);
            return result;
        }
        catch(java.sql.SQLException e)
        {
            throw pl.PlException.toPlException(e);
        }
    }

    /**
     * Set connection auto-commit mode if transactions are supported.
     *
     * @param autoCommit boolean
     */
    public static void setAutoCommit(boolean autoCommit, java.sql.Connection connection) throws java.sql.SQLException
    {
        if(connection.getMetaData().supportsTransactions())
            connection.setAutoCommit(autoCommit);
    }
}
