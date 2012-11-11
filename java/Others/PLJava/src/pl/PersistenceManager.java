package pl;

import java.sql.*;
import java.util.*;
import javax.transaction.Synchronization;
import javax.transaction.Status;

import pl.criteria.*;
import pl.map.*;
import pl.sql.*;

/**
 * @author Artem Rudoy
 */
public class PersistenceManager implements Transaction
{
    private PersistenceManagerFactory factory = null;
    private Synchronization synchronization = null;
    private boolean active = false;
    private HashMap connections = new HashMap();// 保持多个连接
    private boolean closed = false;

    PersistenceManager(PersistenceManagerFactory factory)
    {
        this.factory = factory;
    }

    /**
     * This method is called to retrieve an object from a database.
     *
     * @param object the object to be retrieved
     */
    public void retrieveObject(PersistentObject object) throws PlException
    {
        ClassMap classMap = factory.getClassMap(object);
        Connection conn = getConnection(classMap.getRelationalDatabase());
        try
        {
            retrieveObject(object, classMap, conn, false);
        }
        catch(Exception e)
        {
            throw PlException.toPlException(e);
        }
        finally
        {
            freeConnection(classMap.getRelationalDatabase(), conn);
        }
    }

    /**
     * This method is called to retrieve an object from a database.
     *
     * @param object the object to be retrieved
     */
    public void lockObject(PersistentObject object) throws PlException
    {
        if(!active)
            throw new PlException("Can't lock object if transation is not active");

        ClassMap classMap = factory.getClassMap(object);
        Connection conn = getConnection(classMap.getRelationalDatabase());
        try
        {
            retrieveObject(object, classMap, conn, true);
        }
        catch(Exception e)
        {
            throw PlException.toPlException(e);
        }
        finally
        {
            freeConnection(classMap.getRelationalDatabase(), conn);
        }
    }

    public void lockObjectOptimistic(PersistentObject object) throws PlException
    {
        ClassMap classMap = factory.getClassMap(object);
        Connection conn = getConnection(classMap.getRelationalDatabase());
        try
        {
            // Retrieve timestamp for this object and lock rows for this object
            pl.sql.SqlStatement statement = classMap.getSelectTimestampSqlFor(object);
            java.sql.ResultSet rs = ConnectionUtils.processQuery(statement, conn);
            if(rs.next())
            {
                object.setTimestamp(rs.getObject(1));
            }
            // Free ResultSet and SqlStatement resources
            rs.close();
            statement.close();

            // Retrieve current object state from the database
            retrieveObject(object, classMap, conn, false);

            object.setInOptimisticLock(true);
        }
        catch(Exception e)
        {
            throw PlException.toPlException(e);
        }
        finally
        {
            freeConnection(classMap.getRelationalDatabase(), conn);
        }
    }

    /**
     * Retrieve object.
     *
     * @param object the object to be retrieved
     * @param conn a connection to work with
     */
    void retrieveObject(PersistentObject object, ClassMap classMap, Connection conn, boolean isLock) throws PlException, java.sql.SQLException
    {
        // Check if object is in optimistic lock
        // and get updated timestamp
        if(object.isInOptimisticLock())
        {
            // Retrieve timestamp for this object and lock rows for this object
            pl.sql.SqlStatement statement = classMap.getSelectTimestampSqlFor(object);
            java.sql.ResultSet rs = ConnectionUtils.processQuery(statement, conn);
            if(rs.next())
            {
                object.setTimestamp(rs.getObject(1));
            }
            // Free ResultSet and SqlStatement resources
            rs.close();
            statement.close();
        }

        pl.sql.SqlStatement statement = classMap.getSelectSqlFor(object);

        // Add FOR UPDATE clause if object needs to be locked
        if(isLock)
        {
            statement.addSqlClause(" " + classMap.getRelationalDatabase().getClauseStringForUpdate());
        }

        java.sql.ResultSet rs = ConnectionUtils.processQuery(statement, conn);
        if(rs.next())
        {
            classMap.retrieveObject(object, rs);
        }
        // Free ResultSet and SqlStatement resources
        rs.close();
        statement.close();

        // Retrieve associations after object
        retrieveAssociations(object, classMap, conn);
    }

