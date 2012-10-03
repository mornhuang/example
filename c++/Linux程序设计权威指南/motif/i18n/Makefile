
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: i18n

i18n: i18n.c
	gcc -o i18n i18n.c $(INCS) $(LIBS)

clean:
	rm -f i18n *.bak
