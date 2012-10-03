package pl.criteria;

/**
 * This class is the base class for all criteria elements.
 *
 * @author: Artyom Rudoy
 */
public abstract class SelectionCriteria implements CriteriaPart
{
    private pl.map.AttributeMap attributeMap = null;
    private pl.map.ClassMap classMap = null;

    /**
     * SelectionCriteria constructor.
     */
    public SelectionCriteria(pl.map.ClassMap classMap, pl.map.AttributeMap attributeMap)
    {
        super();
        
        this.attributeMap = attributeMap;
        this.classMap = classMap;
    }

    /**
     * Returns attribute map for this criteria's attribute.
     *
     * @return attribute map for this criteria's attribute
     */
    public pl.map.AttributeMap getAttributeMap()
    {
        return attributeMap;
    }
    
    public pl.map.ClassMap getClassMap()
    {
        return classMap;
    }
}
