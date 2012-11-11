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


/**
 * This class supports control messaging with convenience methods for
 * common calls, and encapsulates data for all control requests.
 *
 * <p> It may be useful to think of USB control messaging as a simple
 * RPC facility.  They may be directed to several types of recipient on the
 * USB device (RECIPIENT_DEVICE for the device itself; also endpoints,
 * interfaces, and "other").  They may be classified (TYPE_STANDARD
 * for requests found in the USB specification; also class requests
 * and vendor-specific requests).  All requests may send thirty-two
 * bits of data, in two sixteen bit fields:  "value" and "index".
 * Requests may optionally send or receive data. USB places a limit
 * of 64 KBytes on the size of that data, but most control requests
 * involve much less data than that.
 *
 * <p> The convenience functions in this class, used for issuing
 * standard requests such as
 * {@link #getStandardDescriptor getStandardDescriptor}, are all
 * wrappers around {@link Device#control Device.control} which does
 * the real work.
 *
 * <p> Control messages are discussed in detail in sections 9.3 and
 * 9.4 of the USB 1.1 specification.
 *
 * <p> At this writing, the Linux implementation has some open
 * issues with its control messaging; they are reduced by effective
 * string caching.
 *
 * @see Device#control
 *
 * @version $Id: ControlMessage.java,v 1.1 2008/10/15 09:28:12 huangmeng Exp $
 */
final public class ControlMessage
{
    private byte	requestType;
    private byte	request;
    private short	value;
    private short	index;

    private byte	buf [];
    private short	len;


    /** Constructs an uninitialized control mesage */
    public ControlMessage () { }

	// 1 bit: 0x80
    /** Request type field indicating device-to-host data transfer phase. */
    public static final byte DIR_TO_HOST = (byte) 0x80;

    /** Request type field indicating host-to-device data transfer phase. */
    public static final byte DIR_TO_DEVICE = 0x00;


	// 2 bits:  0x60
    /** Request type field for standard requests */
    public static final byte TYPE_STANDARD = 0x00;

    /** Request type field for class-specific requests */
    public static final byte TYPE_CLASS = 0x20;

    /** Request type field for vendor-specific requests */
    public static final byte TYPE_VENDOR = 0x40;

	// 3 unused bits: 0x1c

	// 2 bits:  0x03
    /** Request type field for device recipient */
    public static final byte RECIPIENT_DEVICE = 0;

    /** Request type field for interface recipient */
    public static final byte RECIPIENT_INTERFACE = 1;

    /** Request type field for endpoint recipient */
    public static final byte RECIPIENT_ENDPOINT = 2;

    /** Request type field for other recipient */
    public static final byte RECIPIENT_OTHER = 3;

    /**
     * Returns the request type, which masks a DIR, TYPE, and RECIPIENT.
     * For example, DIR_TO_HOST, TYPE_STANDARD, and RECIPIENT_DEVICE
     * would be used in a USB standard request returning device data
     * to the host.
     */
    public byte getRequestType ()
	{ return requestType; }

    /**
     * Assigns bits in the request type.  Construct these values
     * by ORing together three TYPE_ constants:  a DIR (only if
     * a buffer is provided with the message),
     * a RECIPIENT (if it's not the device),
     * and optionally one categorizing the request as
     * being class or vendor specific (if it's not a standard request).
     */
    public void setRequestType (byte bits)
	{ requestType = bits; }


    /**
     * Request value (0) used to determine state.  For devices, that state
     * includes whether remote wakeup is enabled, and whether the device
     * is self powered.  For endpoints, status includes whether it is
     * halted.
     */
    public static final byte GET_STATUS = 0;

    /** Request value (1) used to clear device feature flags. */
    public static final byte CLEAR_FEATURE = 1;

    // 2 reserved

    /** Request value (3) to set device feature flags. */
    public static final byte SET_FEATURE = 3;

    // 4 reserved

