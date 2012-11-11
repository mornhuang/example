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
import java.rmi.server.UnicastRemoteObject;

import usb.core.*;


/**
 * RMI proxy for a {@link USBListener}.  Use one of these
 * when you use {@link Host#addUSBListener} to monitor
 * USB structure changes on a Host that may be remote.
 */
public class USBListenerProxy
    extends UnicastRemoteObject	
    implements RemoteUSBListener
{
    private transient USBListener listener;

    /** Constructs a remotable proxy for the specified listener */
    public USBListenerProxy (USBListener listener)
    throws IOException
	{ this.listener = listener; }

    public void busAdded (Bus bus)
    throws IOException
	{ listener.busAdded (bus); }

    public void busRemoved (Bus bus)
    throws IOException
	{ listener.busRemoved (bus); }

    public void deviceAdded (Device dev)
    throws IOException
	{ listener.deviceAdded (dev); }

    public void deviceRemoved (Device dev)
    throws IOException
	{ listener.deviceRemoved (dev); }
}
