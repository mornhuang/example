
	/* File: server.c */

	#include <stdio.h>
	#include <sys/types.h>
	#include <sys/socket.h>
	#include <netinet/in.h>
	#include <sys/time.h>

	int main(int argc, char **argv)
	{
		int fd;
		int address_len;
		struct sockaddr_in address;
		fd_set fdset;

		//建立套接口
		fd = socket(AF_INET, SOCK_STREAM, 0);

		//绑定地址和端口
		address.sin_family = AF_INET;
		address.sin_addr.s_addr = htonl(INADDR_ANY);
		address.sin_port = htons(1234);
		address_len = sizeof(address);
		bind(fd, (struct sockaddr *)&address, address_len);

		//建立套接口队列
		listen(fd, 64);

		
		printf("waiting...");
		while(1) {
			struct sockaddr_in client_address;
			int len;
			int client_sockfd;
			char *data = "Server to Client String!\n";
			char data2[100];
			struct timeval timeout;

			//设置超时为 2 秒
			timeout.tv_sec = 1;
			timeout.tv_usec = 0;

			//设置fdset
			FD_ZERO(&fdset);
			FD_CLR(fd, &fdset);
			FD_SET(fd, &fdset);
			if((select(fd+1, &fdset, NULL, NULL, &timeout))< 0){
				printf("cannot select.\n");
				fflush(stdout);
			}
			if(FD_ISSET(fd, &fdset)){
				//有联接到来
				len = sizeof(client_address);
				client_sockfd = accept(fd, 
				(struct sockaddr *)&client_address, &len);

				//接收数据
				bzero((void *)data2, 100);
				readline(client_sockfd, (void *)data2, 100);
				printf("server read line:%s", data2);

				//发送数据
				writen(client_sockfd,(void *)data,strlen(data));
				printf("server send line:%s", data);

				close(client_sockfd);
				printf("waiting...");
				fflush(stdout);
			} else {
				printf(".");
				fflush(stdout);
			}
		}
	}

