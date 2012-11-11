
	/* File: ls.c */
	#include <stdio.h>
	#include <sys/types.h>
	#include <dirent.h>

	int main(int argc, char *argv[])
	{
		DIR  *dp;
		struct dirent *dirp;

		if((dp = opendir(".")) == NULL)
			perror("opendir");

		while ((dirp = readdir(dp)) != NULL)
			printf("%s\n", dirp->d_name);

		closedir(dp);
		return 0;
	}

