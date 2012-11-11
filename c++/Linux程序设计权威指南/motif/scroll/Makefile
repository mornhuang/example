
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: scroll

scroll: scroll.c
	gcc -o scroll scroll.c $(INCS) $(LIBS)

clean:
	rm -f scroll *.bak
