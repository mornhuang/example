
	/* File: pipe.c */

	#include <stdio.h>
	#include <sys/types.h>
	#include <unistd.h>

	//子进程读数据
	void read_data(int pipes[]) {
		int c;
		int rc;

		//关闭写的一端
		close(pipes[1]);

		//读数据并打印 
		while ((rc = read(pipes[0], &c, 1)) > 0){
			putchar(c);
		}

		exit(0);
	}

	//父进程写数据
	void write_data(int pipes[])
	{
		int c;
		int rc;

		//关闭读数据一端
		close(pipes[0]);

		//从控制台读数据并向管道写数据
		while ((c = getchar()) > 0) {
			rc = write(pipes[1], &c, 1);
			if (rc == -1) {
				perror("Parent: write");
				close(pipes[1]);
				exit(1);
			}
		}

		//关闭写一端
		close(pipes[1]);
		exit(0);
	}

	int main(int argc, char*argv[])
	{
		int pipes[2];
		pid_t pid;		//fork返回的进程号
		int rc;			//返回值

		//建立管道
		rc = pipe(pipes);
		if (rc == -1) {
			perror("pipe");
			exit(1);
		}

		//建立子进程
		pid = fork();

		switch(pid) {
			//失败
			case -1:
				perror("fork");
				exit(1);
			//子进程
			case 0:
				read_data(pipes);
				//不能到这里
			//父进程
			default:
				write_data(pipes);
				//不能到这里/
		}
		return 0;
	}


