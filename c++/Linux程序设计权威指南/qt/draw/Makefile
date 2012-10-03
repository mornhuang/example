
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: draw

draw: main.o draw.o moc_draw.o
	g++ -o draw draw.o main.o moc_draw.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

draw.o: draw.cpp
	g++ -c $(INC) -o draw.o draw.cpp

moc_draw.o: moc_draw.cpp
	g++ -c $(INC) -o moc_draw.o moc_draw.cpp

moc_draw.cpp: draw.h
	$(MOC) draw.h -o moc_draw.cpp

clean:
	rm -f draw *.bak *.o moc_draw.cpp
