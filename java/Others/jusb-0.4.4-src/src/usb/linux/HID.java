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

package usb.linux;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import usb.core.*;


/**
 * Simple Linux-specific HID access, using the kernel <em>hid</em>
 * and <em>evdev</em> driver modules.  If you have both of those
 * modules loaded, you may use this class; if neither is loaded,
 * you may be able to write a pure Java HID driver; otherwise, you
 * can't currently access HID devices.
 *
 * <p> <b>This is experimental</b>, and its documentation and
 * functionality is known to be incomplete.  Experiment!  You'll
 * likely want the HID spec in hand.
 *
 * @version $Id: HID.java,v 1.1 2008/10/15 09:28:20 huangmeng Exp $
 */
public class HID
{
    private Interface		iface;

    private FileInputStream	in;
    private FileOutputStream	out;

    private String		portid;

    private static Interface getHID (Device dev)
    throws IOException
    {
	Configuration		config;
	Interface		retval = null;
	DeviceDescriptor	info = dev.getDeviceDescriptor ();


	if (info.getDeviceClass () != 0)
	    throw new IllegalArgumentException ("dev class");

	config = dev.getConfiguration ();
	for (int i = config.getNumInterfaces (); i-- != 0; ) {
	    Interface intf = config.getInterface (i, 0);
	    if (intf.getInterfaceClass () == intf.CLASS_HID) {
		if (retval != null)
		    throw new IllegalArgumentException ("multi-hid");
		retval = intf;
	    }
	}
	if (retval == null)
	    throw new IllegalArgumentException ("not hid");
	return retval;
    }

    /**
     * Constructs a HID object associated with the device's
     * single HID interface.
     *
     * @exception IllegalArgumentException if the device doesn't
     *	have exactly one HID interface, or it's not claimed by the
     *	kernel HID driver
     * @exception IOException if it can't find the input device
     *	or has difficulty examining the device configuration
     */
    public HID (Device dev)
    throws IOException
    {
	this (getHID (dev));
    }


    /**
     * Constructs a HID object associated with a given interface.
     *
     * @exception IllegalArgumentException if the interface is
     *	not claimed by the kernel HID driver
     * @exception IOException if it can't find the input device
     */
    public HID (Interface intf)
    throws IOException
    {
	if (!"hid".equals (intf.getClaimer ()))
	    throw new IllegalArgumentException ("hid didn't claim");

	// we don't yet have a way to map interfaces
	// to filesystem names; for now we guess
	for (int i = 0; in == null && i < 32; i++) {
	    StringBuffer	name;

	    name = new StringBuffer ("/dev/input/event");
	    name.append (i);
	    try {
		// FIXME not all HID are "input" only
		// FIXME demand exclusive access

		in = new FileInputStream (name.toString ());

		// FIXME use EVIOCGID to check that
		// it's at least a USB device with
		// the right product/vendor/version
		// info (can't yet distinguish devs)

		iface = intf;
		break;

	    } catch (IOException e) {
		// try the next device
	    }
	}
	if (iface == null)
	    throw new IOException ("what input dev?");
	portid = iface.getDevice ().getPortIdentifier ();
    }


    /**
     * Closes the underlying "event device" file handle
     */
    public void close ()
    {
	if (in != null) {
	    try { in.close (); } catch (IOException e) { }
	    in = null;
	}
	if (out != null) {
	    try { out.close (); } catch (IOException e) { }
	    out = null;
	}
    }

    /**
     * Sends an event (not repeated).
     */
    public void sendEvent (Event event)
    throws IOException
    {
	out.write (event.toBytes ());
    }

    /**
     * Blocks until an input event is reported.
     */
    public Event readEvent ()
    throws IOException
    {
	byte buf [] = new byte [EVENT_SIZE];

	if (in.read (buf, 0, EVENT_SIZE) != EVENT_SIZE)
	    throw new IOException ("bad event read");
	return new Event (buf);
    }

    private static final int EVENT_SIZE = 16;

