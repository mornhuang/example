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

import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import usb.core.Host;
import usb.core.HostFactory;


/**
 * Bootstrapping methods.
 *
 * @version $Id: RemoteHostFactory.java,v 1.1 2008/10/15 09:28:02 huangmeng Exp $
 */
public final class RemoteHostFactory extends HostFactory
{
    static private Host		self;

    /**
     * Not part of the API.
     * This is part of the SPI for the reference implementation.
     */
    public RemoteHostFactory () { }

    /**
     * Not part of the API.
     * This is part of the SPI for the reference implementation.
     */
    public Host createHost () throws IOException
	{ return getHost (); }


    /**
     * Returns a client side proxy for a USB Host.
     */
    public static Host getHost ()
    throws IOException
    {
	synchronized (RemoteHostFactory.class) {
	    if (self != null)
		return self;

	    // FIXME: handle _both_ RMI/JRMP and RMI/IIOP ...

	    try {
		self = (Host) Naming.lookup ("usb.core.Host");
	    } catch (ConnectException e) {

		// no (default) registry found
	    } catch (NotBoundException e) {
		// no proxy was registered
	    }
	}
	return self;
    }
}
