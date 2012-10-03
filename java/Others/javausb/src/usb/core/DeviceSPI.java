/*
 * Java USB Library
 * Copyright (C) 2000-2001 by David Brownell
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
 * This is not an Application Programming Interface.
 * It is used internally to the implementation, and connects to the
 * host OS implementation (probably in native code).
 */
public interface DeviceSPI
{
	// FIXME expects getEndpoint() 0x70 bits not to be set...

	// FIXME probably need to update all these to pass
	// some sort of timeout parameter.  Linux imposes a
	// ten second limitation, but running remotely would
	// make any such service guarantees unreasonable.

    /**
     * Returns a buffer holding the entire set of configuration descriptors
     * for the specified configuration.
     * The format of those descriptors is described in the USB 2.0
     * specification.
     *
     * @param config Number of the configuration, from zero to the limit
     *	specified by {@link DeviceDescriptor#getNumConfigurations}
     */
    public byte [] getConfigBuf (int config)
    throws IOException;

    // public void setConfiguration (int n)
    // throws IOException;


    /**
     * Issues a control IN request with an optional data READ phase.
     * The request goes to the default control endpoint (CONTROL IN zero).
     * No interface needs to be claimed in order to issue control requests.
     *
     * <p>Most of the parameters here are used to build the SETUP packet
     * for the control request, which is always written to the device.
     * Note an OUT control request with only a setup packet is different
     * from the IN request with the same bits in the setup packet data;
     * it goes to a different endpoint.
     *
     * @param type Masked together using three constants from
     *	{@link ControlMessage}: DIR_TO_HOST, a TYPE_*, and a RECIP_*.
     * @param request bRequest field, either standard (from table 9-3 in
     *	the USB 2.0 specification) for TYPE_STANDARD, or else as specified
     *	in the class or vendor device type specification.
     * @param value sixteen bit field associated with request
     * @param index sixteen bit field associated with request
     * @param length How much data should be read; may be zero to indicate
     *	that no READ phase follows the SETUP packet.
     * @return The data read from the device.  If this is shorter than
     *	the requested length, the caller must determine whether to treat
     *	that as an error.  For example, it's typical to request a full size
     *	string descriptor, expecting to get back only the bytes that exist,
     *	rather than read a partial descriptor to learn the size, and then
     *	reading the whole thing.
     */
    public byte [] readControl (byte type, byte request,
	    short value, short index, short length)
    throws IOException;

    /**
     * Issues a control OUT request with an optional data WRITE phase.
     * The request goes to the default control endpoint (CONTROL OUT zero).
     * No interface needs to be claimed in order to issue control requests.
     *
     * <p>Most of the parameters here are used to build the SETUP packet
     * for the control request, which is always written to the device.
     * Note an OUT control request with only a setup packet is different
     * from the IN request with the same bits in the setup packet data;
     * it goes to a different endpoint.
     *
     * @param type Masked together using three constants from
     *	{@link ControlMessage}: DIR_TO_DEVICE, a TYPE_*, and a RECIP_*.
     * @param request bRequest field, either standard (from table 9-3 in
     *	the USB 2.0 specification) for TYPE_STANDARD, or else as specified
     *	in the class or vendor device type specification.
     * @param value sixteen bit field associated with request
     * @param index sixteen bit field associated with request
     * @param buf The data to be written.  Length may be zero to indicate
     *	that no WRITe phase follows the SETUP packet.
     */
    public void writeControl (byte type, byte request,
	    short value, short index, byte buf [])
    throws IOException;

	// hmm, what about overruns on bulk/interrupt read?

    /**
     * Reads a specified number of bytes from a BULK IN endpoint.
     * This may be fewer bytes than were requested; the caller must
     * decide whether that's an error in this particular case.
     * If this is an exact multiple of {@link Endpoint#getMaxPacketSize},
     * the caller may need to decide whether to see if it is followed
     * by a zero length packet.
     *
     * <p>An endpoint's interface should be claimed before it is
     * used for bulk I/O.
     *
     * @param ep Endpoint direction and address, as specified in an
     *	endpoint descriptor by {@link Endpoint#getEndpoint}.
     * @param length How many bytes to read.
     * @return The bytes actually read.
     */
    public byte [] readBulk (int ep, int length)
    throws IOException;

    /**
     * Writes a specified number of bytes to a BULK OUT endpoint.
     * If this isn't an exact multiple of {@link Endpoint#getMaxPacketSize}
     * then the last packet is "short".  To write such an exact multiple
     * with a "short" last packet, explicitly follow this write by another,
     * using a buffer with length zero.
     *
     * <p>An endpoint's interface should be claimed before it is
     * used for bulk I/O.
     *
     * @param ep Endpoint direction and address, as specified in an
     *	endpoint descriptor by {@link Endpoint#getEndpoint}.
     * @param buf The bytes to write.
     */
    public void writeBulk (int ep, byte buf [])
    throws IOException;

