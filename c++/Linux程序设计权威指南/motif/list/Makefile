
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: list extended

list: list.c
	gcc -o list list.c $(INCS) $(LIBS)

extended: extended.c
	gcc -o extended extended.c $(INCS) $(LIBS)

clean:
	rm -f list extended *.bak
