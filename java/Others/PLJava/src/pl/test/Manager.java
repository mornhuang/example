package pl.test;

/**
 * Insert the type's description here.
 * Creation date: (19.09.00 23:55:36)
 * @author:
 */
public class Manager extends Person
{
    private java.lang.String department = "";
    /**
     * Manager constructor comment.
     */
    public Manager(pl.PersistenceManager manager)
    {
        super(manager);
    }
    /**
     * Insert the method's description here.
     * Creation date: (19.09.00 23:55:53)
     * @return java.lang.String
     */
    public java.lang.String getDepartment()
    {
        return department;
    }
    /**
     * Insert the method's description here.
     * Creation date: (19.09.00 23:55:53)
     * @param newDepartmert java.lang.String
     */
    public void setDepartment(java.lang.String newDepartment)
    {
        department = newDepartment;
    }
}
