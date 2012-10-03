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

package usb.util;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import usb.core.*;


/**
 * This provides basic access to USB interfaces which use only bulk I/O,
 * and for devices which only offer such interfaces.   Such access is
 * found in many digital still cameras (including all current Kodak
 * USB-aware cameras, and other cameras using the Digita OS), the Diamond
 * Rio500 MP3 player, and other USB devices.
 *
 * <p> As with network sockets, be sure to close() these when you're
 * no longer accessing the device.  That permits other software to connect
 * to the device.
 *
 * @version $Id: USBSocket.java,v 1.1 2008/10/15 09:28:22 huangmeng Exp $
 */
public final class USBSocket
{
    private Interface		intf;
    private InputStream		in;
    private OutputStream	out;

    /**
     * Initializes the socket using a bulk-only device.
     *
     * @exception IOException if the interface is already claimed by
     *	another driver module; or if the configuration, interface, or
     *	endpoints can't be accessed; and for other reasons.
     * @exception IllegalArgumentException if the interface doesn't
     *	have exactly two endpoints, for bulk input and bulk output.
     */
    public USBSocket (Device dev)
    throws IOException
    {
	DeviceDescriptor	info = dev.getDeviceDescriptor ();

	if (info.getNumConfigurations () != 1
		|| dev.getConfiguration ().getNumInterfaces () != 1
		// or that interface has alternates
		)
	    throw new IllegalArgumentException ();
	init (dev.getConfiguration ().getInterface (0, 0));
    }

    /**
     * Initializes the socket using a bulk-only interface on a
     * device which may have additional interfaces.
     *
     * @exception IOException if the interface is already claimed by
     *	another driver module, or if the endpoints can't be accessed.
     * @exception IllegalArgumentException if the interface doesn't
     *	have exactly two endpoints, for bulk input and bulk output.
     */
    public USBSocket (Interface intf)
    throws IOException
    {
	init (intf);
    }

    private void init (Interface intf)
    throws IOException
    {
	if (intf == null || intf.getNumEndpoints () != 2)
	    throw new IllegalArgumentException ();

	// XXX presumably this handles the "multiple devfs claims" case; check.

	intf.claim ();
	this.intf = intf;

	try {
	    for (int i = 0; i < intf.getNumEndpoints (); i++) {
		Endpoint	ep = intf.getEndpoint (i);
		
		if (!"bulk".equals (ep.getType ()))
		    throw new IllegalArgumentException ();
		if (ep.isInput ())
		    in = ep.getInputStream ();
		else
		    out = ep.getOutputStream ();
	    }
	    if (in == null || out == null)
		throw new IllegalArgumentException ();
	} catch (IOException e) {
	    try { close (); } catch (Exception x) { intf = null; } 
	    throw e;
	} catch (RuntimeException e) {
	    try { close (); } catch (Exception x) { intf = null; } 
	    throw e;
	}
    }

    /**
     * Releases the interface, so that some other software driver can
     * use it to access this device.  Both directions of the socket
     * are closed down.  The behavior seen by other threads trying
     * to send or receive data is undefined.
     */
    public void close () throws IOException
    {
	in = null;
	out = null;
	if (intf != null)
	    intf.release ();
	intf = null;
    }

    /**
     * Finalization releases the interface, if it has not already been
     * released by invoking close().
     */
    protected void finalize () throws IOException
    {
	close ();
    }

    /**
     * Returns an input stream used to perform bulk reads from the interface, or null.
     */
    public InputStream getInputStream () { return in; }

    /**
     * Returns an output stream used to perform bulk writes to the interface, or null.
     */
    public OutputStream getOutputStream () { return out; }

    /**
     * Returns the device to which this socket is connected, or null if the
     * device has been closed or removed.
     */
    public Device getDevice () { return (intf != null) ? intf.getDevice () : null; }
}
