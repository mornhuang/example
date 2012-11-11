package pl.converter;

/**
 * This interface represents converter. Object type is the type of a
 * PersistentObject's attribute.
 *
 * @author  Artem Rudoy
 */
public interface Converter
{
    /**
     * Init converter.
     */
    public void init(java.util.Properties properties) throws pl.PlException;
    
    /**
     * Convert to object attribute type.
     */
    public Object convertTo(Object object) throws pl.PlException;

    /**
     * Convert from object attribute type.
     */
    public Object convertFrom(Object object) throws pl.PlException;
}
