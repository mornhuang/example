
CFLAGS = -I/usr/lib/glib/include
LIBS = -lIIOP -lORBit -lORBitutil -L/usr/lib -lORBit -lIIOP -lORBitutil -lglib -lm
all: idl client server

idl:  echo.idl
	orbit-idl echo.idl

client: client.c echo-common.c echo-stubs.c
	gcc $(CFLAGS) -o client client.c echo-common.c echo-stubs.c $(LIBS)

server: server.c echo-common.c echo-skels.c
	gcc $(CFLAGS) -o server server.c echo-common.c echo-skels.c $(LIBS)

clean:
	rm -f echo-common.c echo-stubs.c echo-skels.c client server echo.h echo.ior *.bak
