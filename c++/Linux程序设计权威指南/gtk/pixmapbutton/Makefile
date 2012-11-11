
CC = gcc

all: pixmapbutton1 pixmapbutton2

pixmapbutton1: pixmapbutton1.c 
	$(CC) `gtk-config --cflags`  pixmapbutton1.c -o pixmapbutton1 `gtk-config --libs`

pixmapbutton2: pixmapbutton2.c 
	$(CC) `gtk-config --cflags`  pixmapbutton2.c -o pixmapbutton2 `gtk-config --libs`

clean: 
	rm -f *.o *.bak pixmapbutton1 pixmapbutton2
