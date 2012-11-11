
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: combobox

combobox: combobox.c
	gcc -o combobox combobox.c $(INCS) $(LIBS)

clean:
	rm -f combobox *.bak
