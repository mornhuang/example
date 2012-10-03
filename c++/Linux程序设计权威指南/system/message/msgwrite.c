
	/* File: msgwrite.c */
	#include <stdio.h>
	#include <stdlib.h>
	#include <sys/types.h>
	#include <sys/ipc.h>
	#include <sys/msg.h>

	#include "defs.h"

	int main(int argc, char* argv[])
	{
		int queue_id;
		struct msgbuf* msg;
		int i;
		int rc;

		//建立消息队列, 只有相同用户可以使用
		queue_id = msgget(QUEUE_ID, IPC_CREAT | IPC_EXCL | 0600);
		if (queue_id == -1) {
			perror("main: msgget");
			exit(1);
		}
		printf("message queue created, queue id '%d'.\n", queue_id);
		msg =(struct msgbuf*)malloc(sizeof(struct msgbuf)+MAX_MSG_SIZE);

		//发送数据
		for (i=1; i <= NUM_MESSAGES; i++) {
			msg->mtype = (i % 3) + 1;
			sprintf(msg->mtext, "hello world - %d", i);
			rc = msgsnd(queue_id, msg, strlen(msg->mtext)+1, 0);
			if (rc == -1) {
				perror("main: msgsnd");
				exit(1);
			}
		}
		free(msg);
    
		printf("generated %d messages, exiting.\n", NUM_MESSAGES);

		return 0;
	}