    /**
     * Delete object from a relation database.
     *
     * @param object the object to be deleted
     */
    public void deleteObject(PersistentObject object) throws PlException
    {
        ClassMap classMap = factory.getClassMap(object);
        Connection conn = getConnection(classMap.getRelationalDatabase());
        try
        {
            deleteObject(object, classMap, conn);
        }
        catch(Exception e)
        {
            throw PlException.toPlException(e);
        }
        finally
        {
            freeConnection(classMap.getRelationalDatabase(), conn);
        }
    }

    /**
     * Delete object from a relation database.
     *
     * @param object the object to be deleted
     * @param classMap object's class map
     * @param conn connection to work with
     */
    private void deleteObject(PersistentObject object, ClassMap classMap, Connection conn) throws PlException
    {
        // Retrieve and delete associated data
        Iterator associations = classMap.getAssociationMaps().values().iterator();
        while(associations.hasNext())
        {
            UniDirectionalAssociationMap aMap = (UniDirectionalAssociationMap)associations.next();
            if(!aMap.isDeleteAutomatic())
                continue;

            DeleteCriteria criteria = new DeleteCriteria(aMap.getCriteria(null, this));
            Vector criteriaParameters = aMap.getCriteriaParameters(object);
            // processCriteria(criteria, criteriaParameters, conn);
        }

        // Delete object
        pl.sql.SqlStatement statement = classMap.getDeleteSqlFor(object);
        ConnectionUtils.processUpdate(statement, conn);
        statement.close();

        if(classMap.getSuperClass() != null)
        {
            // Delete superclass
            deleteObject(object, classMap.getSuperClass(), conn);
        }

        object.setPersistent(false);
    }

    /**
     * Retrieves object as proxy.
     * @param object Object to be retrieve.
     */
    public void retrieveObjectAsProxy(PersistentObject object) throws PlException
    {
        ClassMap classMap = factory.getClassMap(object);
        Connection conn = getConnection(classMap.getRelationalDatabase());
        try
        {
            retrieveObjectAsProxy(object, classMap, conn, false);
        }
        catch(Exception e)
        {
            throw PlException.toPlException(e);
        }
        finally
        {
            freeConnection(classMap.getRelationalDatabase(), conn);
        }
    }

    /**
     * Retrieve object as proxy.
     *
     * @param object the object to be retrieved
     * @param conn a connection to work with
     */
    void retrieveObjectAsProxy(PersistentObject object, ClassMap classMap, Connection conn, boolean isLock) throws PlException, java.sql.SQLException
    {
        // Check if object is in optimistic lock
        // and get updated timestamp
        if(object.isInOptimisticLock())
        {
            // Retrieve timestamp for this object and lock rows for this object
            pl.sql.SqlStatement statement = classMap.getSelectTimestampSqlFor(object);
            java.sql.ResultSet rs = ConnectionUtils.processQuery(statement, conn);
            if(rs.next())
            {
                object.setTimestamp(rs.getObject(1));
            }
            // Free ResultSet and SqlStatement resources
            rs.close();
            statement.close();
        }

        pl.sql.SqlStatement statement = classMap.getSelectProxySqlFor(object);

        // Add FOR UPDATE clause if object needs to be locked
        if(isLock)
        {
            statement.addSqlClause(" " + classMap.getRelationalDatabase().getClauseStringForUpdate());
        }

        java.sql.ResultSet rs = ConnectionUtils.processQuery(statement, conn);
        if(rs.next())
        {
            classMap.retrieveProxyObject(object, rs);
        }
        // Free ResultSet and SqlStatement resources
        rs.close();
        statement.close();

        // Retrieve associations after object
        retrieveAssociations(object, classMap, conn);
    }

