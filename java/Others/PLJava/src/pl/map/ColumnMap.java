package pl.map;

import pl.converter.Converter;
import pl.converter.ConverterFactory;

/**
 * This class contains information about table column.
 * @author: Artyom Rudoy
 */
public class ColumnMap
{
    public final static int KEY_NONE = 0;
    public final static int PRIMARY_KEY = 1;
    public final static int FOREIGN_KEY = 2;

    private java.lang.String name = null;
    private int keyType = KEY_NONE;
    private TableMap tableMap = null;
    private int plType = pl.sql.PlTypes.UNDEFINED;
    private pl.sql.IdGenerator idGenerator = null;
    private Converter converter = null;

    /**
     * Creates ColumnMap.
     */
    public ColumnMap(String name, TableMap tableMap, Converter converter)
    {
        super();

        this.name = name;
        this.tableMap = tableMap;
        this.converter = converter;
    }
    /**
     * Returns fully qualifies name of this column.
     * @return java.lang.String
     */
    public String getFullyQualifiedName()
    {
        return getTableMap().getName() + "." + getName();
    }
    /**
     * Returns key type of this column.
     * @return int
     */
    public int getKeyType()
    {
        return keyType;
    }

    /**
     * Return ID generator for this attribute map.
     *
     * @return ID generator
     */
    public pl.sql.IdGenerator getIdGenerator()
    {
        return idGenerator;
    }

    /**
     * Sets ID generator to the specified value.
     *
     * @param idGenerator IdGenerator
     */
    public void setIdGenerator(pl.sql.IdGenerator idGenerator)
    {
        this.idGenerator = idGenerator;
    }

    /**
     * Returns name of this column.
     * @return java.lang.String
     */
    public java.lang.String getName()
    {
        return name;
    }
    /**
     * Returns type of this column.
     *
     * @return type of this column
     */
    public int getPlType()
    {
        return plType;
    }
    /**
     * Returns TableMap for this column.
     * @return pl.map.TableMap
     */
    public TableMap getTableMap()
    {
        return tableMap;
    }
    /**
     * Sets key type for this column.
     * @param keyType int
     */
    public void setKeyType(int keyType)
    {
        this.keyType = keyType;
    }
    /**
     * Sets type of this column.
     *
     * @param type
     */
    public void setPlType(int plType)
    {
        this.plType = plType;
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
