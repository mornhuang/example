/*
 * Java USB Library
 * Copyright (C) 2000 by David Brownell
 * Copyright (C) 2002 by Wayne Westerman
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

package usb.windows;


/**
 * USBException objects encapsulate OS error indicators as returned through
 * system calls on failed attempts to perform I/O operations.
 * Avoid those error numbers if you care about non-Linux systems, but
 * feel free to use the strings in diagnostics.
 *
 * @author David Brownell
 * @version $Id: USBException.java,v 1.1 2008/10/15 09:28:07 huangmeng Exp $
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
    public USBException (String descriptive)
    {
	super (descriptive);
    }
   
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

    public static final int DEVICE_GENERAL_FAILURE = -2;
    public static final int DEVICE_CAUSED_OVERCURRENT = -3;
    public static final int DEVICE_NOT_ENOUGH_POWER = -4;
    public static final int DEVICE_NOT_ENOUGH_BANDWIDTH = -5;
    public static final int DEVICE_HUB_NESTED_TOO_DEEPLY = -6;
    public static final int DEVICE_IN_LEGACY_HUB = -7;
    public static final int DEVICE_FAILED_ENUMERATION = -8;
    public static final int ERROR_NOT_ANY_FURTHER_SPECIFIED = -1;
    public static final int ERROR_SUCCESS = 0;
    public static final int ERROR_INVALID_FUNCTION = 1;
    public static final int ERROR_FILE_NOT_FOUND = 2;
    public static final int ERROR_PATH_NOT_FOUND = 3;
    public static final int ERROR_TOO_MANY_OPEN_FILES = 4;
    public static final int ERROR_ACCESS_DENIED = 5;
    public static final int ERROR_INVALID_HANDLE = 6;
    public static final int ERROR_ARENA_TRASHED = 7;
    public static final int ERROR_NOT_ENOUGH_MEMORY = 8;
    public static final int ERROR_INVALID_BLOCK = 9;
    public static final int ERROR_BAD_ENVIRONMENT = 10;
    public static final int ERROR_BAD_FORMAT = 11;
    public static final int ERROR_INVALID_ACCESS = 12;
    public static final int ERROR_INVALID_DATA = 13;
    public static final int ERROR_OUTOFMEMORY = 14;
    public static final int ERROR_INVALID_DRIVE = 15;
    public static final int ERROR_CURRENT_DIRECTORY = 16;
    public static final int ERROR_NOT_SAME_DEVICE = 17;
    public static final int ERROR_NO_MORE_FILES = 18;
    public static final int ERROR_WRITE_PROTECT = 19;
    public static final int ERROR_BAD_UNIT = 20;
    public static final int ERROR_NOT_READY = 21;
    public static final int ERROR_BAD_COMMAND = 22;
    public static final int ERROR_CRC = 23;
    public static final int ERROR_BAD_LENGTH = 24;
    public static final int ERROR_GEN_FAILURE = 31;
    public static final int ERROR_BROKEN_PIPE = 109;
    public static final int ERROR_OPEN_FAILED = 110;
    public static final int ERROR_BUFFER_OVERFLOW = 111;
    public static final int ERROR_SEM_TIMEOUT = 121;
    public static final int ERROR_INSUFFICIENT_BUFFER = 122;
    public static final int ERROR_BUSY = 170;
    public static final int ERROR_INVALID_ADDRESS = 487;
    public static final int ERROR_IO_PENDING = 997;
    public static final int ERROR_NOACCESS = 998;
    public static final int ERROR_SERVICE_REQUEST_TIMEOUT = 1053;
    public static final int ERROR_SHUTDOWN_IN_PROGRESS = 1116;
    public static final int ERROR_DEVICE_NOT_CONNECTED = 1167;
    public static final int ERROR_BAD_DEVICE = 1200;
    public static final int ERROR_TIMEOUT = 1460;

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
	String      extra = null;

	switch (errno) {
	    // see win32errors.txt

	case ERROR_ACCESS_DENIED:
	    extra = "Access Denied";
	    break;
	case ERROR_FILE_NOT_FOUND:
	    extra = "Device not found";
	    break;

	case ERROR_NOT_READY://ENOENT
	    extra = "USB operation canceled";
			    //+ SEPARATOR
			    //+ "Green threads issue";
	    break;
	case ERROR_INVALID_HANDLE:	// ENXIO
	    extra = "USB bad endpoint";
	    break;
	/*case 18:	// EXDEV
	    extra = "USB isochronous transfer incomplete";
	    break;*/
	case ERROR_DEVICE_NOT_CONNECTED:
	case ERROR_NOT_SAME_DEVICE://ENODEV:
	    extra = "USB device has been removed";
	    break;
	/*case 28:	// ENOSPC
	    extra = "USB bandwidth reservation would be exceeded";
	    break;*/
	case ERROR_BROKEN_PIPE://EPIPE:
	    extra = "USB endpoint stall";
	    break;

	case ERROR_BUFFER_OVERFLOW://70:	// ECOMM
	    extra = "USB buffer overflow or underflow";
	    break;
	case ERROR_BAD_ENVIRONMENT://71:	// EPROTO
	case ERROR_GEN_FAILURE:
	    extra = "USB internal error";
	    break;
	case ERROR_INSUFFICIENT_BUFFER://EOVERFLOW:
	    extra = "USB data overrun";
	    break;

	case ERROR_CRC://84:	// EILSEQ
	    extra = "USB CRC or data toggle error";
	    break;
	case ERROR_SHUTDOWN_IN_PROGRESS://108:	// ESHUTDOWN
	    extra = "USB host controller shut down";
	    break;
	case ERROR_TIMEOUT:
	case ERROR_SEM_TIMEOUT:
	case ERROR_SERVICE_REQUEST_TIMEOUT:
	    extra = "USB device not responding";
	    break;

	case ERROR_IO_PENDING://115:	// EINPROGRESS
	    extra = "USB operation pending";
	    break;
	case ERROR_BAD_LENGTH://121:	// EREMOTEIO
	    extra = "USB data underrun or short packet";
	    break;
	}

/*
	// strError uses the I18N support of the C library
	// (so does that means trouble on Windows??)
	if (extra == null)
	    return strError (errno);
	else
	    return extra + SEPARATOR + strError (errno);
*/
	// strError uses the I18N support of the C library
	if ( extra == null )
		return Integer.toString (errno);
	else
		return extra + SEPARATOR + Integer.toString (errno);
    }


    /**
     * Returns true iff the exception indicates an endpoint which has
     * stalled; these are often used as protocol error indicators.
     */
    public boolean isStalled ()
	{ return ERROR_BROKEN_PIPE == errno; }

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
    //private native String strError (int errno);
}
