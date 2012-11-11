
	/* File: xlocale.c */

	#include <X11/Xlib.h>
	#include <stdio.h>
	#include <locale.h>


	int main(int argc, char **argv)
	{
		if((setlocale(LC_ALL, "")) == NULL){
			printf("cannot set locale\n");
			exit(1);
		}

		if(!XSupportsLocale()){
			printf("X does not support current locale\n");
			exit(1);
		}

		if(XSetLocaleModifiers(NULL)){
			printf("Cannot set locale modifiers\n");
			exit(1);
		}

		printf("All ok\n");
	}
