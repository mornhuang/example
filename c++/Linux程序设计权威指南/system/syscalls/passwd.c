
	/* File: passwd.c */
	#include <sys/types.h>
	#include <pwd.h>
	#include <unistd.h>

	int main(int argc, char **argv)
	{
		uid_t uid;
		gid_t gid;
		struct passwd *pw;

		uid = getuid();
		gid = getgid();
		printf("user id is:%d, group id is:%d\n", uid, gid);

		pw = getpwuid(uid);
		printf("user login name is :%s\n", pw->pw_name);
		printf("user uid is        :%d\n", pw->pw_uid);
		printf("user gid is        :%d\n", pw->pw_gid);
		printf("home directory is  :%s\n", pw->pw_dir);
		printf("user login shell is:%s\n", pw->pw_shell);
	}
