
	/* File: pipes.c */
	#include <stdio.h>
	#include <sys/types.h>
	#include <unistd.h>
	#include <ctype.h>

	//父进程从控制台读数据, 并向管道中写数据
	void user_handler(int input_pipe[], int output_pipe[])
	{
		int c;
		int rc;

		//关闭不必要的文件描述字
		close(input_pipe[1]);
		close(output_pipe[0]);

		while ((c = getchar()) > 0) {	//读字符

			//向管道中写字符
			rc = write(output_pipe[1], &c, 1);
			if (rc == -1) {
				perror("user_handler: write");
				close(input_pipe[0]);
				close(output_pipe[1]);
				exit(1);
			}

			//从管道中读字符
			rc = read(input_pipe[0], &c, 1);
			if (rc <= 0) {
				perror("user_handler: read");
				close(input_pipe[0]);
				close(output_pipe[1]);
				exit(1);
			}

			//回显字符
			putchar(c);
		}

		//退出时关闭管道
		close(input_pipe[0]);
		close(output_pipe[1]);
		exit(0);
	}


	void translator(int input_pipe[], int output_pipe[])
	{
		int c;
		int rc;
		char ch;

		//关闭不必要的文件描述字
		close(input_pipe[1]);
		close(output_pipe[0]);

		//从管道中读字符
		while (read(input_pipe[0], &c, 1) > 0) {
			//大小写转换
			ch = (char) c;
			if(isascii(ch) && isupper(ch)) c = tolower(ch);
			else if(isascii(ch) && islower(ch)) c = toupper(ch);
			//向管道中写字符
			rc = write(output_pipe[1], &c, 1);
			if (rc == -1) {
				perror("translator: write");
				close(input_pipe[0]);
				close(output_pipe[1]);
				exit(1);
			}
		}

		//退出时关闭管道
		close(input_pipe[0]);
		close(output_pipe[1]);
		exit(0);
	}

	int main(int argc, char* argv[])
	{
		//两个管道
		int user_to_translator[2];
		int translator_to_user[2];
		int pid;
		int rc;

		//建立两个管道
		rc = pipe(user_to_translator);
		if (rc == -1) {
			perror("main: pipe user_to_translator");
			exit(1);
		}
		rc = pipe(translator_to_user);
		if (rc == -1) {
			perror("main: pipe translator_to_user");
			exit(1);
		}

		//建立子进程
		pid = fork();

		switch (pid) {
			case -1:	//错误
				perror("main: fork");
				exit(1);
			case 0:		//子进程
				translator(user_to_translator, 
					translator_to_user);
			default:
				user_handler(translator_to_user, 
					user_to_translator);
		}
		return 0;
	}


