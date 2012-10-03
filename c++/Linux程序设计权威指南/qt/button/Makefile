
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: button

button: main.o button.o moc_button.o
	g++ -o button button.o main.o moc_button.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

button.o: button.cpp
	g++ -c $(INC) -o button.o button.cpp

moc_button.o: moc_button.cpp
	g++ -c $(INC) -o moc_button.o moc_button.cpp

moc_button.cpp: button.h
	$(MOC) button.h -o moc_button.cpp

clean:
	rm -f button *.bak *.o moc_button.cpp
