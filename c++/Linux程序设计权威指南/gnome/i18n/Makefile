
CFLAGS=-g -Wall `gnome-config --cflags gnome gnomeui`
LDFLAGS=`gnome-config --libs gnome gnomeui`

all: i18n i18ntest.mo

i18n: i18n.c
	gcc $(CFLAGS) -o i18n i18n.c $(LDFLAGS)

i18ntest.mo: i18ntest.po
	msgfmt -o i18ntest.mo i18ntest.po

install:
	cp -f i18ntest.mo /usr/share/locale/zh_CN/LC_MESSAGES

clean:
	rm -f i18n i18ntest.mo *.o *.bak
