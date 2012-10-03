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


/**
 * USBException objects encapsulate OS error indicators as returned through
 * system calls on failed attempts to perform I/O operations.
 * Avoid those error numbers if you care about non-Linux systems, but
 * feel free to use the strings in diagnostics.
 *
 * @author David Brownell
 * @version $Id: USBException.java,v 1.1 2008/10/15 09:28:02 huangmeng Exp $
 */
class USBException extends usb.core.USBException
{
    /**
     * OS-specific diagnostic error code being encapsulated.
     * There's also an OS-specific diagnostic string.
     * @see #getErrno
     */
    private int		errno;

    /**
     * Constructs an exception object encapsulating a USB error number.
     */
    public USBException (String descriptive, int errno)
    {
	super (descriptive);
	    // FIXME:  get the string HERE, and rm native code
	this.errno = errno;
    }

    /**
     * Returns OS-specific error code encapsulated by this USB exception. 
     * @deprecated For portability, avoid using this function.
     */
    public int getErrno () { return errno; }

    public static final int ENOENT = 2;
    public static final int EPERM = 13;
    public static final int EBUSY = 16;
    public static final int ENODEV = 19;
    public static final int EPIPE = 32;
    public static final int EOVERFLOW = 75;
    public static final int ETIMEDOUT = 110;

    // separates different diagnostics
    private static final String SEPARATOR = " -- ";


    /**
     * Returns descriptive diagnostic string for the OS error code
     * encpasulated in this exception.  If it is one of the error codes
     * known to be used by the USB subsystem, a USB-related diagnostic
     * is provided.  Otherwise, this method returns the standard host
     * operating system diagnostic.
     * @deprecated For portability, avoid using this function.
     */
    public String getErrnoString ()
    {
	// this needs I18N support
	String		extra = null;

	switch (errno) {
	    // <asm/errno.h> for symbol-to-number mappings
	    // <linux/usb.h> for symbol-to-meaning mappings

	    case ENOENT:
		extra = "USB operation canceled"
		    + SEPARATOR
		    + "Green threads issue";
		break;
	    case 6:	// ENXIO
		extra = "USB bad endpoint";
		break;
	    case 18:	// EXDEV
		extra = "USB isochronous transfer incomplete";
		break;

	    case ENODEV:
		extra = "USB device has been removed";
		break;
	    case 28:	// ENOSPC
		extra = "USB bandwidth reservation would be exceeded";
		break;
	    case EPIPE:
		extra = "USB endpoint stall";
		break;

	    case 70:	// ECOMM
		extra = "USB buffer overflow or underflow";
		break;
	    case 71:	// EPROTO
		extra = "USB internal error";
		break;
	    case EOVERFLOW:
		extra = "USB data overrun";
		break;

	    case 84:	// EILSEQ
		extra = "USB CRC or data toggle error";
		break;
	    case 108:	// ESHUTDOWN
		extra = "USB host controller shut down";
		break;
	    case ETIMEDOUT:
		extra = "USB device not responding";
		break;

	    case 115:	// EINPROGRESS
		extra = "USB operation pending";
		break;
	    case 121:	// EREMOTEIO
		extra = "USB data underrun or short packet";
		break;
	}

	// strError uses the I18N support of the C library
	if (extra == null)
	    return strError (errno);
	else
	    return extra + SEPARATOR + strError (errno);
    }


    /**
     * Returns true iff the exception indicates an endpoint which has
     * stalled; these are often used as protocol error indicators.
     */
    public boolean isStalled ()
	{ return EPIPE == errno; } 

    /**
     * Returns a platform-specific diagnostic message.
     */
    public String getMessage ()
    {
	return super.getMessage ()
	    + SEPARATOR
	    + getErrnoString ()
	    + " [" + errno + "] "
	    ;
    }

    // FIXME ... indirect through something

    // wrapper for strerror(3)
    private native String strError (int errno);
}
