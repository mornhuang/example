
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: cursor

cursor: cursor.cpp
	g++ $(INC) -o cursor cursor.cpp $(LIBS)

clean:
	rm -f cursor *.bak *.o
