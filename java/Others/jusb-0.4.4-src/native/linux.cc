/*
 * Java USB Library for Linux 2.4
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

#include <sys/ioctl.h>
#include <sys/types.h>
#include <sys/stat.h>

#include <fcntl.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>

#include <asm/types.h>

#include <linux/usbdevice_fs.h>

#include <gcj/cni.h>

// don't let CPP mangle these Java symbols
// ... eventually perhaps best to rename the Java symbols

// namespace usb::linux
#undef linux
// members of class usb::linux::USBException
#undef errno
#undef ENOENT
#undef EBUSY
#undef EPERM
#undef ENODEV
#undef EPIPE
#undef EOVERFLOW
#undef ETIMEDOUT

#include <java/lang/RuntimeException.h>
#include <usb/linux/DeviceImpl.h>
#include <usb/linux/USBException.h>

// for debugging only
#include <stdio.h>

// restore per-thread "errno"
#define errno (*__errno_location())


/*
 * $Id: linux.cc,v 1.1 2008/10/15 09:28:11 huangmeng Exp $
 *
 * This file contains CNI implementations of native methods, with
 * overhead comparable to C++ (i.e. less than JNI).
 */

#define	TIMEOUT	(10 * 1000)		// 10 seconds (in ms.)


static void JvFail (char *) __attribute__ (( __noreturn__ ));

static void JvFail (char *message)
{
    fprintf (stderr, "JvFail: %s\n", message);
    fflush (stderr);
    ::exit (1);
}

/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

// usb.linux.DeviceImpl native methods

jint
usb::linux::DeviceImpl::openNative (jstring filename)
{
    int		fd;
    jbyteArray	bytes;
    char	*real_filename;

    // filename /proc/bus/usb/MMM/NNN, in ASCII
    if (filename == 0)
	return -EINVAL;
    real_filename = (char *) elements (filename->getBytes (/* "UTF8" */));

    // insist on r/w access, nothing else lets you do anything
    if ((fd = open (real_filename, O_RDWR)) < 0)
	return -errno;

    fcntl (fd, F_SETFD, FD_CLOEXEC);
    // if (flock (fd, LOCK_EX) != 0) problem;
    return fd;
}

jint
usb::linux::DeviceImpl::closeNative (jint fd)
{
    int		retval = 0;

    if (fd >= 0) {
	if (::close (fd) < 0)
	    retval = -errno;
    }
    return retval;
}

jint
usb::linux::DeviceImpl::setConfiguration (
    jint	fd,
    jint	config
) {
    if (ioctl (fd, USBDEVFS_SETCONFIGURATION, config) < 0)
	return -errno;
    return 0;
}

jint
usb::linux::DeviceImpl::readBulk (
    jint	fd,
    jint	ep,
    jbyteArray	buf,
    jint	off,
    jint	len
) {
    struct usbdevfs_bulktransfer	bulk;
    jbyte	*buffer;
    int		retval;

    if (buf == NULL)
	return -EINVAL;
    buffer = elements (buf);

    bulk.ep = ep;
    bulk.len = len;
    bulk.data = buffer + off;
    bulk.timeout = TIMEOUT;

    if ((retval = ioctl (fd, USBDEVFS_BULK, &bulk)) < 0)
	retval = -errno;
    return retval;
}

jint
usb::linux::DeviceImpl::writeBulk (
    jint	fd,
    jint	ep,
    jbyteArray	buf,
    jint	off,
    jint	len
) {
    struct usbdevfs_bulktransfer	bulk;
    jbyte	*buffer;
    int		retval;

    if (buf == NULL)
	return -EINVAL;
    buffer = elements (buf);

    bulk.ep = ep;
    bulk.len = len;
    bulk.data = buffer + off;
    bulk.timeout = TIMEOUT;

    if ((retval = ioctl (fd, USBDEVFS_BULK, &bulk)) < 0)
	retval = -errno;
    return retval;
}

jint
usb::linux::DeviceImpl::readIntr (
    jint	fd,
    jint	ep,
    jbyteArray	buf,
    jint	off,
    jint	len
) {
    JvFail ("usb::linux::DeviceImpl::readIntr () not implemented");
}

