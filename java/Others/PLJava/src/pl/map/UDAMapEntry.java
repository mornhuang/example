package pl.map;

/**
 * This class represents an entry of the UniDirectionalAssociationMap.
 *
 * @author: Artyom Rudoy
 */
public class UDAMapEntry
{
	private AttributeMap from = null;
	private AttributeMap to = null;
/**
 * UDAMapEntry constructor.
 */
public UDAMapEntry(AttributeMap from, AttributeMap to)
{
	super();

	this.from = from;
	this.to = to;
}
/**
 * Returns a 'from' attribute map.
 *
 * @return pl.map.AttributeMap
 */
public AttributeMap getFrom()
{
	return from;
}
/**
 * Returns a 'to' attribute map.
 *
 * @return pl.map.AttributeMap
 */
public AttributeMap getTo()
{
	return to;
}
/**
 * Sets the 'from' attribute map.
 *
 * @param from the new 'from' attribute map
 */
public void setFrom(AttributeMap from)
{
	this.from = from;
}
/**
 * Sets the 'to' attribute map.
 *
 * @param to the new 'to' attribute map
 */
public void setTo(AttributeMap to)
{
	this.to = to;
}
}
