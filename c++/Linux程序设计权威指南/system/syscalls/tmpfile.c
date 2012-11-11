
	/* File: tmpfile.c */

	#include <stdio.h>
	#include <stdlib.h>

	int main(int argc, char **argv)
	{
		char tmpname[L_tmpnam];
		char template[L_tmpnam];
		char *filename;
		FILE *fp;
		
		filename = tmpnam(tmpname);
		printf("Temporary file name: %s\n", filename);

		fp = tmpfile();
		if(fp) printf("Temporary file opened.\n");
		else perror("tmpfile");

		strcpy(template, "/tmp/myXXXXXX");
		filename = mktemp(template);
		printf("Temporary file name: %s\n", filename);

		return 0;
	}
