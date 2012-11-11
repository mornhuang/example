
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: toolbar

toolbar: main.o toolbar.o moc_toolbar.o
	g++ -o toolbar toolbar.o main.o moc_toolbar.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

toolbar.o: toolbar.cpp
	g++ -c $(INC) -o toolbar.o toolbar.cpp

moc_toolbar.o: moc_toolbar.cpp
	g++ -c $(INC) -o moc_toolbar.o moc_toolbar.cpp

moc_toolbar.cpp: toolbar.h
	$(MOC) toolbar.h -o moc_toolbar.cpp

clean:
	rm -f toolbar *.bak *.o moc_toolbar.cpp
