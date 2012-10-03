package pl.test;

import pl.converter.*;
import java.util.Properties;
import java.text.DecimalFormat;

/**
 * Converts numbers from/to string with the specified pattern.
 */
public class DecimalConverter implements Converter
{
    private DecimalFormat format = null;

    public DecimalConverter()
    {
    }

    public void init(Properties properties) throws pl.PlException
    {
        format = new DecimalFormat(properties.getProperty("format"));
    }

    public Object convertTo(Object object) throws pl.PlException
    {
        try
        {
            return format.parse((String)object);
        }
        catch(java.text.ParseException e)
        {
            throw pl.PlException.toPlException(e);
        }
    }

    public Object convertFrom(Object object) throws pl.PlException
    {
        return format.format(object);
    }
}
