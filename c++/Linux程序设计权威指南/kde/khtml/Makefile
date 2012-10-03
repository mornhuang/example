

INC = -I$(QTDIR)/include
LIB = -L$(QTDIR)/lib -lqt
KLIB = -L/usr/lib -lkdecore -lkdeui -lkfile -lkhtml
MOC = $(QTDIR)/bin/moc

all: khtml Browser.mo

khtml: khtml.o main.o moc_khtml.o
	g++ -o khtml khtml.o main.o moc_khtml.o $(KLIB) $(LIB)

khtml.o: khtml.cpp
	g++ -c khtml.cpp $(INC)

main.o: main.cpp
	g++ -c main.cpp $(INC)

moc_khtml.o: moc_khtml.cpp
	g++ -o moc_khtml.o -c moc_khtml.cpp $(INC)

moc_khtml.cpp: khtml.h
	$(MOC) -o moc_khtml.cpp khtml.h

Browser.mo: Browser.po
	msgfmt -o Browser.mo Browser.po

install:
	cp -f Browser.mo /usr/share/locale/zh_CN.GB2312/LC_MESSAGES

clean:
	rm -f *.o *.mo moc_khtml.h moc_khtml.cpp *.bak khtml
	rm -f /usr/share/locale/zh_CN.GB2312/LC_MESSAGES/Browser.mo

