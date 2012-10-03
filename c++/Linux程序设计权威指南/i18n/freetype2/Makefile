all: ft2string

ft2string: ft2string.o
	gcc -g -o ft2string ft2string.o \
		-L/usr/X11R6/lib -L/usr/local/lib -lX11 -lfreetype

ft2string.o: ft2string.c
	gcc -g -c -Wall -I/usr/local/include ft2string.c

clean:
	rm -f ft2string.o ft2string *.bak
