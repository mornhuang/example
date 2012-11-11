
all: fork signal pipe pipes

pipe: pipe.c
	gcc -o pipe pipe.c

pipes: pipes.c
	gcc -o pipes pipes.c

fork: fork.c
	gcc -o fork fork.c

signal: signal.c
	gcc -o signal signal.c

clean:
	rm -f *.o *.bak fork signal pipe pipes

