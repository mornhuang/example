

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib `imlib-config --libs` -lX11

imlib: imlib.c 
	$(CC) $(CFLAGS)  imlib.c -o imlib 

clean: 
	rm -f *.o imlib

