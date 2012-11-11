
CFLAGS=-g -Wall `gnome-config --cflags gnome gnomeui`
LDFLAGS=`gnome-config --libs gnome gnomeui`

all: stock

stock: stock.c
	gcc $(CFLAGS) -o stock stock.c $(LDFLAGS)

clean:
	rm -f stock *.bak
