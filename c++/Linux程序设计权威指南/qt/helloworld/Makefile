
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt

all: helloworld

helloworld: helloworld.cpp
	g++ $(INC) -o helloworld helloworld.cpp $(LIBS)

clean:
	rm -f helloworld *.bak
