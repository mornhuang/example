

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

noborder: noborder.c 
	$(CC) $(CFLAGS)  noborder.c -o noborder 

clean: 
	rm -f *.o noborder

