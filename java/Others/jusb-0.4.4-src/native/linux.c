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

#include <jni.h>

#include "usb_linux_DeviceImpl.h"
#include "usb_linux_USBException.h"


// for debugging only
// #include <stdio.h>



/*
 * $Id: linux.c,v 1.1 2008/10/15 09:28:09 huangmeng Exp $
 *
 * This file contains implementations of native methods used to to
 * talk to the (preliminary) "usbdevfs" in the Linux 2.4 kernel,
 * so that Java programs can interact directly with USB devices.
 *
 * Green threads doesn't seem to wrap the ioctl() calls this uses,
 * and may interrupt them rudely.  Instead, use "-native" threads.
 */

#define	TIMEOUT	(10 * 1000)		// 10 seconds (in ms.)


/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

// usb.linux.DeviceImpl native methods

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_openNative (
    JNIEnv	*env,
    jobject	ignored,
    jstring	filename
) {
    int		fd;
    const char	*real_filename;
    jboolean	flag;

    // insist on r/w access, nothing else lets you do anything
    real_filename = (*env)->GetStringUTFChars (env, filename, &flag);
    if (real_filename == 0)
	return -EINVAL;
    fd = open (real_filename, O_RDWR);
    (*env)->ReleaseStringUTFChars (env, filename, real_filename);

    if (fd < 0)
	fd = -errno;
    else {
	fcntl (fd, F_SETFD, FD_CLOEXEC);
	// if (flock (fd, LOCK_EX) != 0) problem;
    }
    return fd;
}

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_closeNative (
    JNIEnv	*env,
    jclass	ignored,
    jint	fd
) {
    int		retval = 0;

    if (fd >= 0) {
	if (close (fd) < 0)
	    retval = -errno;
    }
    return retval;
}

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_setConfiguration (
    JNIEnv	*env,
    jclass	ignored,
    jint	fd,
    jint	config
) {
    if (ioctl (fd, USBDEVFS_SETCONFIGURATION, config) < 0)
	return -errno;
    return 0;
}

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_readBulk (
    JNIEnv	*env,
    jobject	this,
    jint	fd,
    jint	ep,
    jbyteArray	buf,
    jint	off,
    jint	len
) {
    struct usbdevfs_bulktransfer	bulk;
    jboolean	isCopy;
    jbyte	*buffer;
    int		retval;

    buffer = (*env)->GetByteArrayElements (env, buf, &isCopy);
    if (buffer == 0)
	return -EINVAL;

    bulk.ep = ep;
    bulk.len = len;
    bulk.data = buffer + off;
    bulk.timeout = TIMEOUT;
    if ((retval = ioctl (fd, USBDEVFS_BULK, &bulk)) < 0)
	retval = -errno;

    (*env)->ReleaseByteArrayElements (env, buf, buffer, 0);
    return retval;
}

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_writeBulk (
    JNIEnv	*env,
    jobject	this,
    jint	fd,
    jint	ep,
    jbyteArray	buf,
    jint	off,
    jint	len
) {
    struct usbdevfs_bulktransfer	bulk;
    jbyte	*buffer;
    jboolean	isCopy;
    int		retval;

    buffer = (*env)->GetByteArrayElements (env, buf, &isCopy);
    if (buffer == 0)
	return -EINVAL;

    bulk.ep = ep;
    bulk.len = len;
    bulk.data = buffer + off;
    bulk.timeout = TIMEOUT;
    if ((retval = ioctl (fd, USBDEVFS_BULK, &bulk)) < 0)
	retval = -errno;

    (*env)->ReleaseByteArrayElements (env, buf, buffer, JNI_ABORT);
    return retval;
}

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_readIntr (
    JNIEnv	*env,
    jobject	this,
    jint	fd,
    jint	ep,
    jbyteArray	buf,
    jint	off,
    jint	len
) {
    // XXX 

    return -EINVAL;
}

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_writeIntr (
    JNIEnv	*env,
    jobject	this,
    jint	fd,
    jint	ep,
    jbyteArray	buf,
    jint	off,
    jint	len
) {
    // XXX 

    return -EINVAL;
}

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_controlMsg (
    JNIEnv	*env,
    jobject	this,
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
    jboolean	isCopy;
    int		retval;

    if (len != 0) {
	buffer = (*env)->GetByteArrayElements (env, buf, &isCopy);
	if (buffer == NULL)
	    return -EINVAL;
    } else
	buffer = NULL;

    ctrl.requesttype = requestType;
    ctrl.request = request;
    ctrl.value = value;
    ctrl.index = index;
    ctrl.length = len & 0xffff;
    ctrl.timeout = TIMEOUT;	// USB should t/o after 5 seconds.
    ctrl.data = buffer + off;
    if ((retval = ioctl (fd, USBDEVFS_CONTROL, &ctrl)) < 0)
	retval = -errno;

    if (buffer != NULL)
	(*env)->ReleaseByteArrayElements (env, buf, buffer, 0);
    return retval;
}

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_clearHalt (
    JNIEnv	*env,
    jclass	ignored,
    jint	fd,
    jbyte	ep
) {
    unsigned	ep_arg = ep & 0xff;

    if (ioctl (fd, USBDEVFS_CLEAR_HALT, &ep_arg) < 0)
	return -errno;
    return 0;
}

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_claimInterface (
    JNIEnv	*env,
    jclass	ignored,
    jint	fd,
    jint	ifno
) {
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
	if (saved_errno != EBUSY)
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

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_releaseInterface (
    JNIEnv	*env,
    jclass	ignored,
    jint	fd,
    jint	ifno
) {
    if (ioctl (fd, USBDEVFS_RELEASEINTERFACE, &ifno) < 0)
	return -errno;
    return 0;
}

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_setInterface (
    JNIEnv	*env,
    jclass	ignored,
    jint	fd,
    jint	ifno,
    jint	alt
) {
    struct usbdevfs_setinterface param;

    param.interface = ifno;
    param.altsetting = alt;
    if (ioctl (fd, USBDEVFS_SETINTERFACE, &param) < 0)
	return -errno;
    return 0;
}

JNIEXPORT jint JNICALL
Java_usb_linux_DeviceImpl_getHubPorts (
    JNIEnv	*env,
    jclass	ignored,
    jint	fd,
    jbyteArray	buf
) {
    jboolean				isCopy;
    char 				pad [128];
    struct usbdevfs_ioctl		command;
    jint				retval;
    jbyte				*array;

    // FIXME:  in some 2.4.0-test releases, "pad" seemed to
    // prevent some sort internal Java error;  kernel bug?
    // Recently, it didn't appear to get overwritten...
    // ... and an old C binary wasn't segfaulting either.
    memset (pad, 0, sizeof pad);

    if ((*env)->GetArrayLength (env, buf)
	    < sizeof (struct usbdevfs_hub_portinfo)) 
	return -EINVAL;

    command.ifno = 0;
    command.ioctl_code = USBDEVFS_HUB_PORTINFO;
    command.data = array = (*env)->GetByteArrayElements (env, buf, &isCopy);

    if (command.data == 0)
	return -EINVAL;
    
    retval = ioctl (fd, USBDEVFS_IOCTL, &command);

    // data is a struct usbdevfs_hub_portinfo ... 128 byte array
    (*env)->ReleaseByteArrayElements (env, buf, array, 0);

    return retval;
}

JNIEXPORT jstring JNICALL
Java_usb_linux_DeviceImpl_getClaimer (
    JNIEnv	*env,
    jclass	ignored,
    jint	fd,
    jint	ifno
) {
    struct usbdevfs_getdriver	info;
    int value;

    info.interface = ifno;
    value = ioctl (fd, USBDEVFS_GETDRIVER, &info);
    if (value < 0)
    	info.driver [0] = 0;
    return (*env)->NewStringUTF (env, info.driver);
}

/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

// usb.linux.USBException native methods

JNIEXPORT jstring JNICALL
Java_usb_linux_USBException_strError (
    JNIEnv	*env,
    jobject	ignored,
    jint	errcode
) {
    // I18N ... strerror output _must_ be ASCII (7-bit)
    // else this needs TBD character encoding conversion...

    return (*env)->NewStringUTF (env, strerror (errcode));
}

