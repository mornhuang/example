
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt

all: splitter

splitter: splitter.cpp
	g++ $(INC) -o splitter splitter.cpp $(LIBS)

clean:
	rm -f splitter *.bak
