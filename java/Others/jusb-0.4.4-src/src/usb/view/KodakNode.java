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

package usb.view;

import java.io.IOException;
import javax.swing.tree.TreeNode;

import usb.core.*;
import usb.devices.Kodak;


/**
 * Represent Kodak DC-240 and DC-280 cameras.
 * The displayed children of this node will be images from the camera.
 *
 * @version $Id: KodakNode.java,v 1.1 2008/10/15 09:28:26 huangmeng Exp $
 */
    // Actually ... should split out the generic stuff to "camera"
    // should also have DigitaCamera and MustekCamera classes
    // and www.pima.net camera
final public class KodakNode extends USBNode
{
    final private Kodak	camera;

    /**
     * This is the standard constructor that viewer nodes need to support.
     */
    public KodakNode (
	Device dev,
	TreeNode parent
    ) throws IOException
    {
	super (dev, parent);
	camera = new Kodak (dev);
    }

    public boolean getAllowsChildren ()
	{ return false; }	// XXX nyet

    public int getChildCount ()
    {
	return camera.getNumPicturesInCard ();
    }

    public TreeNode getChildAt (int n)
    {
	// child should display as thumbnail
	// maybe click-to-enlarge and background image loading
	// want a cache (weak refs!) of the children
	return null;
    }

    /**
     * This currently just shows a few fields of the camera status table.
     */
    protected void showClassInfo (StringBuffer buf)
    {
	buf.append ("<b>Camera Status</b><br>\n");

	buf.append ("Driver Mode: ");
	buf.append (camera.getDriverMode ());
	buf.append ("<br>\n");

	buf.append ("Camera Type: ");
	buf.append (camera.getCameraType ());
	buf.append ("<br>\n");

	buf.append ("Battery Status: ");
	buf.append (camera.getBatteryStatus ());
	buf.append ("<br>\n");

	if (camera.isPowerConnected ())
	    buf.append ("Power connected.<br>");
	else
	    buf.append ("Battery powered.<br>");
	
	// these are the children we'll eventually show
	// ... have to fetch them out of folders though

	buf.append ("Pictures in card: ");
	buf.append (camera.getNumPicturesInCard ());
	buf.append ("<br>\n");

	buf.append ("<br>\n");
    }
}
