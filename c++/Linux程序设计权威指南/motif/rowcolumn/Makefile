
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: rowcolumn

rowcolumn: rowcolumn.c
	gcc -o rowcolumn rowcolumn.c $(INCS) $(LIBS)

clean:
	rm -f rowcolumn *.bak
