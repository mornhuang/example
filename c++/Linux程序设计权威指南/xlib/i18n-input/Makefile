

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

all: rootwin overspot onspot offspot

offspot: offspot.c 
	$(CC) $(CFLAGS)  offspot.c -o offspot 

overspot: overspot.c 
	$(CC) $(CFLAGS)  overspot.c -o overspot 

rootwin: rootwin.c 
	$(CC) $(CFLAGS)  rootwin.c -o rootwin 

onspot: onspot.c 
	$(CC) $(CFLAGS)  onspot.c -o onspot 

clean: 
	rm -f *.o rootwin overspot offspot onspot

