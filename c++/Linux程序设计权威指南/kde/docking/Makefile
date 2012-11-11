
CC = gcc
CPP = gcc -E
CXX = g++
MOC = $(QTDIR)/bin/moc
CFLAGS = -I. -I/usr/include -I$(QTDIR)/include  -I/usr/X11R6/include -O2
LIBS = -L$(QTDIR)/lib  -lqt -lkdecore

all: docking

docking: docking.o moc_docking.o
	gcc -o docking docking.o moc_docking.o $(LIBS)

docking.o: docking.cpp
	$(CXX) $(CFLAGS) -c docking.cpp

moc_docking.o: moc_docking.cpp
	$(CXX) $(CFLAGS) -c moc_docking.cpp

moc_docking.cpp: docking.h
	$(MOC) ./docking.h -o moc_docking.cpp

clean:
	rm -f *.bak moc_docking.cpp *.o docking
