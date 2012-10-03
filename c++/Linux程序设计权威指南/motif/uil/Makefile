

INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lMrm -lX11 -lXp -lXext
UIL = /usr/X11R6/bin/uil

all: test test.uid

test: test.c
	gcc -o test test.c $(INCS) $(LIBS)

test.uid: test.uil
	$(UIL) -o test.uid test.uil

clean:
	rm -f test *.o  *.bak test.uid 
