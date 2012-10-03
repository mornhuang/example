
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: scale

scale: scale.c
	gcc -o scale scale.c $(INCS) $(LIBS)

clean:
	rm -f scale *.bak
