
all: server client

server: server.c
	gcc -o server server.c

client: client.c ../common/common.c
	gcc -o client client.c

clean:
	rm -f *.o *.bak server client
	

