

CC = gcc

toolbar: toolbar.c 
	$(CC) `gtk-config --cflags`  toolbar.c -o toolbar `gtk-config --libs`

clean: 
	rm -f *.o *.bak toolbar

