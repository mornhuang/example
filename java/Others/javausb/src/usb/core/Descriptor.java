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


/**
 * This is a base class for entities with USB typed descriptors.
 * It encapsulates raw descriptor data (byte arrays) and provides
 * parsed access to that data.  It is intended to be subclassed;
 * subclasses will know how to parse the data after the first two
 * bytes that this base class parses.
 *
 * <p> Descriptors are discussed in sections 9.5 and 9.6 of the
 * USB 1.1 specification.
 *
 * @version $Id: Descriptor.java,v 1.1 2008/10/15 09:27:49 huangmeng Exp $
 */
abstract public class Descriptor implements java.io.Serializable
{
    /** @serial Unparsed data */
    byte	data [];
    /** @serial Start of this descriptor in data  */
    int		offset;

    /** Used when deserializing. */
    protected Descriptor () { }

    /** Creates and initializes a descriptor */
    protected Descriptor (byte raw [])
	{ data = raw; }
    

    // package private
    Descriptor (Descriptor raw, int off)
	{ data = raw.data; offset = off; }
    
    /**
     * Returns the device with which this USB object is associated.
     */
    public abstract Device getDevice ();

    /** Returns the length of the descriptor.  */
    public final int getLength ()
	{ return getU8 (0); }
    
    /** Returns the descriptor type. */
    public final int getDescriptorType ()
	{ return getU8 (1); }
    
	// just USB 1.1 constants here now
	// should add noncore class constants too

    /** Indicates a device descriptor */
    public static final byte TYPE_DEVICE = 1;
    /** Indicates a configuration descriptor */
    public static final byte TYPE_CONFIGURATION = 2;
    /** Indicates a string descriptor */
    public static final byte TYPE_STRING = 3;
    /** Indicates an interface descriptor */
    public static final byte TYPE_INTERFACE = 4;
    /** Indicates an endpoint descriptor */
    public static final byte TYPE_ENDPOINT = 5;

    /** Indicates a USB 2.0 "device qualifier" */
    public static final byte TYPE_DEVICE_QUALIFIER = 6;
    /** Indicates a USB 2.0 "other speed configuration" */
    public static final byte TYPE_OTHER_SPEED = 7;
    /** Indicates interface power consumption */
    public static final byte TYPE_INTERFACE_POWER = 8;

    /** Indicates a hid class descriptor */
    public static final byte TYPE_HID = 0x21;
    // hid-specific
    public static final byte TYPE_REPORT = 0x22;
    // hid-specific
    public static final byte TYPE_PHYSICAL = 0x23;
    /** Indicates a hub class descriptor */
    public static final byte TYPE_HUB = 0x29;

    
    /** Returns a name for the type of descriptor (such as "device") */
    public String getDescriptorTypeName ()
    {
	    // I18N support needed
	switch (getDescriptorType ()) {
	    case TYPE_DEVICE:		return "device";
	    case TYPE_CONFIGURATION:	return "configuration";
	    case TYPE_STRING:		return "string";
	    case TYPE_INTERFACE:	return "interface";
	    case TYPE_ENDPOINT:		return "endpoint";
	    case TYPE_DEVICE_QUALIFIER:	return "qualifier";
	    case TYPE_OTHER_SPEED:	return "other-speed";
	    case TYPE_INTERFACE_POWER:	return "interface-power";
	    case TYPE_HID:		return "hid";
	    case TYPE_REPORT:		return "report";
	    case TYPE_PHYSICAL:		return "physical";
	    case TYPE_HUB:		return "hub";
	}
	return "noncore-" + getDescriptorType ();
    }


    /** Identifier for audio class interfaces. */
    public static final byte CLASS_AUDIO = 1;

    /** Identifier for communications control class interfaces. */
    public static final byte CLASS_COMM = 2;

    /** Identifier for Human Interaction Device (HID) class interfaces. */
    public static final byte CLASS_HID = 3;

    /** Identifier for printer class interfaces. */
    public static final byte CLASS_PRINTER = 7;

    /** Identifier for mass storage class interfaces. */
    public static final byte CLASS_MASS_STORAGE = 8;

    /** Identifier for hub class interfaces. */
    public static final byte CLASS_HUB = 9;

    /** Identifier for communications data class interfaces. */
    public static final byte CLASS_DATA = 10;

    /**
     * Identifier for application-specific interfaces.
     * Subclass codes are used to indicate facilities such as
     * firmware update or an IrDA/USB bridge.
     */
    public static final byte CLASS_APP_SPECIFIC = (byte) 0xfe;

