package pl.test;

/**
 * Insert the type's description here.
 * Creation date: (27.07.00 17:19:29)
 * @author:
 */
public class Employee extends Person
{
    private Object positionId = null;
    private Position position = null;
    private java.util.Vector tasks = new java.util.Vector();
    /**
     * Employee constructor comment.
     */
    public Employee(pl.PersistenceManager manager)
    {
        super(manager);
    }
    /**
     * Insert the method's description here.
     * Creation date: (27.07.00 17:20:50)
     * @return pl.test.Position
     */
    public Position getPosition()
    {
        return position;
    }
    /**
     * Insert the method's description here.
     * Creation date: (27.07.00 17:19:53)
     * @return long
     */
    public Object getPositionId()
    {
        return positionId;
    }
    /**
     * Insert the method's description here.
     * Creation date: (21.09.00 22:21:38)
     * @return java.util.Vector
     */
    public java.util.Vector getTasks()
    {
        return tasks;
    }
    /**
     * Insert the method's description here.
     * Creation date: (27.07.00 17:20:50)
     * @param newPosition pl.test.Position
     */
    public void setPosition(Position newPosition)
    {
        position = newPosition;
    }
    /**
     * Insert the method's description here.
     * Creation date: (27.07.00 17:19:53)
     * @param newPositionId long
     */
    public void setPositionId(Object newPositionId)
    {
        positionId = newPositionId;
    }
    /**
     * Insert the method's description here.
     * Creation date: (21.09.00 22:21:38)
     * @param newTasks java.util.Vector
     */
    public void setTasks(java.util.Vector newTasks)
    {
        tasks = newTasks;
    }
}
