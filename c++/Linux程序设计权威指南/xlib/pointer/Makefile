

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

pointer: pointer.c 
	$(CC) $(CFLAGS)  pointer.c -o pointer 

clean: 
	rm -f *.o pointer

