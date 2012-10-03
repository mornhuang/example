

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

key: key.c 
	$(CC) $(CFLAGS)  key.c -o key 

clean: 
	rm -f *.o key

