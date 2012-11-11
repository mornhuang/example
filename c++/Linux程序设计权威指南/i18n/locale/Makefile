
all: setlocale type convert wchar time lconv langinfo test-iconv

setlocale: setlocale.c
	gcc -o setlocale setlocale.c

type: type.c
	gcc -o type type.c

convert: convert.c
	gcc -o convert convert.c

wchar: wchar.c
	gcc -o wchar wchar.c

time: time.c
	gcc -o time time.c

lconv: lconv.c
	gcc -o lconv lconv.c

langinfo: langinfo.c
	gcc -o langinfo langinfo.c

test-iconv: test-iconv.c
	gcc -o test-iconv test-iconv.c

clean:
	rm -f setlocale type convert wchar time lconv langinfo test-iconv *.bak

