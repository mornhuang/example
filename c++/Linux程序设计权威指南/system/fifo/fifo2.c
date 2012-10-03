
	/* File: fifo2.c */
	#include <unistd.h>
	#include <stdlib.h>
	#include <stdio.h>
	#include <string.h>
	#include <fcntl.h>
	#include <limits.h>
	#include <sys/types.h>
	#include <sys/stat.h>
	
	#define FIFO_NAME "/tmp/myfifo"
	#define BUFFER_SIZE PIPE_BUF

	int main(int argc, char *argv[])
	{
		int pipe_fd;
		int res;
		int open_mode = O_RDONLY;
		char buffer[BUFFER_SIZE + 1];
		int bytes_read = 0;

		memset(buffer, '\0', sizeof(buffer));
    
		//打开FIFO, 读数据
		printf("Process %d opening FIFO O_RDONLY\n", getpid());
		pipe_fd = open(FIFO_NAME, open_mode);
		printf("Process %d result %d\n", getpid(), pipe_fd);

		//读数据
		if (pipe_fd != -1) {
			do {
				res = read(pipe_fd, buffer, BUFFER_SIZE);
				bytes_read += res;
			} while (res > 0);
			close(pipe_fd);
		} else {
			exit(1);
		}

		printf("Process %d finished, %d bytes read\n", 
			getpid(), bytes_read);
		return 0;
	}

