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

package usb.devices;

import java.io.*;
import java.util.Vector;

import usb.core.*;
import usb.util.USBSocket;


/**
 * This provides access Rio500 status, and potentially to the
 * full range of functionality supported by this device.
 * It's been created using the
 * <a href=http://rio500.sourceforge.net>Rio500.sourceforge.net</a>
 * resources ... many thanks to the folk there!
 * At this writing, this class exposes roughly the functionality
 * that the rio_stat program provides.
 *
 * <hr>
 *
 * <p> Be very sure to invoke {@link #finish} (if you'll be using the
 * device again) or {@link #close} (so other drivers can use it), after
 * you {@link #start} to use the Rio.  If you don't, a power cycle of
 * the player may be needed to recover.  Use a <code>finally</code> clause
 * to make sure you clean up before you switch to another task.  Only
 * the thread which invokes <code>start</code> may perform subsequent
 * operations with this object, until <code>finish</code>.
 *
 * <p> <em>Do not use your Rio when its battery indicator is so low that
 * none of the solid segments are showing.  It's been seen to behave quite
 * poorly when it's got that little power.  You could need to reformat your
 * storage media; some users have even had to have their Rio units replaced
 * by Diamond.  (This is evidently a known Rio firmware bug.) </em>
 *
 * @version $Id: Rio500.java,v 1.1 2008/10/15 09:27:59 huangmeng Exp $
 */
public final class Rio500
{
    private final ControlMessage	msg = new ControlMessage ();
    private final byte			scratch [] = new byte [4096];
    private /* final */ USBSocket		socket;
    private Thread			talking;

    /**
     * Initializes to talk to the specified device.
     * Claims ownership of the bulk interface.
     */
    public Rio500 (Device dev) throws IOException
    {
	socket = new USBSocket (dev);
    }

    /**
     * If you didn't finish and close this device, they'll be done here.
     * Since finalization won't always be invoked, don't rely on this.
     */
    protected void finalize () throws IOException
    {
	close ();
    }

    /**
     * After you close this object, other drivers can access
     * this particular MP3 player.  Java code will see it as
     * a disconnect followed by a reconnect, with those other
     * drivers competing to claim the interface.
     */
    public void close () throws IOException
    {
	finish ();
	socket.close ();
    }

    /**
     * You must say you are going to start talking to the player, since it
     * can't be used while your thread is doing so.  If the player is now
     * in use, it typically stops playing.
     */
    public void start () throws IOException
    {
	synchronized (socket) {
	    if (talking != null)
		throw new IllegalStateException ();

	    get (RIO_COMM_START, 0, 0);
	    talking = Thread.currentThread ();
	}

	// why op 42 all the time -- 1ms pause each?
	// they don't always seem to be necessary

	// on first call only, C code gets result  0x60000100
	// Java (and C on other calls) gets result 0xf0000100

	get (RIO_UNKNOWN_42, 0, 0);
	get (RIO_UNKNOWN_42, 0, 0);
    }
    
    /**
     * When you finish with this object, it can normally be used to
     * play back audio until you start to use it again.  Use this in
     * a "finally" clause around any block where you start(). 
     */
    public void finish ()
    {
	boolean		noNukes = false;

	try {
	    checkTalking ();
	    get (RIO_COMM_END, 0, 0);
	    get (RIO_UNKNOWN_42, 0, 0);
	} catch (IOException e) {
	    // ignored
	} catch (IllegalStateException e) {
	    noNukes = true;
	} finally {
	    if (!noNukes)
		talking = null;
	}
    }

    // must have start()ed
    private void checkTalking ()
    {
	if (talking != Thread.currentThread ())
	    throw new IllegalStateException ();
    }

    /**
     * Returns the number of bytes of free memory in the player's
     * internal or external memory.
     */
    public int getFreeMemory (boolean external) throws IOException
    {
	int retval;

	checkTalking ();
	retval = get (RIO_MEM_QUERY, 0, external ? 1 : 0);
	get (RIO_UNKNOWN_42, 0, 0);
	get (RIO_UNKNOWN_42, 0, 0);
	    // rio_add_song retries twice if retval is zero
	    // on grounds that it sometimes lied ... old bug?
	return retval;
    }

