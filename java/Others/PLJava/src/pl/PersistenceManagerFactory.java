package pl;

import java.io.PrintStream;
import java.util.TreeMap;

import pl.map.ClassMap;
import pl.sql.RelationalDatabase;
import pl.xml.Marshaller;
import pl.converter.Converter;

/**
 * @author Artem Rudoy
 */
public class PersistenceManagerFactory
{
    private TreeMap classMaps = new TreeMap();
    private TreeMap databases = new TreeMap();
    private TreeMap converters = new TreeMap();
    private boolean debug = false;
    private PrintStream logger = System.out;
    private boolean locked = false;

    /** Get an instance of PersistenceManager from this factory.  The instance has
     * default values for options.
     *
     * <P>After the first use of getPersistenceManager, no "set" methods will
     * succeed.
     *
     * @return a PersistenceManager instance with default options.
     */
    public synchronized PersistenceManager getPersistenceManager()
    {
        locked = true;
        return new PersistenceManager(this);
    }

    public synchronized Marshaller getXMLMarshaller() throws PlException
    {
        return new Marshaller(this);
    }

    public synchronized Marshaller getXMLMarshaller(String namespaceURI, String namespacePrefix) throws PlException
    {
        return new Marshaller(namespaceURI, namespacePrefix, this);
    }

    /**
     * Call this method to load a configuration from the specified ConfigLoader.
     *
     * @param configLoader a ConfigLoader instance from which configuration will be loaded
     * @throws PlException Error wile loading configuration.
     */
    public synchronized void loadConfig(ConfigLoader configLoader) throws PlException
    {
        if(locked)
            throw new PlException("Can't load config after a PersistenceManager was returned");

        configLoader.loadConfig(this);
    }

    public void addClassMap(ClassMap classMap) throws PlException
    {
        if(locked)
            throw new PlException("Can't add class map after a PersistenceManager was returned");

        classMap.init();
        classMaps.put(classMap.getName(), classMap);
    }

    public ClassMap getClassMap(String name)
    {
        return (ClassMap)classMaps.get(name);
    }

    public void addRelationalDatabase(RelationalDatabase database) throws PlException
    {
        if(locked)
            throw new PlException("Can't add relational database after a PersistenceManager was returned");

        databases.put(database.getName(), database);
    }

    public RelationalDatabase getRelationalDatabase(String name)
    {
        return (RelationalDatabase)databases.get(name);
    }

    public Converter getConverter(String name)
    {
        return (Converter)converters.get(name);
    }

    public void putConverter(String name, Converter converter)
    {
        converters.put(name, converter);
    }

    /**
     * Returns a ClassMap instance for this object. For internal use.
     *
     * @return pl.map.ClassMap
     */
    public ClassMap getClassMap(PersistentObject object) throws PlException
    {
        ClassMap classMap = getClassMap(object.getClass().getName());

        if(classMap == null)
            throw new PlException("No class map for " + object.getClass().getName() + " class");

        return classMap;
    }

}