    /**
     * This method is called to store an object in a database.
     *
     * @param object the object to be stored
     * @throw OptimisticLockException of object is marked for the optimistic lock and was modified
     */
    public void saveObject(PersistentObject object) throws PlException
    {
        ClassMap classMap = factory.getClassMap(object);
        Connection conn = getConnection(classMap.getRelationalDatabase());
        try
        {
            saveObject(object, classMap, conn);
        }
        catch(Exception e)
        {
            throw PlException.toPlException(e);
        }
        finally
        {
            freeConnection(classMap.getRelationalDatabase(), conn);
        }
    }

    /**
     * Insert the method's description here.
     *
     * @param object the object to be stored
     * @param conn a connection to work with
     */
    private void saveObject(PersistentObject object, ClassMap classMap, Connection conn) throws PlException, java.sql.SQLException
    {
        // Check if object is in optimistic lock
        // and check whether it was modified
        if(object.isInOptimisticLock())
        {
            // Retrieve timestamp for this object and lock rows for this object
            pl.sql.SqlStatement statement = classMap.getSelectTimestampSqlFor(object);
            java.sql.ResultSet rs = ConnectionUtils.processQuery(statement, conn);
            if(rs.next())
            {
                if(object.getTimestamp() == null || !object.getTimestamp().equals(rs.getObject(1)))
                    throw new OptimisticLockException("Object is marked for optimistic lock and was modified");
            }
            // Free ResultSet and SqlStatement resources
            rs.close();
																														            statement.close();
        }

        if(classMap.getSuperClass() != null)
        {
            // Store superclass
            // Save previous value of isPersistent property
            // This property should not change its value when superclasses are saved
            boolean isPersistent = object.isPersistent();
            saveObject(object, classMap.getSuperClass(), conn);
            object.setPersistent(isPersistent);
        }

        // Store associated data with straight association maps
        for(int j = 0; j < classMap.getStraightAssociationMapSize(); j++)
        {
            UniDirectionalAssociationMap aMap = classMap.getStraightAssociationMap(j);
            if(!aMap.isSaveAutomatic())
                continue;

            if(aMap.getCardinality() == aMap.ONE_TO_ONE)
            {
                PersistentObject value = (PersistentObject)aMap.getTarget().getValue(object);
                if(value != null)
                {
                    saveObject(value, aMap.getForClass(), conn);
                    for(int i = 0; i < aMap.getSize(); i++)
                    {
                        aMap.getEntry(i).getFrom().setValue(object,aMap.getEntry(i).getTo().getValue(value));
                    }
                }
            }
            else if(aMap.getCardinality() == aMap.ONE_TO_MANY)
            {
                Collection collection = (Collection)aMap.getTarget().getValue(object);
                if(collection != null)
                {
                    Iterator values = collection.iterator();
                    while(values.hasNext())
                    {
                        PersistentObject value = (PersistentObject)values.next();
                        saveObject(value, aMap.getForClass(), conn);
                        for(int i = 0; i < aMap.getSize(); i++)
                        {
                            aMap.getEntry(i).getFrom().setValue(value,
                                aMap.getEntry(i).getTo().getValue(object));
                        }
                    }
                }
            }
        }

        if(object.isPersistent())
        {
            // Update existent entry in the database
            pl.sql.SqlStatement statement = classMap.getUpdateSqlFor(object);
            ConnectionUtils.processUpdate(statement, conn);
            statement.close();
        }
        else
        {
            // Insert new entry into the database
            for(int i = 0; i < classMap.getKeySize(); i++)
            {
                AttributeMap keyAttribute = classMap.getKeyAttributeMap(i);
                if(keyAttribute.getColumnMap().getKeyType() != ColumnMap.PRIMARY_KEY ||keyAttribute.getColumnMap().getIdGenerator() == null)
                    continue;
                else
                {
                    // Generate new ID for this attribute
                    keyAttribute.setValue(object, keyAttribute.getColumnMap().getIdGenerator().getNewId(classMap));
                }
            }

            pl.sql.SqlStatement statement = classMap.getInsertSqlFor(object);
            ConnectionUtils.processUpdate(statement, conn);
            statement.close();
        }

        // Store associated data with inverse association maps
        for(int j = 0; j < classMap.getInverseAssociationMapSize(); j++)
        {
            UniDirectionalAssociationMap aMap = classMap.getInverseAssociationMap(j);
            if(!aMap.isSaveAutomatic())
                continue;

            if(aMap.getCardinality() == aMap.ONE_TO_ONE)
            {
                PersistentObject value = (PersistentObject)aMap.getTarget().getValue(object);
                if(value != null)
                {
                    for(int i = 0; i < aMap.getSize(); i++)
                    {
                        aMap.getEntry(i).getFrom().setValue(value,
                            aMap.getEntry(i).getTo().getValue(object));
                    }
                    saveObject(value, aMap.getForClass(), conn);
                }
            }
            else if(aMap.getCardinality() == aMap.ONE_TO_MANY)
            {
                Collection collection = (Collection)aMap.getTarget().getValue(object);
                if(collection != null)
                {
                    Iterator values = collection.iterator();
                    while(values.hasNext())
                    {
                        PersistentObject value = (PersistentObject)values.next();
                        for(int i = 0; i < aMap.getSize(); i++)
                        {
                            aMap.getEntry(i).getFrom().setValue(value,
                                aMap.getEntry(i).getTo().getValue(object));
                        }
                        saveObject(value, aMap.getForClass(), conn);
                    }
                }
            }
        }

        object.setPersistent(true);

        // Check if object is in optimistic lock
        // and get updated timestamp
        if(object.isInOptimisticLock())
        {
            // Retrieve timestamp for this object and lock rows for this object
            pl.sql.SqlStatement statement = classMap.getSelectTimestampSqlFor(object);
            java.sql.ResultSet rs = ConnectionUtils.processQuery(statement, conn);
            if(rs.next())
            {
                object.setTimestamp(rs.getObject(1));
            }
            // Free ResultSet and SqlStatement resources
            rs.close();
            statement.close();
        }
    }