    /**
     * Returns the frmware revision of this MP3 player.
     */
    public String getFirmwareRevision ()
    throws IOException
    {
	Device 			dev = socket.getDevice ();
	DeviceDescriptor	info;


	// MORE CORRECTLY, bcd-ize:
	// 0x0ffff & get (RIO_FIRMWARE_REV, 0, 0)

	if (dev != null) {
	    info = dev.getDeviceDescriptor ();
	    return info.getDeviceId ();
	} else
	    return null;
    }

    /**
     * Returns true if a removable SmartMedia memory card is available
     * for song storage.
     */
    public boolean hasExternalMemory () throws IOException
    {
	return ((get (RIO_UNKNOWN_42, 0, 0) >> 30) & 0x01) != 0;
    }

    /**
     * Returns information about the internal or external memory.
     * @param external when true, the external memory is checked.
     */
    public MemoryStatus getMemoryStatus (boolean external) throws IOException
    {
	checkTalking ();
	synchronized (msg) {
	    // which card provides the status?
	    get (RIO_UNKNOWN_51, 1, external ? 1 : 0);

	    return new MemoryStatus (this, rioRead (RIO_MEM_STATUS, 0, 0, 20));
	}
    }


    private int getFolderBlockCount (boolean external)
    throws IOException
    {
	int card = external ? 1 : 0;
	return get (RIO_GET_BLOCK_COUNT, ROOT_FOLDER_ADDRESS, card);
    }


    /**
     * Returns an array of the folders in internal or external memory.
     */
     public FolderEntry [] getFolders (boolean external) throws IOException
     {
	int		blocks = getFolderBlockCount (external);
	byte		folders [] = new byte [blocks * BLOCKSIZE];
	Data		data = new Data (this, folders);
	Vector		v = new Vector (8);
	int		offset = 0;
	int		folderNum = 0;
	FolderEntry	temp;
	FolderEntry	retval [];

	checkTalking ();
	read (external, ROOT_FOLDER_ADDRESS, folders, 0, folders.length);
	while (offset < folders.length) {
	    temp = new FolderEntry (this, external, folderNum++, data, offset);
	    if (temp.getOffset () == 0xffff)
		break;
	    v.addElement (temp);
	    offset += 2048;
	}

	retval = new FolderEntry [v.size ()];
	for (int i = 0; i < retval.length; i++)
	    retval [i] = (FolderEntry) v.elementAt (i);
	return retval;
     }

    /*==================================================================*/

    // commands
    private static final byte	RIO_FIRMWARE_REV = 0x40;
    private static final byte	RIO_UNKNOWN_42 = 0x42;
    private static final byte	RIO_OFFSET_QUERY = 0x43;
    private static final byte	RIO_SET_READ_LEN = 0x45;
    private static final byte	RIO_WRITE = 0x46;
    private static final byte	RIO_COMM_START = 0x47;

    private static final byte	RIO_COMM_END = 0x48;
    private static final byte	RIO_FORMAT = 0x4D;
    private static final byte	RIO_UNKNOWN_4C = 0x4C;
    private static final byte	RIO_SET_READ_PTR = 0x4E;
    private static final byte	RIO_UNKNOWN_4F = 0x4F;

    private static final byte	RIO_MEM_QUERY = 0x50;
    private static final byte	RIO_UNKNOWN_51 = 0x51;
    private static final byte	RIO_FOLDER_QUERY = 0x56;
    private static final byte	RIO_MEM_STATUS = 0x57;
    private static final byte	RIO_FOLDER_END = 0x58;

    private static final byte	RIO_GET_BLOCK_COUNT = 0x59;

    // "big" chunk is 64k; small is a block.
    private static final int	IO_SIZE_BIG = 0x10000;

