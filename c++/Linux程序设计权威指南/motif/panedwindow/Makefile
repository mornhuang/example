
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: panedwindow

panedwindow: panedwindow.c
	gcc -o panedwindow panedwindow.c $(INCS) $(LIBS)

clean:
	rm -f panedwindow *.bak
