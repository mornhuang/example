
	/* File: shm-semaphore.c */
	#include <stdio.h>
	#include <linux/types.h>
	#include <linux/shm.h>
	#include <linux/sem.h>
	#include <unistd.h>
	#include <wait.h>
	#include <time.h>
	#include <stdlib.h>

	#define SEM_ID    250

	struct country {
		char name[30];
		char capital_city[30];
		char currency[30];
		int population;
	};

	//时间延迟函数
	void random_delay()
	{
		static int initialized = 0;
		int random_num;
		struct timespec delay;

		if (!initialized) {
			srand(time(NULL));
			initialized = 1;
		}

		random_num = rand() % 10;
		delay.tv_sec = 0;
		delay.tv_nsec = 10*random_num;
		nanosleep(&delay, NULL);
	}

	//锁定信号灯
	void sem_lock(int sem_set_id)
	{
		struct sembuf sem_op;

		//等待信号灯, 直到它的数值非负数
		sem_op.sem_num = 0;
		sem_op.sem_op = -1;
		sem_op.sem_flg = 0;
		semop(sem_set_id, &sem_op, 1);
	}

	//信号灯解锁
	void sem_unlock(int sem_set_id)
	{
		struct sembuf sem_op;

		//增加信号灯的数值
		sem_op.sem_num = 0;
		sem_op.sem_op = 1;
		sem_op.sem_flg = 0;
		semop(sem_set_id, &sem_op, 1);
	}

	//写数据
	void add_country(int sem_set_id, int* countries_num, 
		struct country* countries, char* country_name, 
		char* capital_city, char* currency, int population)
	{
		sem_lock(sem_set_id);
		strcpy(countries[*countries_num].name, country_name);
		strcpy(countries[*countries_num].capital_city, capital_city);
		strcpy(countries[*countries_num].currency, currency);
		countries[*countries_num].population = population;
		(*countries_num)++;
		sem_unlock(sem_set_id);
	}

	//子进程写数据
	void do_child(int sem_set_id, int* countries_num, 
		struct country* counties)
	{
		add_country(sem_set_id, countries_num, counties,
			"U.S.A", "Washington", "U.S. Dollar", 250000000);
		random_delay();
		add_country(sem_set_id, countries_num, counties,
			"Israel", "Jerusalem", "New Israeli Shekel", 6000000);
		random_delay();
		add_country(sem_set_id, countries_num, counties,
			"France", "Paris", "Frank", 60000000);
		random_delay();
		add_country(sem_set_id, countries_num, counties,
			"Great Britain", "London", "Pound", 55000000);
	}

	//父进程访问共享内存, 并打印结果
	void do_parent(int sem_set_id, int* countries_num, 
		struct country* countries)
	{
		int i, num_loops;

		for (num_loops=0; num_loops < 5; num_loops++) {
			sem_lock(sem_set_id);
			printf("------------------------------------------\n");
			printf("Number Of Countries: %d\n", *countries_num);
			for (i=0; i < (*countries_num); i++) {
				printf("Country %d:\n", i+1);
				printf("  name: %s:\n", countries[i].name);
				printf("  capital city: %s:\n", 
					countries[i].capital_city);
				printf("  currency: %s:\n", 
					countries[i].currency);
				printf("  population: %d:\n", 
					countries[i].population);
			}
			sem_unlock(sem_set_id);
			random_delay();
		}
	}

	int main(int argc, char* argv[])
	{
		int sem_set_id;            //信号灯的ID
		union semun sem_val;       //信号灯数值
		int shm_id;                //共享内存的ID
		char *shm_addr;            //共享内存的地址
		int *countries_num;        //数据总数量
		struct country* countries; //数据
		struct shmid_ds shm_desc;
		int rc;
		pid_t pid;
		int child_status;

		//建立信号灯
		sem_set_id = semget(SEM_ID, 1, IPC_CREAT | 0600);
		if (sem_set_id == -1) {
			perror("main: semget");
			exit(1);
		}

		//设置信号灯的数值是 1
		sem_val.val = 1;
		rc = semctl(sem_set_id, 0, SETVAL, sem_val);
		if (rc == -1) {
			perror("main: semctl");
			exit(1);
		}

		//建立共享内存
		shm_id = shmget(100, 2048, IPC_CREAT | IPC_EXCL | 0600);
		if (shm_id == -1) {
			perror("main: shmget: ");
			exit(1);
		}

		//attach 共享内存
		shm_addr = (char *)shmat(shm_id, NULL, 0);
		if (!shm_addr) {
			perror("main: shmat: ");
			exit(1);
		}

		//初始化数据
		countries_num = (int*) shm_addr;
		*countries_num = 0;
		countries = (struct country*) ((void*)shm_addr+sizeof(int));

		//建立子进程
		pid = fork();
		switch (pid) {
			case -1:
				perror("fork: ");
				exit(1);
			case 0:
				do_child(sem_set_id, countries_num, countries);
				exit(0);
			default:
				do_parent(sem_set_id, countries_num, countries);
				break;
		}

		//等待子进程退出
		wait(&child_status);

		//分离共享内存
		if (shmdt(shm_addr) == -1) {
			perror("main: shmdt: ");
		}

		//撤消共享内存
		if (shmctl(shm_id, IPC_RMID, &shm_desc) == -1) {
			perror("main: shmctl: ");
		}

		return 0;
	}

