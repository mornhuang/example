

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lXpm -lXext -lX11

shape: shape.c 
	$(CC) $(CFLAGS)  shape.c -o shape 

clean: 
	rm -f *.o shape

