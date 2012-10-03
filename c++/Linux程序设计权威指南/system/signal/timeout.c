
	/* File: timeout.c */
	#include <stdio.h>
	#include <unistd.h>
	#include <signal.h>

	char user[40];

	//alarm 的捕捉函数
	void catch_alarm(int sig_num)
	{
		//清除定时器
		alarm(0);

		printf("Sorry, time limit reached.\n");
		exit(0);
	}

	int main(int argc, char* argv[])
	{

		//设置信号回调
		signal(SIGALRM, catch_alarm);

		//提示输入用户名
		printf("Your Username(10 seconds limit): ");
		fflush(stdout);

		//设置10秒时限
		alarm(10);

		//等待用户输入
		(char *)gets(user);

		printf("Your username is: '%s'\n", user);

		return 0;
	}


