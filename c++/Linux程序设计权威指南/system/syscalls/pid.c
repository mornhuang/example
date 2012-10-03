
	/* File: pid.c */
	#include <stdio.h>
	#include <unistd.h>
	#include <stdlib.h>

	int main(void)
	{
		printf("PID = %d\n", getpid());
		printf("PPID = %d\n", getppid());
		exit(EXIT_SUCCESS);
	}


