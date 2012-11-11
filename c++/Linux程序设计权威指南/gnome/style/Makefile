
CFLAGS=-g -Wall `gnome-config --cflags gnome gnomeui`
LDFLAGS=`gnome-config --libs gnome gnomeui`

all: style

style: style.c
	gcc $(CFLAGS) -o style style.c $(LDFLAGS)

clean:
	rm -f style *.bak
