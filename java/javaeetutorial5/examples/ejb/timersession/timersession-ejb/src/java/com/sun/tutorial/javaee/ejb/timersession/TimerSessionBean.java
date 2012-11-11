/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.ejb.timersession;

import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;


/**
 * TimerBean is a stateless session bean that creates a timer and prints out a
 * message when a timeout occurs.
 * Created Jan 9, 2006 3:44:08 PM
 * @author ian
 */
@Stateless
public class TimerSessionBean implements TimerSession {
    private static final Logger logger = Logger.getLogger(
                "com.sun.tutorial.javaee.ejb.timersession.TimerSessionBean");
    @Resource
    TimerService timerService;

    public void setTimer(long intervalDuration) {
        Timer timer = timerService.createTimer(
                    intervalDuration,
                    "Created new timer");
    }

    @Timeout
    public void timeout(Timer timer) {
        logger.info("Timeout occurred");
    }
}
