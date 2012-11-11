
	/* file type.c */
	#include <stdio.h>
	#include <locale.h>
	#include <stdlib.h>
	#include <wctype.h>

	int main(int argc, char *argv[])
	{
		unsigned char *mbc = "。";
		wchar_t wcc;
		unsigned char buf[100];

		//设置 locale
		setlocale(LC_ALL, "");

		//把多字节字符串转换为宽字符
		mbtowc(&wcc, mbc, 2);
		printf("wc = %x\n", wcc);

		//转换回多字节字符
		wctomb(buf, wcc);
		printf("mb = %s\n", buf);

		printf("下列结果在老版本的glibc下可能不正确:\n");
		printf("iswalpnum: %d\n", iswalnum(wcc));
		printf("iswdigit : %d\n", iswdigit(wcc));
		printf("iswpunct : %d\n", iswpunct(wcc));

	}

