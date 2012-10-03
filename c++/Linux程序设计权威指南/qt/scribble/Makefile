
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: scribble

scribble: main.o scribble.o moc_scribble.o
	g++ -o scribble scribble.o main.o moc_scribble.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

scribble.o: scribble.cpp
	g++ -c $(INC) -o scribble.o scribble.cpp

moc_scribble.o: moc_scribble.cpp
	g++ -c $(INC) -o moc_scribble.o moc_scribble.cpp

moc_scribble.cpp: scribble.h
	$(MOC) scribble.h -o moc_scribble.cpp

clean:
	rm -f scribble *.bak *.o moc_scribble.cpp
