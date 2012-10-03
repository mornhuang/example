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
 * Provides access to a USB device.
 *
 * <p> To use a device, first make sure that
 * you can use its current configuration.
 * Then get the interfaces for that configuration and claim the ones
 * you want to use.  Then just use the endpoints from those interfaces.
 * You might want to switch some interfaces
 * to their alternate settings before you start using endpoints.
 *
 * <p> To provide behaviors specific to a device, or type of device,
 * wrap one of these Device objects in a class that understands how
 * to perform device-specific operations.
 *
 * <hr>
 *
 * <p> Except for a root hub, or when interrupted by disconnection,
 * <code><em>dev</em>.getHub().getChild(<em>dev</em>.getHubPortNum()</code>
 * is equal to <code><em>dev</em></code>.  However, it is good to avoid
 * assuming that disconnects don't happen; they can be caused just by
 * surge currents through hubs.
 * Somewhat related:  the best available view of hub parent/child
 * relationships may not always match physical reality because of an
 * inherent scheduling lag, and sometimes event reordering, that also
 * affects {@link USBListener}.
 *
 * @see Bus#getDevice
 * @see USBListener
 * @see #getHub
 * @see #getChild
 *
 * @author David Brownell
 * @version $Id: Device.java,v 1.1 2008/10/15 09:27:56 huangmeng Exp $
 */
abstract public class Device implements java.io.Serializable
{
    /** @serial handle to device implementation */
    private DeviceSPI		spi;

    /** @serial The bus we're connected to */
    private Bus			bus;

    /** @serial Bus address for this device connection */
    private int			address;

    /** Used when deserializing. */
    protected Device () { }

    /**
     * Used by implementations of {@link Host} and {@link Bus}.
     */
    protected Device (DeviceSPI spi, Bus bus, int address)
    {
	// we are a "smart proxy" or a real implementation
	if (spi != null)
	    this.spi = spi;
	else
	    this.spi = (DeviceSPI) this;

	this.bus = bus;
	this.address = address;
    }

    protected DeviceSPI getSPI () { return spi; }

    /**
     * Device status bit that reports the device is self powered.
     * You can't set or clear this feature.
     */
    public static final int DEVICE_SELFPOWERED = 0;

    /**
     * Device status bit used to enable and disable remote
     * wakeup; this feature may be set and cleared.
     */
    public static final int DEVICE_REMOTE_WAKEUP = 1;


    /**
     * Returns the bus with which the device is associated.
     */
    public final Bus getBus () { return bus; }


    /**
     * Returns port identifier associated with this device.
     */
    public final String getPortIdentifier ()
    {
	try { return new PortIdentifier (this).toString (); }
	catch (IOException x) { return null; }
    }

    /**
     * Returns address assigned to this device on its USB {@link Bus}.
     * Addresses are established when the device is connected, get reused
     * after devices disconnect, and don't correspond to anything that
     * users can affect.  In short, avoid using these.
     *
     * @see #getPortIdentifier
     * @see Bus#getDevice
     */
// FIXME:  this  method should just go away
// "avoid using these" is easier if it's not there...
    public final int getAddress () { return address; }

    /**
     * Returns the hub to which this device is connected, or null if
     * this device is disconnected or is the root hub for its bus.
     */
    abstract public Device getHub ();

    /**
     * Returns the number of the hub port to which this device is connected.
     */
    abstract public int getHubPortNum ();

    /**
     * Returns the speed of the connection the device is using. 
     * The return value is either "high", "full", or  "low"; or else null.
     * Null is used for root hubs, or indicates some error prevented
     * determining the speed being used for the device's hub port.
     */
    abstract public String getSpeed ();

    /**
     * Returns the number of ports in this hub; or zero.
     * This should return the same value as {@link Hub#getNumPorts}.
     */
    abstract public int getNumPorts ();
    
    /**
     * Returns the device descriptor.
     */
    abstract public DeviceDescriptor getDeviceDescriptor ();

    /**
     * Returns the current device configuration's descriptor.
     */
    abstract public Configuration getConfiguration ()
    throws IOException;


    // the rest are less likely to be able to use local caches


    /**
     * Returns the device connected to this hub's specified port
     * (origin one), or null.
     */
    abstract public Device getChild (int port)
    throws IOException;

    /**
     * Returns the specified configuration descriptor.
     */
    abstract public Configuration getConfiguration (int index)
    throws IOException;

//    /**
//     * Assigns a device configuration, which must be one of those
//     * supported by this device.
//     */
//    public void setConfiguration (int index)
//    throws IOException;

    /**
     * Returns the string index with the specified ID and language,
     * or null if there is no such string. 
     * Zero as a language ID means to pick a default.
     */
    public String getString (int id, int language)
    throws IOException
    {
	return ControlMessage.getString (this, (byte) id, language);
    }

    /**
     * Sends a control message to the device, optionally sending or
     * receiving associated data.  Control messages may be used
     * whether or not you have claimed some interface. 
     *
     * @exception USBException if exchanging a control message
     *	with an interface or endpoint some other driver has claimed
     */
    public void control (ControlMessage msg)
    throws IOException
    {
	byte data [];

	if ((msg.getRequestType () & msg.DIR_TO_HOST) == msg.DIR_TO_HOST)
	    msg.setBuffer (spi.readControl (
		    msg.getRequestType (), msg.getRequest (),
		    msg.getValue (), msg.getIndex (), msg.getLength ()));
	else
	    spi.writeControl (msg.getRequestType (), msg.getRequest (),
		    msg.getValue (), msg.getIndex (), msg.getBuffer ());
    }
}
