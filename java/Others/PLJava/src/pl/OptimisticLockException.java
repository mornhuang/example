package pl;

/**
 * This exception is thrown if optimistic lock is not
 * supported by an object.
 *
 * @author: Artyom Rudoy
 */
public class OptimisticLockException extends PlException
{
/**
 * OptimisticLockException constructor.
 */
public OptimisticLockException()
{
	super();
}
/**
 * OptimisticLockException constructor.
 * @param s java.lang.String
 */
public OptimisticLockException(String s)
{
	super(s);
}
/**
 * OptimisticLockException constructor.
 * @param e java.lang.Throwable
 */
public OptimisticLockException(Throwable e)
{
	super(e);
}
}
