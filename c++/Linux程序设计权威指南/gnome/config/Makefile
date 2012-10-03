
CFLAGS=-g -Wall `gnome-config --cflags gnome gnomeui`
LDFLAGS=`gnome-config --libs gnome gnomeui`

all: config

config: config.c
	gcc $(CFLAGS) -o config config.c $(LDFLAGS)

clean:
	rm -f config *.o *.bak

