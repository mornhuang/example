package pl;

import pl.criteria.OrderEntry;
import pl.map.*;

/**
 * This class encapsulates the behavior needed to make
 * single instances persistent and is the class that
 * business/domain classes inherit from to become persistent.
 *
 * @author: Artyom Rudoy
 */
public abstract class PersistentObject
{
    private boolean persistent = false;
    private boolean proxy = false;
    private Object id = null;
    private Object timestamp = null;
    private boolean inOptimisticLock;
    public final static java.lang.String TIMESTAMP_FIELD_NAME = "timestamp";
    private PersistenceManager manager = null;

    /**
     * PersistentObject constructor.
     */
    public PersistentObject(PersistenceManager manager)
    {
        super();

        this.manager = manager;
    }

    /**
     * Call this method to permanently delete this object from a database.
     */
    public void delete() throws PlException
    {
        manager.deleteObject(this);
    }

    /**
     * If the object is persistent and Id is used as the key column this
     * method will return Id of this object in a database.
     *
     * @return Object
     */
    public Object getId()
    {
        return id;
    }

    /**
     * Getter method for the timestamp field.
     *
     * @return java.sql.Timestamp
     */
    public Object getTimestamp()
    {
        return timestamp;
    }

    /**
     * Returns true if object is marked for optimistic lock.
     *
     * @return true if object is marked for optimistic lock.
     */
    boolean isInOptimisticLock()
    {
        return inOptimisticLock;
    }

    /**
     * Returns true if this object is in a database.
     *
     * @return boolean
     */
    public boolean isPersistent()
    {
        return persistent;
    }

    public boolean isProxy()
    {
        return proxy;
    }

    /**
     * Marks this object for optimistic lock.
     *
     * @exception pl.OptimisticLockException if this object could not be optimistically locked.
     */
    public void lock() throws PlException
    {
        manager.lockObject(this);
    }

    /**
     * Marks this object for optimistic lock.
     *
     * @exception pl.OptimisticLockException if this object could not be optimistically locked.
     */
    public void lockOptimistic() throws PlException
    {
        manager.lockObjectOptimistic(this);
    }

    /**
     * Call this method to synchronize state of this object with a database state.
     */
    public void retrieve() throws PlException
    {
        manager.retrieveObject(this);
    }

    public void retrieveAsProxy() throws PlException
    {
        manager.retrieveObjectAsProxy(this);
    }

    /**
     * Call this method to retreive associations from a database.
     */
    public void retrieveAssociation(String targetName, OrderEntry[] orderAttributes) throws PlException
    {
        manager.retrieveAssociation(this, targetName, orderAttributes);
    }

    /**
     * Call this method to store this object in a database.
     */
    public void save() throws PlException
    {
        manager.saveObject(this);
    }

    /**
     * Setter method for the id property.
     *
     * @param id
     */
    public void setId(Object id)
    {
        this.id = id;
    }

    /**
     * Setter method for isOptimisticLock field. For internal use only.
     *
     * @param inOptimisticLock
     */
    void setInOptimisticLock(boolean inOptimisticLock)
    {
        this.inOptimisticLock = inOptimisticLock;
    }

    /**
     * Setter method for the persistent property.
     *
     * @param persistent
     */
    public void setPersistent(boolean persistent)
    {
        this.persistent = persistent;
    }

    public void setProxy(boolean proxy)
    {
        this.proxy = proxy;
    }

    /**
     * Setter method for the timestamp field.
     *
     * @param timestamp
     */
    void setTimestamp(Object timestamp)
    {
        this.timestamp = timestamp;
    }
}
