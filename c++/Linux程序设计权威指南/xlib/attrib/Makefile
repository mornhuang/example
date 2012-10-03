

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

attrib: attrib.c 
	$(CC) $(CFLAGS)  attrib.c -o attrib 

clean: 
	rm -f *.o attrib

