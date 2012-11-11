# $Id: Makefile,v 1.1 2008/10/15 09:28:08 huangmeng Exp $
#
# Copyright (c) 2000 by David Brownell.  All Rights Reserved.
#
# This program is free software; you may use, copy, modify, and
# redistribute it under the terms of the LICENSE with which it was
# originally distributed.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# LICENSE for more details.
#

# Win32 builds presume cygwin (http://sources.redhat.com/cygwin)
# invokes a Win32 JVM, with JAVA_HOME set to a Win32 path
# (like "d:/programs/jdk1.3"); no win32 native code yet
ifeq ($(OSTYPE),cygwin)
    SEP=;

# ... else assume a UNIX build environment
else
    SEP=:
endif


DOCTITLE="Java USB (for Linux)"

# for releases (override in env)
VERSION ?= DEV

CPATH :=	classes


# "$(NAME).jar", "lib$(NAME).so", ...
NAME = jusb

CORE_SOURCES := \
	usb/core/Bus.java \
	usb/core/Configuration.java \
	usb/core/ControlMessage.java \
	usb/core/Descriptor.java \
	usb/core/Device.java \
	usb/core/DeviceDescriptor.java \
	usb/core/DeviceSPI.java \
	usb/core/Endpoint.java \
	usb/core/Host.java \
	usb/core/HostFactory.java \
	usb/core/Hub.java \
	usb/core/Interface.java \
	usb/core/PortIdentifier.java \
	usb/core/USBException.java \
	usb/core/USBListener.java \
	usb/core/USBListenerAdapter.java

REMOTE_SOURCES := \
	usb/remote/HostProxy.java \
	usb/remote/RemoteBus.java \
	usb/remote/RemoteDeviceSPI.java \
	usb/remote/RemoteHost.java \
	usb/remote/RemoteHostFactory.java \
 	usb/remote/RemoteUSBListener.java \
 	usb/remote/USBD.java \
 	usb/remote/USBListenerProxy.java

UTIL_SOURCES := \
	usb/util/LangCode.java \
	usb/util/ShowTree.java \
	usb/util/USBSocket.java

DEVICES_SOURCES := \
	usb/devices/Kodak.java \
	usb/devices/Rio500.java

LINUX_SOURCES := \
	usb/linux/DeviceImpl.java \
	usb/linux/HID.java \
	usb/linux/Linux.java \
	usb/linux/USB.java \
	usb/linux/USBException.java

LINUX_NATIVE_SOURCES = \
	native/linux.c

# WIN32_SOURCES := 
	
VIEW_SOURCES := \
	usb/view/Foo.java \
	usb/view/HubNode.java \
	usb/view/KodakNode.java \
	usb/view/RioNode.java \
	usb/view/USBNode.java

# all code in the distribution
JAVA_SOURCES = \
	$(CORE_SOURCES) \
	$(REMOTE_SOURCES) \
	$(UTIL_SOURCES) \
	$(DEVICES_SOURCES) \
	$(LINUX_SOURCES) \
	$(VIEW_SOURCES)

NATIVE_SOURCES = \
	$(LINUX_NATIVE_SOURCES) \
	native/linux.cc


