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
 * Represents a host with one or more Universal Serial Busses.
 *
 * <p> A Host is the first object you need to access in order to
 * use this API.  At this writing, accessing that first object
 * uses OS-specific APIs, or unstandardized policies for using
 * RMI Naming.  That will change over time.
 *
 * @see usb.linux.Linux
 *
 * @author David Brownell
 * @version $Id: Host.java,v 1.1 2008/10/15 09:28:15 huangmeng Exp $
 */
public interface Host
{
    /**
     * Returns an array of objects representing the USB busses currently
     * available on this host.
     */
    public Bus [] getBusses ()
    throws IOException;

    /**
     * Returns the device, if any, associated with the specified
     * {@link usb.core.PortIdentifier PortIdentifier} string.
     */
    public Device getDevice (String portId)
    throws IOException;

    /** Adds a callback for USB structure changes */
    public void addUSBListener (USBListener l)
    throws IOException;
	
    /** Removes a callback for USB structure changes */
    public void removeUSBListener (USBListener l)
    throws IOException;
}
