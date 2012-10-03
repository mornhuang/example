
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: spinbox

spinbox: spinbox.c
	gcc -o spinbox spinbox.c $(INCS) $(LIBS)

clean:
	rm -f spinbox *.bak
