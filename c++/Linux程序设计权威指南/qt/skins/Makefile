
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: skin

skin: main.o skin.o moc_skin.o skinbutton.o moc_skinbutton.o
	g++ -o skin skin.o main.o moc_skin.o skinbutton.o  moc_skinbutton.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

skin.o: skin.cpp
	g++ -c $(INC) -o skin.o skin.cpp

moc_skin.o: moc_skin.cpp
	g++ -c $(INC) -o moc_skin.o moc_skin.cpp

moc_skin.cpp: skin.h
	$(MOC) skin.h -o moc_skin.cpp

skinbutton.o: skinbutton.cpp
	g++ -c $(INC) -o skinbutton.o skinbutton.cpp

moc_skinbutton.o: moc_skinbutton.cpp
	g++ -c $(INC) -o moc_skinbutton.o moc_skinbutton.cpp

moc_skinbutton.cpp: skinbutton.h
	$(MOC) skinbutton.h -o moc_skinbutton.cpp


clean:
	rm -f skin *.bak *.o moc_skin.cpp moc_skinbutton.cpp
