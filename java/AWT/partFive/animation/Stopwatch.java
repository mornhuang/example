import java.awt.*;
import java.beans.*;

/**
 * A Thread that acts as a stopwatch.<p>
 *
 * Stopwatch starts running when it is constructed, and may be 
 * reset by the reset() method.  getHour(), getMinute(), 
 * getSecond(), and getMillisecond() are used to get the 
 * elapsed time since construction, or since the last reset.<p>
 *
 * toString() returns the elapsed time in the form of 
 * HH:MM:SS:mm, where HH == hours, MM == minutes, SS == seconds 
 * and mm == milliseconds.<p>
 *
 * Each Stopwatch may have a StopwatchClient associated with it.
 * If the StopwatchClient is non-null, the StopwatchClients' 
 * tick() method is invoked every 50 milliseconds.<p>
 *
 * @author  David Geary
 * @see     StopwatchClient
 * @see     Sequence
 * @see     Sprite
 */
public class Stopwatch extends Thread {
	static private long _defaultTickInterval = 50;

    private StopwatchClient client;
    private long            start, now, elapsed;
    private long            hour, minute, second, millisecond;
	private long            tickInterval;
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public Stopwatch() {
        this(null);
    }
    public Stopwatch(StopwatchClient client) {
		this(client, _defaultTickInterval);
    }
    public Stopwatch(StopwatchClient client, long milliseconds) {
		tickInterval = milliseconds;
        start        = System.currentTimeMillis();
        this.client  = client;
	}
	public void setTickInterval(long milliseconds) {
		tickInterval = milliseconds;
	}
	public long getTickInterval() {
		return tickInterval;
	}
    public void update() {
        now     = System.currentTimeMillis();
        elapsed = now - start;
        hour    = minute = second = millisecond = 0;

        second = elapsed / 1000;
        millisecond  = elapsed % 1000;
        millisecond  = (millisecond == 0) ? 0 : millisecond/10;

        if(second > 59) {
            minute = second / 60;
            second = second - (minute*60);
        }
        if(minute > 59) {
            hour   = minute / 60;
            minute = minute - (hour*60);
        }
    }
    public String toString() {
        update();
        return new String(stringValueOf(hour)   + ":" + 
                          stringValueOf(minute) + ":" + 
                          stringValueOf(second) + ":" +
                          stringValueOf(millisecond));
    }
    public long getHour        () { return hour;        }
    public long getMinute      () { return minute;      }
    public long getSecond      () { return second;      }
    public long getMillisecond () { return millisecond; }

	public void setElapsedTime(long l) {
		elapsed = l;
		start = System.currentTimeMillis() - elapsed;
	}
    public long getElapsedTime() { 
        update();
        return elapsed;
    }
	/**
	 * @deprecated for JavaBeans design patterns. Replaced by
	 * getElapsedTime().
	 */
	public long elapsedTime() {
		return getElapsedTime();	
	}
    public void reset() {
        start = System.currentTimeMillis();
    }
    public void run() {
        while(true) {
            try {
                Thread.currentThread().sleep(tickInterval, 0);
                update();
                if(client != null)
                    client.tick(this);

				support.firePropertyChange("elapsedTime", null,
				                           new Long(getElapsedTime()));
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private String stringValueOf(long l) {
        if(l < 10) return "0" + String.valueOf(l);
        else       return String.valueOf(l);

    }
	public void addPropertyChangeListener(PropertyChangeListener l) {
		support.addPropertyChangeListener(l);
	}
	public void removePropertyChangeListener(PropertyChangeListener l) {
		support.removePropertyChangeListener(l);
	}
}
