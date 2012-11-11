
	/* File: semaphore.c */
	#include <stdio.h>
	#include <stdlib.h>
	#include <unistd.h>
	#include <time.h>
	#include <sys/types.h>
	#include <sys/wait.h>
	#include <linux/sem.h>

	#define NUM_PROCS  5
	#define SEM_ID    250
	#define FILE_NAME "/tmp/sem_mutex"
	#define DELAY     400000


	void update_file(int sem_set_id, char* file_path, int number)
	{
		struct sembuf sem_op;
		FILE* file;

		//等待信号灯的数值变为非负数
		sem_op.sem_num = 0;
		sem_op.sem_op = -1;
		sem_op.sem_flg = 0;
		semop(sem_set_id, &sem_op, 1);

		//写如文件, 写入的数值是当前进程的进程号
		file = fopen(file_path, "w");
		if (file) {
			fprintf(file, "%d\n", number);
			printf("%d\n", number);
			fclose(file);
		}

		//发送信号, 把信号灯的数值加1
		sem_op.sem_num = 0;
		sem_op.sem_op = 1;
		sem_op.sem_flg = 0;
		semop(sem_set_id, &sem_op, 1);
	}

	//子进程写文件
	void do_child_loop(int sem_set_id, char* file_name)
	{
		pid_t pid = getpid();
		int i, j;

		for (i=0; i<3; i++) {
			update_file(sem_set_id, file_name, pid);
			for (j=0; j<400000; j++) ;
		}
	}

	int main(int argc, char **argv)
	{
		int sem_set_id;           //信号灯集的ID
		union semun sem_val;      //信号灯的数值, 用于 semctl()
		int child_pid;            //子进程的进程号
		int i;
		int rc;

		//建立信号灯集, ID是250, 其中只有一个信号灯
		sem_set_id = semget(SEM_ID, 1, IPC_CREAT | 0600);
		if (sem_set_id == -1) {
			perror("main: semget");
			exit(1);
		}

		//把第一个信号灯的数值设置为1
		sem_val.val = 1;
		rc = semctl(sem_set_id, 0, SETVAL, sem_val);
		if (rc == -1) {
			perror("main: semctl");
			exit(1);
		}

		//建立一些子进程, 使它们可以同时以竞争的方式访问信号灯
		for (i=0; i<NUM_PROCS; i++) {
			child_pid = fork();
			switch(child_pid) {
				case -1:
					perror("fork");
					exit(1);
				case 0:		//子进程
					do_child_loop(sem_set_id, FILE_NAME);
					exit(0);
				default:	//父进程接着运行
					break;
			}
		}

		//等待子进程结束
		for (i=0; i<NUM_PROCS; i++) {
			int child_status;
			wait(&child_status);
		}

		printf("main: we're done\n");
		fflush(stdout);
		return 0;
	}


