####### kdevelop will overwrite this part!!! (begin)##########
bin_PROGRAMS = kdev
kdev_SOURCES = kdevview.cpp kdevdoc.cpp kdev.cpp main.cpp 
kdev_LDADD   =  -lqt -lXext -lX11 $(LIBSOCKET)

SUBDIRS = docs 

EXTRA_DIST = main.cpp kdev.cpp kdev.h kdevdoc.cpp kdevdoc.h kdevview.cpp kdevview.h resource.h kdev.xpm mini-kdev.xpm filenew.xpm filesave.xpm fileopen.xpm 

####### kdevelop will overwrite this part!!! (end)############


# set the include path for X, qt and KDE
INCLUDES= $(all_includes)

kdev_METASOURCES = AUTO

# the library search path. 
kdev_LDFLAGS = $(all_libraries) 

# them while "make clean", use CLEANFILES
DISTCLEANFILES = $(kdev_METASOURCES)




