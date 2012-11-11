package pl.converter;

/**
 * Trivial converter. Does nothing.
 *
 * @author  Artem Rudoy
 */
public class TrivialConverter implements Converter
{
    /**
     * Init converter.
     */
    public void init(java.util.Properties properties) throws pl.PlException
    {
    }

    /**
     * Convert to object attribute type.
     */
    public Object convertTo(Object object) throws pl.PlException
    {
        return object;
    }
    
    /**
     * Convert from object attribute type.
     */
    public Object convertFrom(Object object) throws pl.PlException
    {
        return object;
    }
}
