
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: fontlist1 fontlist2

fontlist1: fontlist1.c
	gcc -o fontlist1 fontlist1.c $(INCS) $(LIBS)

fontlist2: fontlist2.c
	gcc -o fontlist2 fontlist2.c $(INCS) $(LIBS)

clean:
	rm -f fontlist1 fontlist2 *.bak
