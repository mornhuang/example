#include <stdio.h>       /* 定义标准的 I/O 函数         */
#include <pthread.h>     /* 定义 pthread 函数和数据结构 */

/* 本程序全局的 mutex，用赋值的方法来初始化它 */
pthread_mutex_t a_mutex = PTHREAD_MUTEX_INITIALIZER;

/* 存放雇员信息的结构 */
struct employee {
    int number;
    int id;
    char first_name[20];
    char last_name[30];
    char department[30];
    int room_number;
};

/* 全局变量，2 位雇员的数据，分别编号为 1 和 2 */
struct employee employees[] = {
    { 1, 12345678, "danny", "cohen", "Accounting", 101},
    { 2, 87654321, "moshe", "levy", "Programmers", 202}
};

/* 全局变量，本日雇员 */
struct employee employee_of_the_day;

/* 把一个雇员信息结构的内容拷贝到另一个雇员信息结构中 */
void
copy_employee(struct employee* from, struct employee* to)
{
    int rc;	/* 保存 mutex 锁定/解锁过程的结果 */

    /* 锁定 mutex，以保证对 from 和 to 的排他性访问 */
    rc = pthread_mutex_lock(&a_mutex);

    /* 拷贝数据 */
    to->number = from->number;
    to->id = from->id;
    strcpy(to->first_name, from->first_name);
    strcpy(to->last_name, from->last_name);
    strcpy(to->department, from->department);
    to->room_number = from->room_number;

    /* 解锁 mutex */
    rc = pthread_mutex_unlock(&a_mutex);
}

/* 设置本日雇员的函数 */
void*
do_loop(void* data)
{
    int my_num = *((int*)data);   /* 线程的雇员编号 */

    while (1) {
        /* 设置本日雇员为编号 my_num 的雇员 */
	copy_employee(&employees[my_num-1], &employee_of_the_day);
    }
}

/* 主程序，或主线程 */
int
main(int argc, char* argv[])
{
    int        i;                      /* 循环计数器           */
    int        ret1;
    int        ret2;
    pthread_t  p_thread1;              /* 第一个线程结构       */
    pthread_t  p_thread2;              /* 第二个线程结构       */
    int        num1      = 1;          /* 第一个线程的雇员编号 */
    int        num2      = 2;          /* 第二个线程的雇员编号 */
    struct employee eotd;              /* 本日雇员的本地拷贝   */
    struct employee* worker;           /* 指向正在检查的雇员   */

    /* 初始化本日雇员为第一个雇员 */
    copy_employee(&employees[0], &employee_of_the_day);

    /* 创建一个新线程，以数据 1 执行 do_loop()，即设置 1 号雇员为本日雇员 */
    ret1 = pthread_create(&p_thread1, NULL, do_loop, (void*)&num1);
    /* 创建第二个新线程，以数据 2 执行 do_loop()，即设置 2 号雇员为本日雇员 */
    ret2 = pthread_create(&p_thread2, NULL, do_loop, (void*)&num2);

    /* 创建一个循环，检查本日雇员的数据是否一致，即是否属于同一位雇员 */
    for (i=0; i<60000; i++) {
        /* 保存本日雇员内容 */
        copy_employee(&employee_of_the_day, &eotd);
        /* 取出本日雇员的原始数据*/
	worker = &employees[eotd.number-1];

        /* 比较数据 */
	if (eotd.id != worker->id) {
	    printf("mismatching 'id' , %d != %d (loop '%d')\n",
		   eotd.id, worker->id, i);
	    exit(0);
	}
	if (strcmp(eotd.first_name, worker->first_name) != 0) {
	    printf("mismatching 'first_name' , %s != %s (loop '%d')\n",
		   eotd.first_name, worker->first_name, i);
	    exit(0);
	}
	if (strcmp(eotd.last_name, worker->last_name) != 0) {
	    printf("mismatching 'last_name' , %s != %s (loop '%d')\n",
		   eotd.last_name, worker->last_name, i);
	    exit(0);
	}
	if (strcmp(eotd.department, worker->department) != 0) {
	    printf("mismatching 'department' , %s != %s (loop '%d')\n",
		   eotd.department, worker->department, i);
	    exit(0);
	}
	if (eotd.room_number != worker->room_number) {
	    printf("mismatching 'room_number' , %d != %d (loop '%d')\n",
		   eotd.room_number, worker->room_number, i);
	    exit(0);
	}
    }

    /* 全部数据都一致 */
    printf("Glory, employees contents was always consistent\n");
    
    return 0;
}
