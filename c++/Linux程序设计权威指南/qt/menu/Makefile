
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: menu

menu: main.o menu.o moc_menu.o
	g++ -o menu menu.o main.o moc_menu.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

menu.o: menu.cpp
	g++ -c $(INC) -o menu.o menu.cpp

moc_menu.o: moc_menu.cpp
	g++ -c $(INC) -o moc_menu.o moc_menu.cpp

moc_menu.cpp: menu.h
	$(MOC) menu.h -o moc_menu.cpp

clean:
	rm -f menu *.bak *.o moc_menu.cpp
