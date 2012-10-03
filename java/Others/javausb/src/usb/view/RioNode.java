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
import javax.swing.JEditorPane;
import javax.swing.tree.TreeNode;

import usb.core.*;
import usb.devices.Rio500;
import usb.devices.Rio500.*;


/**
 * Represent Rio500 MP3 players.
 * The displayed children will represent the folder and song structure
 * on the device and on any external memory card it may have.
 *
 * @version $Id: RioNode.java,v 1.1 2008/10/15 09:28:07 huangmeng Exp $
 */
final public class RioNode extends USBNode
{
    private Rio500		rio = null;
    private FolderEntry		folders [];
    private TreeNode		children [];

    public RioNode (
	Device dev,
	TreeNode parent
    ) throws IOException
    {
	super (dev, parent);
	getRio ();
    }

    private Rio500 getRio ()
    {
	if (rio == null) {
	    try {
		rio = new Rio500 (dev);
		rio.start ();

		folders = rio.getFolders (false);
		if (rio.hasExternalMemory ()) {
		    FolderEntry external [] = rio.getFolders (true);

		    if (external.length != 0) {
			FolderEntry temp [] =
			    new FolderEntry [folders.length + external.length];

			System.arraycopy (folders, 0, temp, 0,
			    folders.length);
			System.arraycopy (external, 0, temp, folders.length,
			    external.length);
			folders = temp;
		    }
		}
		children = new TreeNode [folders.length];

		for (int i = 0; i < folders.length; i++)
		    children [i] = new FolderNode (folders [i]);

		// we should perhaps fire an event saying
		// that our structure changed...

	    } catch (IOException e) {
		// returns null
	    } finally {
		if (rio != null)
		    rio.finish ();
	    }
	}
	return rio;
    }

    private class FolderNode extends USBNode
    {
	final FolderEntry	folder;
	final SongEntry		songs [];

	FolderNode (FolderEntry f) throws IOException
	{
	    super (null, RioNode.this);
	    folder = f;
	    songs = folder.getSongs ();
	}
    
	public String toString ()
	    { return folder.getName1 (); }

	public void draw (JEditorPane editor)
	{
	    StringBuffer	buf = new StringBuffer ();
	    String		name = toString ();

	    editor.setContentType ("text/html");

	    buf.append ("<html>\n<head><title>");
	    buf.append (name);
	    buf.append (
		"</title></head>\n<body><font size=+1><b>");
	    buf.append (folder.isExternal () ? "SmartMedia" : "Built-in Memory");
	    buf.append ("</b></font><br>\n");

	    buf.append ("<table>\n");
	    buf.append ("<tr><th>Number</th>");
	    buf.append ("<th>Size (kB)</th>");
	    buf.append ("<th>Name</th>");
	    buf.append ("</tr>\n");
	    for (int j = 0; j < songs.length; j++) {
		buf.append ("<tr><td>");
		buf.append (j + 1);
		buf.append ("</td>");
		buf.append ("<td>");
		buf.append (songs [j].getLength () / 1024);
		buf.append ("</td>");
		buf.append (songs [j].getName1 ());
		buf.append ("</td>");
		buf.append ("</tr>\n");
	    }
	    buf.append ("</table>\n");

	    buf.append ("</body></html>");
	    editor.setText (buf.toString ());
	}
    }

    public boolean getAllowsChildren ()
	{ return true; }

    public int getChildCount ()
    {
	if (getRio () != null)
	    return children.length;
	return 0;
    }

    public TreeNode getChildAt (int i)
	{ return children [i]; }

    public int getIndex (TreeNode n)
    {
	for (int i = 0; i < children.length; i++)
	    if (n == children [i])
		return i;
	return -1;
    }

    /** This is currently a placeholder. */
    protected void showClassInfo (StringBuffer buf)
    {
	try {
	    buf.append ("<b>Rio Status</b><br>\n");

	    if (getRio () == null) {
		buf.append ("<em>Device claimed by another driver.</em>");
		buf.append ("<br><br>\n");
		return;
	    }

	    rio.start ();
	    showStorage (buf, false);
	    if (rio.hasExternalMemory ())
		showStorage (buf, true);

	} catch (IOException e) {
	    buf.append ("\n<br>");
	    buf.append (e.getClass ().getName ());
	    buf.append (": ");
	    buf.append (e.getMessage ());
	    buf.append ("<br>\n");
	
	} finally {
	    if (rio != null)
		rio.finish ();
	}
    }

    private void showStorage (StringBuffer buf, boolean external)
    throws IOException
    {
	int		temp = rio.getFreeMemory (external);
	MemoryStatus	mem = rio.getMemoryStatus (external);

	// basic memory statistics
	buf.append ("<em>");
	buf.append (external ? "SmartMedia" : "Built-in Memory");
	buf.append ("</em>");
	buf.append (" ");
	buf.append (temp / (1024 * 1024));
	buf.append (".");
	buf.append ((10 * (temp % (1024 * 1024))) / (1024 * 1024));
	buf.append (" MB free<br>\n");

	// XXX "NN MBytes" ... forget the block count

	temp = mem.getBlockCount ();
	if (temp != 0)
	    temp = ((temp - mem.getNumUnusedBlocks ()) * 100) / temp;
	else
	    temp = 1000;
	
	// blocksize always 16K ?
	buf.append (mem.getNumUnusedBlocks ());
	buf.append ("/");
	buf.append (mem.getBlockCount ());
	buf.append (" blocks available, ");
	buf.append (temp);
	buf.append ("% full<br>");

	for (int i = 0; i < folders.length; i++) {
	    if (folders [i].isExternal () != external)
		continue;

	    buf.append ("Folder <em>");
	    buf.append (folders [i].getName1 ());
	    buf.append ("</em>");
	    buf.append (" ... has ");
	    buf.append (folders [i].getSongCount ());
	    buf.append (" songs.");
	    buf.append ("<br>\n");
	}
	
	buf.append ("<br>\n");
    }
}