    /**
     * Retrieve associations for the specified target of the specified object.
     *
     * @param object Target object.
     * @param targetName Name of the association.
     * @param orderAttributes Order attributes.
     */
    public void retrieveAssociation(PersistentObject object, String targetName, OrderEntry[] orderAttributes) throws PlException
    {
        ClassMap classMap = factory.getClassMap(object);
        Connection conn = getConnection(classMap.getRelationalDatabase());
        try
        {
            retrieveAssociation(object, targetName, orderAttributes, classMap, conn);
        }
        catch(Exception e)
        {
            throw PlException.toPlException(e);
        }
        finally
        {
            freeConnection(classMap.getRelationalDatabase(), conn);
        }
    }

    /**
     * Retrieve associations for the specified target of the specified object.
     *
     * @param object
     * @param targetName
     */
    void retrieveAssociation(PersistentObject object, String targetName, OrderEntry[] orderAttributes, ClassMap classMap, Connection conn) throws PlException, java.sql.SQLException
    {
        UniDirectionalAssociationMap aMap = classMap.getAssociationMap(targetName);
        if(aMap == null)
            throw new PlException("Association name for target " + targetName + " not found");
        if(aMap.getTarget() == null)
            throw new PlException("Target attribute with name " + targetName + " not found");
        retrieveAssociation(object, aMap, orderAttributes, classMap, conn);
    }

