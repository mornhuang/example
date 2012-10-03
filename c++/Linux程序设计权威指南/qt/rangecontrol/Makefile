
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: rangecontrol

rangecontrol: main.o rangecontrol.o moc_rangecontrol.o
	g++ -o rangecontrol rangecontrol.o main.o moc_rangecontrol.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

rangecontrol.o: rangecontrol.cpp
	g++ -c $(INC) -o rangecontrol.o rangecontrol.cpp

moc_rangecontrol.o: moc_rangecontrol.cpp
	g++ -c $(INC) -o moc_rangecontrol.o moc_rangecontrol.cpp

moc_rangecontrol.cpp: rangecontrol.h
	$(MOC) rangecontrol.h -o moc_rangecontrol.cpp

clean:
	rm -f rangecontrol *.bak *.o moc_rangecontrol.cpp
