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
 * Subclassable USBListener adapter, which ignores all calls.
 * Your subclasses can provide behaviors only for the events
 * relevant to their roles.
 *
 * <p> You may want to wrap your instances within a
 * {@link usb.remote.USBListenerProxy} before you hand them
 * to {@link Host#addUSBListener}.
 */
public class USBListenerAdapter
    implements USBListener
{
    /** Default constructor does nothing. */
    public USBListenerAdapter () throws IOException { }

    public void busAdded (Bus bus) throws IOException {}
    public void busRemoved (Bus bus) throws IOException {}
    public void deviceAdded (Device dev) throws IOException {}
    public void deviceRemoved (Device dev) throws IOException {}
}
