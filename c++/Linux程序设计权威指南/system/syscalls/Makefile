
all: env1 env2 pid tmpfile passwd host ls file

env1: env1.c
	gcc -o env1 env1.c

env2: env2.c
	gcc -o env2 env2.c

pid: pid.c
	gcc -o pid pid.c

tmpfile: tmpfile.c
	gcc -o tmpfile tmpfile.c

passwd: passwd.c
	gcc -o passwd passwd.c

host: host.c
	gcc -o host host.c

ls: ls.c
	gcc -o ls ls.c

file: file.c
	gcc -o file file.c

clean:
	rm -f *.o *.bak env1 env2 pid tmpfile passwd host ls file
