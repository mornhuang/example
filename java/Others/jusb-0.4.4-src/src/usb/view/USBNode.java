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

import java.util.Enumeration;
import java.util.Locale;

import javax.swing.JEditorPane;
import javax.swing.tree.TreeNode;

import usb.core.*;
import usb.util.LangCode;


/**
 * Models entities, in particular devices, that are displayed using
 * this package's USB tree viewer.
 *
 * <p> Subclassers will commonly provide support specific to a class
 * or device device information.  If the device wants to present children
 * (such as hub ports, files of images or audio data, or something else)
 * then the three relevant methods should be overridden:
 * {@link #getAllowsChildren}, {@link #getChildAt} and
 * {@link #getChildCount}.
 * By default, nodes provide textual descriptions using HTML, and 
 * subclasses can add more information by overriding
 * {@link #showClassInfo}.
 * If a non-HTML display is needed, override {@link #draw}.
 *
 * @version $Id: USBNode.java,v 1.1 2008/10/15 09:28:26 huangmeng Exp $
 */
public class USBNode implements TreeNode
{
    /**
     * The device node which is associated with this node, or null.
     */
    final protected Device	dev;

    /**
     * Swing's tree model requires nodes to know their parents.
     */
    final protected TreeNode	parent;

    /**
     * This node's device descriptor, or null.
     */
    final private DeviceDescriptor	info;

    /** 
     * We cache the port identifier to avoid recomputing it all the time
     */
    final private String	portid;

    /**
     * Constructs a tree node model.
     */
    public USBNode (Device device, TreeNode nodeParent)
    throws IOException
    {
	dev = device;
	parent = nodeParent;

	// Host node?
	if (device == null) {
	    info = null;
	    portid = null;
	} else {
	    info = device.getDeviceDescriptor ();
	    portid = device.getPortIdentifier ();
	}
    }

    // These five methods get overridden to support children.

    /** Reports that children are not allowed.  */
    public boolean getAllowsChildren ()
	{ return false; }
    /** Returns null. */
    public TreeNode getChildAt (int n)
	{ return null; }
    /** Returns zero. */
    public int getChildCount ()
	{ return 0; }
    /** Not implemented. */
    public int getIndex (TreeNode n)
	{ throw new RuntimeException ("nyi"); }
    /** Not implemented. */
    public Enumeration children ()
	{ throw new RuntimeException ("nyi"); }

    /** Returns the parent provided to the constructcor. */
    final public TreeNode getParent ()
	{ return parent; }
    /** Returns true for nodes that don't allow children. */
    final public boolean isLeaf ()
	{ return !getAllowsChildren (); }
    

    /**
     * Returns the label to be used displaying this tree node.
     * This uses the device's product string if one exists, else
     * the name of the device class.  If no device is available,
     * the default java.lang.Object method is used.
     */
    public String toString ()
    {
	if (dev != null) {
	    String retval = info.getProduct (0);
	    if (retval == null)
		retval = info.getDeviceClassName ();
	    return retval;
	} else
	    return super.toString ();
    }

    /**
     * Renders this node in an auxiliary pane.
     * The base class uses a simple HTML rendering of any available device
     * information, both standard and (through {@link #showClassInfo})
     * class-specific.  Subclasses may choose to use a non-HTML display,
     * or a different sort of HTML display,
     * by overriding this method.
     */
    public void draw (JEditorPane editor)
    {
	StringBuffer	buf = new StringBuffer ();
	String		name = toString ();

	editor.setContentType ("text/html");

	buf.append ("<html>\n<head><title>");
	buf.append (name);
	buf.append ("</title></head>\n<body><font size=+1><b>");
	buf.append (name);
	buf.append ("</b></font><br>\n");

	editor.setText (buf.toString ()
	    + "... querying for current device status ..."
	    + "</body></html>"
	    );
	editor.repaint ();

	if (dev != null) {
	    try {
		showDeviceInfo (buf);
		showClassInfo (buf);
		showConfigInfo (buf);
	    } catch (IOException e) {
		e.printStackTrace ();
	    }
	}
	buf.append ("</body></html>");

	// the config info could be cached.
	// other bits involve "live" status.

	editor.setText (buf.toString ());
    }

