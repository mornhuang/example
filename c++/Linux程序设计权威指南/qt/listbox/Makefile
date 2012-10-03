
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: listbox

listbox: main.o listbox.o moc_listbox.o
	g++ -o listbox listbox.o main.o moc_listbox.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

listbox.o: listbox.cpp
	g++ -c $(INC) -o listbox.o listbox.cpp

moc_listbox.o: moc_listbox.cpp
	g++ -c $(INC) -o moc_listbox.o moc_listbox.cpp

moc_listbox.cpp: listbox.h
	$(MOC) listbox.h -o moc_listbox.cpp

clean:
	rm -f listbox *.bak *.o moc_listbox.cpp