    /**
     * Request value (5) used by the host controller driver to assign device
     * numbers during device enumeration.  Currently user mode code does not
     * have access to USB when addresses are not assigned; you shouldn't need
     * to use this value.
     */
    public static final byte SET_ADDRESS = 5;

    /**
     * Request value (6) used to get configuration, interface, endpoint,
     * string, or other device descriptors.
     */
    public static final byte GET_DESCRIPTOR = 6;

    /** Request value (7) to assign a descriptor */
    public static final byte SET_DESCRIPTOR = 7;


    /** Request value (8) for the current device configuration descriptor. */
    public static final byte GET_CONFIGURATION = 8;

    /**
     * Request value (9) to choose the device configuration.
     * <em>This should interact with device claiming, power management,
     * and likely more ...</em>
     */
    public static final byte SET_CONFIGURATION = 9;


    /** Request value (10) to determine an interface alternate setting. */
    public static final byte GET_INTERFACE = 10;

    /** Request value (11) to select an interface alternate setting. */
    public static final byte SET_INTERFACE = 11;


    /** Request value (12) for synchronizing isochronous behaviors. */
    public static final byte SYNCH_FRAME = 12;


    /** Returns the request (GET_STATUS, etc) */
    public byte getRequest ()
	{ return request; }

    /** Assigns the request (GET_STATUS, etc) */
    public void setRequest (byte code)
	// could mask request type fields ..
	{ request = code; }

    
    /** Returns the "value" field of the message */
    public short getValue ()
	{ return value; }
    
    /** Assigns the "value" field of the message */
    public void setValue (short value)
	{ this.value = value; }
    
    /**
     * Returns the "index" field of the message.
     */
    public short getIndex ()
	{ return index; }
    
    /**
     * Assigns the "index" field of the message.
     * Endpoints are encoded in the low four bits, with bit seven
     * set if it is an IN endpoint.
     * Interfaces are encoded using the low eight bits.
     */
    public void setIndex (short index)
	{ this.index = index; }
    
    /**
     * Assigns the buffer used in any data transfer stage of
     * this control operation; used before sending data.
     * You are responsible for having the
     * DIR_TO_HOST (buffer is allocated with control message)
     * or DIR_TO_DEVICE (buffer provided before call) values set
     * correctly in the request type.
     */
    public void setBuffer (byte buf [])
    {
	if (buf == null || buf.length >= 0xffff)
	    throw new IllegalArgumentException ();
	this.buf = buf;
	this.len = (short) buf.length;
    }

    /** Returns the buffer used to transfer data */
    public byte [] getBuffer () { return buf; }

    /** Sets an amount of data to be read (max 64K) */
    public void setLength (int length) { len = (short) length; }

    /** Returns the size of the IO buffer (unsigned 16 bits) */
    public short getLength () { return len; }

    /**
     * Utility for working with control message results,
     * returning the Nth bit (little endian) starting from the
     * specified byte offset in the buffer.
     *
     * <p>Many standard USB data structures use feature IDs as
     * bit numbers in status record fields ... in particular,
     * feature flags are normally bits in device status words.
     */
    public static boolean getBit (int bitNum, byte buf [], int fieldOffset)
    {
	byte	b;

	b = buf [fieldOffset + (bitNum / 8)];
	b >>= bitNum % 8;
	return (b & 0x01) != 0;
    }

    /**
     * Returns the Nth bit (little endian) from the specified
     * byte offset in this message's result.
     *
     * @see #getStatus
     */
    public boolean getBit (int bitNum, int fieldOffset)
    {
	return getBit (bitNum, buf, fieldOffset);
    }


    /**
     * Requests a USB standard descriptor from the specified device.
     *
     * @param descriptorType a {@link Descriptor}.TYPE_* value
     */
    public static byte [] getStandardDescriptor (
	Device dev,
	byte descriptorType,
	byte id,
	int index,
	int len
    ) throws IOException
    {
	return getDescriptor (dev, TYPE_STANDARD,
	    descriptorType, id, index, len);
    }

