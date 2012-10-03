

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

wmhint: wmhint.c 
	$(CC) $(CFLAGS)  wmhint.c -o wmhint 

clean: 
	rm -f *.o *.bak wmhint

