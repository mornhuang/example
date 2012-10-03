
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc
UIC = $(QTDIR)/bin/uic

all: designerdemo

designerdemo: main.o designerdemo.o moc_designerdemo.o
	g++ -o designerdemo designerdemo.o main.o moc_designerdemo.o $(LIBS)

main.o: main.cpp designerdemo.h
	g++ -c $(INC) -o main.o main.cpp

designerdemo.o: designerdemo.cpp
	g++ -c $(INC) -o designerdemo.o designerdemo.cpp

designerdemo.cpp: designerdemo.h
	$(UIC) -i designerdemo.h -o designerdemo.cpp designerdemo.ui

designerdemo.h: designerdemo.ui
	$(UIC) -o designerdemo.h designerdemo.ui

moc_designerdemo.o: moc_designerdemo.cpp
	g++ -c $(INC) -o moc_designerdemo.o moc_designerdemo.cpp

moc_designerdemo.cpp: designerdemo.h
	$(MOC) designerdemo.h -o moc_designerdemo.cpp

clean:
	rm -f designerdemo *.bak *.o moc_designerdemo.cpp designerdemo.cpp designerdemo.h
