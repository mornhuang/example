

	/* File: env2.c */
	int main(int argc, char **argv)
	{
		extern char **environ;

		char **env = environ;

		//打印环境变量
		while(*env){
			printf("%s\n", *env);
			env++;
		}

		return 0;
	}
