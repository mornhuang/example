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
import java.io.Serializable;

import java.util.Dictionary;
import java.util.StringTokenizer;


/**
 * This class provides "stable" string identifiers for USB devices,
 * appropriate for use in operations and troubleshooting.  The names
 * identify the path used to access a hub port (and hence implicitly
 * identify any device connected there) on a {@link Host}.
 *
 * <p> If you control the physical cabling of a tree of USB hubs and
 * devices, you control the devices associated with these names.
 * If you don't rewire your USB tree, port identifiers won't change.
 * When you do switch cables, you can figure out the new port ids
 * by using the port numbers on your hubs (or positions, for hubs
 * that don't label their ports).
 *
 * <p> There are three basic parts in the string representation,
 * currently separated by single dashes (slashes are also reserved): <ul>
 *
 * <li> Type tag:  <em>usb</em> always.  Other sorts of physical
 * address might want to fit into a similar identifier framework.
 *
 * <li> Bus identifier, such as <em>03.02:0</em>; these are returned
 * by {@link Bus#getBusId}.  If the port identifier only has a type
 * tag and bus id, it's referring to the root hub for that bus.
 *
 * <li> Path through hubs, such as <em>2.4</em>.  Each number identifies
 * a port on a hub, using "1" for the first port.  "2.4" would mean to
 * use the second port from the root hub, which had better be connected
 * to a hub; then use the fourth port on that hub.
 *
 * </ul>
 *
 * <p> These names are distinct from everyday user-meaningful names.  A
 * convenience method is provided to support simple mappings from user
 * assigned names to physical port identifiers, using a Dictionary.
 * Use {@link Host} methods to enumerate devices which are
 * physically present, unaffected by all naming policies.  The model
 * here is direct driver-level access to devices, with security policies
 * to be established separately.
 *
 * <p> Note that with USB 2.0 hosts, port ids change depending on what
 * kind of device you plug into the root hub.  USB 2.0 devices (high speed,
 * 480 Mbit/s) get switched to a different controller than USB 1.1 devices
 * (slower, at 12 Mbit/s or 1.5 Mbit/s).  The identifiers are consistent
 * and still predictable, but you know whether you're talking about a
 * USB 2.0 device or not.
 *
 * @see Host
 *
 * @author David Brownell
 * @version $Id: PortIdentifier.java,v 1.1 2008/10/15 09:27:57 huangmeng Exp $
 */
final public class PortIdentifier
{
    /** Host-assigned identifier for the bus.  */
    private String		busId;

    /**
     * Path to port:  port numbers for each hub, branching
     * from the root.
     */
    private short		port [];


    /**
     * Constructs an identifier for the physical location of this device,
     * scoped to the associated host.
     */
    public PortIdentifier (Device dev)
    throws IOException
    {
	int	len = 0;
	Device	node = dev;

	for (Device temp = node;
		(temp = node.getHub ()) != null;
		node = temp)
	    len++;
	if (len > 5)		// tree size limit from USB 1.1 spec
	    throw new IllegalArgumentException ("illegal depth: " + len);

	busId = node.getBus ().getBusId ();
	port = new short [len];

	for (node = dev; len-- > 0; node = node.getHub ())
	    port [len] = (short) node.getHubPortNum ();
    }

    /**
     * Constructs a port identifier using its string form.
     *
     * @exception IllegalArgumentException if the parameter isn't
     *	a valid port identifier string.
     */
    public PortIdentifier (String path)
    {
	StringTokenizer	tokens = new StringTokenizer (path, "-");

	if (!tokens.hasMoreTokens ()
		|| !"usb".equals (tokens.nextToken ())
		|| !tokens.hasMoreTokens ())
	    throw new IllegalArgumentException (path);
	busId = tokens.nextToken ();

	// root hub
	if (!tokens.hasMoreTokens ()) {
	    port = new short [0];
	    return;
	}

	// device on some hub port
	String		route = tokens.nextToken ();
	int		i;
	short		scratch [] = new short [5];

	tokens = new StringTokenizer (route, ".");
	for (i = 0; i < scratch.length && tokens.hasMoreTokens (); i++) {
	    String temp = tokens.nextToken ();
	    scratch [i] = Short.parseShort (temp);
	}
	if (tokens.hasMoreTokens ())
	    throw new IllegalArgumentException ();
	if (i == 5)
	    port = scratch;
	else {
	    port = new short [i];
	    System.arraycopy (scratch, 0, port, 0, i);
	}
    }

    /**
     * Returns the appropriate device from this host, or null if the address
     * describes a port connection that isn't available just now.  Keep in
     * mind that it might not actually be the same device which was at that
     * position last time; check at least the device type against what was
     * expected, and other identifiers (serial number?) where they matter.
     *
     * @see Host#getDevice
     */
    public Device getDevice (Host host)
    throws IOException
    {
	Bus	busses [], bus = null;

	// Is there a likely Universal Serial Bus?
	if (host == null)
	    return null;
	try {
	    busses = host.getBusses ();
	    for (int i = 0; i < busses.length; i++) {
		if (!busses [i].getRootHub ()
			.getDeviceDescriptor ().getSerial (0)
			.equals (busId))
		    continue;
		bus = busses [i];
		break;
	    }
	    if (bus == null)
		return null;
	
	    // see if such a device exists.
	    Device	dev = bus.getRootHub ();

	    for (int i = 0; i < port.length && dev != null; i++) {
		dev = dev.getChild (port [i]);
	    }
	    return dev;
	} catch (Exception e) {
	    // fallthrough
	}
	return null;
    }

    /**
     * Provides a printable (and restorable) form of this path, for example
     * <em>usb-00:0b.0-3</em> identifying the device on the third port on
     * the root hub of the USB controller on PCI slot 0:0b.0.
     */
    public String toString ()
    {
	StringBuffer buf = new StringBuffer (20);

	buf.append ("usb");
	buf.append ('-');
	buf.append (busId);
	if (port.length != 0) {
	    buf.append ('-');
	    for (int i = 0; i < port.length; i++) {
		if (i != 0)
		    buf.append ('.');
		// workaround for kjc 1.0.6 bug where
		// port[i] was handled as char not int
		buf.append (Integer.toString (port [i]));
	    }
	}
	return buf.toString ();
    }

    /**
     * This maps names to devices, using a dictionary to associate
     * user-meaningful names like <code>ReceiptPrinter</code> or
     * <code>RedCable</code> with port identifier strings like
     * <em>usb-00:0b.0-1.3</em>.  If the name isn't found in the
     * dictionary, but is a valid port identifier, then the device
     * at that port is returned.
     *
     * @param dict Maps names to port identifier strings
     * @param name Used as lookup key in the dict.
     * @return Handle to the device with the port identifier that
     *	corresponds to the name; or null if none is present, or
     *	if the identifier was faulty, or on getDevice error.
     */
    public static Device lookup (Host host, Dictionary dict, String name)
    {
	String		portId = (String) dict.get (name);
	PortIdentifier	id = null;

	try {
	    if (portId != null)
		id = new PortIdentifier (portId);
	} catch (IllegalArgumentException e) {
	    try {
		id = new PortIdentifier (name);
	    } catch (IllegalArgumentException x) {
	    }
	}
	try {
	    return id == null ? null : id.getDevice (host);
	} catch (IOException e) {
	    return null;
	}
    }
}
