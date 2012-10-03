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
import java.util.EventListener;


/**
 * Interface implemented by objects that want to monitor USB structure.
 * The order in which these changes are reported is not necessarily
 * going to be the order in which the changes were seen in the real
 * world, and delays also occur.
 *
 * <p> For the moment it seems wise to ensure that your listener
 * callbacks can safely handle duplicated notifications.
 */
public interface USBListener extends EventListener
{
    // not bothering to wrap the info inside an "EventObject"

    /** New bus added.  */
    public void busAdded (Bus bus)
    throws IOException;

    /** Bus removed. */
    public void busRemoved (Bus bus)
    throws IOException;

    /** New device added.  */
    public void deviceAdded (Device dev)
    throws IOException;

    /** Device removed. */
    public void deviceRemoved (Device dev)
    throws IOException;
}
