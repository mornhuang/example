
CFLAGS=-g -Wall `gnome-config --cflags gnome gnomeui`
LDFLAGS=`gnome-config --libs gnome gnomeui`

all: helloworld

helloworld: helloworld.c
	gcc $(CFLAGS) -o helloworld helloworld.c $(LDFLAGS)

clean:
	rm -f helloworld *.bak
