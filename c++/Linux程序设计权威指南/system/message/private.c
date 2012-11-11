
	/* File: private.c */
	#include <stdio.h>
	#include <sys/types.h>
	#include <sys/ipc.h>
	#include <sys/msg.h>

	int main(int argc, char* argv[])
	{
		int queue_id;
		struct msgbuf *msg;
		struct msgbuf *recv_msg;
		int rc;

		//建立消息队列
		queue_id = msgget(IPC_PRIVATE, IPC_CREAT | 0600);
		if (queue_id == -1) {
			perror("main: msgget");
			exit(1);
		}
		printf("message queue created, queue id '%d'.\n", queue_id);

		//创建消息结构
		msg = (struct msgbuf*)malloc(sizeof(struct msgbuf)
			+strlen("hello world"));
		msg->mtype = 1;
		strcpy(msg->mtext, "hello world");

		//发送消息
		rc = msgsnd(queue_id, msg, strlen(msg->mtext)+1, 0);
		if (rc == -1) {
			perror("main: msgsnd");
			exit(1);
		}
		free(msg);
		printf("message placed on the queue successfully.\n");

		//接收消息
		recv_msg = (struct msgbuf*)malloc(sizeof(struct msgbuf)
			+strlen("hello world"));
		rc = msgrcv(queue_id, recv_msg, strlen("hello world")+1, 0, 0);
		if (rc == -1) {
			perror("main: msgrcv");
			exit(1);
		}
		printf("msgrcv: received message: mtype '%d'; mtext '%s'\n",
			recv_msg->mtype, recv_msg->mtext);

		return 0;
	}
