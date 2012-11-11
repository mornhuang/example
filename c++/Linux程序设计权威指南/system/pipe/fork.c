
	/* File: fork.c */

	#include <sys/types.h>
	#include <sys/wait.h>

	int main(int argc, char **argv)
	{
		pid_t child_pid;
		int child_status;

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
				//等待子进程退出
				printf("parent: I am waiting child process\n");
				wait(&child_status);
		}
	}