    /**
     * Clears halt/stall status on an endpoint.
     * Bulk and interrupt endpoints will stall to report some kinds of
     * error.  Clearing this status resets the "data toggle" at the USB
     * protocol level, and re-enables I/O to the previously halted endpoint.
     *
     * <p>The endpoint's interface should be claimed before clearing its
     * halt status.
     *
     * @param ep Endpoint address and direction, as returned by
     *	{@link Endpoint#getEndpoint}.
     */
    public int clearHalt (byte ep)
    throws IOException;

	// NOTE:  interrupt handling model is subject to change.

	// Dedicating a thread seems a requirement, unless maybe "New IO"
	// (JDK 1.4) is assumed.  It doesn't necessarily need to avoid
	// callbacks native-to-Java ... but at least the original Linux
	// host implementation doesn't do well (yet?) with the notion of
	// kernel (native) calling to userland (and thence to Java), so
	// we end up with this when all the calls go the other way.

	// Hmm, what about ZLPs and interrupt polling... bad idea,
	// but what if someone tries?

    /**
     * Reads a specified number of bytes from a INTERRUPT IN endpoint.
     * This may be fewer bytes than were requested; the caller must
     * decide whether that's an error in this case.
     *
     * <p>This blocks until an interrupt transfer completes.  It's the
     * caller's responsibility to ensure that the endpoint is polled
     * often enough to meet {@link Endpoint#getInterval} requirements
     * after a transfer completes.
     *
     * <p>An endpoint's interface should be claimed before it is
     * used for interrupt I/O.
     *
     * @param ep Endpoint direction and address, as specified in an
     *	endpoint descriptor by {@link Endpoint#getEndpoint}.
     * @param length How many bytes to read.
     * @return The bytes actually read.
     */
    public byte [] readIntr (int ep, int len)
    throws IOException;

    /**
     * Writes a specified number of bytes to a INTERRUPT OUT endpoint.
     * If this isn't an exact multiple of {@link Endpoint#getMaxPacketSize}
     * then the last packet is "short".  To write such an exact multiple
     * with a "short" last packet, explicitly follow this write with a
     * write of a buffer with length zero.
     *
     * <p>This blocks until an interrupt transfer completes.  It's the
     * caller's responsibility to ensure that the endpoint is polled
     * often enough to meet {@link Endpoint#getInterval} requirements
     * after a transfer completes.
     *
     * <p>An endpoint's interface should be claimed before it is
     * used for interrupt I/O.
     *
     * @param ep Endpoint direction and address, as specified in an
     *	endpoint descriptor by {@link Endpoint#getEndpoint}.
     * @param buf The bytes to write.
     */
    public void writeIntr (int ep, byte buf [])
    throws IOException;


    /**
     * Identifies the driver claiming a particular interface in the
     * current configuration.
     * USB is oriented towards "interface drivers", where several different
     * drivers can cooperate to handle different functions of a device.
     * (An example might be an audio output connected to a speaker, and
     * a HID control for input to a software mixer.)
     *
     * @param ifnum Number from the descriptor, returned by
     *	{@link Interface#getNumber}.
     * @return Driver name, or null to indicate the interface is not in use
     *	by any driver.  Driver names are not standardized between host
     *	operating systems.
     */
    public String getClaimer (int ifnum)
    throws IOException;

    /**
     * Claims a particular interface in the current configuration.
     * Might kick another driver off of that interface; avoid calling
     * this unless no other driver has claimed it.
     *
     * @see #releaseInterface
     * @param ifnum Number from the descriptor, returned by
     *	{@link Interface#getNumber}.
     */
    public void claimInterface (int ifnum)
    throws IOException;

    /**
     * Assigns a particular alternate setting to a given interface.
     * These are often used to change characteristics such as throughput
     * requirements or maximum packet sizes.
     *
     * @param ifnum Number from the descriptor, returned by
     *	{@link Interface#getNumber}.
     * @param alt Number from the descriptor, returned by
     *	{@link Interface#getAlternateSetting}.
     */
    public void setInterface (int ifnum, int alt)
    throws IOException;

    /**
     * Releases a claim on a given interface, so that another driver
     * can safely claim it.
     *
     * @see #claimInterface
     * @param ifnum Number from the descriptor, returned by
     *	{@link Interface#getNumber}.
     */
    public void releaseInterface (int ifnum)
    throws IOException;


    /**
     * Returns children of a hub device.
     *
     * @param port Number of the hub port of interest, from one to the
     *	{@link Hub#getNumPorts}.
     * @return Device connected to that port, or null if there is no
     *	such device.  Always returns null for devices that are not hubs.
     */
    public Device getChild (int port)
    throws IOException;
}
