package pl.sql;

/**
 * This class holds ID generator with it's attributes.
 *
 * @author: Artem Rudoy
 */
class IdGeneratorHolder
{
    private java.util.Properties parameters = null;
    private boolean singleInstance = false;
    private java.lang.Class idGeneratorClass = null;
    private pl.sql.IdGenerator instance = null;

    /**
     * IdGeneratorHolder constructor comment.
     */
    public IdGeneratorHolder(Class idGeneratorClass, java.util.Properties parameters, boolean singleInstance)
    {
        super();

        this.idGeneratorClass = idGeneratorClass;
        this.parameters = parameters;
        this.singleInstance = singleInstance;
    }

    /**
     * Return instance of this ID generator class.
     *
     * @return instance of this ID generator class
     */
    public synchronized pl.sql.IdGenerator getInstance(java.util.Properties additionalParameters) throws pl.PlException
    {
        if(!singleInstance)
            return getNewInstance(additionalParameters);
        else
        {
            if(instance == null)
            {
                instance = getNewInstance(new java.util.Properties());
            }

            return instance;
        }
    }

    /**
     * Return new instance of this ID generator class.
     *
     * @return new instance of this ID generator class
     */
    private pl.sql.IdGenerator getNewInstance(java.util.Properties additionalParameters) throws pl.PlException
    {
        try
        {
            java.util.Properties allParameters = new java.util.Properties();
            allParameters.putAll(parameters);
            allParameters.putAll(additionalParameters);
            pl.sql.IdGenerator idGen = (pl.sql.IdGenerator)idGeneratorClass.newInstance();
            idGen.init(allParameters);
            return idGen;
        }
        catch(pl.PlException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new pl.PlException(e);
        }
    }
}
