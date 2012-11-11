

CC = gcc

imlib: imlib.c 
	$(CC) `gtk-config --cflags`  imlib.c -o imlib `gtk-config --libs` `imlib-config --libs` -lgdk_imlib

clean: 
	rm -f *.o *.bak imlib

