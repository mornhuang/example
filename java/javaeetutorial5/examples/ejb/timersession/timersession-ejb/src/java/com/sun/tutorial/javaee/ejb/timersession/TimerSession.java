/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.ejb.timersession;

import javax.ejb.Remote;
import javax.ejb.Timer;


@Remote
public interface TimerSession {
    public void setTimer(long intervalDuration);

    public void timeout(Timer timer);
}
