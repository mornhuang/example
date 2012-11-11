
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: text edit passwd

text: text.c
	gcc -o text text.c $(INCS) $(LIBS)

edit: edit.c
	gcc -o edit edit.c $(INCS) $(LIBS)

passwd: passwd.c
	gcc -o passwd passwd.c $(INCS) $(LIBS)

clean:
	rm -f text edit passwd *.bak
