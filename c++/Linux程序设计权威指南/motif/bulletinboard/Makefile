
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: bulletinboard

bulletinboard: bulletinboard.c
	gcc -o bulletinboard bulletinboard.c $(INCS) $(LIBS)

clean:
	rm -f bulletinboard *.bak
