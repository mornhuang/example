

CC = gcc
CFLAGS = -I/usr/X11R6/include -L/usr/X11R6/lib -lX11

i18n-output: i18n-output.c 
	$(CC) $(CFLAGS)  i18n-output.c -o i18n-output 

clean: 
	rm -f *.o i18n-output

