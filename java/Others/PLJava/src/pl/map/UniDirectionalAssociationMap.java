package pl.map;

import java.util.*;

import pl.PersistenceManager;
import pl.criteria.OrderEntry;
import pl.criteria.RetrieveCriteria;

/**
 * This class contains information about an association map.
 *
 * @author: Artyom Rudoy
 */
public class UniDirectionalAssociationMap
{
    public static final int ONE_TO_ONE = 0;
    public static final int ONE_TO_MANY = 1;

    private int cardinality = ONE_TO_ONE;
    private boolean must = false;
    private boolean saveAutomatic = false;
    private boolean deleteAutomatic = false;
    private boolean retrieveAutomatic = false;
    private ClassMap forClass = null;
    private AttributeMap target = null;
    private java.util.Vector entries = new Vector();
    private boolean inverse = false;
    private java.lang.String targetName = null;
    private OrderEntry[] orderAttributes = null;

    /**
     * Creates UniDirectionalAssociationMap.
     */
    public UniDirectionalAssociationMap()
    {
        super();
    }

    /**
     * Adds new entry to this association map.
     * @param entry pl.map.UDAMapEntry
     */
    public void addEntry(UDAMapEntry entry)
    {
        entries.addElement(entry);
    }

    /**
     * Returns cardinality of the association.
     * @return int
     */
    public int getCardinality()
    {
        return cardinality;
    }

    /**
     * Returns entry with the given index.
     *
     * @return pl.map.UDAMapEntry
     * @param index int index of the entry
     */
    public UDAMapEntry getEntry(int index)
    {
        return (UDAMapEntry)entries.get(index);
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public ClassMap getForClass()
    {
        return forClass;
    }

    /**
     * Returns number of entries.
     * @return int number of entries
     */
    public int getSize()
    {
        return entries.size();
    }

    /**
     * Insert the method's description here.
     * @return java.lang.String
     */
    public AttributeMap getTarget()
    {
        return target;
    }

    /**
     * Returns the name of the target for this attribute map.
     *
     * @return java.lang.String
     */
    public String getTargetName()
    {
        return targetName;
    }

    /**
     * Insert the method's description here.
     * @return boolean
     */
    public boolean isDeleteAutomatic()
    {
        return deleteAutomatic;
    }

    /**
     * Returns true if this association is inversed.
     *
     * @return boolean
     */
    public boolean isInverse()
    {
        return inverse;
    }

    /**
     * Insert the method's description here.
     * @return boolean
     */
    public boolean isMust()
    {
        return must;
    }

    /**
     * Insert the method's description here.
     * @return boolean
     */
    public boolean isRetrieveAutomatic()
    {
        return retrieveAutomatic;
    }

    /**
     * Insert the method's description here.
     * @return boolean
     */
    public boolean isSaveAutomatic()
    {
        return saveAutomatic;
    }

    /**
     * Insert the method's description here.
     * @param newCardinality int
     */
    public void setCardinality(int newCardinality)
    {
        cardinality = newCardinality;
    }

    /**
     * Insert the method's description here.
     * @param newDeleteAutomatic boolean
     */
    public void setDeleteAutomatic(boolean newDeleteAutomatic)
    {
        deleteAutomatic = newDeleteAutomatic;
    }

    /**
     * Insert the method's description here.
     * @param newForClass java.lang.String
     */
    public void setForClass(ClassMap newForClass)
    {
        forClass = newForClass;
    }

    /**
     * Sets the <code>inverse</code> property to the specified value.
     *
     * @param inverse new value for the <code>inverse</code> property
     */
    public void setInverse(boolean inverse)
    {
        this.inverse = inverse;
    }

    /**
     * Insert the method's description here.
     * @param newMust boolean
     */
    public void setMust(boolean newMust)
    {
        must = newMust;
    }

    /**
     * Insert the method's description here.
     * @param newRetrieveAutomatic boolean
     */
    public void setRetrieveAutomatic(boolean newRetrieveAutomatic)
    {
        retrieveAutomatic = newRetrieveAutomatic;
    }

    /**
     * Insert the method's description here.
     * @param newSaveAutomatic boolean
     */
    public void setSaveAutomatic(boolean newSaveAutomatic)
    {
        saveAutomatic = newSaveAutomatic;
    }

    /**
     * Insert the method's description here.
     * @param newTarget java.lang.String
     */
    public void setTarget(AttributeMap newTarget)
    {
        target = newTarget;
    }

    /**
     * Sets the name of the target for this association map.
     *
     * @param newTargetName the name of the target
     */
    public void setTargetName(String targetName)
    {
        this.targetName = targetName;
    }

    public void setOrderAttributes(OrderEntry[] orderAttributes)
    {
        this.orderAttributes = orderAttributes;
    }

    public OrderEntry[] getOrderAttributes()
    {
        return orderAttributes;
    }

    public RetrieveCriteria getCriteria(OrderEntry[] orderAttrs, PersistenceManager manager) throws pl.PlException
    {
        RetrieveCriteria criteria = new RetrieveCriteria(forClass, manager);
        if(isInverse())
        {
            for(int i = 0; i < entries.size(); i++)
            {
                criteria.getWhereCondition().addAndCriteria(criteria.getEqualToCriteria(getEntry(i).getFrom().getName()));
            }
        }
        else
        {
            for(int i = 0; i < entries.size(); i++)
            {
                criteria.getWhereCondition().addAndCriteria(criteria.getEqualToCriteria(getEntry(i).getTo().getName()));
            }
        }

        if(orderAttrs != null)
        {
            for(int i = 0; i < orderAttrs.length; i++)
            {
                criteria.addOrderAttribute(orderAttrs[i]);
            }
        }

        return criteria;
    }

    public Vector getCriteriaParameters(pl.PersistentObject object) throws pl.PlException
    {
        Vector criteriaParameters = new Vector();
        if(isInverse())
        {
            for(int i = 0; i < entries.size(); i++)
            {
                criteriaParameters.add(getEntry(i).getTo().getValue(object));
            }
        }
        else
        {
            for(int i = 0; i < entries.size(); i++)
            {
                criteriaParameters.add(getEntry(i).getFrom().getValue(object));
            }
        }

        return criteriaParameters;
    }
}
