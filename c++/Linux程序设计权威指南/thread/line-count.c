#include <stdio.h>             /* 标准 I/O 函数          */
#include <pthread.h>           /* pthread 函数和数据结构 */

#define DATA_FILE "very_large_data_file"

/* 程序的全局 mutex，赋值初始化它 */
pthread_mutex_t action_mutex = PTHREAD_MUTEX_INITIALIZER;

/* 程序的全局条件变量，赋值初始化它 */
pthread_cond_t  action_cond   = PTHREAD_COND_INITIALIZER;

/* 用户是否中途取消操作的标志 */
int cancel_operation = 0;

/*
 * restore_coocked_mode - 恢复通常的屏幕模式
 * 算法: 使用 stty 命令恢复通常的屏幕模式。是用户输入线程的清理函数
 */

void
restore_coocked_mode(void* dummy)
{
    system("stty -raw echo");
}

/*
 * read_user_input - 在长的操作期间读取用户输入
 * 算法：设置屏幕为 raw 模式（不回显），用一个无限循环读取用户输入。
 *            如果用户键入 e，向条件变量发信号并终止线程
 */
void*
read_user_input(void* data)
{
    int c;

    /* 登记清理函数 */
    pthread_cleanup_push(restore_coocked_mode, NULL);

    /* 选择立即处理取消模式，这样我们在读取大量数据时也可以被终止 */
    pthread_setcanceltype(PTHREAD_CANCEL_ASYNCHRONOUS, NULL);

    /* 设置屏幕为 raw 模式 */
    system("stty raw -echo");

    /* 用一个无限循环读取用户输入。               */
    /* 如果用户键入 e，向条件变量发信号并终止线程 */
    while ((c = getchar()) != EOF) {
	if (c == 'e') {
	    /* 用户的取消请求 */
	    cancel_operation = 1;
	    /* 告诉大家我们要退出了 */
	    pthread_cond_signal(&action_cond);
	    pthread_exit(NULL);
	}
    }

    /* 弹出清理函数，同时执行它以恢复正常屏幕模式 */
    pthread_cleanup_pop(1);
}

/*
 * file_line_count - 计算指定文件的行数
 * 算法：打开数据文件，一个字符一个字符地读，计算换行符的数目
 */
void*
file_line_count(void* data)
{
    char* data_file = (char*)data;
    FILE* f = fopen(data_file, "r");
    int wc = 0;
    int c;

    if (!f) {
	perror("fopen");
	exit(1);
    }

    /* 选择立即处理取消模式，这样我们在读取大量数据时也可以被终止 */
    pthread_setcanceltype(PTHREAD_CANCEL_ASYNCHRONOUS, NULL);

    while ((c = getc(f)) != EOF) {
	if (c == '\n')
	    wc++;
    }

    fclose(f);

    /* 告诉大家我们要退出了 */
    pthread_cond_signal(&action_cond);

    return (void*)wc;
}

/* 主线程 */
int
main(int argc, char* argv[])
{
    pthread_t thread_line_count; /* 计算行数线程           */
    pthread_t thread_user_input; /* 用户输入线程       */
    void* line_count;		 /* 计算行数线程的返回行数 */

    printf("Checking file size (press 'e' to cancel operation)...");
    fflush(stdout);

    /* 创建计算行数线程 */
    pthread_create(&thread_line_count,
		   NULL,
		   file_line_count,
		   (void*)DATA_FILE);
    /* 创建用户输入线程 */
    pthread_create(&thread_user_input,
		   NULL,
		   read_user_input,
		   (void*)DATA_FILE);

    /* 锁定 mutex，守候条件变量，直到其中一个线程完成并发出信号 */
    pthread_mutex_lock(&action_mutex);
    pthread_cond_wait(&action_cond, &action_mutex);
    pthread_mutex_unlock(&action_mutex);

    /* 检查接收的信号是哪个线程发出的 */
    if (cancel_operation) {
	/* 加入用户输入线程以保证它在我们打印之前恢复屏幕模式 */
        pthread_join(thread_user_input, NULL);
	printf("operation canceled\n");
	fflush(stdout);
        /* 取消计算行数线程 */
        pthread_cancel(thread_line_count);
    }
    else {
        /* 加入计算行数线程，以得到其结果 */
        pthread_join(thread_line_count, &line_count);

        /* 取消并加入用户输入线程以保证它在我们打印之前恢复屏幕模式 */
        pthread_cancel(thread_user_input);
        pthread_join(thread_user_input, NULL);

	/* 打印结果 */
        printf("'%d' lines.\n", (int)line_count);
    }

    return 0;
}
