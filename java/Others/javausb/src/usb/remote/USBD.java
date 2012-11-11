/*
 * Java USB Library
 * Copyright (C) 2000 by David Brownell
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package usb.remote;

import usb.core.*;
import java.rmi.Naming;
import java.rmi.Remote;



/**
 * This starts an RMI {@link HostProxy} for this USB Host,
 * exporting it through RMI naming. 
 *
 * <p> This is preliminary.  It may be preferable to have some
 * sort of authenticated session associated with each client,
 * to control access and to prevent clients from interfering
 * with each others' interface claiming.
 *
 * @version $Id: USBD.java,v 1.1 2008/10/15 09:28:02 huangmeng Exp $
 */
final public class USBD
{
    private static final boolean	debug = true;

    // no instances
    private USBD () { }

    /**
     * This holds the name under which this class registers
     * a proxy in the RMI naming context.
     * Its current value is "usb.core.Host".
     */
    public static final String		rmiName = "usb.core.Host";

    /**
     * Arguments are "start" or "stop", but "stop' is currently a NOP.
     */
    public static void main (String argv [])
    {
	try {
	    
	    // FIXME: error diagnostics via {/usr/bin,...}/logger

	    if (argv.length != 1) {
		System.err.println ("USBD [ start | stop ]");
		System.exit (1);
	    }

	    if ("start".equals (argv [0])) {
		Host	host = HostFactory.getHost ();

		if (host == null) {
		    System.err.println ("USBD:  USB is not available.");
		    System.exit (1);
		}

		// don't chain proxies till we can prevent loops
		if (host instanceof Remote) {
		    System.err.println ("USBD: proxy already registered");
		    System.exit (1);

		    // FIXME: check to see if it's usable, then
		    // normally rebind
		}

		// Export our host services via RMI
		try {
		    HostProxy	proxy = new HostProxy (host);
		    Naming.bind (rmiName, proxy);
		    System.err.println ("USBD:  Bound RMI name " + rmiName);
		} catch (Exception e) {
		    System.err.println ("USBD:  Couldn't bind to: " + rmiName);
		    if (debug)
			e.printStackTrace ();
		    else
			System.err.println (e.getMessage ());
		}


	    } else if ("stop".equals (argv [0])) {

		// FIXME get the daemon to set 'done'
		// publish our URI in some file (config state)

		System.err.println ("USBD: no 'stop' yet ... "
			+ "use 'ps' and 'kill' for now");
	    
	    // and status query

	    } else {
		System.err.println ("USBD: no '" + argv [0] + "' command");
		System.exit (1);
	    }

	} catch (Exception e) {
	    if (debug)
		e.printStackTrace ();
	    else
		System.err.println (e.getMessage ());
	}
    }
}
