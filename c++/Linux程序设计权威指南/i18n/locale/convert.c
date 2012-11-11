
	/* file convert.c */
	#include <stdio.h>
	#include <locale.h>
	#include <stdlib.h>
	#include <wctype.h>

	int main(int argc, char *argv[])
	{
		unsigned char *mbs = "测试Mixed字符串.";
		wchar_t wcs[100];
		unsigned char buf[100];
		int n;

		//设置 locale
		setlocale(LC_ALL, "");

		//把多字节字符串转换为宽字符
		printf("mbs:%s\n", mbs);
		n = mbstowcs(wcs, mbs, strlen(mbs));
		printf("wcslen = %d\n", wcslen(wcs));
		printf("return by mbstowcs():%d\n\n", n);

		//转换回多字节字符串
		n = wcstombs(buf, wcs, 100);
		printf("Number of multibyte chars:%d\n", n);
		printf("mbs =%s\n\n", buf);

		//其它判断字符串的长度的函数
		n = mbstowcs(NULL, mbs, 0);
		printf("mbstowcs(NULL, mbs, 0): %d\n", n);
		n = wcstombs(NULL, wcs, 0);
		printf("wcstombs(NULL, wcs, 0): %d\n", n);



	}

