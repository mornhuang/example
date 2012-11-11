

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

i18n-xom: i18n-xom.c 
	$(CC) $(CFLAGS)  i18n-xom.c -o i18n-xom 

clean: 
	rm -f *.o *.bak i18n-xom

