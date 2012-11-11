package pl.converter;

import java.util.Properties;
import pl.PlException;

/**
 * This class is the factory for converter objects.
 *
 * @author  Artem Rudoy
 */
public class ConverterFactory
{
    private static Converter trivialConverter = new TrivialConverter();

    public static final Converter getConverter(String className, ClassLoader loader, Properties properties) throws PlException
    {
        try
        {
            Class cl = Class.forName(className, true, loader);
            Converter converter = (Converter)cl.newInstance();
            converter.init(properties);
            return converter;
        }
        catch(InstantiationException e)
        {
            throw new PlException(e);
        }
        catch(IllegalAccessException e)
        {
            throw new PlException(e);
        }
        catch(ClassNotFoundException e)
        {
            throw new PlException(e);
        }
    }

/*    public static final Converter getConverter(String className, ClassLoader loader, Properties properties)
        throws PlException
    {
        try
        {
            return getConverter(Class.forName(className, true, loader), properties);
        }
        catch(ClassNotFoundException e)
        {
            throw new PlException(e);
        }
    }

    public static final Converter getConverter(String className, Properties properties)
        throws PlException
    {
        return getConverter(className, ConverterFactory.class.getClassLoader(), properties);
    }*/

    public static final Converter getTrivialConverter()
    {
        return trivialConverter;
    }
}
