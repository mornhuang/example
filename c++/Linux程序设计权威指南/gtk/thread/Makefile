

CC = gcc

thread: thread.c 
	$(CC) `gtk-config --cflags`  thread.c -o thread `gtk-config --libs gthread` -lpthread

clean: 
	rm -f *.o *.bak thread

