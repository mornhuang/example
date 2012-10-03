
all: private msgread msgwrite

private: private.c
	gcc -o private private.c

msgread: msgread.c
	gcc -o msgread msgread.c

msgwrite: msgwrite.c
	gcc -o msgwrite msgwrite.c

clean:
	rm -f *.o *.bak private msgread msgwrite

