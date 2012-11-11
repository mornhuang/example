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

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import usb.core.*;

// FIXME -- we need a generic driver framework, perhaps
// driven by XML config files to give binding info.

import usb.devices.Rio500;
import usb.devices.Kodak;


/**
 * Displays the host's Universal Serial Bus (USB) in XML on standard output.
 * This scans the preliminary USB device filesystem and looks at each
 * device; it needs read/write access to a device file in order to do
 * anything even vaguely intereseting with the device, but one hopes
 * that will change over time.
 *
 * <p> This is here as a debug-and-get-going facility.  It expects to
 * be able to read (and, sadly, write) all /proc/bus/usb/BBB/DDD nodes;
 * unfortunately, all these reads currently count as writes due to the
 * odd permissions model currently used by usbdevfs.
 *
 * @version $Id: ShowTree.java,v 1.1 2008/10/15 09:28:02 huangmeng Exp $
 */
final public class ShowTree
{
    static int		defaultLanguage;

    private ShowTree () {}

    public static void main (String argv [])
    {
	Bus		busses [] = null;
	Host		host;

	try {

	    // FIXME:  option flag for rmi (iiop?) host:port spec.

	    host = HostFactory.getHost ();
	    if (host == null) {
		System.err.println ("USB is unavailable, can't run.");
		return;
	    }

	    System.out.println ("<!-- " + host + " -->");

	    busses = host.getBusses ();
	    indentLine (0, "<host busses='" + busses.length + "'>");
	    for (int busno = 0; busno < busses.length; busno++) {
		indentLine (2, "<!-- Bus #" + (busno + 1) + " -->");
		// show tree from root hub
		if (busses [busno] != null) {
		    try { printDevice (2, busses [busno].getRootHub ()); }
		    catch (IOException e) { e.printStackTrace (); }
		}
	    }
	    indentLine (0, "</host>");

	} catch (SecurityException e) {
	    System.err.println ("USB permissions problem:");
	    System.err.println (e.getMessage ());
	    System.exit (1);

	} catch (Exception e) {
	    e.printStackTrace ();
	}
    }

    static void indentLine (int n, String s)
    {
	for (; n >= 8; n -= 8)
	    System.out.print ('\t');
	while (n-- > 0)
	    System.out.print (' ');
	System.out.println (s);
    }

    private static void printDevice (int indent, Device dev)
    throws IOException
    {
	String	temp;
	int	langs [];

	// typical case:  most device slots are empty
	if (dev == null)
	    return;

	langs = ControlMessage.getLanguages (dev);
	if (langs != null && langs.length > 0)
	    defaultLanguage = langs [0];
	else
	    defaultLanguage = 0;
	
	//
	// two kinds of address:  port ID, bus device number
	//
	indentLine (indent, "<?addresses portid='"
	    + dev.getPortIdentifier ()
	    + "' busaddr='"
	    + dev.getAddress ()
	    + "'?>");

	//
	// device descriptor
	//
	DeviceDescriptor	info = dev.getDeviceDescriptor ();

	indentLine (indent, "<"
	    + info.getDeviceClassName ()
	    + " usb='"
	    + info.getUSBVersion ()
	    + "'");
	
	indentLine (indent + 4, "vendorId='0x"
	    + Integer.toHexString (info.getVendorId ())
	    + "' productId='0x"
	    + Integer.toHexString (info.getProductId ())
	    + "' device-version='"
	    + info.getDeviceId ()
	    + "'");
	
	temp = info.getManufacturer (defaultLanguage);
	if (temp != null)
	    indentLine (indent +4, "manufacturer='" + temp + "'");
	temp = info.getProduct (defaultLanguage);
	if (temp != null)
	    indentLine (indent + 4, "product='" + temp + "'");
	temp = info.getSerial (defaultLanguage);
	if (temp != null)
	    indentLine (indent + 4, "serial='" + temp + "'");
	
	indentLine (indent + 4, "class='"
	    + info.getDeviceClass ()
	    + "' subclass='"
	    + info.getDeviceSubClass ()
	    + "' protocol='"
	    + info.getDeviceProtocol ()
	    + "'");
	indentLine (indent + 4, "ep0-maxpacket='"
	    + Integer.toString (info.getMaxPacketSize0 ())
	    + "' configurations='"
	    + info.getNumConfigurations ()
	    + "'>");
	indent += 2;

	if (langs != null) {
	    for (int x = 0; x < langs.length; x++) {
		Locale	locale = LangCode.getLocale (langs [x]);
		String	tmp = "";

		if (locale != null)
		    tmp = "' locale='" + locale.toString ();
		indentLine (indent, "<language id='0x"
		    + Integer.toHexString (langs [x])
		    + tmp
		    + "'/>");
	    }
	}
	
	for (int i = 0; i < info.getNumConfigurations (); i++)
	    try {
		printConfiguration (indent, dev.getConfiguration (i));
	    } catch (IOException e) {
		indentLine (indent, "<!-- CAN'T GET CONFIGURATION: ");
		e.printStackTrace (System.out);
		indentLine (indent, "-->");
	    }
	maybePrintDeviceDetails (indent, info);
	indentLine (indent, "</" + info.getDeviceClassName () + ">");
    }

