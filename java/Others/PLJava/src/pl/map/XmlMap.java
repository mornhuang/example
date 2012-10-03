package pl.map;

import pl.converter.Converter;
import pl.converter.ConverterFactory;

/**
 *
 * @author  ArtemRd
 * @version
 */
public class XmlMap
{
    public final static int TYPE_ATTRIBUTE = 0;
    public final static int TYPE_NODE = 1;

    private String name = null;
    private int type = TYPE_NODE;
    private Converter converter = null;

    /**
     * Creates new XmlMap
     */
    public XmlMap(String name, Converter converter)
    {
        this.name = name;
        this.converter = converter;
    }

    public String getName()
    {
        return name;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getType()
    {
        return type;
    }

    public void setConverter(Converter converter)
    {
        this.converter = converter;
    }

    public Converter getConverter()
    {
        return converter;
    }
}
