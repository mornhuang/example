
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: notebook

notebook: notebook.c
	gcc -o notebook notebook.c $(INCS) $(LIBS)

clean:
	rm -f notebook *.bak