    private static void printConfiguration (int indent, Configuration c)
    {
	String	temp;

	// atypical:  should always be able to read configuration
	if (c == null) {
	    indentLine (indent, "<!-- null configuration -->");
	    return;
	}

	indentLine (indent, "<config value='"
	    + c.getConfigurationValue ()
	    + "' total-length='"
	    + c.getTotalLength ()
	    + "' attributes='"
	    + Integer.toHexString (c.getAttributes ())
	    + "'");
	temp = c.getConfiguration (defaultLanguage);
	if (temp != null)
	    indentLine (indent + 4, "description='"
		+ temp
		+ "'");
	indentLine (indent + 4, "max-power='"
	    + (2 * c.getMaxPower ())
	    + " mA.' interfaces='"
	    + c.getNumInterfaces ()
	    + "'>");
	indent += 2;

	maybePrintDescriptors (indent, c.nextDescriptor ());
	
	for (int i = 0; i < c.getNumInterfaces (); i++) {
	    try {
		Interface	intf;

		// NOTE:  assumes altsetting range is contiguous, [0..N] 
		for (int j = 0;
			(intf = c.getInterface (i, j)) != null;
			j++) {
		    // claimer of interface selects altsettings
		    if (j == 0) {
			String claimer = intf.getClaimer ();
			if (claimer != null)
			    indentLine (indent, "<!-- interface "
				+ i
				+ " is claimed by: "
				+ claimer
				+ " driver -->");
			else
			    indentLine (indent, "<!-- interface "
				+ i
				+ " is unclaimed -->");
		    }
		    printInterface (indent, intf);
		}
	    } catch (IOException e) {
		indentLine (indent, "<!-- CAN'T GET INTERFACE: ");
		e.printStackTrace (System.out);
		indentLine (indent, "-->");
	    }
	}
	indentLine (indent, "</config>");
    }

    private static void printInterface (int indent, Interface intf)
    throws IOException
    {
	indentLine (indent, "<"
	    + intf.getInterfaceClassName ()
	    + " number='"
	    + intf.getNumber ()
	    + "' alt='"
	    + intf.getAlternateSetting ()
	    + "' endpoints='"
	    + intf.getNumEndpoints ()
	    + "'");
	indentLine (indent + 4, "class='"
	    + intf.getInterfaceClass ()
	    + "' subclass='"
	    + intf.getInterfaceSubClass ()
	    + "' protocol='"
	    + intf.getInterfaceProtocol ()
	    + "'>");
	indent += 2;

	maybePrintDescriptors (indent, intf.nextDescriptor ());
	
	for (int ep = 0; ep < intf.getNumEndpoints (); ep++)
	    try {
		printEndpoint (indent, intf.getEndpoint (ep));
	    } catch (IOException e) {
		indentLine (indent, "<!-- CAN'T GET ENDPOINT: ");
		e.printStackTrace (System.out);
		indentLine (indent, "-->");
	    }

	// FIXME:  can print interface class details

	indentLine (indent, "</" + intf.getInterfaceClassName () + ">");
    }

