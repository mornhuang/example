
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: edit

edit: main.o edit.o moc_edit.o
	g++ -o edit edit.o main.o moc_edit.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

edit.o: edit.cpp
	g++ -c $(INC) -o edit.o edit.cpp

moc_edit.o: moc_edit.cpp
	g++ -c $(INC) -o moc_edit.o moc_edit.cpp

moc_edit.cpp: edit.h
	$(MOC) edit.h -o moc_edit.cpp

clean:
	rm -f edit *.bak *.o moc_edit.cpp
