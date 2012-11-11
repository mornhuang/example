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

package usb.view;

import java.io.IOException;
import java.util.Vector;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultTreeModel;

import usb.core.*;


/**
 * Instance of this class represent USB Hub nodes.
 * It's used internally to the viewer.
 *
 * @author David Brownell
 * @version $Id: HubNode.java,v 1.1 2008/10/15 09:28:05 huangmeng Exp $
 */
final public class HubNode extends USBNode
{
    private final Hub		hub;
    private final int		addr;

    // synchronize against 'children' when accessing 'last'
    private final USBNode	children [];

    // same as children.length unless isFlatTree
    private int			last;

    // two display modes:  flattened, and normal
    private final boolean	isFlatTree = false;


    static final boolean	trace = false;


    // XXX the node population stuff here is a hack, it
    // should instead get filled in after initialization.

    HubNode (
	Device dev,
	TreeNode parent
    ) throws IOException
    {
	super (dev, parent);
	hub = new Hub (dev);
	addr = dev.getAddress ();

	// Flat tree display:  devices connect to root hub.
	if (isFlatTree) {
	    if (hub.isRootHub ()) {
		Bus		bus = dev.getBus ();

		children = new USBNode [127];
		last = 0;
		for (int i = 1; i <= 127; i++) {
		    Device	d = bus.getDevice (i);

		    if (d != null && d != dev)
			deviceAdded (d, null);
		}
	    } else
		children = null;

	// Normal tree display: devices connect to their hubs.
	} else {
	    children = new USBNode [hub.getNumPorts ()];
	    last = 0;

	    for (int i = 1; i <= children.length; i++) {
		Device		d = dev.getChild (i);

		if (d != null)
		    deviceAdded (d, null);
	    }
	}
    }

    // XXX set the JTree icon to be the usb hub logo ...

    public boolean getAllowsChildren ()
	{ return isFlatTree ? (children != null) : true; }

    public int getChildCount ()
    {
	return last;
    }

    public TreeNode getChildAt (int n)
	{ return children [n]; }

    public int getIndex (TreeNode n)
    {
	synchronized (children) {
	    for (int i = 0; i < last; i++)
		if (children [i] == n)
		    return i;
	}
	return -1;
    }

//    public Enumeration children ()
//	{ throw new RuntimeException ("nyi"); }

    private HubNode getHubOf (String type, Device child)
    throws IOException
    {
	// in flat model, only the root hub calls this
	if (isFlatTree)
	    return this;
	
	// am I the right hub?

	// on addition, children will not reflect it yet; go direct.
	if ("add" == type) {
	    int ports = hub.getNumPorts ();

	    for (int i = 1; i <= ports; i++) {
		if (dev.getChild (i) == child)
		    return this;
	    }
	}

	synchronized (children) {
	    // on removal, search our records for what to purge
	    if ("remove" == type) {
		for (int i = 0; i < last; i++) {
		    if (children [i].dev == child)
			return this;
		}
	    }

	    // ask the connected hubs (that we know about).
	    for (int i = 0; i < last; i++) {
		if (children [i] instanceof HubNode) {
		    HubNode	value = (HubNode) children [i];

		    if ((value = value.getHubOf (type, child)) != null)
			return value;
		}
	    }
	}
	return null;
    }

    void deviceAdded (Device dev, DefaultTreeModel model)
    throws IOException
    {
	HubNode		h = getHubOf ("add", dev);

	if (h == null) {
	    System.err.println ("what hub has " + dev);
	    return;
	} else if (h != this) {
	    h.deviceAdded (dev, model);
	    return;
	}
	

	synchronized (children) {
	    for (int i = 0; i < last; i++) {
		if (children [i].dev == dev)
		    return;
	    }
	    children [last] = createTreeNode (dev);
	    if (trace)
		System.err.println ("HubNode "
		    + addr
		    + " add child " + last
		    + ", addr " + dev.getAddress ()
		    );
	    if (model != null)
		model.nodesWereInserted (this, new int [] { last });
	    last++;
	}
    }

    void deviceRemoved (Device dev, DefaultTreeModel model)
    throws IOException
    {
	HubNode		h = getHubOf ("remove", dev);

	if (h == null) {
	    System.err.println ("HubNode: forgot about " + dev);
	    return;
	} else if (h != this) {
	    h.deviceRemoved (dev, model);
	    return;
	}

	synchronized (children) {
	    USBNode	node = null;

	    for (int i = 0; i < last; i++) {
		if (children [i] == null || children [i].dev != dev)
		    continue;
		node = children [i];

		if (trace)
		    System.err.println ("HubNode "
			+ addr
			+ " remove child " + i
			+ ", addr " + dev.getAddress ()
			);

		// shift remaining nodes down
		for (int j = i + 1; j < last; j++)
		    children [j - 1] = children [j];
		children [--last] = null;

		// report model changes, maybe
		if (model != null)
		    model.nodesWereRemoved (this,
			new int [] { i },
			new TreeNode [] { node });
	    }
	    if (trace && node == null)
		System.err.println ("HubNode no child " + dev);
	}
    }

    // create an appropriately specialized model node

// FIXME -- we need a generic driver framework, perhaps
// driven by XML config files to give binding info.
// GUI or text mode UI support options...

