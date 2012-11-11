

all: vgagl

vgagl: vgagl.c ../capture/capture.c
	gcc -o vgagl vgagl.c ../capture/capture.c -lvga -lvgagl

clean:
	rm -f vgagl *.bak
