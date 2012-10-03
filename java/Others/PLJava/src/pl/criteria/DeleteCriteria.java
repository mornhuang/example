package pl.criteria;

import pl.PersistenceManager;

/**
 * Insert the type's description here.
 * @author: Artyom Rudoy
 */
public class DeleteCriteria extends PersistentCriteria
{
/**
 * Creates copy of the specified criteria
 *
 * @param criteria source criteria
 */
public DeleteCriteria(PersistentCriteria criteria)
{
	super(criteria);
}
/**
 * Creates DeleteCriteria.
 *
 * @param classMap class map for this criteria
 */
public DeleteCriteria(pl.map.ClassMap classMap, PersistenceManager manager)
{
	super(classMap, manager);
}
/**
 * This method performs this criteria.
 *
 * @param parameters parameters for this criteria. For the
 * <code>addSelectIn</code> criteria the parameter must have <code>Vector</code> type
 */
public int perform(java.util.Vector parameters) throws pl.PlException
{
	return manager.processCriteria(this, parameters);
}
}
