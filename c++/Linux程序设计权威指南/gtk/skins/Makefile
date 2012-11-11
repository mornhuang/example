

CC = gcc

skins: skins.c 
	$(CC) `gtk-config --cflags`  skins.c -o skins `gtk-config --libs` `imlib-config --libs` -lgdk_imlib

clean: 
	rm -f *.o *.bak skins

