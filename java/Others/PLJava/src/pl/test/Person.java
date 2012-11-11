package pl.test;

/**
 * Insert the type's description here.
 * Creation date: (27.07.00 17:18:36)
 * @author:
 */
public class Person extends pl.PersistentObject
{
    private java.lang.String name = "";
    /**
     * Person constructor comment.
     */
    public Person(pl.PersistenceManager manager)
    {
        super(manager);
    }
    /**
     * Insert the method's description here.
     * Creation date: (27.07.00 17:19:07)
     * @return java.lang.String
     */
    public java.lang.String getName()
    {
        return name;
    }
    /**
     * Insert the method's description here.
     * Creation date: (27.07.00 17:19:07)
     * @param newName java.lang.String
     */
    public void setName(java.lang.String newName)
    {
        name = newName;
    }
}
