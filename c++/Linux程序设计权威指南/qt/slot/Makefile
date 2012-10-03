
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt

all: slot

slot: slot.cpp
	g++ $(INC) -o slot slot.cpp $(LIBS)

clean:
	rm -f slot *.bak
