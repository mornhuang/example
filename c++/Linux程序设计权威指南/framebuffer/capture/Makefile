
all: capture

capture: capture.c other.c vga16.c
	gcc -o capture capture.c other.c vga16.c

clean:
	rm -f capture *.bak
