
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: menu popup option

menu: menu.c
	gcc -o menu menu.c $(INCS) $(LIBS)

popup: popup.c
	gcc -o popup popup.c $(INCS) $(LIBS)

option: option.c
	gcc -o option option.c $(INCS) $(LIBS)

clean:
	rm -f menu popup option *.bak