#
# What JDK is installed?  Builds work a bit differently...
# tweak to support your setup.  jdk 1.1/1.2/1.3 on linux have
# worked, also on cygwin; and kaffe 1.0.6 on linux.  Vame? :-)
#
JDK :=		$(filter-out java version, $(shell java -version 2>&1))
JDK :=		$(subst ",,$(JDK))

ifeq ($(origin JAVA_HOME),undefined)
    JAVA_HOME := $(patsubst %/bin/java,%,$(shell which java))
endif
JNI_INC := -I$(JAVA_HOME)/include

ifeq ($(findstring 1.1, $(JDK)),1.1)
    ifeq ($(findstring Kaffe, $(JDK)),Kaffe)
	# hope it's Kaffe 1.0.6; 1.0.5 had problems!
	CPATH := $(CPATH)$(SEP)$(JAVA_HOME)/share/kaffe/Klasses.jar
	JNI_INC += -I$(JAVA_HOME)/include/kaffe 
	JAVAH =	kaffeh
    else
	CPATH := $(CPATH)$(SEP)$(JAVA_HOME)/lib/classes.zip
	JNI_INC += -I$(JAVA_HOME)/include/linux 
	JAVAH =	javah
    endif
    ifneq ($(origin SWING_HOME),undefined)
	CPATH := $(CPATH)$(SEP)$(SWING_HOME)/swing.jar
    else
	USE_SWING := false
    endif
else
    JAVAH = javah
    JNI_INC += -I$(JAVA_HOME)/include/linux 
endif

#
# what to compile?
# start with basics (embedded configs can get smaller)
# add implementations and other modules
#
SOURCES = $(CORE_SOURCES) $(UTIL_SOURCES)
OBJECTS = objects
NATIVE =

# building with Kaffe RMI has been iffy
ifneq ($(findstring Kaffe, $(JDK)),Kaffe)
#ifeq (true,false)
    # SOURCES += usb/core/HostFactory.java usb/util/ShowTree.java
    SOURCES += $(REMOTE_SOURCES)

    REMOTE_IMPLEMENTATIONS = \
	usb.remote.HostProxy \
	usb.remote.HostProxy.BusProxy \
	usb.remote.HostProxy.DeviceSPIProxy \
	usb.remote.USBListenerProxy
    OBJECTS += rmi_jrmp_objects

ifeq ($(findstring 1.3, $(JDK)),1.3)
    REMOTE_INTERFACES = \
	usb.remote.RemoteHost \
	usb.remote.RemoteBus \
	usb.remote.RemoteDeviceSPI \
	usb.remote.RemoteUSBListener
    OBJECTS += rmi_iiop_objects
endif

endif

# older versions seem to say "Linux"; newer ones, "linux-gnu"
ifeq ($(OSTYPE),Linux)
    OSTYPE = linux-gnu
endif

# only Linux native code just now ...
ifeq ($(OSTYPE),linux-gnu)
# ... which doesn't compile on Kaffe (JNIEXPORT #undef?)
ifneq ($(findstring Kaffe, $(JDK)),Kaffe)
    SOURCES += $(LINUX_SOURCES)
    NATIVE_SRC = linux.c
    NATIVE_HEADERS = \
	usb_linux_DeviceImpl.h \
	usb_linux_USBException.h
    NATIVE += lib$(NAME).so
endif
endif


#
# Swing is widely available but not open;
# so support for it can't be in the core
#
ifneq ($(origin USE_SWING),file)
   SOURCES += $(VIEW_SOURCES)
endif


#
# By default, build enough to run everything
#
default: $(NAME).jar $(NATIVE)

#
# Make source distributions easier to use
#
install: $(NAME).jar $(NATIVE)
ifeq ($(OSTYPE),linux-gnu)
	sh bin/install
	@echo "Documentation NOT installed."
else
	@echo "Sorry, only Linux installs are now supported."
endif



all:	default javadoc

src-distrib:
	cd src; tar cvfz ../src.tgz \
		$(JAVA_SOURCES) \
		`find * -name package.html`
	tar cvfz $(NAME)-$(VERSION)-src.tgz \
		README README.linux LICENSE \
		Makefile \
		src.tgz $(NATIVE_SOURCES) \
		doc/overview.html \
		bin/usbd \
		bin/install \
		bin/viewer

# bin-distrib:


#
# basic apps, as a sanity check after building
#
# -Djava.compiler=NONE if you're missing line numbers in backtraces (no JIT)
#
# NOTE:  prefer "-native" when running with JDK 1.2
#
show:
ifneq ($(findstring 1.1, $(JDK)),1.1)
	@LD_LIBRARY_PATH=. java -classpath $(NAME).jar \
		usb.util.ShowTree
else
	@LD_LIBRARY_PATH=. CLASSPATH=$(CPATH) \
		java -native usb.util.ShowTree
endif

viewer:
	sh bin/viewer

usbd:
	sh bin/usbd start

#
# Compiling the Java code ...
#
$(NAME).jar:	$(OBJECTS)
	cd classes; jar cf ../$(NAME).jar *

objects:	classes $(SOURCES:%=src/%)
	javac -classpath "src$(SEP)$(CPATH)" -d classes \
		$(SOURCES:%=src/%)

src:
	@echo "*** You may need to extract 'src.tgz' into 'src' directory ..."
	@exit 1

rmi_jrmp_objects:
	rmic -classpath "$(CPATH)" -d classes \
		$(REMOTE_IMPLEMENTATIONS)

rmi_iiop_objects:
	rmic -iiop -classpath "$(CPATH)" -d classes \
		$(REMOTE_INTERFACES)

idl_specs:
	mkdir -p idl
	rmic -idl -classpath "$(CPATH)" -d idl \
		$(REMOTE_INTERFACES)

classes:
	-mkdir classes

#
# Native compiles
#

# using nonstandard kernel?
KERNEL_INC ?=	/home/src/linux/include
INCLUDES =	-I$(KERNEL_INC)

CFLAGS =	-g -O $(INCLUDES) $(RPM_OPT_FLAGS)


native: $(NATIVE)

lib$(NAME).so: jni-native

jni-native: $(NATIVE_HEADERS:%=native/%) $(NATIVE_SRC:%=native/%)
	cd native; $(CC) -Wall -shared -o ../lib$(NAME).so \
		$(CFLAGS) $(JNI_INC) $(NATIVE_SRC)

native/usb_linux_DeviceImpl.h: classes/usb/linux/DeviceImpl.class
ifneq ($(findstring 1.1, $(JDK)),1.1)
	javah -jni -d native -classpath classes usb.linux.DeviceImpl
else
	CLASSPATH=$(CPATH) $(JAVAH) -jni -d native usb.linux.DeviceImpl
endif

native/usb_linux_USBException.h: classes/usb/linux/USBException.class
ifneq ($(findstring 1.1, $(JDK)),1.1)
	$(JAVAH) -jni -d native -classpath classes usb.linux.USBException
else
	CLASSPATH=$(CPATH) $(JAVAH) -jni -d native usb.linux.USBException
endif

#
# GCJ native compiles ... experimental
#
# Klugey build ... SOURCES depends on which "java" is found;
# normally presumes RMI, which GCJ doesn't yet handle ...
#
GCJ_SOURCES = \
	$(CORE_SOURCES) \
	$(UTIL_SOURCES) \
	$(LINUX_SOURCES) \
	$(DEVICES_SOURCES)

GCJ_CFLAGS =	-Wunsupported-jdk11


# NOTE:  The "-u ..." forces the Linux native implementation
# to be statically linked.  When GCJ supports CORBA or RMI,
# those implementations will need the same treatment.
# No native, no remote ... means no USB access!

showtree: gcj-native
	gcj --main=usb.util.ShowTree -o $@ \
		obj-static/usb/util/ShowTree.o \
		-u _CL_Q33usb5linux5Linux lib$(NAME)-gcj.a


gcj-native: include-cni lib$(NAME)-gcj.a

lib$(NAME)-gcj.a: $(GCJ_SOURCES:%.java=obj-static/%.o) \
		obj-static/linux.o
	ar rv $@ $^

obj-static/%.o : src/%.java
	@mkdir -p `dirname $@`
	gcj $(GCJ_CFLAGS) $(CFLAGS) -c -o $@ -Iclasses $^

include-cni:	$(NAME).jar
	mkdir -p include-cni/usb/{core,util,linux,devices}
	X=`cd classes; find usb/{core,util,linux,devices} -name '*.class' \
		| sed -e 's/\.class//' -e 'sx/x.xg'` ;\
	for C in $$X ; do \
	    gcjh --classpath "$(CPATH)" -d include-cni $$C ; \
	done

obj-static/linux.o: native/linux.cc
	gcj $(GCJ_CFLAGS) $(CFLAGS) -c -o $@ -Iinclude-cni native/linux.cc


#
# Javadoc
#
javadoc:	src apidoc doc/overview.html
ifneq ($(findstring 1.1, $(JDK)),1.1)
	javadoc -d apidoc \
	    -J-Xmx64M -J-Xms64M \
	    -windowtitle $(DOCTITLE) \
	    -nodeprecatedlist \
	    -overview doc/overview.html \
	    -bottom "<em>Associated source code \
	    	is licenced under the LGPL.</em>\
		<br>See <a href="http://jusb.sourceforge.net"> \
		http://jusb.sourceforge.net</a> \
		<br>This documentation was derived from that\
		source code on `date "+%e-%b-%Y"`.\
		" \
	    -classpath "$(CPATH)$(SEP)src" \
	    usb.core usb.linux usb.remote usb.util usb.view usb.devices
else
	@echo "Requires JDK 1.2 (or later) javadoc."
	@exit 1
endif
apidoc:
	-mkdir apidoc

#
# housekeeping
#
clean:
	if [ -d src ]; then rm -rf src.tgz apidoc; fi
	rm -rf classes include-cni idl \
		native/usb_linux_*.h obj-static showtree \
		jusb-$(VERSION)-src.tgz *.jar lib$(NAME)* \
		Log *~ *.s core
	find . '(' -name '*~' -o -name '.#*' ')' -exec rm -f '{}' ';'

dump:
	: OSTYPE is ... "$(OSTYPE)"
	: SEP is ... "$(SEP)"
	: JAVA_HOME is ... $(JAVA_HOME)
	: JNI_INC is ... $(JNI_INC)
	: JDK is ... "$(JDK)"
	: CPATH is ... "$(CPATH)"
	: SOURCES is ... $(SOURCES)
	: OBJECTS is ... $(OBJECTS)
	: NATIVE is ... $(NATIVE)
	: USE_SWING is ... $(USE_SWING), $(origin USE_SWING),