    private USBNode createTreeNode (Device dev)
    throws IOException
    {
	try {
	    //
	    // this should mimic what the system usbd does.  it's the
	    // same functionality, and user surprises are a non-goal.
	    //
	    // ... selection should be driven by structured text data
	    //
	    // ... probe: map dev to a java Class object, then create
	    // a new instance of that class.
	    //

	    //
	    // device-specific cases first
	    //
	    DeviceDescriptor	info = dev.getDeviceDescriptor ();
	    int			vendor = info.getVendorId ();
	    int			product = info.getProductId ();
	    
	    if (0x040a == vendor) {		// Kodak

		// DC-240 or DC-280 ... latest incarnations of
		// a protocol dating back to the DC-50
		if (0x0120 == product || 0x0130 == product)
		    return new KodakNode (dev, this);
		
		// DC-220, DC-260, DC-265, DC-290 ... DigitaOS
		if (0x0100 == product
			|| 0x0110 == product
			|| 0x0111 == product
			|| 0x0112 == product
			)
		    /* no DigitaOS class yet */ ;

	    } else if (0x0841 == vendor) {	// Diamond
		// Rio500 MP3 player
		if (0x0001 == product)
		    return new RioNode (dev, this);

	    } else if (0xf003 == vendor) {	// HP (?)
		
		// PhotoSmart C-500 is DigitaOS/USB
		if (0x6002 == product)
		    /* no DigitaOS class yet */ ;
	    }

	    //
	    // then class or interface specific code
	    // audio, comm, hid, printer, mass-storage, data
	    //
	    if ("hub".equals (info.getDeviceClassName ()))
		return new HubNode (dev, this);

	} catch (IOException e) {

	    // another driver claimed some resource?
	    System.err.println ("can't use specialized viewer node:");
	    e.printStackTrace ();

	} catch (Exception e) {
	    // custom code startup failed for some reason.
	    if (trace)
		e.printStackTrace ();
	}

	// Use generic node class
	return new USBNode (dev, this);
    }


    /** Displays hub class information, including current port status. */
    protected void showClassInfo (StringBuffer buf)
    {
	boolean		compound;

	buf.append ("<b>Hub Class Info</b><br>\n");

	buf.append ("Overcurrent protection: ");
	buf.append (hub.getOverCurrentMode ());
	buf.append ("\n<br>Power switching: ");
	buf.append (hub.getPowerSwitchingMode ());
	buf.append ("\n<br>Bus current usage: ");
	buf.append (hub.getHubCurrent ());
	buf.append (" mA.\n");
	if ((compound = hub.isCompound ()) == true)
	    buf.append ("<br>This hub is part of a compound device.\n");

	// XXX color _should_ highlight that this is "live" status

	buf.append ("<font color='maroon'>");
	buf.append ("<table>\n");

	byte status [] = new byte [4];
	for (int port = 1; port <= hub.getNumPorts (); port++) {
	    buf.append ("<tr><th>Port ");
	    buf.append (port);
	    buf.append ("</th>\n<td>");

	    try {
		status = ControlMessage.getStatus (dev,
		    (byte)(ControlMessage.TYPE_CLASS 
			| ControlMessage.RECIPIENT_OTHER),
		    0, port, 4);

		// port status flags
		if (ControlMessage.getBit (hub.PORT_CONNECTION, status, 0))
		    buf.append ("Connected ");
		if (ControlMessage.getBit (hub.PORT_ENABLE, status, 0))
		    buf.append ("Enabled ");
		if (ControlMessage.getBit (hub.PORT_SUSPEND, status, 0))
		    buf.append ("Suspended ");
		if (ControlMessage.getBit (hub.PORT_OVER_CURRENT, status, 0))
		    buf.append ("Overcurrent ");
		if (ControlMessage.getBit (hub.PORT_RESET, status, 0))
		    buf.append ("Resetting ");

		// ... flags with no notification flags
		if (ControlMessage.getBit (hub.PORT_LOW_SPEED, status, 0))
		    buf.append ("Lowspeed ");
		if (ControlMessage.getBit (hub.PORT_HIGH_SPEED, status, 0))
		    buf.append ("Highspeed ");
		if (ControlMessage.getBit (hub.PORT_INDICATOR, status, 0))
		    buf.append ("Indicator ");
		if (ControlMessage.getBit (hub.PORT_POWER, status, 0))
		    buf.append ("Powered ");
		if (compound && !hub.isRemovable (port))
		    buf.append ("(Built-in) ");
		
		// port change notification flags
		if (status [2] != 0 || status [3] != 0) {
		    buf.append ("<br>\n");
		    if (ControlMessage.getBit (hub.C_PORT_CONNECTION,
				status, 2))
			buf.append ("C_Connected ");
		    if (ControlMessage.getBit (hub.C_PORT_ENABLE, status, 2))
			buf.append ("C_Enabled ");
		    if (ControlMessage.getBit (hub.C_PORT_SUSPEND, status, 2))
			buf.append ("C_Suspend ");
		    if (ControlMessage.getBit (hub.C_PORT_OVER_CURRENT,
				status, 2))
			buf.append ("C_Overcurrent ");
		    if (ControlMessage.getBit (hub.C_PORT_RESET, status, 2))
			buf.append ("C_Reset ");
		}
		
	    } catch (IOException e) {
		buf.append ("USB Exception on getHubStatus:<br><em>");
		buf.append (e.getMessage ());
		buf.append ("</em>");
	    }

	    buf.append ("</td></tr>");
	}
	buf.append ("</table>");
	buf.append ("</font>");
    }
}
