package pl.test;

/**
 * Insert the type's description here.
 * Creation date: (21.09.00 22:01:35)
 * @author:
 */
public class Task extends pl.PersistentObject
{
    private java.lang.String name = "";
    private Object employeeId = null;
    /**
     * Task constructor comment.
     */
    public Task(pl.PersistenceManager manager)
    {
        super(manager);
    }
    /**
     * Insert the method's description here.
     * Creation date: (21.09.00 22:02:27)
     * @return long
     */
    public Object getEmployeeId()
    {
        return employeeId;
    }
    /**
     * Insert the method's description here.
     * Creation date: (21.09.00 22:01:56)
     * @return java.lang.String
     */
    public java.lang.String getName()
    {
        return name;
    }
    /**
     * Insert the method's description here.
     * Creation date: (21.09.00 22:02:27)
     * @param newEmployeeId long
     */
    public void setEmployeeId(Object newEmployeeId)
    {
        employeeId = newEmployeeId;
    }
    /**
     * Insert the method's description here.
     * Creation date: (21.09.00 22:01:56)
     * @param newName java.lang.String
     */
    public void setName(java.lang.String newName)
    {
        name = newName;
    }
}
