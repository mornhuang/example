
	/* File: host.c */
	#include <unistd.h>
	#include <sys/utsname.h>

	int main(int argc, char **argv)
	{
		char hname[256];
		struct utsname uts;

		if( gethostname(hname, 255) != 0 || uname(&uts) < 0){
			printf("Cannot get host information.\n");
			exit(1);
		}

		printf("Host name is:%s\n", hname);
		printf("System is %s on %s\n", uts.sysname, uts.machine);
		printf("Nodename is %s\n", uts.nodename);
		printf("Version is %s, %s\n", uts.release, uts.version);


	}