    /**
     * This method is a hook to provide class-specific data (in HTML)
     * when this node is selected.  Subclasses using the default HTML
     * display should append markup to the string buffer, ending in
     * an empty line (use <em>&lt;br&gt;;&lt;br&gt;</em>).  Note that
     * this can be used both to provide standardized USB class data
     * (e.g. for hubs and printers) as well as "class-per-interface"
     * style data, for devices with less standard behaviours.
     */
    protected void showClassInfo (StringBuffer buf)
	{ }

    private void showDeviceInfo (StringBuffer buf)
    throws IOException
    {
	String	temp;

	buf.append ("Port Identifier: ");
	buf.append (portid);
	// buf.append ("; Address ");
	// buf.append (dev.getAddress ());
	buf.append ("<br>\n");

	buf.append ("USB ");
	buf.append (info.getUSBVersion ());
	if (info.getDeviceClass () != 0) {
	    buf.append (", Device class: ");
	    buf.append (info.getDeviceClassName ());
	}
	buf.append ("<br>\n");

	temp = info.getManufacturer (0);
	if (temp != null) {
	    buf.append ("Manufacturer: <em>");
	    buf.append (temp);
	    buf.append ("</em><br>\n");
	}
	
	temp = info.getProduct (0);
	if (temp != null) {
	    buf.append ("Product: <em>");
	    buf.append (temp);
	    buf.append ("</em> (rev ");
	    buf.append (info.getDeviceId ());
	    buf.append (")<br>\n");
	}
	
	temp = info.getSerial (0);
	if (temp != null) {
	    buf.append ("Serial #: <em>");
	    buf.append (temp);
	    buf.append ("</em><br>\n");
	}

	int	languages [] = ControlMessage.getLanguages (dev);

	if (languages != null) {
	    buf.append ("Languages: ");
	    for (int i = 0; i < languages.length; i++) {
		Locale	locale = LangCode.getLocale (languages [i]);
		if (locale != null)
		    buf.append (locale.toString ());
		else {
		    buf.append ("0x");
		    buf.append (Integer.toHexString (languages [i]));
		}
		buf.append (" ");
	    }
	    buf.append ("<br>\n");
	}
	
	buf.append ("Vendor ID: 0x");
	buf.append (Integer.toHexString (info.getVendorId ()));
	buf.append (", Product ID: 0x");
	buf.append (Integer.toHexString (info.getProductId ()));
	buf.append ("<br>\n");

	/*
	buf.append ("class ");
	buf.append (info.getDeviceClass ());
	buf.append (", subclass ");
	buf.append (info.getDeviceSubclass ());
	buf.append (", protocol ");
	buf.append (info.getDeviceProtocol ());
	buf.append ("<br>\n");
	*/

	try {
	    byte status [] = ControlMessage.getStatus (dev,
		ControlMessage.RECIPIENT_DEVICE, 0, 0, 2);

	    if (ControlMessage.getBit (0, status, dev.DEVICE_SELFPOWERED))
		buf.append ("Currently self-powered<br>\n");
	    if (ControlMessage.getBit (1, status, dev.DEVICE_REMOTE_WAKEUP))
		buf.append ("Remote Wakeup is enabled<br>\n");

	} catch (IOException e) {
	    // should never happen !!
	    buf.append ("USB Exception on getDeviceStatus:<br><em>");
	    buf.append (e.getMessage ());
	    buf.append ("</em>");
	}

	buf.append ("<br>\n");
    }

