
	/* file time.c */
	#include <stdio.h>
	#include <locale.h>
	#include <time.h>

	int main(int argc, char *argv[])
	{

		time_t now;
		struct tm *tm;
		char buf[100];
      
		//…Ë÷√ locale
		setlocale(LC_ALL, "");
      
		now = time(NULL);
		tm = localtime(&now);
		strftime(buf, sizeof(buf), "%c", tm);
		printf("Current Time:%s\n", buf);

		strftime(buf, sizeof(buf), "%D %T %p", tm);
		printf("Current Time:%s\n", buf);

		return 0;
	}

