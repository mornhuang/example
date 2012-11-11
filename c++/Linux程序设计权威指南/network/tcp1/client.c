
	/* File: client.c */

	#include <sys/types.h>
	#include <sys/socket.h>
	#include <netinet/in.h>

	int main(int argc, char **argv)
	{
		int fd;
		struct sockaddr_in address;
		int address_len;
		int rtval;
		char *data = "Client to Server string!\n";
		char data2[100];
		int len;

		//建立套接口
		fd = socket(AF_INET, SOCK_STREAM, 0);

		//联接
		address.sin_family = AF_INET;
		address.sin_addr.s_addr = inet_addr("127.0.0.1");
		address.sin_port = htons(1234);
		address_len = sizeof(address);

		rtval = connect(fd, (struct sockaddr *)&address, address_len);
		if(rtval == -1) exit(1);

		//发送数据
		writen(fd, (void *)data, strlen(data));
		printf("sent line:%s", data);

		//接收数据
		len = readline(fd, (void *)data2, 100);
		printf("readline:%s", data2);

		printf("client exit.\n");
		//关闭
		close(fd);
	}
