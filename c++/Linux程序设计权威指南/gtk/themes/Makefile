

CC = gcc

themes: themes.c 
	$(CC) `gtk-config --cflags`  themes.c -o themes `gtk-config --libs`

clean: 
	rm -f *.o *.bak themes

