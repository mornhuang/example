
	/* file setlocale.c */
	#include <stdio.h>
	#include <locale.h>
	#include <stdlib.h>

	int main(int argc, char *argv[])
	{
		char *cur_locale;

		//…Ë÷√ locale
		cur_locale = setlocale(LC_ALL, "");

		printf("current locale:%s\n", cur_locale);

		printf("MB_CUR_MAX:%d\n", MB_CUR_MAX);

	}
       
