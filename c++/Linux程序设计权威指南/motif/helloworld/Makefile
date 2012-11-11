
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: helloworld

helloworld: helloworld.c
	gcc -o helloworld helloworld.c $(INCS) $(LIBS)

clean:
	rm -f helloworld *.bak
