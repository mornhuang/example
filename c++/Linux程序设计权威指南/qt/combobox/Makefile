
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt
MOC = $(QTDIR)/bin/moc

all: combobox

combobox: main.o combobox.o moc_combobox.o
	g++ -o combobox combobox.o main.o moc_combobox.o $(LIBS)

main.o: main.cpp
	g++ -c $(INC) -o main.o main.cpp

combobox.o: combobox.cpp
	g++ -c $(INC) -o combobox.o combobox.cpp

moc_combobox.o: moc_combobox.cpp
	g++ -c $(INC) -o moc_combobox.o moc_combobox.cpp

moc_combobox.cpp: combobox.h
	$(MOC) combobox.h -o moc_combobox.cpp

clean:
	rm -f combobox *.bak *.o moc_combobox.cpp