jint
usb::linux::DeviceImpl::writeIntr (
    jint	fd,
    jint	ep,
    jbyteArray	buf,
    jint	off,
    jint	len
) {
    JvFail ("usb::linux::DeviceImpl::writeIntr () not implemented");
}

jint
usb::linux::DeviceImpl::controlMsg (
    jint	fd,
    jbyte	requestType,
    jbyte	request,
    jshort	value,
    jshort	index,
    jbyteArray	buf,
    jint	off,
    jshort	len
) {
    struct usbdevfs_ctrltransfer	ctrl;
    jbyte	*buffer;
    int		retval;

    ctrl.requesttype = requestType;
    ctrl.request = request;
    ctrl.value = value;
    ctrl.index = index;
    ctrl.length = len & 0xffff;
    ctrl.timeout = TIMEOUT;	// USB should t/o after 5 seconds.

    if (len != 0) {
	char *ptr = (char *) elements (buf);
	ctrl.data = ptr + off;
    } else
	ctrl.data = NULL;

    if ((retval = ioctl (fd, USBDEVFS_CONTROL, &ctrl)) < 0)
	retval = -errno;

    return retval;
}

jint
usb::linux::DeviceImpl::clearHalt (jint fd, jbyte ep)
{
    unsigned	ep_arg = ep & 0xff;

    if (ioctl (fd, USBDEVFS_CLEAR_HALT, &ep_arg) < 0)
	return -errno;
    return 0;
}

jint
usb::linux::DeviceImpl::claimInterface (jint fd, jint ifno)
{
    if (ioctl (fd, USBDEVFS_CLAIMINTERFACE, &ifno) < 0) {
#ifdef	USBDEVFS_DISCONNECT
	int				saved_errno = errno;
	struct usbdevfs_ioctl		command;
	int				retval;

	/*
	 * maybe we need to boot a kernel driver off before we
	 * can bind to this.  a "polite" unbind might be nice;
	 * for now we expect apps to adopt a reasonble policy,
	 * checking if it's claimed already (when it matters).
	 */
	if (saved_errno != usb::linux::USBException::EBUSY)
	    return -errno;
	command.ifno = ifno;
	command.ioctl_code = USBDEVFS_DISCONNECT;
	command.data = 0;
	retval = ioctl (fd, USBDEVFS_IOCTL, &command);
	if (retval < 0)
	    return -saved_errno;
	
	if (ioctl (fd, USBDEVFS_CLAIMINTERFACE, &ifno) < 0)
	    return -errno;
#else
	return -errno;
#endif
    }
    return 0;
}

jint
usb::linux::DeviceImpl::releaseInterface (jint fd, jint ifno)
{
    if (ioctl (fd, USBDEVFS_RELEASEINTERFACE, &ifno) < 0)
	return -errno;
    return 0;
}

jint
usb::linux::DeviceImpl::setInterface (jint fd, jint ifno, jint alt)
{
    struct usbdevfs_setinterface param;

    param.interface = ifno;
    param.altsetting = alt;
    if (ioctl (fd, USBDEVFS_SETINTERFACE, &param) < 0)
	return -errno;
    return 0;
}

jint
usb::linux::DeviceImpl::getHubPorts (jint fd, jbyteArray buf)
{
    struct usbdevfs_ioctl		command;

    if (buf == NULL || buf->length < sizeof (struct usbdevfs_hub_portinfo))
	return -EINVAL;

    command.ifno = 0;
    command.ioctl_code = USBDEVFS_HUB_PORTINFO;
    command.data = elements (buf);

    return ioctl (fd, USBDEVFS_IOCTL, &command);
}

jstring
usb::linux::DeviceImpl::getClaimer (jint fd, jint ifno)
{
    struct usbdevfs_getdriver	info;
    int value;

    info.interface = ifno;
    value = ioctl (fd, USBDEVFS_GETDRIVER, &info);
    if (value < 0)
    	info.driver [0] = 0;
    
    // NOTE:  assumes kernel driver names are in ASCII ...
    return JvNewStringLatin1 (info.driver);
}

/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

// usb.linux.USBException native methods

jstring
usb::linux::USBException::strError (jint errcode)
{
    // I18N ... strerror output _must_ be ASCII (7-bit)
    // else this needs TBD character encoding conversion...

    return JvNewStringUTF (strerror (errcode));
}

