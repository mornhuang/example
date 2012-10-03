
INC = -I$(QTDIR)/include
LIB = -L$(QTDIR)/lib

all: helloworld

helloworld: helloworld.cpp
	g++ -o helloworld helloworld.cpp -L/usr/lib -lkdecore $(INC) $(LIB)


clean:
	rm -f *.o *.bak helloworld
