package pl;

/**
 * Exception class for all errors in the Persistence Layer.
 *
 * @author: Artyom Rudoy
 */
public class PlException extends Exception
{
    private Throwable cause = null;

    /**
     * PlException constructor.
     */
    public PlException()
    {
        super();
    }

    /**
     * PlException constructor.
     *
     * @param s a message for this exception
     */
    public PlException(String s)
    {
        super(s);
    }

    /**
     * PlException constructor.
     *
     * @param e an exception from which message will be taken
     */
    public PlException(Throwable cause)
    {
        this(cause.getClass().getName() + ": " + cause.getMessage());
        this.cause = cause;
    }

    /**
     * PlException constructor.
     */
    public PlException(String message, Throwable cause)
    {
        this(message);
        this.cause = cause;
    }

    public void printStackTrace()
    {
        this.printStackTrace(System.err);
    }

    public void printStackTrace(java.io.PrintStream s)
    {
        this.printStackTrace(new java.io.PrintWriter(s));
    }

    public void printStackTrace(java.io.PrintWriter s)
    {
        super.printStackTrace(s);

        if(cause != null)
        {
            s.println("\nCaused by:");
            cause.printStackTrace(s);
        }
        s.flush();
    }

    public static final PlException toPlException(Throwable e)
    {
        if(e instanceof PlException)
            return (PlException)e;
        else
            return new PlException(e);
    }
}
