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
 * This is not an Application Programming Interface.
 * It is used internally to the implementation.
 */
public interface DeviceSPI
{
    public byte [] getConfigBuf (int n)
    throws IOException;

    // public void setConfiguration (int n)
    // throws IOException;


    public byte [] readControl (byte type, byte request,
	    short value, short index, short length)
    throws IOException;

    public void writeControl (byte type, byte request,
	    short value, short index, byte buf [])
    throws IOException;


    public byte [] readBulk (int ep, int length)
    throws IOException;

    public void writeBulk (int ep, byte buf [])
    throws IOException;

    public int clearHalt (byte ep)
    throws IOException;


    public byte [] readIntr (int ep, int len)
    throws IOException;

    public void writeIntr (int ep, byte buf [])
    throws IOException;


    public String getClaimer (int ifnum)
    throws IOException;

    public void claimInterface (int ifnum)
    throws IOException;

    public void setInterface (int ifnum, int alt)
    throws IOException;

    public void releaseInterface (int ifnum)
    throws IOException;


    public Device getChild (int port)
    throws IOException;
}
