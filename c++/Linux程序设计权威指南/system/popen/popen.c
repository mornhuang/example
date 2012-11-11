
	/* File: popen.c */
	#include <unistd.h>
	#include <stdlib.h>
	#include <stdio.h>
	#include <string.h>

	int main(int argc, char *argv[])
	{
		FILE *fp;
		char *cmd = "ls -l";
		char line[1024];

		//打开管道
		fp = popen(cmd, "r");
		if(!fp){
			perror("popen");
			exit(1);
		}

		//读结果
		while(fgets(line, 1024, fp)){
			printf("%s", line);
		}

		return 0;
	}
