package pl.test;

import pl.converter.*;
import java.util.Properties;
import java.text.*;

public class DateConverter implements Converter
{
    private SimpleDateFormat format = null;

    public DateConverter()
    {
    }

    public void init(Properties properties) throws pl.PlException
    {
        format = new SimpleDateFormat(properties.getProperty("format"));
    }

    public Object convertTo(Object object) throws pl.PlException
    {
        try
        {
            return format.parse((String)object);
        }
        catch(ParseException e)
        {
            throw pl.PlException.toPlException(e);
        }
    }

    public Object convertFrom(Object object) throws pl.PlException
    {
        return format.format(object);
    }
}
