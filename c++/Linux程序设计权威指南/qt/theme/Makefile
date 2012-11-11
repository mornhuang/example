
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: theme

theme: main.o theme.o moc_theme.o wood.o metal.o
	g++ -o theme theme.o main.o moc_theme.o wood.o metal.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

theme.o: theme.cpp
	g++ -c $(INC) -o theme.o theme.cpp

moc_theme.o: moc_theme.cpp
	g++ -c $(INC) -o moc_theme.o moc_theme.cpp

moc_theme.cpp: theme.h
	$(MOC) theme.h -o moc_theme.cpp

wood.o: wood.cpp
	g++ -c $(INC) -o wood.o wood.cpp

metal.o: metal.cpp
	g++ -c $(INC) -o metal.o metal.cpp
clean:
	rm -f theme *.bak *.o moc_theme.cpp
