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
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import usb.core.*;


/**
 * Represents a Universal Serial Bus (USB), which hosts a set of devices.
 * Such buses are largely autoconfiguring.  Aspects such as power
 * management and detailed device configuration involve policy choices,
 * which may be modified after the OS kernel (or other infrastructure)
 * has first set them up.
 *
 * <p> The bus can be examined device by device.  Since devices can be
 * inserted and removed at any time there is no notion of a self-consistent
 * listing snapshot in this API.  Rather than scanning the bus, instead
 * use {@link Host#addUSBListener addUSBListener}
 * with an object that can immediately
 * react to device addition or removal.
 *
 * <p> Note that for consistency with USB itself, bus addresses
 * start at one (not zero). 
 *
 * @see Host
 *
 * @author David Brownell
 * @version $Id: USB.java,v 1.1 2008/10/15 09:28:20 huangmeng Exp $
 */
final class USB implements Bus
{
    /** @serial the host to which this bus is connected */
    final private Host			host;

    // n.b. busfile is like /proc/bus/usb/NNN (decimal)
    final transient private File	busfile;
    final transient private int		busnum;

// FIXME:  root can change over suspend/resume cycle ...
    private transient int		root = -1;

    final transient private Vector	listeners;
    final transient private DeviceImpl	devices [] = new DeviceImpl [127];

    // package private
    USB (File parent, String file, int num, Vector l, Host h)
    throws IOException
    {
	busfile = new File (parent, file);
	busnum = num;
	listeners = l;
	host = h;
    }

    public String toString ()
    {
	StringBuffer buf = new StringBuffer ("{ Linux Bus: ");
	buf.append (busfile.toString ());
	if (root >= 0) {
	    buf.append (" root = ");
	    buf.append (root);
	    buf.append (" id = ");
	    buf.append (getBusId ());
	}
	buf.append ("}");
	return buf.toString ();
    }

    /** Returns the USB host for this bus. */
    public Host getHost ()
	{ return host; }

    /** Returns the number assigned to this bus. */
    public int getBusNum ()
	{ return busnum; }

    /** Returns the root hub of the bus, if it is known yet. */
    public Device getRootHub ()
	{ return (root >= 0) ? devices [root] : null; }

    public String getBusId ()
    {
	if (root < 0)
	    return null;
	return devices [root].getDeviceDescriptor ().getSerial (0);
    }

    /**
     * Returns an object representing the device with the specified
     * address (1 through 127), or null if no such device exists.
     */
    public Device getDevice (int address)
    {
	return devices [address - 1];
    }

    // assemble new tree
    private void newTree (String names [])
    throws SecurityException
    {
	DeviceImpl	hub = null;

	// create devices
	for (int i = 0; i < names.length; i++) {
	    if (devices [i] == null) {
		if (names [i] == null)
		    continue;
		try {
		    File f = new File (busfile, names [i]);
		    devices [i] = new DeviceImpl (this, f, i + 1);
		} catch (IOException e) {
		    if (Linux.debug)
			e.printStackTrace ();
		    else
			System.err.println ("can't create dev: "
			    + e.getMessage ());
		}
	    }
	}

	// update lists of children; find some interior node
	for (int i = 0; i < 127; i++) {
	    if (devices [i] == null)
		continue;
	    if (devices [i].getDeviceDescriptor ()
		    .getDeviceClass () != Descriptor.CLASS_HUB)
		continue;
	    devices [i].updateChildren ();
	    if (hub == null)
		hub = devices [i];
	}

	// walk up from that node to the root
	DeviceImpl 	parent;

	root = -1;
	for ( ; hub != null; hub = parent) {
	    parent = (DeviceImpl) hub.getHub ();
	    if (parent == null) {
		root = hub.getAddress () - 1;
		added (hub);
		break;
	    }
	}
	if (Linux.trace)
	    System.err.println ("Got root: " + this);
    }

    private boolean checkHub (String type, DeviceImpl dev, String relevant [])
    {
	boolean	needUpdate = false;
	boolean reported = false;
	int	ports = dev.getNumPorts ();

	if (type == "add")
	    dev.updateChildren ();

	for (int port = 1; port <= ports; port++) {
	    DeviceImpl		child = (DeviceImpl) dev.getChild (port);
	    int			index;
	    DeviceDescriptor	d;

	    if (child == null)
		continue;
	    index = child.getAddress () - 1;

	    // additions reported from root to leaves
	    if (type == "add" && relevant [index] != null) {
		if (Linux.trace)
		    System.err.println ("checkHub add, "
			+ " dev" + dev.getAddress ()
			+ " [" + port
			+ "] dev" + (index + 1)
			);
		added (child);
		reported = true;
	    }

	    d = child.getDeviceDescriptor ();
	    if (d.getDeviceClass () == d.CLASS_HUB)
		reported = checkHub (type, child, relevant) || reported;

	    // removals reported from leaves to root
	    if (type == "remove" && relevant [index] == null) {
		if (Linux.trace)
		    System.err.println ("checkHub remove, "
			+ " dev" + dev.getAddress ()
			+ " port" + port
			+ "  = dev" + (index + 1)
			);
		removed (child);
		devices [index] = null;
		needUpdate = true;
	    }
	}

	// disconnect now-orphaned nodes
	if (needUpdate)
	    dev.updateChildren ();

	return reported;
    }

