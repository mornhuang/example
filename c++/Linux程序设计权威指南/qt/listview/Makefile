
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: listview

listview: main.o listview.o moc_listview.o
	g++ -o listview listview.o main.o moc_listview.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

listview.o: listview.cpp
	g++ -c $(INC) -o listview.o listview.cpp

moc_listview.o: moc_listview.cpp
	g++ -c $(INC) -o moc_listview.o moc_listview.cpp

moc_listview.cpp: listview.h
	$(MOC) listview.h -o moc_listview.cpp

clean:
	rm -f listview *.bak *.o moc_listview.cpp
