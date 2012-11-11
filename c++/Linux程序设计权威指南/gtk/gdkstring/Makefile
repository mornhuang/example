

CC = gcc

gdkstring: gdkstring.c 
	$(CC) `gtk-config --cflags`  gdkstring.c -o gdkstring `gtk-config --libs`

clean: 
	rm -f *.o *.bak gdkstring

