

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

font: font.c 
	$(CC) $(CFLAGS)  font.c -o font 

clean: 
	rm -f *.o font

