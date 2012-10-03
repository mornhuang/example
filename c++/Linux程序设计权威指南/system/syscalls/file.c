
	/* File: file.c */
	#include <sys/types.h>
	#include <sys/stat.h>

	int main(int argc, char *argv[])
	{
		int i;
		struct stat buf;
		char *ptr;

		if(argc < 2) {
			printf("Usage: ./file [filename]\n");
			exit(1);
		}
		if (lstat(argv[1], &buf) < 0) {
			perror("lstat");
			exit(1);
		}

		if      (S_ISREG(buf.st_mode))	ptr = "regular";
		else if (S_ISDIR(buf.st_mode))	ptr = "directory";
		else if (S_ISCHR(buf.st_mode))	ptr = "character special";
		else if (S_ISBLK(buf.st_mode))	ptr = "block special";
		else if (S_ISFIFO(buf.st_mode))	ptr = "fifo";
		else if (S_ISLNK(buf.st_mode))	ptr = "symbolic link";
		else if (S_ISSOCK(buf.st_mode))	ptr = "socket";
		else				ptr = "** unknown mode **";
		printf("File %s is %s file\n", argv[1], ptr);

		if(S_ISREG(buf.st_mode))
			printf("File size is %d bytes\n", buf.st_size);

		return 0;
	}
