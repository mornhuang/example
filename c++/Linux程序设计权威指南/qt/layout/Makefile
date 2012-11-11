
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt

all: layout

layout: layout.cpp
	g++ $(INC) -o layout layout.cpp $(LIBS)

clean:
	rm -f layout *.bak *.o
