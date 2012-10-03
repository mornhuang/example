

CC = gcc

animation: animation.c 
	$(CC) `gtk-config --cflags` animation.c -o animation `gdk-pixbuf-config --libs`

clean: 
	rm -f *.o animation *.bak

