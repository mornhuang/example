
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: pushbutton dblclick togglebutton checkbox radiobox arrowbutton drawnbutton

pushbutton: pushbutton.c
	gcc -o pushbutton pushbutton.c $(INCS) $(LIBS)

togglebutton: togglebutton.c
	gcc -o togglebutton togglebutton.c $(INCS) $(LIBS)

checkbox: checkbox.c
	gcc -o checkbox checkbox.c $(INCS) $(LIBS)

radiobox: radiobox.c
	gcc -o radiobox radiobox.c $(INCS) $(LIBS)

dblclick: dblclick.c
	gcc -o dblclick dblclick.c $(INCS) $(LIBS)

arrowbutton: arrowbutton.c
	gcc -o arrowbutton arrowbutton.c $(INCS) $(LIBS)

drawnbutton: drawnbutton.c
	gcc -o drawnbutton drawnbutton.c $(INCS) $(LIBS) -lXpm

clean:
	rm -f pushbutton dblclick checkbox radiobox togglebutton arrowbutton drawnbutton *.bak
