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
 * Bundles access to a USB Hub descriptor and some hub operations.  You will
 * access underlying device directly, to examine and modify hub state, but
 * for many purposes will use constants (notably feature IDs) and hub data
 * as exposed through this class.
 *
 * <p> The hub class is defined in chapter 11 of the USB specification, and
 * you'll need the later sections of that chapter to talk to a hub.  You'll use
 * {@link ControlMessage#setFeature setFeature} and
 * {@link ControlMessage#clearFeature clearFeature} to change feature
 * flags, and {@link ControlMessage#getStatus getStatus} or the hub
 * descriptor (many methods on this class) for your indicators.
 *
 * <p> Because the kernel hub driver claims exclusive access to the hub
 * interrupt endpoint (the hub "status change pipe"),
 * user mode programs don't have direct access to that
 * data.  It's a stream of masks, often one byte each, with a bit set for
 * each port with a status change (C_PORT_*) notification bit set.  You
 * can access much of the same information by hub and port status queries.
 * If you see change notifications (C_*) managed by the kernel driver, do
 * not clear or respond to these.
 *
 * <p> Note that for consistency with USB itself, port numbers start
 * with one instead of zero.
 *
 * @version $Id: Hub.java,v 1.1 2008/10/15 09:28:15 huangmeng Exp $
 */
final public class Hub extends Descriptor
{
    private Device	dev;
    private int		ports;

    /*-------------------------------------------------------------*/

	// C_* are all clearable indicators
	// C_HUB_* for port events (clear hub feature)
	// C_PORT_* for port events (clear port feature)

	// hub status (byte offset 0) isn't changeable like the
	// indicators (byte offset 2); use the same constants

    /** Hub change indicator, indicating local power */
    public static final byte	C_HUB_LOCAL_POWER = 0;

    /** Hub change indicator, indicating overcurrent status (for safety).  */
    public static final byte	C_HUB_OVER_CURRENT = 1;

    /*-------------------------------------------------------------*/

    /** Port feature selector, indicating whether a device is connected. */
    public static final byte	PORT_CONNECTION = 0;

    /** Port feature selector, controlling whether the port is enabled. */
    public static final byte	PORT_ENABLE = 1;

    /** Port feature selector, controlling "suspend" state for the port. */
    public static final byte	PORT_SUSPEND = 2;

    /** Port feature selector, indicating an overcurrent state for the port. */
    public static final byte	PORT_OVER_CURRENT = 3;

    /** Port feature selector, set to initiate a port reset. */
    public static final byte	PORT_RESET = 4;

    /*-------------------------------------------------------------*/

    /**
     * Port feature selector, controlling power availability to this
     * port (subject to gang power switching rules).
     */
    public static final byte	PORT_POWER = 8;

    /** Port feature selector, indicates if an enabled port is low speed USB. */
    public static final byte	PORT_LOW_SPEED = 9;

    /** Port feature selector, indicates if an enabled port is high speed. */
    public static final byte	PORT_HIGH_SPEED = 10;

    /** Port feature selector, indicates if an enabled port is test mode. */
    public static final byte	PORT_TEST = 11;

    /** Port feature selector, indicates if enabled port has an indicator. */
    public static final byte	PORT_INDICATOR = 12;

    /*-------------------------------------------------------------*/
	
	// C_* are all clearable indicators
	// these are in the SECOND 16-bit word of status

    /** Port feature selector, indicating a device was attached or detached. */
    public static final byte	C_PORT_CONNECTION = 0;

    /** Port feature selector, indicating a port disabled due to error. */
    public static final byte	C_PORT_ENABLE = 1;

    /** Port feature selector, indicating port resume is complete. */
    public static final byte	C_PORT_SUSPEND = 2;

    /** Port feature selector, reporting changed per-port overcurrent status. */
    public static final byte	C_PORT_OVER_CURRENT = 3;

    /** Port feature selector, indicating reset processing is complete. */
    public static final byte	C_PORT_RESET = 4;

    /*-------------------------------------------------------------*/


    /**
     * Constructs a hub object wrapping the specified USB hub device.
     */
    public Hub (Device dev)
    throws IOException
    {
	super (ControlMessage.getClassDescriptor (dev,
		TYPE_HUB, (byte) 0, 0, 10));
	this.dev = dev;

	if (dev.getDeviceDescriptor ().getDeviceClass () != CLASS_HUB)
	    throw new IllegalArgumentException ();
	if (getDescriptorType () != TYPE_HUB)
	    throw new IllegalArgumentException (
		Integer.toHexString (getDescriptorType ())
		);
	if ((ports = getU8 (2)) > 15)	// more bytes to read?
	    throw new RuntimeException ("nyi");
    }

    public Device getDevice () { return dev; }

    /**
     * Returns true if this is the root hub.
     */
    public boolean isRootHub ()
    throws IOException
    {
	return dev.getHub () == null;
    }

    /**
     * Returns the number of ports on this hub.
     * USB ports are numbered beginning at one.
     */
    public int getNumPorts ()
    {
	return ports;
    }

    /**
     * Returns the hub characteristics bitmap.
     * @see #getPowerSwitchingMode
     * @see #isCompound
     * @see #getOverCurrentMode
     */
    public int getHubCharacteristics ()
    {
	return getU16 (3);
    }

    /**
     * Returns the power logical power switching mode, "ganged",
     * "switched", or otherwise indicating an old USB 1.0 hub.
     */
    public String getPowerSwitchingMode ()
    {
	switch (getHubCharacteristics () & 0x0003) {
	    case 0x00:	return "ganged";
	    case 0x01:	return "switched";
	    case 0x02:	return "reserved-2";
	    case 0x03:	return "reserved-3";
	}
	return "?";
    }

    /** Returns true if this hub is part of a compound device. */
    public boolean isCompound ()
    {
	return (getHubCharacteristics () & 0x0004) != 0;
    }

    /**
     * Returns the overcurrent protection mode, "global",
     * "per-port"; or only for USB 1.0 hubs, "none".
     */
    public String getOverCurrentMode ()
    {
	switch (getHubCharacteristics () & 0x0018) {
	    case 0x00:	return "global";
	    case 0x08:	return "per-port";
	    default:	return "none";
	}
    }

    /** Returns the power-on to power-good time, in units of 2ms. */
    public int getPOTPGT ()
    {
	return getU8 (5);
    }

    /**
     * Returns the maximum current requirement of the hub electronics,
     * in mA units.
     */
    public int getHubCurrent ()
    {
	return getU8 (6);
    }

    /**
     * Returns true if the specified port (one-based) is removable.
     * Non-removable ports may be built in to compound devices, such
     * as keyboards with built-in hubs.
     */
    public boolean isRemovable (int port)
    {
	int	b;

	if (port <= 0 || port > ports)
	    throw new IllegalArgumentException ();

	b = getU8 (7 + (port / 8));
	b >>= port % 8;
	return (b & 0x01) == 0;
    }

    // not exposing "must-be-ones" port power control mask


    /**
     * Suspends the port.  Port must be operational.  It will resume
     * automatically if necessarary, or by explicit resume().
     *
     * <p><em>Use with caution</em>; parent/child relationships are
     * tricky, you may not affect the device you intend. 
     */
    public void suspend (int port)
    throws IOException
    {
	if (port <= 0 || port > ports)
	    throw new IllegalArgumentException ();
	ControlMessage.setFeature (dev,
	    ControlMessage.TYPE_CLASS | ControlMessage.RECIPIENT_OTHER,
	    PORT_SUSPEND,
	    port
	    );
    }

    /**
     * Resumes the port.  Ignored if port isn't suspended.
     *
     * <p><em>Use with caution</em>; parent/child relationships are
     * tricky, you may not affect the device you intend. 
     */
    public void resume (int port)
    throws IOException
    {
	if (port <= 0 || port > ports)
	    throw new IllegalArgumentException ();
	ControlMessage.clearFeature (dev,
	    ControlMessage.TYPE_CLASS | ControlMessage.RECIPIENT_OTHER,
	    PORT_SUSPEND,
	    port
	    );
    }

    /**
     * Resets the port (and the device connected to it).
     *
     * <p><em>Use with caution</em>; parent/child relationships are
     * tricky, you may not affect the device you intend. 
     */
    public void reset (int port)
    throws IOException
    {
	if (port <= 0 || port > ports)
	    throw new IllegalArgumentException ();
	ControlMessage.setFeature (dev,
	    ControlMessage.TYPE_CLASS | ControlMessage.RECIPIENT_OTHER,
	    PORT_RESET,
	    port
	    );
    }
}
