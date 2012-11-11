
CC = gcc

all: helloworld1 helloworld2 helloworld3

helloworld1: helloworld1.c 
	$(CC) `gtk-config --cflags`  helloworld1.c -o helloworld1 `gtk-config --libs`

helloworld2: helloworld2.c 
	$(CC) `gtk-config --cflags`  helloworld2.c -o helloworld2 `gtk-config --libs`

helloworld3: helloworld3.c 
	$(CC) `gtk-config --cflags`  helloworld3.c -o helloworld3 `gtk-config --libs`

clean: 
	rm -f *.o *.bak helloworld1 helloworld2 helloworld3
