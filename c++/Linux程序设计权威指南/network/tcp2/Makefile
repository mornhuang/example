
all: server client

server: server.c ../common/common.c
	gcc -o server server.c ../common/common.c

client: client.c ../common/common.c
	gcc -o client client.c ../common/common.c

clean:
	rm -f *.o *.bak server client
	