    private static void printEndpoint (int indent, Endpoint e)
    {
	if (e == null) {
	    indentLine (indent, "<!-- endpoint not available -->");
	    return;
	}

	indentLine (indent, "<endpoint addr='"
	    + e.getEndpointAddress ()
	    + "' direction='"
	    + (e.isInput () ? "in" : "out")
	    + "' type='"
	    + e.getType ()
	    + "'");
	indentLine (indent + 4, "attributes='"
	    + Integer.toHexString (e.getAttributes ())
	    + "' maxpacket='"
	    + e.getMaxPacketSize ()
	    + "' interval='"
	    + e.getInterval ()
	    // XXX two "extra" bytes in audio endpoints ...
	    + "'/>");
	maybePrintDescriptors (indent, e.nextDescriptor ());
    }

    // call this to print anything between one descriptor and
    // the next interface or endpoint (or end of configuration)
    private static void maybePrintDescriptors (int indent, Descriptor d)
    {
	while (d != null) {
	    switch (d.getDescriptorType ()) {
		case Descriptor.TYPE_INTERFACE:
		case Descriptor.TYPE_ENDPOINT:
		    return;
		default:
		    indentLine (indent, "<descriptor type='"
			    + d.getDescriptorType ()
			    + "' length='"
			    + d.getLength ()
			    + "'/>");
		    // dumping contents could be useful too
		    d = d.nextDescriptor ();
	    }
	}
    }

    // let's try some intelligence with some devices
    private static void maybePrintDeviceDetails (
	int indent,
	DeviceDescriptor info
    )
    {
	indent += 8;

	// FIXME: use external config data; XML format

	if (info.getDeviceClass () == Descriptor.CLASS_HUB)
	    printHubDetails (indent, info.getDevice ());
	else if (info.getVendorId () == 0x040a)
	    printKodakDetails (indent, info);
	else if (info.getVendorId () == 0x0841)
	    printDiamondDetails (indent, info);
    }

    private static void printHubDetails (int indent, Device dev)
    {
	try {
	    Hub		h = new Hub (dev);
	    int		ports = h.getNumPorts ();

	    indentLine (indent,
		(h.isRootHub () ? "Root " : "")
		+ "Hub, "
		+ ports
		+ " ports"
		);
	    indentLine (indent,
		"overcurrent protection: "
		+ h.getOverCurrentMode ()
		);
	    indentLine (indent,
		"power switching: "
		+ h.getPowerSwitchingMode ()
		);
	    if (h.isCompound ())
		indentLine (indent, "Part of a compound device");

	    // not showing POTPGT, or hub's own current draw

	    indent -= 4;
	    indentLine (indent, "");
	    for (int i = 1; i <= ports; i++) {
		Device child = dev.getChild (i);

		if (child == null)
		    continue;

		indentLine (indent, "<!-- Port "
			+ i
			+ (h.isRemovable (i) ? "" : " is built-in.")
			+ " -->");
		printDevice (indent, child);
	    }

	} catch (IOException e) {
	    e.printStackTrace (System.out);
	}
    }


    private static void printKodakDetails (int indent, DeviceDescriptor info)
    {
	String	type;

	switch (info.getProductId ()) {
	    //
	    // "Taka" serial/usb protocol, dating back to DC-50
	    //
	    case 0x0120:	type = "dc240"; break;
	    case 0x0130:	type = "dc280"; break;
	    case 0x0131:	type = "dc5000"; break;
	    case 0x0132:	type = "dc3400"; break;

	    //
	    // DigitaOS serial/usb protocol isn't Kodak-proprietary.
	    // Also used by HP PhotoSmart C500
	    //
	    case 0x0100:	// dc220
	    case 0x0110:	// dc260
	    case 0x0111:	// dc265
	    case 0x0112:	// dc290
		indentLine (indent, "http://ods.sourceforge.net/");
		return;

	    //
	    // There are many other old serial protocols that have been
	    // brought forward into USB.  One hopes that vendor-neutral
	    // standards will start to really catch on.
	    //


	    //
	    // PTP protocol -- based on an ISO draft, intended to
	    // be neutral with respect to vendor and protocol.  New.
	    //
	    // Doesn't have serial line legacy features.
	    //
	    case 0x0121:	// dc240 with PTP prototype firmware
	    case 0x0160:	// dc4800
		indentLine (indent, "http://jphoto.sourceforge.net/");

		    // FIXME:  if jPhoto is available it should
		    // be able to plug itself in ...

		return;
	    
	    default:
		indentLine (indent, "I don't know about this product.");
		return;
	}
	indentLine (indent, "I can talk to a " + type + " ...");

	try {
	    Kodak	camera = new Kodak (info.getDevice ());

	    indentLine (indent, "Current camera status: ");
	    camera.printSomeStatus (indent);

	} catch (IOException e) {
	    indentLine (indent, "... but I can't do it just now.");
	    indentLine (indent, e.getMessage ());

	} catch (Exception e) {
	    e.printStackTrace (System.out);
	}
    }

