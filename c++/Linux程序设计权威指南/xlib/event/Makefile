

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

event: event.c 
	$(CC) $(CFLAGS)  event.c -o event 

clean: 
	rm -f *.o event

