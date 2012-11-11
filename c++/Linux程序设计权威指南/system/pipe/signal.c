
	/* File: signal.c */

	#include <stdio.h>
	#include <unistd.h>
	#include <sys/types.h>
	#include <sys/wait.h>
	#include <signal.h>

	void signal_handler(int n)
	{
		//这时已经有一个 zombie 进程
		int child_status;

		wait(&child_status);
		printf("child exited.\n");
	}


	int main(int argc, char **argv)
	{
		pid_t child_pid;
		int child_status;

		signal(SIGCHLD, signal_handler);

		//建立新进程
		child_pid = fork();

		//根据返回值判断是否成功, 如果成功判断是子进程还是父进程
		switch (child_pid) {
			case -1:	//失败
				perror("fork");
				exit(1);
			case 0:		//子进程
				printf("child: hello from child process\n");
				exit(0);
			default:	//成功, 父进程内

				//等待
				//这时从另外的终端看是否还有 zombie 进程?
				sleep(10);
		}
	}
