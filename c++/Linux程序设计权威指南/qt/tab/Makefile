
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt

all: tab

tab: tab.cpp
	g++ $(INC) -o tab tab.cpp $(LIBS)

clean:
	rm -f tab *.bak
