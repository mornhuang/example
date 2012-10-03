package pl.criteria;

/**
 * Entry for building ORDER BY clause.
 * @author: Artyom Rudoy
 */
public class OrderEntry
{
	private pl.map.AttributeMap attributeMap = null;
	private boolean ascend = true;
/**
 * OrderEntry constructor.
 */
public OrderEntry(pl.map.AttributeMap attributeMap, boolean ascend)
{
	super();

	this.attributeMap = attributeMap;
	this.ascend = ascend;
}
/**
 * Returns attribute map.
 * @return pl.map.AttributeMap
 */
public pl.map.AttributeMap getAttributeMap()
{
	return attributeMap;
}
/**
 * Returns true if order is ascending or false if descending.
 * @return boolean
 */
public boolean isAscend()
{
	return ascend;
}
}
