
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: drawarea

drawarea: drawarea.c
	gcc -o drawarea drawarea.c $(INCS) $(LIBS)

clean:
	rm -f drawarea *.bak
