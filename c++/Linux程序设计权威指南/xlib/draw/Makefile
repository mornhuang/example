

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

draw: draw.c 
	$(CC) $(CFLAGS)  draw.c -o draw 

clean: 
	rm -f *.o draw