    /**
     * Requests a USB class descriptor from the specified device.
     */
    public static byte [] getClassDescriptor (
	Device dev,
	byte descriptorType,
	byte id,
	int index,
	int len
    ) throws IOException
    {
	return getDescriptor (dev, TYPE_CLASS,
	    descriptorType, id, index, len);
    }

    /**
     * Requests a USB vendor descriptor from the specified device.
     */
    public static byte [] getVendorDescriptor (
	Device dev,
	byte descriptorType,
	byte id,
	int index,
	int len
    ) throws IOException
    {
	return getDescriptor (dev, TYPE_VENDOR,
	    descriptorType, id, index, len);
    }

    /**
     * Returns a descriptor from the specified device.
     *
     * @param descriptorClass TYPE_STANDARD, TYPE_CLASS, TYPE_VENDOR
     * @param descriptorType a {@link Descriptor}.TYPE_* value;
     *	the high order byte of the control request "value"
     * @param id the ID (low order byte) in the control request "value"
     * @param index the index parameter in the USB control request
     * @param len the maximum amount of data to be returned
     *
     * @exception USBException reflecting a stall condition for
     *	devices not supporting the specified descriptor
     */
    static byte [] getDescriptor (
	Device dev,
	byte descriptorClass,
	byte descriptorType,
	byte id,
	int index,
	int len
    ) throws IOException
    {
	byte		buf [] = new byte [len];
	ControlMessage	msg = new ControlMessage ();

	msg.setRequestType ((byte)(msg.DIR_TO_HOST
				| descriptorClass
				| msg.RECIPIENT_DEVICE
				));
	msg.setRequest (msg.GET_DESCRIPTOR);
	msg.setValue ((short) ((descriptorType << 8) | (0xff & id)));
	msg.setIndex ((short) index);
	msg.setLength (len);
	dev.control (msg);
	return msg.getBuffer ();
    }


    /**
     * Sets a descriptor on the specified device.
     *
     * @param descriptorClass TYPE_STANDARD, TYPE_CLASS, or TYPE_VENDOR
     */
    public static void setDescriptor (
	Device dev,
	byte descriptorClass,
	byte descriptorType,
	byte id,
	int index,
	byte buf []
    ) throws IOException
    {
	if (index > 0xffff || buf.length > 0xffff)
	    throw new IllegalArgumentException ();

	ControlMessage	msg = new ControlMessage ();

	msg.setRequestType ((byte)(msg.DIR_TO_DEVICE
				| descriptorClass
				| msg.RECIPIENT_DEVICE
				));
	msg.setRequest (msg.SET_DESCRIPTOR);
	msg.setValue ((short) ((descriptorType << 8) | (0xff & id)));
	msg.setIndex ((short) index);
	msg.setBuffer (buf);
	dev.control (msg);
    }


    /**
     * Returns an array of languages supported by this device for
     * its string descriptors, or null if no string descriptors
     * are provided.  These languages are identified by the numeric
     * codes described in the USB 1.1 specification, which need
     * mapping to Java locales if you intend to use them with other
     * Java APIs.
     *
     * @see usb.util.LangCode#getLocale
     */
    public static int [] getLanguages (Device dev)
    throws IOException
    {
	byte buf [] = null;

	try {
	    buf = getStandardDescriptor (dev,
		Descriptor.TYPE_STRING,
		(byte) 0, 0, 256);
	} catch (USBException e) {
	    // devices without strings stall here
	    if (!e.isStalled ())
		throw e;
	} 
	if (buf == null || buf.length <4)
	    return null;

	int len = 0xff & buf [0];
	len >>= 1;
	len -= 1;
	if (len <= 0)
	    return null;

	int retval [] = new int [len];
	for (int i = 0; i < len; i++) {
	    int offset = 2 + (2 * i);
	    retval [i] = 0xff & buf [offset];
	    retval [i] += (0xff & buf [offset + 1]) << 8;
	}
	return retval;
    }

