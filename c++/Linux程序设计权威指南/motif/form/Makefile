
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext -lXpm

all: form

form: form.c menu.c
	gcc -o form form.c menu.c $(INCS) $(LIBS)

clean:
	rm -f form *.bak
