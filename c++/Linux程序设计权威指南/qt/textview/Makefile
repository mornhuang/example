
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt

all: textview

textview: textview.cpp
	g++ $(INC) -o textview textview.cpp $(LIBS)

clean:
	rm -f *.bak textview