    /**
     * Gets the specified string descriptor from the device.
     * @param id Identifier for the string, any byte except zero
     */
    public static String getString (
	Device dev,
	byte id,
	int language
    ) throws IOException
    {
	byte	buf [];
	int	len;

	if (id == 0)
	    throw new IllegalArgumentException ();
	buf = getStandardDescriptor (dev, Descriptor.TYPE_STRING,
		id, language, 256);

	if (buf.length < 2
		|| buf [1] != Descriptor.TYPE_STRING
		|| (len = 0x0ff & buf [0]) > buf.length
		|| ((len -= 2) % 2) != 0
		) {
	    return null;
	}

	// some JVMs won't handle UTF-16LE ("UnicodeLittle"); convert by hand
	char	data [] = new char [len >> 1];

	for (int i = 0; i < data.length; i++) {
	    int j = (2 * i) + 2;
	    data [i] = (char) ((buf [j + 1] << 8) + (0x0ff & buf [j]));
	}
	return new String (data);
    }


    /**
     * Returns the specified type of status.
     * Device and endpoint status are described in section 9.4.5
     * of the USB specification; bits are set and cleared using
     * setFeature and clearFeature.
     *
     * @param dest includes a message type and recipient, such as masking
     *	the values TYPE_CLASS and RECIPIENT_INTERFACE.
     * @param value typically zero
     * @param index field for the feature, such as an endpoint (don't rely
     *	on seeing status for control endpoint zero) or hub port number
     * @param len maximum size of returned array
     *
     * @exception USBException if another driver has claimed that
     *	interface or endpoint
     */
    public static byte [] getStatus (
	Device dev,
	int dest,
	int value,
	int index,
	int len
    ) throws IOException
    {
	ControlMessage		msg = new ControlMessage ();

	msg.setRequestType ((byte)(dest | DIR_TO_HOST));
	msg.setRequest (msg.GET_STATUS);
	msg.setValue ((short)value);
	msg.setIndex ((short)index);
	msg.setLength (len);
	dev.control (msg);
	return msg.getBuffer ();
    }


    /**
     * Clears the identified feature flag to false.
     * @see #setFeature
     * @param dest includes a message type and recipient, such as masking
     *	the values TYPE_CLASS and RECIPIENT_OTHER.
     * @param feature one of the device's defined feature identifiers
     * @param index field for the feature, such as a hub port number (or zero)
     *
     * @exception USBException if another driver has claimed that
     *	interface or endpoint
     */
    public static void clearFeature (
	Device dev,
	int dest,
	int feature,
	int index
    ) throws IOException
    {
	ControlMessage		msg = new ControlMessage ();

	msg.setRequestType ((byte)(dest & ~DIR_TO_HOST));
	msg.setRequest (CLEAR_FEATURE);
	msg.setValue ((short)(feature & 0xff));
	msg.setIndex ((short)index);
	dev.control (msg);
    }

    /**
     * Sets the identified feature flag to true.
     * This will retry in the face of stalls.
     * @see #clearFeature
     * @param dest includes a message type and recipient, such as masking
     *	the values TYPE_STANDARD and RECIPIENT_DEVICE.
     * @param feature one of the device's defined feature identifiers
     * @param index field for the feature, such as a hub port number (or zero)
     *
     * @exception USBException if another driver has claimed that
     *	interface or endpoint
     */
    public static void setFeature (
	Device dev,
	int dest,
	int feature,
	int index
    ) throws IOException
    {
	ControlMessage		msg = new ControlMessage ();

	msg.setRequestType ((byte)(dest & ~DIR_TO_HOST));
	msg.setRequest (SET_FEATURE);
	msg.setValue ((short)(feature & 0xff));
	msg.setIndex ((short)index);
	dev.control (msg);
    }
}
