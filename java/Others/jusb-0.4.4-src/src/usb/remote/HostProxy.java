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

package usb.remote;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

import usb.core.*;


    // FIXME:  Don't let one client's proxy use another's interface
    // claims, configuration, or altsettings.  That state should
    // get bound to some client session protocol.

/**
 * RMI proxy for a local {@link Host} implementation.
 */
public final class HostProxy
    extends UnicastRemoteObject
    implements RemoteHost
{
    private transient Host		host;

    private transient Hashtable		mapping;
    private transient MappingPurger	purger;

    /**
     * Constructs a proxy for the specified Host.
     */
    public HostProxy (Host host)
    throws IOException
    {
	this.host = host;
	mapping = new Hashtable ();
	purger = new MappingPurger ();
	host.addUSBListener (purger);
    }

    public void finalize ()
    {
	try { host.removeUSBListener (purger); }
	catch (IOException e) { /* shouldn't happen */ }
    }

    public Bus [] getBusses ()
    throws IOException
    {
	Bus	value [] = host.getBusses ();
	Bus	retval [];

	if (value.length == 0)
	    return value;

	retval = new Bus [value.length];
	for (int i = 0; i < retval.length; i++)
	    retval [i] = get (value [i]);
	return retval;
    }

    public Device getDevice (String portid)
    throws IOException
	{ return get (host.getDevice (portid)); }

    public void addUSBListener (USBListener listener)
    throws IOException
	{ host.addUSBListener (listener); }

    public void removeUSBListener (USBListener listener)
    throws IOException
	{ host.removeUSBListener (listener); }


    /////////////////////////////////////////////////////////////


    private class MappingPurger extends USBListenerAdapter
    {
	MappingPurger () throws IOException { }

	public void busRemoved (Bus bus)
	throws IOException
	    { mapping.remove (bus); }

	public void deviceRemoved (Device dev)
	throws IOException
	    { mapping.remove (dev); }
    }

    private BusProxy get (Bus bus)
    throws IOException
    {
	if (bus == null)
	    return null;
	synchronized (mapping) {
	    BusProxy	proxy = (BusProxy) mapping.get (bus);

	    if (proxy == null) {
		proxy = new BusProxy (bus);
		mapping.put (bus, proxy);
	    }
	    return proxy;
	}
    }

    private DeviceProxy get (Device dev)
    throws IOException
    {
	if (dev == null)
	    return null;
	synchronized (mapping) {
	    DeviceProxy	proxy = (DeviceProxy) mapping.get (dev);

	    if (proxy == null) {
		proxy = new DeviceProxy (dev,
			    new DeviceSPIProxy ((DeviceSPI) dev),
			    get (dev.getBus ()),
			    get (dev.getHub ()),
			    dev.getAddress ());
		mapping.put (dev, proxy);
	    }
	    return proxy;
	}
    }


    /////////////////////////////////////////////////////////////


    private class BusProxy
	extends UnicastRemoteObject	
	implements RemoteBus
    {
	private transient Bus bus;

	public BusProxy (Bus bus)
	throws IOException
	    { this.bus = bus; }
	
	public Host getHost ()
	    { return HostProxy.this; }

	public Device getRootHub ()
	throws IOException
	    { return get (bus.getRootHub ()); }

	public Device getDevice (int address)
	throws IOException
	    { return get (bus.getDevice (address)); }

	public String getBusId ()
	throws IOException
	    { return bus.getBusId (); }
    }


    /////////////////////////////////////////////////////////////

    // "server side" proxy ...  wrapped in a DeviceProxy for client

    private class DeviceSPIProxy
	extends UnicastRemoteObject	
	implements RemoteDeviceSPI
    {
	private transient DeviceSPI spi;

	public DeviceSPIProxy (DeviceSPI spi)
	throws IOException
	    { this.spi = spi; }
	

	public byte [] getConfigBuf (int n)
	throws IOException
	    { return spi.getConfigBuf (n); }

//	public void setConfiguration (int n)
//	throws IOException
//	    { spi.setConfiguration (n); }


	public byte [] readControl (byte type, byte request,
		short value, short index, short length)
	throws IOException
	    { return spi.readControl (type, request, value, index, length); }

	public void writeControl (byte type, byte request,
		short value, short index, byte buf [])
	throws IOException
	    { spi.writeControl (type, request, value, index, buf); }


	public byte [] readBulk (int ep, int length)
	throws IOException
	    { return spi.readBulk (ep, length); }

	public void writeBulk (int ep, byte buf [])
	throws IOException
	    { spi.writeBulk (ep, buf); }

	public int clearHalt (byte ep)
	throws IOException
	    { return spi.clearHalt (ep); }


	public byte [] readIntr (int ep, int len)
	throws IOException
	    { return spi.readIntr (ep, len); }

	public void writeIntr (int ep, byte buf [])
	throws IOException
	    { spi.writeIntr (ep, buf); }


	public String getClaimer (int ifnum)
	throws IOException
	    { return spi.getClaimer (ifnum); }

	public void claimInterface (int ifnum)
	throws IOException
	    { spi.claimInterface (ifnum); }

	public void setInterface (int ifnum, int alt)
	throws IOException
	    { spi.setInterface (ifnum, alt); }

	public void releaseInterface (int ifnum)
	throws IOException
	    { spi.releaseInterface (ifnum); }


	public Device getChild (int port)
	throws IOException
	    { return get (spi.getChild (port)); }
    }


    /////////////////////////////////////////////////////////////

    // 'smart' proxy to (remote) DeviceSPIProxy;
    // ... sent from server to client, used on client

    // FIXME:  surely there's an easy way to make RMI ensure
    // JVMs only have one of these for each unique SPIProxy?

    private static class DeviceProxy extends Device implements Serializable
    {
	/** @serial Cached Configuration descriptor */
	private Configuration		config;

	/** @serial Descriptor for this device */
	private DeviceDescriptor	desc;

	/** @serial parent hub this is attached to, or null */
	private DeviceProxy		hub;

	/** @serial port on the hub */
	private int			hubPortNum;

	/** @serial how many ports this has, if it's a hub */
	private int			numPorts;

	private transient Device	children [];


	DeviceProxy (Device dev, DeviceSPIProxy spi, BusProxy bus, DeviceProxy h, int addr)
	throws IOException
	{
	    super (spi, bus, addr);
	    hub = h;

	    hubPortNum = dev.getHubPortNum ();
	    numPorts = dev.getNumPorts ();

	    config = dev.getConfiguration ().clone (this);
	    desc = dev.getDeviceDescriptor ().clone (this);
	}

	public Configuration getConfiguration ()
	    { return config; }

	public DeviceDescriptor getDeviceDescriptor ()
	    { return desc; }

	public int getHubPortNum ()
	    { return hubPortNum; }

	public Device getHub ()
	    { return hub; }

	public int getNumPorts ()
	    { return numPorts; }


	public Configuration getConfiguration (int n)
	throws IOException
	{
	    // FIXME:  if it's the current config, return it directly
	    // ... also, track what the current config setting is!

	    return new Configuration (this, getSPI ().getConfigBuf (n));
	}

// FIXME:  make client update the cache !!

	public Device getChild (int port)
	throws IOException
	{
	    if (numPorts == 0 || port < 1 || port > numPorts)
		return null;
	    synchronized (this) {
		int	index = port - 1;

	    	if (children == null)
		    children = new Device [numPorts];
		if (children [index] == null)
		    children [index] = getSPI ().getChild (port);
		return children [index];
	    }
	}
    }
}
