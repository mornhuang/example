
all: dl libtest.so

dl: dl.c
	gcc -o dl dl.c -ldl

libtest.so: libtest.o
	gcc -shared -o libtest.so  libtest.o

libtest.o: libtest.c
	gcc -c -fPIC libtest.c
