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
import java.util.Enumeration;
import java.util.Vector;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import usb.core.*;
import usb.remote.USBListenerProxy;


/**
 * This is a USB tree viewer.  This currently provides as much of the
 * /proc/bus/usb/devices information as can be gotten without actually
 * reading that file.  At this writing, that means that bus topology
 * information is unavailable:  you can't tell what device is connected
 * to a given hub port, in the typical case.  Accordingly, the "tree"
 * is pretty flat so far.
 *
 * <p> It'll get renamed when it's a bit less toylike.
 *
 * @version $Id: Foo.java,v 1.1 2008/10/15 09:28:22 huangmeng Exp $
 */
public class Foo extends JApplet
{
    /**
     * For invoking from the command line; no arguments.
     * The only security restrictions you'll get are those from
     * the operating system; this accesses the /proc/bus/usb
     * files using the process UID/GID.
     */
    public static void main (String argv [])
    {
	try {
	    JFrame	frame = new JFrame ();
	    Foo		foo = new Foo ();

	    frame.getContentPane ().add ("Center", foo);
	    frame.addWindowListener (new WindowAdapter () {
		    public void windowClosing (WindowEvent e)
			{ System.exit (0); }
		    });
	    frame.pack ();
	    frame.setSize (new Dimension (640, 480));
	    frame.setTitle ("Java USB Viewer ("
		    + System.getProperty ("os.name")
		    + " "
		    + System.getProperty ("os.version")
		    + ")"
		    );
	    foo.init ();
	    foo.start ();
	    frame.setVisible (true);

	} catch (Throwable t) {
	    t.printStackTrace ();
	    System.exit (1);
	}
    }
    


    transient private boolean	initted;

    /** Applet constructor saves all work for initialization */
    public Foo ()
	{ }

    /**
     * Applet initialization; expect a security exception
     * unless you've granted this code appropriate privileges.
     */
    public void init ()
    {
	if (initted)
	    return;

	try {
	    getContentPane ().add ("Center",
		    new DisplayPanel ());
	    initted = true;
	} catch (IOException e) {
	    e.printStackTrace ();
	    throw new RuntimeException (e.getMessage ());
	}
    }


    // left pane = jtree bus browser
    // right pane = draw() result of selected jtree node

    // XXX a button or menu bar will be needed.
    // - quit
    // - props (where load what from ... jars, programs, etc)
    // - customizations
    // - refresh bus list

    class DisplayPanel extends JSplitPane
	implements TreeSelectionListener
    {
	private JTree		tree;
	private USBHost		host;
	private JEditorPane	editor;

	DisplayPanel ()
	throws IOException
	{
	    super (HORIZONTAL_SPLIT);
	    JScrollPane		treeView, textView;
	    Dimension		minSize = new Dimension (150, 100);
	    DefaultTreeModel	model;

	    // set up a jtree viewing the USB busses in the left
	    host = new USBHost ();
	    model = new DefaultTreeModel (host, true);

	    host.setModel (model);
	    tree = new JTree (model);

	    tree.getSelectionModel ()
		.setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
	    tree.addTreeSelectionListener (this);

	    tree.putClientProperty ("JTree.lineStyle", "Angled");

	    setLeftComponent (treeView = new JScrollPane (tree));
	    treeView.setMinimumSize (minSize);

	    // set up a rendering pane in the right
	    editor = new JEditorPane ();
	    editor.setEditable (false);

	    setRightComponent (textView = new JScrollPane (editor));
	    textView.setMinimumSize (minSize);


	    // XXX prefer purely automatic sizing/layout
	    setPreferredSize (new Dimension (620, 400));
	    setDividerLocation (220);
	}

	public void valueChanged (TreeSelectionEvent e)
	{
	    TreePath	path = tree.getSelectionPath ();
	    Object	selected;

	    // deselection, node removal, etc
	    if (path == null) {
		host.draw (editor);
		return;
	    }

	    selected = path.getLastPathComponent ();
	    if (selected instanceof USBNode)
		((USBNode)selected).draw (editor);
	    else {
		editor.setContentType ("text/html");
		editor.setText ("<html><head><title></title></head><body>"
		    + "<b>Unrecognized Node Type:</b>"
		    + selected.getClass ().getName ()
		    + "</body></html>");
	    }
	}
    }

