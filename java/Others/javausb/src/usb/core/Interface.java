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
 * USB interfaces describe sets of endpoints, and are associated with
 * a particular device configuration.  Applications must claim interfaces
 * before they can communicate with devices using any method other than
 * control messaging, to structure the sharing of device resources
 * between different drivers.
 *
 * <p> Interfaces may have alternate
 * settings, affecting issues such as bandwidth consumption.  For example, a
 * given video camera interface might use alternate settings to provide
 * control over multiple image sizes and compression options, or to
 * reduce USB bandwidth utilization.  Applications make sure they are claiming
 * the right alternate setting, and be aware of claims other modules may have
 * made on the interface, before they try to claim use of a device interface.
 *
 * <p> Most fields of this descriptor are specified in section 9.6.3 of
 * the USB 1.1 specification.
 *
 * @version $Id: Interface.java,v 1.1 2008/10/15 09:27:57 huangmeng Exp $
 */
final public class Interface extends Descriptor
{
    /** @serial Configuration exposing this interface */
    private Configuration	config;

    private Endpoint		endpoints [];
    private boolean		claimed;

    // package private
    Interface (Configuration conf, int offset)
    {
	super (conf, offset);
	config = conf;
	if (getDescriptorType () != TYPE_INTERFACE)
	    throw new IllegalArgumentException ();
	endpoints = new Endpoint [getU8 (4)];
    }

    public
    Interface (Configuration conf, byte buf [])
    {
	super (buf);
	config = conf;
	if (getDescriptorType () != TYPE_INTERFACE)
	    throw new IllegalArgumentException ();
    }

    // access to interface-specific descriptor fields

    /** Returns the interface number. */
    public int getNumber ()
	{ return getU8 (2); }

    /**
     * Used to identify alternate setting for an interface.
     * Interfaces may have multiple alternates; only one of them
     * may be claimed at a time.
     */
    public int getAlternateSetting ()
	{ return getU8 (3); }

    /**
     * Returns the number of endpoints in this interface.
     */
    public int getNumEndpoints ()
	{ return endpoints.length; }

    /** Returns the interface class */
    public int getInterfaceClass ()
	{ return getU8 (5); }

    /** Returns the name of interface class */
    public String getInterfaceClassName ()
	{ return getClassName (getInterfaceClass (), "interface"); }

    /** Returns the interface subclass */
    public int getInterfaceSubClass ()
	{ return getU8 (6); }

    /** Returns the interface protocol */
    public int getInterfaceProtocol ()
	{ return getU8 (7); }

    /**
     * Returns a string describing this interface in the specified language,
     * or null if no such string is available.
     */
    public String getInterface (int language)
    {
	try {
	    int id = getInterfaceStringId ();
	    if (id > 0)
		return getDevice ().getString (id, language);
	} catch (IOException e) {
	}
	return null;
    }

    /**
     * Returns the identifier of the string describing this interface, or zero.
     */
    public int getInterfaceStringId ()
	{ return getU8 (8); }

    public Device getDevice ()
	{ return config.getDevice (); }


    /** Returns the configuration with which the interface is associated */
    public Configuration getConfiguration ()
	{ return config; }

    /**
     * Returns descriptor for an endpoint in this interface.
     * @exception IOException if the specified descriptor is unavailable
     */
    public Endpoint getEndpoint (int index)
    throws IOException
    {
	// FIXME: don't even try this if we're not the
	// current altsetting !!

	synchronized (endpoints) {
	    if (endpoints [index] == null) {
		int	offset = getOffset ();
		int	count = index;

		do {
		    offset = nextDescriptorOffset (offset);
		    if (descriptorType (offset) == TYPE_ENDPOINT)
			count--;
		} while (count >= 0 && offset > 0);
		if (offset > 0)
		    endpoints [index] = new Endpoint (this, offset);
		else {
		    // FIXME try getStdDescriptor() with TYPE_ENDPOINT,
		    // though not many devices seem to support it
		    throw new IOException ("missing endpoint descriptor");
		}
	    }
	}
	return endpoints [index];
    }


    /**
     * Claims this interface if it is not claimed by some other module,
     * and assigns the appropriate alternate setting.
     *
     * <p><em>NOTE (may be temporary):</em> This may force a claim by
     * some other module to be released.  It may be preferable to expose
     * an API to let that be requested.  For now, applications should
     * probably avoid claiming interfaces that another module claimed.
     *
     * @see #getClaimer()
     *
     * @returns true if a new claim was made, and this alternate
     *	setting was made.
     * @exception IOException if some other module claimed
     *	this interface instead; or if the alternate setting
     *	could not be assigned.
     */
    public boolean claim () throws IOException
    {
	if (claimed)
	    return false;
	getDevice().getSPI ().claimInterface (getNumber ());
	claimed = true;
	setAlternate ();
	return true;
    }


    /**
     * Releases a previous claim on this interface.
     * @exception IllegalStateException if it was unclaimed
     */
    public void release () throws IOException
    {
	if (!claimed)
	    throw new IllegalStateException ();
	getDevice().getSPI ().releaseInterface (getNumber ());
	claimed = false;
    }


    /**
     * Returns a string identifying the driver which has claimed this
     * interface, or null.  At this writing, this claim may be silently
     * (to the application)
     * be undone if this thread claims this interface.  Also, these
     * strings always identify kernel drivers (including usbdevfs).
     *
     * <p><em>With the addition of remote device support, it is now
     * practical to talk with devices managed by other Java processes.
     * That will require recording non-kernel driver claims.</em>
     *
     * @see #claim()
     */
    public String getClaimer ()
    throws IOException
    {
	String value = getDevice().getSPI ().getClaimer (getNumber ());

	return "".equals (value)
	    ? null
	    : value;
    }

	// FIXME:  This needs to feed back to Configuration.
	// Likely there also needs to be an SPI to find what
	// setting is current, and event callbacks to to keep
	// clients (other processes ...) in sync.

	// FIXME:  configuration changing has the same kind
	// of relationship to the device:  update "caches".


    /**
     * Sets this as the alternate setting for this interface.
     * <em>Other interfaces with the same interface number should then
     * not be used to interact with this device!</em>
     */
    private void setAlternate () throws IOException
    {
	getDevice().getSPI ().setInterface (getNumber (), getAlternateSetting ());
    }
}
