#include <stdio.h>       /* 定义标准的 I/O 函数         */
#include <pthread.h>     /* 定义 pthread 函数和数据结构 */

/* 新线程将执行的函数 */
void*
do_loop(void* data)
{
    int i;                      /* 计数器，要打印的数字 */
    int j;                      /* 计数器，延迟         */
    int me = *((int*)data);     /* 线程编号             */

    for (i=0; i<10; i++) {
        for (j=0; j<500000; j++) /* 延迟循环 */
            ;
        printf("'%d' - Got '%d'\n", me, i);
    }

    /* 退出线程 */
    pthread_exit(NULL);
}

/* 程序从 main 函数开始执行 */
int
main(int argc, char* argv[])
{
    int        ret;            /* 线程创建函数的返回值 */
    pthread_t  p_thread;       /* 线程的 ID 号         */
    int        a         = 1;  /* 线程编号             */
    int        b         = 2;  /* 线程编号             */

    /* 创建一个新线程，它将执行 do_loop() */
    ret = pthread_create(&p_thread, NULL, do_loop, (void*)&a);
    /* 在主线程中也同样执行 do_loop() */
    do_loop((void*)&b);

    /* 不会执行到这一句 */
    return 0;
}

