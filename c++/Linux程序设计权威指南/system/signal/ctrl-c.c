
	/* File: ctrl-c.c */
	#include <stdio.h> 
	#include <unistd.h>
	#include <signal.h>

	void catch_int(int sig_num)
	{
		// 重新设置 SIGINT 的 handler
		signal(SIGINT, catch_int);

		printf("Control-C is ignored, please don't waste time.\n");
		fflush(stdout);
	}

	int main(int argc, char* argv[])
	{
		//设置Control-C的回调
		printf("Want to try Control-C ?\n");

		signal(SIGINT, catch_int);
 
		//进入循环
		while(1) pause();
	}
