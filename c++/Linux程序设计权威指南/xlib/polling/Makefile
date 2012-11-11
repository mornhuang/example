

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

polling: polling.c 
	$(CC) $(CFLAGS)  polling.c -o polling 

clean: 
	rm -f *.o polling

