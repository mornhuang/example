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

package usb.core;

import java.io.IOException;

/**
 * Bootstrapping methods.
 */
public abstract class HostFactory
{
    static private Host		self;

    /**
     * Not part of the API.
     * This is part of the SPI for the reference implementation.
     */
    protected HostFactory () { }

    /**
     * Not part of the API.
     * This is part of the SPI for the reference implementation.
     */
    public abstract Host createHost () throws IOException;


    /**
     * Returns a USB Host according to an environment-specific policy.
     * This bootstrapping method is part of the API, but the policy
     * used by the environment isn't.
     *
     * @exception IOException When USB Host functions are not available.
     */
    public static Host getHost ()
    throws IOException
    {
	synchronized (HostFactory.class) {
	    if (self != null)
		return self;

	    // The exact policy used to find a host to return is
	    // NOT standardized -- embedded systems might want
	    // to avoid reflection (at some cost in portability),
	    // others might be driven by some system property.

	    try {
		// prefer any designated proxy
		self = maybeGetHost ("usb.remote.RemoteHostFactory");
		if (self != null)
		    return self;

	    // ignore exceptions here
	    } catch (IOException e) {
	    } catch (RuntimeException e) {
	    }

	    // else try a native implementation
	    String os = System.getProperty ("os.name");
	    self = maybeGetHost ("usb." + os.toLowerCase () + "." + os);
	    if (self == null)
		throw new IOException ("USB Host support is unavailable.");
	}
	return self;
    }

    static private Host maybeGetHost (String classname)
    throws IOException, SecurityException
    {
	try {
	    Object	temp;
	    HostFactory	factory;

	    temp = Class.forName (classname);
	    temp = ((Class)temp).newInstance ();
	    return ((HostFactory) temp).createHost ();

	} catch (SecurityException e) {
	    throw e;

	} catch (IOException e) {
	    throw e;

	} catch (Exception e) {
	    return null;
	}
    }
}
