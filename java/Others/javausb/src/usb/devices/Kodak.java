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

package usb.devices;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import usb.core.*;
import usb.util.USBSocket;


/**
 * Simple wrapper for Kodak DC 240, 280, 3400, DC-5000
 * cameras. These cameras support a common protocol family.
 * A full camera driver would be substantial; it might become
 * part of a project such as
 * <a href="http://jphoto.sourceforge.net/">jPhoto</a>.
 *
 * @version $Id: Kodak.java,v 1.1 2008/10/15 09:27:59 huangmeng Exp $
 */
public class Kodak	// "Taka" protocol ?
{
    // system codes -- one of several message types
    private final static byte KODAK_COMPLETED = (byte) 0x00;
    private final static byte KODAK_ACK = (byte) 0xD1;
    private final static byte KODAK_CORRECT = (byte) 0xD2;
    private final static byte KODAK_NAK = (byte) 0xE1;
    private final static byte KODAK_ERROR = (byte) 0xE2;
    private final static byte KODAK_ILLEGAL = (byte) 0xE3;
    private final static byte KODAK_CANCEL = (byte) 0xE4;
    private final static byte KODAK_BUSY = (byte) 0xF0;

    // one or the other
    private USBSocket		socket;
    private RandomAccessFile	random;

    private InputStream		in;
    private OutputStream	out;

    private byte		statusTable [];


    /** Wraps access to the specified camera.  */
    public Kodak (Device dev)
    throws IOException
    { 
	try {
	    // can we use usbdevfs?
	    socket = new USBSocket (dev);
	    in = socket.getInputStream ();
	    out = socket.getOutputStream ();

	} catch (IOException e) {

	    if ("Linux".equals (System.getProperty ("os.name"))) {
		// assume this is the LOCAL implementation
		// semantics are like a half duplex packet stream socket

		// assume just one camera (kernel handles up to 16)
		// single open, per the driver's exclusion policy
		random = new RandomAccessFile ("/dev/usb/dc2xx0", "rw");
		in = new InputHack (random);
		out = new OutputHack (random);
	    } else {
		throw e;
	    }

	    // NOTE:  GCJ 2.96 bug on this path ... putting this
	    // call here evidently prevented the constructor from
	    // returning a null object.  Strange.
	    System.err.print ("");
	}
	getCameraStatus ();
	getCameraType ();	// ensures it's a supported camera type
    }

    public void finalize ()
    {
	if (in != null)
	    try { close (); } catch (IOException e) { }
    }

    public void close () throws IOException
    {
	in = null;
	out = null;
	
    	if (random != null) {
	    random.close ();
	    random = null;
	} else if (socket != null) {
	    socket.close ();
	    socket = null;
	}
    }

    /**
     * Updates the local copy of the camera status table, and returns a copy
     * of it.  The format of this data is in section 3.1 of the DC-280
     * specification; that is a superset of previous models including the
     * dc240.  Note that this table snapshots the camera's notion of the
     * current time.
     */
    public byte [] getCameraStatus ()
    throws IOException
    {
	// this is how NOT to do it!
	// no error handling, no framework for other calls.

	byte			temp [] = new byte [8];
	byte			response [] = new byte [258];
	int			len;

	temp [0] = 0x7F;	// send camera status table
	temp [7] = 0x1A;

	// write command, receive ack
	out.write (temp, 0, temp.length);
	len = in.read (temp, 0, 1);
	if (len != 1 || temp [0] != KODAK_ACK)
	    throw new IOException ("needed ACK");

	// get, check, and ack response (ignoring packet checksum!)
	len = in.read (response);
	if (len != 258 || temp [0] != KODAK_ACK)
	    throw new IOException ("needed status packet");
	response [0] = KODAK_CORRECT;
	out.write (response, 0, 1);

	// copy and save the table 
	temp = new byte [256];
	System.arraycopy (response, 1, temp, 0, 256);
	statusTable = temp;

	// also copy a return value
	temp = new byte [256];
	System.arraycopy (response, 1, temp, 0, 256);

	// get and check ACK
	len = in.read (temp, 0, 1);
	if (len != 1 || temp [0] != KODAK_COMPLETED)
	    throw new IOException ("needed ACK2");
	
	return temp;
    }

