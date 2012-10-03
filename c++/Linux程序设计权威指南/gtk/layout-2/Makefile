
CC = gcc

layout: layout.c 
	$(CC) `gtk-config --cflags`  layout.c -o layout `gtk-config --libs`

clean: 
	rm -f *.o *.bak layout