    /** Identifier for vendor-specific interfaces. */
    public static final byte CLASS_VENDOR_SPECIFIC = (byte) 0xff;


    /**
     * Returns the name corresponding to an interface class code.
     * Device class codes are not quite identical to these.
     */
    public static String getClassName (int klass, String zeroValue)
    {
	    // I18N support needed
	switch ((byte) klass) {
		// "reserved" ... device classes used zero a lot.
	    case 0:	return zeroValue;

	    case CLASS_AUDIO:		return "audio";
	    case CLASS_COMM:		return "comm";
	    case CLASS_HID:		return "hid";
	    case CLASS_PRINTER:		return "printer";
	    case CLASS_MASS_STORAGE:	return "mass-storage";
	    case CLASS_HUB:		return "hub";
	    case CLASS_DATA:		return "data";
	    case CLASS_APP_SPECIFIC:	return "app-specific";
	    case CLASS_VENDOR_SPECIFIC:	return "vendor-specific";
	}
	return "unknown-" + klass;
    }

    /**
     * Returns an eight bit unsigned integer value, as encoded
     * in the descriptor at the specified byte offset.
     */
    public final int getU8 (int index)
    {
	return 0xff & data [offset + index];
    }

    /**
     * Returns a sixteen bit unsigned integer value, as encoded
     * in the descriptor at the specified byte offset.
     */
    public final int getU16 (int index)
    {
	int	retval;

	index += offset;
	retval = 0xff & data [index++];
	retval |= 0xff00 & (data [index] << 8);
	return retval;
    }

    /**
     * Returns a thirty-two bit integer value, as encoded
     * in the descriptor at the specified byte offset.
     * Be careful with sign interpretation.
     */
    public final int getU32 (int index)
    {
	int	retval;

	index += offset;
	retval  =  0xff & data [index++];
	retval |= (0xff & data [index++]) << 8;
	retval |= (0xff & data [index++]) << 8;
	retval |= (0xff & data [index  ]) << 8;
	return retval;
    }

    /**
     * Returns the two byte BCD string at the specified offset.
     */
    public String getBCD (int offset)
    {
	char	buf [] = new char [5];
	int	start = 0, end = buf.length;

	buf [0] = (char) ('0' + ((0xf0 & getU8 (offset + 1)) >> 4));
	buf [1] = (char) ('0' + (0x0f & getU8 (offset + 1)));
	buf [2] = '.';
	buf [3] = (char) ('0' + ((0xf0 & getU8 (offset)) >> 4));
	buf [4] = (char) ('0' + (0x0f & getU8 (offset)));
	if (buf [0] == '0')
	    start++;
	if (buf [4] == '0')
	    end--;
	return new String (buf, start, end - start);
    }


    /**
     * Provides successive access to all the descriptors in a block such a
     * complete configuration descriptor.  This returns a "generic"
     * descriptor.  Use methods on configurations and interfaces to get
     * {@link Interface} and {@link Endpoint} descriptor objects.  Then
     * if you use this method and see that the type of the resulting
     * descriptor is neither TYPE_INTERFACE nor TYPE_ENDPOINT, you will
     * know that you have some other kind of descriptor, such as a
     * device-specific one.
     *
     * @return null when no more descriptors are available
     */
    public final Descriptor nextDescriptor ()
    {
	int next;
	
	if ((next = nextDescriptorOffset (offset)) < 0)
	    return null;
	// NOTE:  intentionally not constructing any non-generic
	// subtype here.  Not enough context information.
	return new GenericDescriptor (this, next,
	    getDevice ());
    }


    private static final class GenericDescriptor extends Descriptor
    {
	private Device		dev;

	GenericDescriptor (Descriptor buf, int offset, Device d)
	{
	    super (buf, offset);
	    dev = d;
	}

	public Device getDevice ()
	    { return dev; }
    }


    // package private
    int getOffset ()
	{ return offset; }

    // package private
    int descriptorType (int start)
    {
	if (start++ < 0)
	    return -1;
//	if (data.length <= start) return -2;
	return 0xff & data [start];
    }

    // package private
    int nextDescriptorOffset (int offset)
    {
	if (data.length < offset)
	    return -1;

	offset += 0xff & data [offset];
	if (data.length <= (offset + 2))
	    return -2;
	return offset;
    }
}