    public String toString ()
    {
	return "{HID at "
	    + portid
	    + " interface "
	    + iface.getNumber ()
	    + "}";
    }


    /**
     * Encapsulates HID i/o event reports.
     */
    static public class Event
    {
	// from <linux/input.h>, the event structs read/written
	// to file descriptors from /dev/input/eventNN:
	//
	// struct input_event {
	//     struct timeval time;		// int sec, usec;
	//     unsigned short type;
	//     unsigned short code;
	//     unsigned int value;
	// };
	//
	// sorry, for now you'll have to read that header and
	// know chunks of the HID support, also perhaps the
	// driver source (.../linux/drivers/input/evdev.c)

	// FIXME:  this assumes little endian host byte order

	long	time;
	short	type;
	short	code;
	int	value;

	private int getU32 (byte buf [], int offset)
	{
	    int retval = buf [offset++] & 0x0ff;
	    retval |= buf [offset++] << 8;
	    retval |= buf [offset++] << 16;
	    retval |= buf [offset++] << 24;
	    return retval;
	}

	Event (byte buf [])
	{
	    time = getU32 (buf, 0) * 1000000 + getU32 (buf, 4);
	    type = buf [8];
	    type &= (short) 0xff;
	    type |= buf [9] << 8;
	    code = buf [10];
	    type &= (short) 0xff;
	    code |= buf [11] << 8;
	    value = getU32 (buf, 12);
	}

	/** Returns the event's timestamp */
	public long getTime () { return time; }

	public short getType () { return type; }

	/** Returns a symbolic name identifying the event type */
	public String getTypeName ()
	{
	    switch (type) {
		case 0:		return "RST";
		case 1:		return "KEY";
		case 2:		return "REL";
		case 3:		return "ABS";

		case 0x11:	return "LED";
		case 0x12:	return "SND";
		case 0x14:	return "REP";
		default:
		    if (type > 0x1f)
			throw new IllegalStateException ();
	    }
	    return "Unknown-0x" + Integer.toHexString (type);
	}

	/**
	 * Returns a code associated with this event type,
	 * perhaps identifying a button, key, controller axis,
	 * or specific LED
	 */
	public short getCode () { return code; }

	/**
	 * Returns a value associated with this event type,
	 * perhaps LED settings or a relative or absolute value.
	 */
	public int getValue () { return value; }

	/** Returns a simple string representation */
	public String toString ()
	{
	    return "{Event: " + getTypeName ()
		+ ", code = " + code
		+ ", value = " + value
		+ "}"
		;
	}

	byte [] toBytes ()
	{
	    byte retval [] = new byte [EVENT_SIZE];

	    // leave timeval zeroed for now
	    retval [8] = (byte) type;
	    retval [9] = (byte) (type >> 8);
	    retval [10] = (byte) code;
	    retval [11] = (byte) (code >> 8);
	    retval [12] = (byte) value;
	    retval [13] = (byte) (value >> 8);
	    retval [14] = (byte) (value >> 16);
	    retval [15] = (byte) (value >> 24);
	    return retval;
	}
    }


    /**/

    static public void main (String argv [])
    {
	try {
	    Host	h = Linux.getHost ();
	    Bus		bus [] = h.getBusses ();

	    for (int i = 0; i < bus.length; i++) {
		for (int j = 1; j < 128; j++) {
		    Device	dev = bus [i].getDevice (j);

		    if (dev == null)
			continue;

		    System.out.println (new PortIdentifier (dev).toString ());
		    try {
			HID	hid = new HID (dev);

			System.out.println ("HID: " + hid);
			for (int k = 0; k < 20; k++) {
			    Event e = hid.readEvent ();
			    System.out.println ("Event:  " + e);
			}
			System.out.println ("done!");
			System.exit (0);
		    } catch (IllegalArgumentException e) {
			System.out.println ("skip, " + e.getMessage ());
			continue;
		    }
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace ();
	}
    }
/*
    */
}