    /**
     * Insert the method's description here.
     *
     * @param object the object to be retrieved
     * @param conn a connection to work with
     */
    void retrieveAssociation(PersistentObject object, UniDirectionalAssociationMap aMap, OrderEntry[] orderAttributes, ClassMap classMap, Connection conn) throws PlException, java.sql.SQLException
    {
        RetrieveCriteria criteria = aMap.getCriteria(orderAttributes, this);
        Vector criteriaParameters = aMap.getCriteriaParameters(object);
        Cursor cursor = processCriteria(criteria, criteriaParameters, conn, false);
        if(aMap.getCardinality() == aMap.ONE_TO_ONE)
        {
            PersistentObject value = null;
            if(cursor.next())
                value = cursor.getObject();
            aMap.getTarget().setValue(object, value);
        }
        else if(aMap.getCardinality() == aMap.ONE_TO_MANY)
        {
            Collection objects = (Collection)aMap.getTarget().getValue(object);
            // Clear collection if it exists
            if(objects != null)
            {
                objects.clear();
                while(cursor.next())
                {
                    objects.add(cursor.getObject());
                }
            }
        }
        cursor.close();
    }

    /**
     * This method is called to retrieve object's associations from a database.
     *
     * @param object the object to be retrieved
     */
    void retrieveAssociations(PersistentObject object) throws PlException
    {
        ClassMap classMap = factory.getClassMap(object);
        Connection conn = getConnection(classMap.getRelationalDatabase());
        try
        {
            retrieveAssociations(object, classMap, conn);
        }
        catch(Exception e)
        {
            throw PlException.toPlException(e);
        }
        finally
        {
            freeConnection(classMap.getRelationalDatabase(), conn);
        }
    }

    /**
     * Insert the method's description here.
     *
     * @param object the object to be retrieved
     * @param conn a connection to work with
     */
    void retrieveAssociations(PersistentObject object, ClassMap classMap, Connection conn) throws PlException, java.sql.SQLException
    {
        if(classMap.getSuperClass() != null)
        {
            // Retrieve associated data for superclass
            retrieveAssociations(object, classMap.getSuperClass(), conn);
        }

        // Retrieve associated data
        Iterator associations = classMap.getAssociationMaps().values().iterator();
        while(associations.hasNext())
        {
            UniDirectionalAssociationMap aMap = (UniDirectionalAssociationMap)associations.next();
            if(!aMap.isRetrieveAutomatic())
                continue;
            else
                retrieveAssociation(object, aMap, aMap.getOrderAttributes(), classMap, conn);
        }
    }

    /**
     * This method is called from the DeleteCriteria perform method
     * to delete multiple objects from a database.
     *
     * @return int
     * @param criteria pl.api.DeleteCriteria
     * @param parameters parameters for the criteria
     * @param conn a connection to work with
     */
    int processCriteria(DeleteCriteria criteria, Vector parameters, Connection conn) throws PlException
    {
        // Create RetrieveCriteria to get object for deletion
        RetrieveCriteria retrieveCriteria = new RetrieveCriteria(criteria);
        Cursor result = processCriteria(retrieveCriteria, parameters, conn, false);

        // Move objects from cursor to vector first
        Vector objects = new Vector();
        while(result.next())
        {
            objects.add(result.getObject(conn));
        }
        result.close();

        // Delete objects
        for(int i = 0; i < objects.size(); i++)
        {
            deleteObject((PersistentObject)objects.get(i), criteria.getClassMap(), conn);
        }

        return objects.size();
    }

    /**
     * This method is called from the RetrieveCriteria perform method
     * to retrieve multiple objects from a database. Returns Cursor with the result.
     *
     * @return pl.api.Cursor
     * @param criteria pl.api.RetrieveCriteria
     * @param parameters parameters for the criteria
     */
    public Cursor processCriteria(RetrieveCriteria criteria, Vector parameters) throws PlException
    {
        Connection conn = getConnection(criteria.getClassMap().getRelationalDatabase());
        try
        {
            Cursor result = processCriteria(criteria, parameters, conn, false);
            return result;
        }
        catch(Exception e)
        {
            throw PlException.toPlException(e);
        }
        finally
        {
            freeConnection(criteria.getClassMap().getRelationalDatabase(), conn);
        }
    }

