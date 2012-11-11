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


/**
 * Provides access to a USB configuration supported by a device, and to
 * the interfaces associated with that configuration.  Some devices do
 * not expose their interface configuration when they're operational, so
 * their interface descriptions may not be available.
 *
 * <p> Configurations provide a coarse granularity mechanism to select device
 * functionality.  For example, one configuration of a telephone might support
 * use of the headset, while another might be a conference call setup.  Such
 * configurations may affect device power consumption levels, and hence the
 * ability to work using power supplied from the hub to which they are
 * connected.
 * <em>Not all devices need, or support, multiple configurations.</em>
 * consumption of USB bandwidth.
 *
 * <p> Most fields of this descriptor are specified in section 9.6.2 of
 * the USB 1.1 specification.
 *
 * @version $Id: Configuration.java,v 1.1 2008/10/15 09:27:28 huangmeng Exp $
 */
final public class Configuration extends Descriptor
{
    /** The device whose configuration is described */
    private Device	dev;

    // interface and endpoint descriptors get recreated on the client
    // they share data from this config's buffer

    	// FIXME: this needs an SPI hook to update current alt settings

    // current alternate for each of the selected interfaces
    private transient Interface	interfaces [];
    private transient int	altSettings [];

    public Configuration clone (Device d)
    {
	return new Configuration (d, data);
    }

    public Configuration (Device device, byte descriptor [])
    {
	super (descriptor);
	if (getDescriptorType () != TYPE_CONFIGURATION)
	    throw new IllegalArgumentException ();
	dev = device;
	interfaces = new Interface [getU8 (4)];
	altSettings = new int [interfaces.length];
    }

    /**
     * Returns the total length of the descriptor for this configuration,
     * including descriptors for interfaces, their endpoints, and other
     * associated elements.  That full descriptor may not be available
     * when the device is operational; some devices won't expose it.
     */
    public int getTotalLength ()
	{ return getU16 (2); }

    /** Returns the number of interfaces exposed in this configuration. */
    public int getNumInterfaces ()
    {
	return getU8 (4);
    }

    /**
     * Value passed in a setConfiguration() request to select
     * this configuration.
     */
    // @see Device#setConfiguration(int)
    public int getConfigurationValue ()
	{ return getU8 (5); }

    /**
     * Returns a string describing this configuration, in the
     * specified language; or null if no such string is available.
     */
    public String getConfiguration (int language)
    {
	try {
	    int value = getU8 (6);
	    if (value == 0)
		return null;
	    return getDevice ().getString (value, language);
	} catch (IOException e) {
	    return null;
	}
    }

    /**
     * If this bit is set in an attribute mask, this configuration
     * provides some of its own power in addition to drawing the
     * amount of power described by {@link #getMaxPower}.
     */
    public static final byte ATTR_SELF_POWERED = 0x40;

    /**
     * If this bit is set in an attribute mask, this configuration
     * supports remote wakeup (which must be separately enabled).
     */
    public static final byte ATTR_REMOTE_WAKEUP = 0x20;

    /**
     * Returns attributes of this configuration.  These are specified
     * in table 9.8 of the USB 1.1 specification, and expose whether the
     * device can be self powered, and whether it support remote wakeup.
     * Use the ATTR_* constants in this class.
     */
    public int getAttributes ()
	{ return getU8 (7); }

    /**
     * Returns the maximum amount of power used in this configuration,
     * in units of 2mA.
     */
    public int getMaxPower ()
	{ return getU8 (8); }

    /** Returns the device with which the configuration is associated */
    public Device getDevice ()
    {
	return dev;
    }

    /**
     * Returns the specified interface from this configuration, or
     * null if the device won't provide it.  Some devices won't return
     * their full descriptor set when they're operational, and most
     * don't expose interface or endpoint descriptors except as part
     * of their configuration.
     */
    public Interface getInterface (int index, int alt)
    throws IOException
    {
	synchronized (this) {
	    if (interfaces == null) {
		interfaces = new Interface [getNumInterfaces ()];
		altSettings = new int [interfaces.length];
	    }

	    if (interfaces [index] == null || alt != altSettings [index]) {
		int		offset = getOffset ();
		Interface	temp = null;

		do {

		    // Look at each interface descriptor
		    offset = nextDescriptorOffset (offset);
		    if (offset < 0)
		    	break;
		    if (descriptorType (offset) != TYPE_INTERFACE)
			continue;
		    if (temp == null)
			temp = new Interface (this, offset);
		    else
			temp.offset = offset;

		    // see if it's interesting
		    int		number = temp.getNumber ();
		    int		altSetting = temp.getAlternateSetting ();

		    if (interfaces [number] == null
			    && altSetting == altSettings [number]) {
			interfaces [number] = temp;
			if (number != index || alt != altSetting) {
			    temp = null;
			    continue;
			}
		    }

		    // this is the one!!
		    if (number == index && alt == altSetting)
			return temp;
		    
		} while (offset > 0);
		return null;

	    } else
		return interfaces [index];
	}
    }
}
