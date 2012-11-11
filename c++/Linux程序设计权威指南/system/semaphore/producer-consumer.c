
	/* File: producer-consumer.c */

	#include <stdio.h>
	#include <stdlib.h>
	#include <unistd.h>
	#include <time.h>
	#include <sys/types.h>
	#include <linux/sem.h>

	#define NUM_LOOPS       20

	int main(int argc, char* argv[])
	{
		int sem_set_id;           //信号灯集的ID
		union semun sem_val;      //信号灯的数值
		int child_pid;            //子进程的PID
		struct sembuf sem_op;     //信号灯操作结构
		int i;
		int rc;                   //返回值
		struct timespec delay;    //延迟时间

		//建立信号灯集, 其中只含有一个信号灯
		sem_set_id = semget(IPC_PRIVATE, 1, 0600);
		if (sem_set_id == -1) {
			perror("main: semget");
			exit(1);
		}
		printf("semaphore set created, semaphore set id '%d'.\n", 
			sem_set_id);

		//信号灯数值设置为0
		sem_val.val = 0;
		rc = semctl(sem_set_id, 0, SETVAL, sem_val);

		//建立子进程
		child_pid = fork();
		switch (child_pid) {
			case -1:	//失败
				perror("fork");
				exit(1);
			case 0:		//子进程
				for (i=0; i<NUM_LOOPS; i++) {
					//被阻塞, 直到信号灯的数值变为非负数
					sem_op.sem_num = 0;
					sem_op.sem_op = -1;
					sem_op.sem_flg = 0;
					semop(sem_set_id, &sem_op, 1);
					printf("consumer: '%d'\n", i);
					fflush(stdout);
				}
				break;
			default:	//父进程
				for (i=0; i<NUM_LOOPS; i++) {
					printf("producer: '%d'\n", i);
					fflush(stdout);
					//把信号灯的数值增加1
					sem_op.sem_num = 0;
					sem_op.sem_op = 1;
					sem_op.sem_flg = 0;
					semop(sem_set_id, &sem_op, 1);

					//等待一段时间
					if (rand() > 3*(RAND_MAX/4)) {
						delay.tv_sec = 0;
						delay.tv_nsec = 10;
						nanosleep(&delay, NULL);
					}
				}
				break;
		}
		return 0;
	}
