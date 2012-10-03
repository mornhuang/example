

	/* File: msgread.c */
	#include <stdio.h>
	#include <stdlib.h>
	#include <unistd.h>
	#include <sys/types.h>
	#include <sys/ipc.h>
	#include <sys/msg.h>

	#include "defs.h"


	int main(int argc, char* argv[])
	{
		int queue_id;
		struct msgbuf* msg;
		int rc;
 		int msg_type;

		if (argc != 2) {
			fprintf(stderr, "Usage: %s <message type>\n", argv[0]);
			fprintf(stderr, "  <message type> must be between 1 and 3.\n");
			exit(1);
		}

		msg_type = atoi(argv[1]);
		if (msg_type < 1 || msg_type > 3) {
			fprintf(stderr, "Usage: %s <message type>\n", argv[0]);
			fprintf(stderr, "  <message type> must be between 1 and 3.\n");
			exit(1);
		}

		//取得消息队列ID
		queue_id = msgget(QUEUE_ID, 0);
		if (queue_id == -1) {
			perror("main: msgget");
			exit(1);
		}
		printf("message queue opened, queue id '%d'.\n", queue_id);
		msg = (struct msgbuf*)malloc(sizeof(struct msgbuf)+MAX_MSG_SIZE);

		//读取消息队列中的数据
		while (1) {
			rc = msgrcv(queue_id, msg, MAX_MSG_SIZE+1, msg_type, 0);
			if (rc == -1) {
				perror("main: msgrcv");
				exit(1);
			}
			printf("Reader '%d' read message: '%s'\n", msg_type,msg->mtext);
			sleep(1);
		}
		return 0;
	}