    // Root of the JTree ...

    static final class USBHost extends USBNode
	implements USBListener
    {
	private final Host		host;
	private Bus			busses [];
	private HubNode			hubs [];

	private DefaultTreeModel	model;

	public USBHost ()
	throws IOException
	{
	    super (null, null);

	    if ((host = HostFactory.getHost ()) == null)
		throw new RuntimeException ("USB is not available");
	    busses = new Bus [0];	// NPE guard
	    host.addUSBListener (new USBListenerProxy (this));
	    busses = host.getBusses ();

	    // XXX want runtime choice of which display mode
	    // to use:  flattened or hierarchical

	    hubs = new HubNode [busses.length];
	    for (int i = 0; i < hubs.length; i++)
		hubs [i] = new HubNode (busses [i].getRootHub (), this);
	    
	}

	void setModel (DefaultTreeModel m)
	    { model = m; }
	
	private HubNode getBusOf (Device dev)
	throws IOException
	{
	    Bus		bus = dev.getBus ();

	    for (int i = 0; i < busses.length; i++) {
		if (bus == busses [i])
		    return hubs [i];
	    }
System.err.println ("what bus ??");
	    return null;
	}

	public void busAdded (Bus bus)
	throws IOException
	{
	    if (model == null)
		return;

	    synchronized (busses) {
		Bus		newBusses [] = new Bus [busses.length + 1];
		HubNode		newHubs [] = new HubNode [newBusses.length];

		// old busses first
		System.arraycopy (busses, 0, newBusses, 0, busses.length);
		System.arraycopy (hubs, 0, newHubs, 0, busses.length);

		// new one always at the end (simpler)
		newBusses [busses.length] = bus;
		try {
		    newHubs [busses.length]
			    = new HubNode (bus.getRootHub (), this);
		} catch (IOException e) {
		    // why?
		    e.printStackTrace ();
		}

System.err.println ("new bus: " + newHubs [busses.length]);

		busses = newBusses;
		hubs = newHubs;

		if (model != null)
		    model.nodesWereInserted (this,
				new int [] { busses.length });
	    }
	}

	public void busRemoved (Bus bus)
	throws IOException
	{
	    if (model == null)
		return;

	    synchronized (busses) {
		Bus		newBusses [] = new Bus [busses.length - 1];
		HubNode		newHubs [] = new HubNode [newBusses.length];
		int		index, nodeIndex = -1;
		HubNode		node = null;

		// which one went?
		for (int i = index = 0; i < busses.length; i++) {
		    if (busses [i] == bus) {
			nodeIndex = i;
			node = hubs [i];
		    } else {
			newBusses [index] = busses [i];
			newHubs [index] = hubs [i];
			index++;
		    }
		}

System.err.println ("rm bus: " + node);

		busses = newBusses;
		hubs = newHubs;
		if (model != null)
		    model.nodesWereRemoved (this,
			new int [] { nodeIndex },
			new TreeNode [] { node });
	    }
	}

	public void deviceAdded (Device dev)
	throws IOException
	    { getBusOf (dev).deviceAdded (dev, model); }

	public void deviceRemoved (Device dev)
	throws IOException
	    { getBusOf (dev).deviceRemoved (dev, model); }


	public boolean getAllowsChildren ()
	    { return true; }
	public TreeNode getChildAt (int n)
	    { return hubs [n]; }
	public int getChildCount ()
	    { return hubs.length; }

	public int getIndex (TreeNode n)
	{
	    for (int i = 0; i < hubs.length; i++)
		if (hubs [i] == n)
		    return i;
	    return -1;
	}
	public Enumeration children ()
	    { throw new RuntimeException ("nyi"); }

	public String toString ()
	    { return "Host: " + host.toString (); }
	
	public void draw (JEditorPane pane)
	{
	    pane.setContentType ("text/html");
	    pane.setText ("<html><head><title></title></head><body>\n"
		+ "<h2>Java USB Viewer</h2>\n"
		+ "<p>http://jusb.sourceforge.org\n"
		+ "</body></html>"
		);
	}
    }
}
