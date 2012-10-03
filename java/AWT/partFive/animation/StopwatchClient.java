/**
 * Client of a Stopwatch.  Stopwatches that have non-null 
 * clients, call their clients' tick() method every 50 
 * milliseconds.<p>
 *
 * @author  David Geary
 * @see     Stopwatch
 */
public interface StopwatchClient {
    public void tick(Stopwatch stopwatch);
}
