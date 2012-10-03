package pl.test;

/**
 * Insert the type's description here.
 * Creation date: (27.07.00 17:20:10)
 * @author:
 */
public class Position extends pl.PersistentObject
{
    private java.lang.String name = "";
    /**
     * Position constructor comment.
     */
    public Position(pl.PersistenceManager manager)
    {
        super(manager);
    }
    /**
     * Insert the method's description here.
     * Creation date: (27.07.00 17:20:21)
     * @return java.lang.String
     */
    public java.lang.String getName()
    {
        return name;
    }
    /**
     * Insert the method's description here.
     * Creation date: (27.07.00 17:20:21)
     * @param newName java.lang.String
     */
    public void setName(java.lang.String newName)
    {
        name = newName;
    }
}
