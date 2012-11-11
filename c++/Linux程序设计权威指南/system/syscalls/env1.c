
	/* File: env1.c */
	int main(int argc, char **argv)
	{
		//打印环境变量 LC_ALL 的值
		if(getenv("LC_ALL"))
			printf("LC_ALL is %s\n", getenv("LC_ALL"));

		//把环境变量设置为其它的值
		setenv("LC_ALL", "de_DE", 1);
		printf("Now LC_ALL is %s\n", getenv("LC_ALL"));

		//取消环境变量
		unsetenv("LC_ALL");
		printf("Now LC_ALL is %s\n", getenv("LC_ALL"));

		return 0;
	}
