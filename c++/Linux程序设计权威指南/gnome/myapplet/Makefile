

CC = gcc

myapplet: myapplet.c 
	$(CC) `gnome-config --cflags applets`  myapplet.c -o myapplet `gnome-config --libs applets`

install:
	cp -f myapplet /usr/bin
	cp -f icon_myapplet.xpm /usr/share/icons
	mkdir -p /usr/share/applets/Utility
	cp -f myapplet.desktop /usr/share/applets/Utility
	mkdir -p /etc/CORBA/servers
	cp -f myapplet.gnorba /etc/CORBA/servers

clean: 
	rm -f *.o myapplet *.bak 