    // memory is block-addressed
    private static final int	BLOCKSIZE = 0x4000;

    // eight song entries per block
    private static final int	SONG_ENTRY_SIZE = BLOCKSIZE / 8;

    // directory of folders (which are directories of songs)
    private static final int	ROOT_FOLDER_ADDRESS = 0xff00;


    /**
     * This class is a base class that wraps a data buffer;
     * subclasses expose the fields in that data.
     */
    static public class Data
    {
	// keep all internals package-private

	final byte	data [];
	final int	off;
	final Rio500	rio;

	Data (Rio500 dev, byte buf []) { rio = dev; data = buf; off = 0; }
	Data (Rio500 dev, byte buf [], int o) { rio = dev; data = buf; off = o; }

	final int getU8 (int index)
	{
	    return data [index] & 0xff;
	}

	final int getU16 (int index)
	{
	    int	retval;

	    index += off;
	    retval  = 0xff & data [index++];
	    retval |= 0xff00 & (data [index] << 8);
	    return retval;
	}

	final int getU32 (int index)
	{
	    int temp;

	    index += off;
			temp  = 0x0ff & data [3 + index];
	    temp <<= 8; temp |= 0x0ff & data [2 + index];
	    temp <<= 8; temp |= 0x0ff & data [1 + index];
	    temp <<= 8; temp |= 0x0ff & data [0 + index];
	    return temp;
	}

	final String getString (int start, int count)
	{
	    try {
		int end;

		start += off;
		for (end = start; count > 0 && data [end] != 0; end++)
		    count--;
			    // NOTE:  this constructor doesn't exist
			    // in some embedded profiles
		return new String (data, start, end - start, "8859_1");
	    } catch (UnsupportedEncodingException e) {
		throw new RuntimeException ("8859_1");	// "can't happen"
	    }
	}
    }

    /** Exposes the fields of a memory status record.  */
    final static public class MemoryStatus extends Data
    {
	MemoryStatus (Rio500 dev, byte buf []) { super (dev, buf); }

	// 2 bytes ?
	public int getBlockSize () { return getU16 (2); }
	public int getBlockCount () { return getU16 (4); }
	public int getFirstFreeBlock () { return getU16 (6); }
	public int getNumUnusedBlocks () { return getU16 (8); }
	// 4 bytes ?
	// 4 bytes ?
    }

    /** Exposes the fields desribing a folder. */
    final static public class FolderEntry extends Data
    {
	private boolean	external;
	private int	folderNumber;

	FolderEntry (Rio500 dev, boolean e, int folder, Data d, int off)
	{
	    super (dev, d.data, d.off + off);
	    external = e;
	    folderNumber = folder;
	}
	
	public int getFolderNumber () { return folderNumber; }
	
	public int getOffset () { return getU16 (0); }
	// 2 bytes ? /2
	public int getFirstFreeEntryOffset () { return getU16 (4); }
	// 2 bytes ? /6
	// 4 bytes ? /8
	// 4 bytes ? /12
	public int getTime () { return getU16 (16); }
	// Bitmap /20
	public String getName1 () { return getString (1558, 362); }
	public String getName2 () { return getString (1920, 128); }


	// song entries are eight per block (like folders)

	public int getSongCount ()
	    { return getFirstFreeEntryOffset () / SONG_ENTRY_SIZE; }
	
	public SongEntry [] getSongs ()
	throws IOException
	{
	    int		temp = getFirstFreeEntryOffset ();

	    // a lot of these calculations feel quite odd
	    // they're from the c code

	    int		blocks = temp / BLOCKSIZE;
	    int		count = 8 * blocks;

	    temp %= BLOCKSIZE;
	    if (temp > 0)
		blocks++;
	    count += temp / SONG_ENTRY_SIZE;

	    byte 	data [] = new byte [blocks * BLOCKSIZE];
	    SongEntry	retval [] = new SongEntry [count];

	    int		address = getFolderNumber ();

	    address <<= 8;
	    address |= 0x0ff;
	    address &= 0xfff;
	    rio.read (external, address, data, 0, data.length);

	    address = 0;
	    for (int i = 0; i < retval.length; i++) {
		retval [i] = new SongEntry (this, data, address);
		address += SONG_ENTRY_SIZE;
	    }

	    return retval;
	}

