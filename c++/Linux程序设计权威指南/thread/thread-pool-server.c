#include <stdio.h>       /* 标准 I/O 函数                     */
#include <pthread.h>     /* pthread 函数和数据结构            */
#include <stdlib.h>      /* rand() and srand() 函数           */

/* 处理请求的线程数 */
#define NUM_HANDLER_THREADS 3

/* 程序的全局 mutex，赋值初始化它 */
/* 注意我们使用一个递归 mutex，因为一个可能会连续锁定它两次 */
pthread_mutex_t request_mutex = PTHREAD_RECURSIVE_MUTEX_INITIALIZER_NP;

/* 程序的全局条件变量，赋值初始化它 */
pthread_cond_t  got_request   = PTHREAD_COND_INITIALIZER;

int num_requests = 0;	/* 待处理的请求数，初始为 0 */

/* 单个请求的格式 */
struct request {
    int number;		    /* 请求的编号 */
    struct request* next;   /* 指向下一个请求的指针，NULL 表示没有下一个 */
};

struct request* requests = NULL;     /* 请求链表头 */
struct request* last_request = NULL; /* 指向最后一个请求的指针 */

/*
 * add_request(): 向请求链表增加一个请求
 * 算法: 创建一个请求结构，添加到链表中，并把待处理的请求数加 1
 */
void
add_request(int request_num,
	    pthread_mutex_t* p_mutex,
	    pthread_cond_t*  p_cond_var)
{
    int rc;	                    /* pthread 函数的返回值 */
    struct request* a_request;      /* 指向新添加的请求     */

    /* 创建新的请求结构 */
    a_request = (struct request*)malloc(sizeof(struct request));
    if (!a_request) {
	fprintf(stderr, "add_request: out of memory\n");
	exit(1);
    }
    a_request->number = request_num;
    a_request->next = NULL;

    /* 锁定 mutex，以保证排它性访问链表 */
    rc = pthread_mutex_lock(p_mutex);

    /* 把新请求添加到链表尾部，按要求更新链表指针 */
    if (num_requests == 0) {
	requests = a_request;
	last_request = a_request;
    }
    else {
	last_request->next = a_request;
	last_request = a_request;
    }

    /* 把等待的请求总数加 1 */
    num_requests++;

    /* 解锁 mutex */
    rc = pthread_mutex_unlock(p_mutex);

    /* 向条件变量发信号，表示有一个新的请求有待处理 */
    rc = pthread_cond_signal(p_cond_var);
}

/*
 * get_request(): 取得链表中第一个请求，然后从链表中删除它
 */
struct request*
get_request(pthread_mutex_t* p_mutex)
{
    int rc;	                    /* pthread 函数的返回值  */
    struct request* a_request;      /* 请求指针              */

    /* 锁定 mutex，以保证排它性访问链表 */
    rc = pthread_mutex_lock(p_mutex);

    if (num_requests > 0) {
	a_request = requests;
	requests = a_request->next;
	if (requests == NULL) {
	    last_request = NULL;
	}
        /* 把等待的请求总数加 1 */
	num_requests--;
    }
    else {
	a_request = NULL;
    }

    /* 解锁 mutex */
    rc = pthread_mutex_unlock(p_mutex);

    /* 向调用者返回这个请求 */
    return a_request;
}

/*
 * handle_request(): 处理单一请求
 * 算法: 打印一条信息，表示指定请求已被指定线程处理
 */
void
handle_request(struct request* a_request, int thread_id)
{
    struct timespec delay;			 /* 延迟时间 */
    if (a_request) {
	printf("Thread '%d' handled request '%d'\n",
	       thread_id, a_request->number);
	fflush(stdout);
    }
}

/*
 * handle_requests_loop(): 处理请求的无限循环
 * 算法：如果有请求要处理，取第一个处理。然后守候指定条件变量，收到信号后
 *       重新开始循环，并把待处理的请求数加 1。
 */
void*
handle_requests_loop(void* data)
{
    int rc;	                    /* pthread 函数的返回值  */
    struct request* a_request;      /* 请求指针              */
    int thread_id = *((int*)data);  /* 线程序号              */

    /* 锁定 mutex，以保证排它性访问链表 */
    rc = pthread_mutex_lock(&request_mutex);

    /* 无限循环 ... */
    while (1) {
	if (num_requests > 0) {
	    a_request = get_request(&request_mutex);
	    if (a_request) {
		handle_request(a_request, thread_id);
		free(a_request);
	    }
	}
	else {
            /* 等待请求抵达：解锁——守候——锁定——返回 */
	    rc = pthread_cond_wait(&got_request, &request_mutex);
	}
    }
}

/* 主线程 */
int
main(int argc, char* argv[])
{
    int        i;                                /* 循环变量 */
    int        thr_num[NUM_HANDLER_THREADS];      /* 线程序号 */
    pthread_t  p_threads[NUM_HANDLER_THREADS];   /* 线程结构 */
    struct timespec delay;			 /* 延迟时间 */

    /* 创建请求处理线程 */
    for (i=0; i<NUM_HANDLER_THREADS; i++) {
	thr_num[i] = i;
	pthread_create(&p_threads[i], NULL, handle_requests_loop, (void*)&thr_num[i]);
    }

    /* 循环产生请求 */
    for (i=0; i<600; i++) {
	add_request(i, &request_mutex, &got_request);
	/* 暂停一会，以让其它线程运行并处理某些请求 */
	if (rand() > 3*(RAND_MAX/4)) { /* 需要 25% 的时间 */
	    delay.tv_sec = 0;
	    delay.tv_nsec = 10;
	    nanosleep(&delay, NULL);
	}
    }
    /* 等待，直到没有剩下有待处理的请求 */
    sleep(5);

    printf("Glory,  we are done.\n");
    
    return 0;
}
