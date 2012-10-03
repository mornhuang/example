
INCS = -I/usr/X11R6/include
LIBS = -L/usr/X11R6/lib -lXm -lXt -lX11 -lXp -lXext

all: fallbacks

fallbacks: fallbacks.c
	gcc -o fallbacks fallbacks.c $(INCS) $(LIBS)

clean:
	rm -f fallbacks *.bak
