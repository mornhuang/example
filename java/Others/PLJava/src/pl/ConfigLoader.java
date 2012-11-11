package pl;

/**
 * Interface for all config loaders for the Persistence Layer.
 *
 * @author: Artem Rudoy
 */
public interface ConfigLoader {
	/**
	 * This method is called from the {@link PersistenceBroker}
	 * {@link PersistenceBroker#loadConfig loadConfig(ConfigLoader)} method.
	 * Implement this method to fill databaseMap and classMap hashtables of
	 * the specified persistence broker.
	 *
	 * @param broker
	 */
	void loadConfig(PersistenceManagerFactory pmf) throws PlException;
}



