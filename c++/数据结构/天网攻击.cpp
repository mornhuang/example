#include "stdlib.h" 
#include "stdio.h" 
#include "string.h" 
#include "winsock2.h" 

int ConnectSrv(char * ip, int port); 

int main( ) 
{ 
  int rc; 
WSADATA WsaData; 

char szIPAddr[256]; 
int nPort; 
int nTimes; 
printf("Input the IP and port you want to connect, Like:\n" 
" 111.111.111.111 222\n"); 
scanf("%s %d", szIPAddr, &nPort); 

printf("How many times you want to connect to the server:"); 
scanf("%d", &nTimes); 

WSAStartup (0x0101, &WsaData); 

for( int i = 0; i < nTimes; i++) 
rc = ConnectSrv(szIPAddr, nPort); 

WSACleanup(); 
scanf("%d", &rc); 
  return 1; 

} 

int ConnectSrv(char * ip, int port) 
{ 
SOCKET sc; 
  SOCKADDR_IN server_addr; 
int len; 
  if ((sc = socket(AF_INET, SOCK_STREAM, 0)) < 0) 
  { 
    return -1; 
  } 
  long lret = 1L; /* ·Ç×èÈûÄ£Ê½ */ 
  ioctlsocket(sc, FIONBIO, (u_long FAR *) &lret); 
  memset((char*) &server_addr, 0, sizeof(server_addr)); 
  server_addr.sin_family = AF_INET; 

server_addr.sin_port = htons(port); 
server_addr.sin_addr.s_addr = inet_addr(ip); 

  len = sizeof(SOCKADDR_IN); 
  if (int rc = connect(sc, (PSOCKADDR) &server_addr, len) == SOCKET_ERROR) 
  { 
if ((rc != 0) && (WSAGetLastError() == WSAEWOULDBLOCK)) 
printf("Connecting!"); 
else 
printf("Connect Failed!"); 
return -1; 
  } 
printf("Connect OK!"); 

closesocket(sc); 
return 0; 
} 