    /** returns string naming camera type, such as DC-240 or DC-280 */
    public String getCameraType ()
    {
	switch (statusTable [1]) {
	    // case 0:	?
	    // case 1:	return "DC-50";
	    // case 2:	return "DC-120";
	    // case 3:	return "DC-200";
	    // case 4:	return "DC-210/215";
	    case 5:	return "DC-240";
	    case 6:	return "DC-280";
// FIXME:
case 7: return "DC-3400 (?)";
case 8: return "DC-5000 (?)";

	    default:	return "Unknown-" + getCameraType ();
	}
    }

    /** returns good, weak, or empty */
    public String getBatteryStatus ()
    {
	switch (statusTable [8]) {
	    case 0:	return "good";
	    case 1:	return "weak";
	    case 2:	return "empty";
	    default:	throw new InternalError (); 
	}
    }

    public boolean isPowerConnected ()
    {
	return statusTable [9] != 0;
    }

    public int getNumPicturesInCard ()
    {
	int temp = 0xff & statusTable [14];
	temp <<= 8;
	temp += 0xff & statusTable [15];
	return temp;
    }

    
    /**
     * Set's the camera's clock to match the local time.
     */
    public void setTime (long time)
    throws IOException
    {
    	// XXX implement me!
    }


    /**
     * Returns a string identifying the driver mode (kernel, user).
     */
    public String getDriverMode ()
    {
	if (socket == null)
	    return "kernel";
	else
	    return "user";
    }


    // XXX move to ShowTree (with indentLine), after
    // making sure all this has reasonable accessors

    public void printSomeStatus (int indent)
    throws IOException
    {
	StringBuffer	buf = new StringBuffer ();
	int		temp;

	buf.append ("Driver Mode: ");
	buf.append (getDriverMode ());
	indentLine (indent, buf.toString ());

	buf.setLength (0);
	buf.append (getCameraType ());
	buf.append (", ");
	buf.append ("battery ");
	buf.append (getBatteryStatus ());
	buf.append (", ");

	buf.append ("Power Connected: ");
	buf.append (isPowerConnected ());
	buf.append (", ");

	switch (statusTable [12]) {
	    case 0:		buf.append ("NTSC, "); break;
	    case 1:		buf.append ("PAL, "); break;
	    default:	buf.append ("??, "); break;
	}
	indentLine (indent, buf.toString ());

	buf.setLength (0);
	buf.append (getNumPicturesInCard ());
	buf.append (" pictures in card, ");

	temp = 0xff & statusTable [62];
	temp <<= 8;
	temp += 0xff & statusTable [63];

	buf.append (temp);
	buf.append (" more at medium quality,");
	indentLine (indent, buf.toString ());

	    // XXX wrap this up in a date object, local timezone

	buf.setLength (0);
	temp = 0xff & statusTable [88];
	temp <<= 8;
	temp += 0xff & statusTable [89];
	buf.append ("Thinks date/time is now Y/M/D ");
	buf.append (temp);
	buf.append ('/');
	buf.append (statusTable [90]);
	buf.append ('/');
	buf.append (statusTable [91]);

	buf.append (" H/M/S/ms ");
	buf.append (statusTable [92]);
	buf.append ('/');
	buf.append (statusTable [93]);
	buf.append ('/');
	buf.append (statusTable [94]);
	buf.append ('/');
	buf.append ((0xff & statusTable [90]) * 10);

	indentLine (indent, buf.toString ());
    }

    private void indentLine (int indent, String m)
    {
	for (; indent > 8; indent -= 8)
	    System.out.print ('\t');
	for (; indent > 0; indent -= 1)
	    System.out.print (' ');
	System.out.println (m);
    }

    /*****************************************************************/

    /* input/output hacks supporting driver exclusion policy */

    private static class InputHack extends InputStream
    {
	private RandomAccessFile	f;

	InputHack (RandomAccessFile file) { f = file; }

	public int read ()
	throws IOException
	{
		return f.read ();
	}

	public int read (byte buf [], int off, int len)
	throws IOException
	{
		return f.read (buf, off,len);
	}
    }

    private static class OutputHack extends OutputStream
    {
	private RandomAccessFile	f;

	OutputHack (RandomAccessFile file) { f = file; }

	public void write (int b)
	throws IOException
	{
		f.write (b);
	}

	public void write (byte buf [], int off, int len)
	throws IOException
	{
		f.write (buf, off,len);
	}
    }
}