    private void showConfigInfo (StringBuffer buf)
    {
	try {
	    Configuration	config = dev.getConfiguration ();
	    String		tmp = config.getConfiguration (0);
	    int			value;
	    
	    buf.append ("<b>");
	    if (info.getNumConfigurations () != 1)
		buf.append ("Current ");
	    buf.append ("Configuration</b><br>\n");
	    if (tmp != null) {
		buf.append ("Description: ");
		buf.append ("<em>");
		buf.append (tmp);
		buf.append ("</em><br>\n");
	    }

	    value = config.getAttributes ();
	    buf.append ("Uses ");
	    if ((value & config.ATTR_SELF_POWERED) != 0)
		buf.append ("own power and ");
	    buf.append (2 * config.getMaxPower ());
	    buf.append (" mA. of bus power<br>\n");
	    if ((value & config.ATTR_REMOTE_WAKEUP) != 0)
		buf.append ("Can support remote wakeup.<br>\n");

	    buf.append ("<br>\n");

	    // XXX actually want to show each of the next
	    // descriptors in order ... can see the type of
	    // the next descriptor, mention it if it's not
	    // going to be shown otherwise.

	    showIfInfo (buf, config);

	} catch (IOException e) {
	    buf.append ("Current configuration unavailable:\n<em>");
	    buf.append (e.getMessage ());
	    buf.append ("</em>\n");
	}
    }

    // XXX this doesn't handle alternates right yet ...

    private void showIfInfo (StringBuffer buf, Configuration config)
    {
	int	numIf = config.getNumInterfaces ();
	byte	epStatus [] = new byte [2];

	for (int i = 0; i < numIf; i++) {
	    try {
		Interface	intf = config.getInterface (i, 0);

		// some devices don't expose these when configured
		if (intf == null) {
		    buf.append ("<em>Can't get interface ");
		    buf.append (i);
		    buf.append ("</em>.<br>\n");
		    continue;
		}

		String		temp;
		int		value;

		buf.append ("<b>Interface ");
		buf.append (intf.getNumber ());
		value = intf.getInterfaceClass ();
		if (value != 0) {
		    buf.append (" (");
		    buf.append (intf.getInterfaceClassName ());
		    buf.append (")");
		}
		value = intf.getAlternateSetting ();
		if (value != 0) {
		    buf.append (", alt ");
		    buf.append (value);
		}
		buf.append ("</b><br>\n");

		temp = intf.getInterface (0);
		if (temp != null) {
		    buf.append ("Description: <em>");
		    buf.append (temp);
		    buf.append ("</em><br>\n");
		}

		/*
		buf.append ("class ");
		buf.append (intf.getInterfaceClass ());
		buf.append (", subclass ");
		buf.append (intf.getInterfaceSubclass ());
		buf.append (", protocol ");
		buf.append (intf.getInterfaceProtocol ());
		buf.append ("<br>\n");
		*/

		value = intf.getNumEndpoints ();

		for (int j = 0; j < value; j++) {
		    Endpoint	ep = intf.getEndpoint (j);

		    buf.append ("<em>Endpoint ");
		    buf.append (ep.getEndpointAddress ());
		    buf.append (":</em> ");
		    temp = ep.getType ();
		    buf.append (temp);
		    if (ep.isInput ())
			buf.append (" IN ");
		    else
			buf.append (" OUT ");
		    if ("interrupt".equals (temp)) {
			buf.append (" (poll ");
			buf.append (ep.getInterval ());
			buf.append ("ms.) ");
		    }
		    buf.append ("maxpacket ");
		    buf.append (ep.getMaxPacketSize ());

		    // NOTE:  can't try for interface status (zeroes)
		    // or endpoint status (HALT) unless we claimed the
		    // interface, which we don't want at this time.

		    buf.append ("<br>\n");
		}

		buf.append ("<br>\n");

	    } catch (IOException e) {
		buf.append ("<br>Interface data unavailable. ");
		buf.append ("Index = ");
		buf.append (i);
		buf.append ("<br>\nDiagnostic: <em>");
		buf.append (e.getMessage ());
		buf.append ("</em>\n");
	    }
	}
    }
}
