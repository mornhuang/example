

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

image: image.c 
	$(CC) $(CFLAGS)  image.c -o image 

clean: 
	rm -f *.o image

