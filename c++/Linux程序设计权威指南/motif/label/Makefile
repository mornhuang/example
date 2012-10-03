
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: label xbmlabel pixlabel

label: label.c
	gcc -o label label.c $(INCS) $(LIBS)

xbmlabel: xbmlabel.c
	gcc -o xbmlabel xbmlabel.c $(INCS) $(LIBS)

pixlabel: pixlabel.c
	gcc -o pixlabel pixlabel.c $(INCS) $(LIBS) -lXpm

clean:
	rm -f label xbmlabel pixlabel *.bak
