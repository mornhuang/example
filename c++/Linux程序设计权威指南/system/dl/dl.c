
	#include <dlfcn.h>

	int main(int argc, char *argv[])
	{

		const char *msg;
		int rtn;
		void *handle;
		int (*testdl)(int n, char *str);

		//打开动态库
		handle = dlopen("./libtest.so", RTLD_LAZY);
		if(!handle) {
			printf("dlopen() error: %s\n", dlerror());
			exit(1);
		}

		//取得符号并检查错误
		testdl = dlsym(handle, "output");
		msg = dlerror();
		if(msg) {
			printf("Error fetching 'output':%s\n", msg);
			exit(1);
		}

		//使用符号
		rtn = testdl(3, "Hello World!");
		printf("returned: %d\n", rtn);
		

	}
