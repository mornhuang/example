package pl;

/**
 * @author Artem Rudoy
 */
public interface Transaction
{
    /** Begin a transaction.  The type of transaction is determined by the
     * setting of the Optimistic flag.
     * @see #setOptimistic
     * @see #getOptimistic
     * @throws JDOUserException if transactions are managed by a container
     * in the managed environment, or if the transaction is already active.
     */
    void begin();

    /** Commit the current transaction.
     * @throws JDOUserException if transactions are managed by a container
     * in the managed environment, or if the transaction is not active.
     */
    void commit();

    /** Roll back the current transaction.
     * @throws JDOUserException if transactions are managed by a container
     * in the managed environment, or if the transaction is not active.
     */
    void rollback();

    /** Returns whether there is a transaction currently active.
     * @return true if the transaction is active.
     */
    boolean isActive();

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
    void setSynchronization(javax.transaction.Synchronization sync);

    /** The user-specified Synchronization instance for this Transaction instance.
     * @return the user-specified Synchronization instance.
     */
    javax.transaction.Synchronization getSynchronization();

    /** The Tranansaction instance is always associated with exactly one
     * PersistenceManager.
     *
     * @return the PersistenceManager for this Transaction instance
     */
    PersistenceManager getPersistenceManager();
}