    // returns true if changes were detected
    // package private
    boolean scanBus ()
    throws SecurityException
    {
	boolean retval = false;

	// FIXME: when kernel sets mtime of bus directories right, we
	// can optimize: compare mtime against lastTime, and maybe stop.

	synchronized (devices) {
	    String	devs [] = busfile.list ();
	    String	names [] = new String [127];
	    boolean	addAll = false;

	    for (int i = 0; i < devs.length; i++) {
		try {
		    int	index = Integer.parseInt (devs [i]) - 1;
		    names [index] = devs [i];
		} catch (Exception e) {
		    if (Linux.debug)
			System.err.println ("illegal name: "
			    + busfile + "/" + devs [i]);
		}
	    }

	    // init or reinit (e.g. PM-induced restart)
	    if (root < 0 || names [root] == null) {
		newTree (names);
		addAll = true;
	    
	    // flag any removals from existing tree
	    } else
		retval = checkHub ("remove", devices [root], names);

	    // catch any new devices
	    for (int index = 0; index < 127; index++) {
		DeviceImpl	 dev = devices [index];

		if (dev == null) {
		    // new device ... report later
		    if (names [index] != null) {
			try {
			    File f = new File (busfile, names [index]);
			    dev = new DeviceImpl (this, f, index + 1);
			    devices [index] = dev;
			    retval = true;
			} catch (IOException e) {
			    // Maybe:  between the directory scan and
			    // creating the device object, it went away.

			    // Or it doesn't report its descriptors on
			    // demand, like it's supposed to ...

			    if (Linux.debug)
				e.printStackTrace (System.err);
			}
		    }
		    // else: as expected

		} else {
		    // usbdevfs keeps filenames pinned while we
		    // have open file descriptors.  So we know
		    // that if we have a dev, we reported it.
		    if (names [index] != null && !addAll)
			names [index] = null;
		}
	    }

	    // report any additions in order (inside out)
	    // ... for devices not removed from names
	    checkHub ("add", devices [root], names);
	}
	return retval;
    }

    // package private
    void kill ()
    {
	if (Linux.trace)
	    System.err.println ("kill bus" + this);

	// notify any listeners that the bus died, and
	// clear backlinks that we control
	if (listeners.size () > 0) {
	    synchronized (devices) {
		for (int i = 0; i < devices.length; i++) {
		    if (devices [i] == null)
			continue;
		    removed (devices [i]);
		}
	    }
	}
    }

    private void added (DeviceImpl dev)
    {
	if (Linux.trace)
	    System.err.println ("notify add " + dev);

	// call synch'd on devices
	for (int i = 0; i < listeners.size (); i++) {
	    USBListener	listener;
	    listener = (USBListener) listeners.elementAt (i);
	    try { listener.deviceAdded (dev); }
	    catch (Exception e) {
		if (Linux.debug)
		    e.printStackTrace ();
	    }
	}
    }

    private void removed (DeviceImpl dev)
    {
	if (Linux.trace)
	    System.err.println ("notify remove " + dev);
	
	// call synch'd on devices
	for (int i = 0; i < listeners.size (); i++) {
	    USBListener	listener;
	    listener = (USBListener) listeners.elementAt (i);
	    try { listener.deviceRemoved (dev); }
	    catch (Exception e) {
		if (Linux.debug)
		    e.printStackTrace ();
	    }
	}
	try { dev.close (); }
	catch (IOException e) { /* ignore */ }
    }

    // package private ... for DeviceImpl.close() only !!
    void removeDev (DeviceImpl dev)
    {
	synchronized (devices) {
	    DeviceImpl	d = devices [dev.getAddress () - 1];
	    int		i;

	    if (d == null || d != dev)
		return;
	    i = d.getAddress () - 1;
	    devices [i] = null;
	    if (root == i) {
		root = -1;
		if (Linux.trace)
		    System.err.println ("bus root hub removed!");
		if (Linux.debug) {
		    for (i = 0; i < 127; i++) {
			if (devices [i] != null)
			    System.err.println ("? addr " + (i + 1)
				    + " present with no root ?");
		    }
		}
	    }
	}
    }


    // XXX Want management operations to put the bus (via root
    // hub) through the various states:  reset, suspend, resume,
    // operational.  We can invoke reset/suspend/resume methods
    // on the root hub ports, but that's not the same thing.

    // XXX similarly, PM interactions ... hmmm ...
}