    private static void showStorage (int indent, Rio500 rio, boolean external)
    throws IOException
    {
	Rio500.MemoryStatus	mem;
	int			temp;

	indentLine (indent, external ? "SmartMedia" : "Built-in Memory");
	indent += 2;

	temp = rio.getFreeMemory (external);
	indentLine (indent, "Available Memory in bytes:  "
		+ (temp / (1024 * 1024))
		+ "."
		+ ((10 * (temp % (1024 * 1024))) / (1024 * 1024))
		+ " MB, "
		);
	mem = rio.getMemoryStatus (external);
	temp = mem.getBlockCount ();
	if (temp != 0)
	    temp = ((temp - mem.getNumUnusedBlocks ()) * 100) / temp;
	else
	    temp = 1000;

	indentLine (indent, "Memory status: "
	    + mem.getBlockCount () + " Blocks, "
	    + mem.getBlockSize () + " bytes each, "
	    + mem.getNumUnusedBlocks () + " blocks unused "
	    + temp + "% full "
	    );
	indentLine (indent, "First free block at 0x"
		+ Integer.toHexString (mem.getFirstFreeBlock ())
		);

	// dump folders and their contents
	Rio500.FolderEntry	folders [] = rio.getFolders (external);

	for (int i = 0; i < folders.length; i++) {
	    Rio500.FolderEntry	f = folders [i];

	    indentLine (indent, "Folder # "
		    + i
		    + ", offset = 0x"
		    + Integer.toHexString (f.getOffset ())
		    + ", name1 = "
		    + f.getName1 ()
		    // + ", name2 = "
		    // + f.getName2 ()
		    + ", entries = "
		    + f.getSongCount ()
		    );
	    indent += 2;
	    try {
		Rio500.SongEntry	songs [] = f.getSongs ();

		for (int j = 0; j < songs.length; j++) {
		    Rio500.SongEntry	s = songs [j];
		    indentLine (indent, "Song # "
			+ j
			+ ", offset = 0x"
			+ Integer.toHexString (s.getOffset ())
			+ ", kbytes = "
			+ (s.getLength () / 1024)
			);
		    indentLine (indent + 4, "name1 = "
			+ s.getName1 ()
			// + ", name2 = "
			// + s.getName2 ()
			);
		}
	    } catch (Exception e) {
		e.printStackTrace ();
	    }
	    indent -= 2;
	}
    }


    private static void printDiamondDetails (int indent, DeviceDescriptor info)
    {
	Device	dev = info.getDevice ();

	if (info.getProductId () != 0x0001) {
	    indentLine (indent, "That's not a Rio 500 ...");
	    return;
	}
	Rio500			rio = null;

	try {
	    indentLine (indent, "Rio 500 Status: ");
	    indent += 2;

	    rio = new Rio500 (dev);
	    rio.start ();

	    // basic memory statistics
	    showStorage (indent, rio, false);
	    if (rio.hasExternalMemory ())
		showStorage (indent, rio, true);

	} catch (Exception e) {
	    e.printStackTrace (System.out);
	    return;
	} finally {
	    if (rio != null)
		rio.finish ();
	}
    }
}
