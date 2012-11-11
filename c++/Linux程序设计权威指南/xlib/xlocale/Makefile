

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

xlocale: xlocale.c 
	$(CC) $(CFLAGS)  xlocale.c -o xlocale 

clean: 
	rm -f *.o *.bak xlocale

