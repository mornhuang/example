
INC = -I$(QTDIR)/include
LIBS = -L$(QTDIR)/lib -lqt

all: i18n

i18n: i18n.cpp
	g++ $(INC) -o i18n i18n.cpp $(LIBS)

clean:
	rm -f i18n *.bak