    /**
     * This method is called from the DeleteCriteria perform method
     * to delete multiple objects from a database.
     *
     * @return int
     * @param criteria pl.api.DeleteCriteria
     * @param parameters parameters for the criteria
     * @throws PlException Error occured while processing criterion.
     */
    public int processCriteria(DeleteCriteria criteria, Vector parameters) throws PlException
    {
        Connection conn = getConnection(criteria.getClassMap().getRelationalDatabase());
        try
        {
            int result = processCriteria(criteria, parameters, conn);
            return result;
        }
        catch(Exception e)
        {
            throw PlException.toPlException(e);
        }
        finally
        {
            freeConnection(criteria.getClassMap().getRelationalDatabase(), conn);
        }
    }

    /**
     * This method is called from the RetrieveCriteria perform method
     * to retrieve multiple objects from a database. Returns Cursor with the result.
     *
     * @return pl.api.Cursor
     * @param criteria pl.api.RetrieveCriteria
     * @param parameters parameters for the criteria
     */
    public Cursor processCriteriaForProxies(RetrieveCriteria criteria, Vector parameters) throws PlException
    {
        Connection conn = getConnection(criteria.getClassMap().getRelationalDatabase());
        try
        {
            Cursor result = processCriteria(criteria, parameters, conn, true);
            return result;
        }
        catch(Exception e)
        {
            throw PlException.toPlException(e);
        }
        finally
        {
            freeConnection(criteria.getClassMap().getRelationalDatabase(), conn);
        }
    }

    /**
     * This method is called from the RetrieveCriteria perform method
     * to retrieve multiple objects from a database. Returns Cursor with the result.
     *
     * @return pl.api.Cursor
     * @param criteria pl.api.RetrieveCriteria
     * @param parameters parameters of the criteria
     * @param conn a connection to work with
     */
    Cursor processCriteria(RetrieveCriteria criteria, Vector parameters, Connection conn, boolean forProxy) throws PlException
    {
        pl.sql.SqlStatement statement = criteria.getSqlStatement(parameters, forProxy);
        java.sql.ResultSet rs = ConnectionUtils.processQuery(statement, conn);
        return new Cursor(statement, rs, criteria.getClassMap(), forProxy, this);
    }

    public RetrieveCriteria getRetrieveCriteria(ClassMap forClass)
    {
        return new RetrieveCriteria(forClass, this);
    }

    public RetrieveCriteria getRetrieveCriteria(Class forClass)
    {
        return getRetrieveCriteria(forClass.getName());
    }

    public RetrieveCriteria getRetrieveCriteria(String className)
    {
        return new RetrieveCriteria(factory.getClassMap(className), this);
    }

    public RetrieveCriteria getRetrieveCriteria(PersistentCriteria criteria)
    {
        return new RetrieveCriteria(criteria);
    }

    public DeleteCriteria getDeleteCriteria(ClassMap forClass)
    {
        return new DeleteCriteria(forClass, this);
    }

    public DeleteCriteria getDeleteCriteria(Class forClass)
    {
        return getDeleteCriteria(forClass.getName());
    }

    public DeleteCriteria getDeleteCriteria(String className)
    {
        return new DeleteCriteria(factory.getClassMap(className), this);
    }

    public DeleteCriteria getDeleteCriteria(PersistentCriteria criteria)
    {
        return new DeleteCriteria(criteria);
    }

    public void close()
    {
        closed = true;

        // Connection is rolled back by default
        rollback();
    }

    public void finalize() throws Throwable
    {
        close();
    }

    public Transaction getTransaction()
    {
        return this;
    }

    /** Begin a transaction.  The type of transaction is determined by the
     * setting of the Optimistic flag.
     * @see #setOptimistic
     * @see #getOptimistic
     * @throws JDOUserException if transactions are managed by a container
     * in the managed environment, or if the transaction is already active.
     */
    public void begin()
    {
        active = true;
    }

