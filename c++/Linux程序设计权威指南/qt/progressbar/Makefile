
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: progressbar

progressbar: main.o progressbar.o moc_progressbar.o
	g++ -o progressbar progressbar.o main.o moc_progressbar.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

progressbar.o: progressbar.cpp
	g++ -c $(INC) -o progressbar.o progressbar.cpp

moc_progressbar.o: moc_progressbar.cpp
	g++ -c $(INC) -o moc_progressbar.o moc_progressbar.cpp

moc_progressbar.cpp: progressbar.h
	$(MOC) progressbar.h -o moc_progressbar.cpp

clean:
	rm -f progressbar *.bak *.o moc_progressbar.cpp
