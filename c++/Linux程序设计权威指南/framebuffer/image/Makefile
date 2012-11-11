
all: image

image: image.c other.c pics/image.red
	gcc -o image image.c other.c

pics/image.red:
	cd tools; make

clean:
	rm -f image *.bak
	cd tools; make clean