    /** Commit the current transaction.
     * @throws JDOUserException if transactions are managed by a container
     * in the managed environment, or if the transaction is not active.
     */
    public void commit()
    {
        if(active)
        {
            try
            {
                try
                {
                    if(synchronization != null)
                        synchronization.beforeCompletion();
                }
                catch(Throwable e) {}

                Iterator databases = connections.keySet().iterator();
                while(databases.hasNext())
                {
                    RelationalDatabase database = (RelationalDatabase)databases.next();
                    Connection conn = (Connection)connections.get(database);
                    try
                    {
                        ConnectionUtils.commit(conn);
                        database.freeConnection(conn);
                    }
                    catch(SQLException e) {}
                }
                connections.clear();

                try
                {
                    if(synchronization != null)
                        synchronization.afterCompletion(Status.STATUS_COMMITTED);
                }
                catch(Throwable e) {}
            }
            finally
            {
                active = false;
            }
        }
    }

    /** Roll back the current transaction.
     * @throws JDOUserException if transactions are managed by a container
     * in the managed environment, or if the transaction is not active.
     */
    public void rollback()
    {
        if(active)
        {
            try
            {
                Iterator databases = connections.keySet().iterator();
                while(databases.hasNext())
                {
                    RelationalDatabase database = (RelationalDatabase)databases.next();
                    Connection conn = (Connection)connections.get(database);
                    try
                    {
                        ConnectionUtils.rollback(conn);
                        database.freeConnection(conn);
                    }
                    catch(SQLException e) {}
                }
                connections.clear();

                try
                {
                    if(synchronization != null)
                        synchronization.afterCompletion(Status.STATUS_ROLLEDBACK);
                }
                catch(Throwable e) {}
            }
            finally
            {
                active = false;
            }
        }
    }

    /** Returns whether there is a transaction currently active.
     * @return true if the transaction is active.
     */
    public boolean isActive()
    {
        return active;
    }

    /** The user can specify a Synchronization instance to be notified on
     * transaction completions.  The beforeCompletion method is called prior
     * to flushing instances to the data store.
     *
     * <P>The afterCompletion method is called after performing state
     * transitions of persistent and transactional instances, following
     * the data store commit or rollback operation.
     * <P>Only one Synchronization instance can be registered with the
     * Transaction. If the application requires more than one instance to
     * receive synchronization callbacks, then the single application instance
     * is responsible for managing them, and forwarding callbacks to them.
     * @param sync the Synchronization instance to be notified; null for none
     */
    public void setSynchronization(Synchronization synchronization)
    {
        this.synchronization = synchronization;
    }

    /** The user-specified Synchronization instance for this Transaction instance.
     * @return the user-specified Synchronization instance.
     */
    public Synchronization getSynchronization()
    {
        return synchronization;
    }

    /** The Tranansaction instance is always associated with exactly one
     * PersistenceManager.
     *
     * @return the PersistenceManager for this Transaction instance
     */
    public PersistenceManager getPersistenceManager()
    {
        return this;
    }

    private synchronized Connection getConnection(RelationalDatabase database) throws PlException
    {
        if(closed)
            throw new PlException("PersistenceManager is closed");

        Connection conn = null;
        if(active)
        {
            conn = (Connection)connections.get(database.getName());
            if(conn == null)
            {
                // Obtain connection from the database and put it
                // to the internal cache
                conn = database.getConnection();
                connections.put(database, conn);
            }
        }
        else
        {
            conn = database.getConnection();
        }

        try
        {
            ConnectionUtils.setAutoCommit(false, conn);
        }
        catch(SQLException e)
        {
            throw PlException.toPlException(e);
        }
        return conn;
    }

    private void freeConnection(RelationalDatabase database, Connection conn) throws PlException
    {
        if(!active)
        {
            try
            {
                ConnectionUtils.commit(conn);
                database.freeConnection(conn);
            }
            catch(SQLException e)
            {
                throw PlException.toPlException(e);
            }
        }
    }
}
