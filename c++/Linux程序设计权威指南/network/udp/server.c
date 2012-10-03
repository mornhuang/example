
	/* File: server.c */

	#include <stdio.h>
	#include <sys/types.h>
	#include <sys/socket.h>
	#include <netinet/in.h>

	int main(int argc, char **argv)
	{
		int fd;
		int address_len;
		struct sockaddr_in address;

		//建立套接口
		fd = socket(AF_INET, SOCK_DGRAM, 0);	//SOCK_DGRAM

		//绑定地址和端口
		bzero(&address, sizeof(address));
		address.sin_family = AF_INET;
		address.sin_addr.s_addr = htonl(INADDR_ANY);
		address.sin_port = htons(1234);
		address_len = sizeof(address);
		bind(fd, (struct sockaddr *)&address, address_len);

		while(1) {
			struct sockaddr_in client_address;
			socklen_t len = sizeof(client_address);
			int n;
			char line[80];

			printf("waiting...");
			fflush(stdout);

			//接收数据
			n = recvfrom(fd, line, 80, 0, 
				(struct sockaddr *)&client_address, &len);
			printf("server received %d:%s", n, line);

			//发送数据
			sendto(fd, line, n, 0, 
				(struct sockaddr *)&client_address, len);
		}
	}

