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
 * A Universal Serial Bus connects a set of (USB) devices to a Host.
 * Such buses are largely autoconfiguring.  Aspects such as power
 * management, driver binding, and device configuration involve policy
 * choices, typically made in cooperation between the host OS kernel 
 * and user-mode software such as applications or services for them.
 *
 * <p> The bus can be examined device by device.  Because devices can be
 * inserted and removed at any time, there is no notion of a self-consistent
 * listing snapshot in this API.  Rather than scanning the bus, instead
 * use {@link Host#addUSBListener addUSBListener}
 * with an object that can immediately react to device addition or removal,
 * or directly walk the tree of USB devices.
 *
 * @author David Brownell
 * @version $Id: Bus.java,v 1.1 2008/10/15 09:28:11 huangmeng Exp $
 */
public interface Bus
{
    /** Returns the USB host to which this bus is connected. */
    public Host getHost ()
    throws IOException;

    /** Returns the root hub of the bus, if it is known yet. */
    public Device getRootHub ()
    throws IOException;

    /**
     * Returns an object representing the device with the specified
     * address (1 through 127), or null if no such device exists.
     *
     * @see Device#getAddress
     */
    public Device getDevice (int address)
    throws IOException;

    /**
     * Returns a host-specific stable identifier for this bus.  An
     * example would be the PCI slot name for its controller, but
     * other identifiers can be used too.  The identifier is expected
     * to be stable across host OS reboots.
     * These identifiers do not have slashes or dashes ("/", "-").
     */
    public String getBusId ()
    throws IOException;
}
