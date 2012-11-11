
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: frame

frame: frame.c
	gcc -o frame frame.c $(INCS) $(LIBS)

clean:
	rm -f frame *.bak