	/** Returns true if the folder is on a removable memory card */
	public boolean isExternal () { return external; }

	// upload song
	// download song
	// delete song
	// rename song
    }

    /**
     * Represents the information known about each song.
     */
    static final public class SongEntry extends Data
    {
	private FolderEntry	folder;

	SongEntry (FolderEntry f, byte buf [], int off)
	    { super (f.rio, buf, off); folder = f; }

	public int getOffset () { return getU16 (0); }
	// 2 bytes ?/2
	public int getLength () { return getU32 (4); }
	// 2 bytes ?/8
	// 2 bytes ?/10
	public int getMP3Sig () { return getU32 (12); }
	public int getTime () { return getU32 (16); }
	// Bitmap/20
	public String getName1 () { return getString (1558, 362); }
	public String getName2 () { return getString (1920, 128); }

	// for debug
	public void dump ()
	{
	}

	FolderEntry	getFolder () { return folder; }

	// long		getSize () { return 0; }
	// int		getNumber () { return 0; }
    }

/*
    static final class Bitmap extends Data
    {
	// 2 bytes == #blocks
	// 1536 bytes == bits
    }

    static final public class FolderLocation extends Data
    {
	// 2 bytes offset
	// 2 bytes bytes
	// 2 bytes folder num
    }

    // FOLDER OPS:
	// add folder
	// rename folder
	// delete folder
	// [get folder location]
    
    // format
    // set report func
    // destroy content
    // get content

    // font operations
*/

    /*==================================================================*/
    
    // read and write chunks of memory starting at a given block

    private void read (boolean external, int address, byte buf [], int off, int len)
    throws IOException
    {
	int val;
	int card = external ? 1 : 0;

	if ((len % BLOCKSIZE) != 0)
	    throw new IllegalArgumentException ();

	if ((val = get (RIO_SET_READ_PTR, address, card)) == 0
		|| (val = get (RIO_SET_READ_LEN,
				len / IO_SIZE_BIG,
				len % IO_SIZE_BIG)) == 0)
	    throw new IOException ("rio read");
	// value of the READ command appears to be bytecount (len).
	socket.getInputStream ().read (buf, off, len);
    }

    private void write (int address, byte buf [], int off, int len)
    throws IOException
    {
	int val;

	if ((len % BLOCKSIZE) != 0)
	    throw new IllegalArgumentException ();
	if ((val = get (RIO_UNKNOWN_4C, address, 0)) == 0
		|| (val = get (RIO_UNKNOWN_4F, address, 0)) == 0
		|| (val = get (RIO_WRITE,
				len / IO_SIZE_BIG,
				len % IO_SIZE_BIG)) == 0)
	    throw new IOException ("rio write");
	socket.getOutputStream ().write (buf, off, len);
    }

    /*==================================================================*/

    // sends 32 bits with request code; receives 32 bits back
    // 0 indicates failure
    private int get (byte req, int value, int index)
    throws IOException
    {
	synchronized (msg) {
	    return new Data (this, rioRead (req, value, index, 4)).getU32 (0);
	}
    }

    // synchronize on 'msg' before calling this

    private byte [] rioRead (byte req, int value, int index, int length)
    throws IOException
    {
	msg.setRequestType ((byte) (ControlMessage.DIR_TO_HOST
		    | ControlMessage.RECIPIENT_DEVICE
		    | ControlMessage.TYPE_VENDOR));
	msg.setRequest (req);
	msg.setValue ((short)value);
	msg.setIndex ((short)index);
	msg.setLength (length);

	socket.getDevice ().control (msg);
	return msg.getBuffer ();
    }
}
