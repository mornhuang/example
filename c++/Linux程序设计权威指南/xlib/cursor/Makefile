

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

all: cursor1 cursor2

cursor1: cursor1.c 
	$(CC) $(CFLAGS)  cursor1.c -o cursor1 

cursor2: cursor2.c 
	$(CC) $(CFLAGS)  cursor2.c -o cursor2

clean: 
	rm -f *.o *.bak cursor1 cursor2

